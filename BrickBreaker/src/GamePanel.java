import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class GamePanel extends JPanel implements Runnable
{
    static final int GAME_WIDTH = 1200;
    static final int GAME_HEIGHT = 650;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 25;
    static final int PADDLE_WIDTH = 125;
    static final int PADDLE_HEIGHT = 25;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball1;
    Ball ball2;
    RemainingCount count1;
    RemainingCount count2;
    int brickWidth = 75;
    int brickHeight = 25;
    Brick bricks = new Brick(100, 100, brickWidth, brickHeight);

    int broken1 = 0; // How many bricks are broken by player 1
    int broken2 = 0; // How many bricks are broken by player 2
    int remaining1 = 5; // How many balls are left for player 1
    int remaining2 = 5; // How many balls are left for player 2
    int i;

    SoundEffect se = new SoundEffect();

    public enum State {TITLE, OPTIONS, TRANSITION, START1, START2, RUN1a, RUN1b, RUN1c, RUN2a, RUN2b, RUN2c, PAUSE, GAME_OVER}
    private State state;
    State previousState;
    State stateToBe;

    public TitleScreen title;
    public OptionsScreen options;
    public TransitionScreen transition;
    public MainMenu startScreen;
    public PauseScreen pause;
    public GameOver gameOver;

    public GamePanel()
    {
        newPaddle1();
        newPaddle2();
        newBall1();
        newBall2();

        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.addMouseListener(new ML());
        this.addMouseMotionListener(new ML());

        this.setPreferredSize(SCREEN_SIZE);
        this.setMinimumSize(new Dimension(GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT));
        this.setMaximumSize(new Dimension(GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT));

        this.state = State.TITLE;

        this.title = new TitleScreen(0, 0, GAME_WIDTH, GAME_HEIGHT);
        this.options = new OptionsScreen();
        this.transition = new TransitionScreen();
        this.startScreen = new MainMenu();
        this.pause = new PauseScreen(0, 0, GAME_WIDTH, GAME_HEIGHT);
        this.count1 = new RemainingCount(remaining1);
        this.count2 = new RemainingCount(remaining2);
        this.gameOver = new GameOver();

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void drawBackground(Graphics g)
    {
        try
        {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource("Background.png")));
            g.drawImage(image, 0, 0, this);
            g.setColor(new Color(0, 0, 0, 0.5f));
            g.fillRect(0,0, GAME_WIDTH, GAME_HEIGHT);
            g.setColor(Color.white);
            g.drawRect(0, 0, GamePanel.GAME_WIDTH - 1, GamePanel.GAME_HEIGHT - 1);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void drawGameOptions(Graphics g)
    {
        Font next = new Font("OCR A Extended", Font.PLAIN, 45);
        g.setColor(Color.white);
        g.setFont(next);
        g.drawString(options.forTransition, GamePanel.GAME_WIDTH / 2 -
                g.getFontMetrics(next).stringWidth(options.forTransition) / 2, GamePanel.GAME_HEIGHT / 2);
    }

    public void drawGeneral(Graphics g)
    {
        drawBackground(g);
        paddle1.draw(g);
        if (remaining1 > 0)
        {
            ball1.draw(g);
        }
        count1.draw(g);
        g.setColor(Color.white);
    }

    public void draw(Graphics g)
    {
        switch (state)
        {
            case TITLE:
                title.draw(g);
                break;
            case OPTIONS:
                drawBackground(g);
                options.draw(g);
                break;
            case TRANSITION:
                drawBackground(g);
                transition.draw(g);
                drawGameOptions(g);
                break;
            case START1:
                drawBackground(g);
                startScreen.draw(g);
                break;
            case START2:
                drawBackground(g);
                startScreen.draw2(g);
                break;
            case RUN1a:
                drawGeneral(g);
                bricks.draw50Bricks(g);
                break;
            case RUN1b:
                drawGeneral(g);
                bricks.draw100Bricks(g);
                break;
            case RUN1c:
                drawGeneral(g);
                bricks.drawInfiniteBricks(g);
                break;
            case RUN2a:
                drawGeneral(g);
                paddle2.draw(g);
                if (remaining2 > 0)
                {
                    ball2.draw(g);
                }
                count2.draw2(g);
                bricks.draw50Bricks(g);
                break;
            case RUN2b:
                drawGeneral(g);
                paddle2.draw(g);
                if (remaining2 > 0)
                {
                    ball2.draw(g);
                }
                count2.draw2(g);
                bricks.draw100Bricks(g);
                break;
            case RUN2c:
                drawGeneral(g);
                paddle2.draw(g);
                if (remaining2 > 0)
                {
                    ball2.draw(g);
                }
                count2.draw2(g);
                bricks.drawInfiniteBricks(g);
                break;
            case PAUSE:
                if (previousState == State.RUN1a)
                {
                    drawGeneral(g);
                    bricks.draw50Bricks(g);
                }
                else if (previousState == State.RUN1b)
                {
                    drawGeneral(g);
                    bricks.draw100Bricks(g);
                }
                else if (previousState == State.RUN1c)
                {
                    drawGeneral(g);
                    // drawWin1 infinite bricks
                }

                if (previousState == State.RUN2a)
                {
                    drawGeneral(g);
                    paddle2.draw(g);
                    if (remaining2 > 0)
                    {
                        ball2.draw(g);
                    }
                    count2.draw2(g);
                    bricks.draw50Bricks(g);
                }
                else if (previousState == State.RUN2b)
                {
                    drawGeneral(g);
                    paddle2.draw(g);
                    if (remaining2 > 0)
                    {
                        ball2.draw(g);
                    }
                    count2.draw2(g);
                    bricks.draw100Bricks(g);
                }
                else if (previousState == State.RUN2c)
                {
                    drawGeneral(g);
                    paddle2.draw(g);
                    if (remaining2 > 0)
                    {
                        ball2.draw(g);
                    }
                    count2.draw2(g);
                    // drawWin1 infinite bricks
                }
                pause.draw(g);
                break;
            case GAME_OVER:
                drawBackground(g);
                if (options.playerMode2Selected)
                {
                    if (remaining1 == 0 && remaining2 == 0)
                    {
                        gameOver.drawLose2(g, broken1, broken2);
                    }
                    else
                    {
                        gameOver.drawWin2(g, broken1, broken2);
                    }
                }
                else
                {
                    if (remaining1 == 0)
                    {
                        gameOver.drawLose1(g, broken1);
                    }
                    else
                    {
                        gameOver.drawWin1(g, broken1);
                    }
                }
                break;
        }
    }

    public void checkState()
    {
        switch (state)
        {
            case TITLE:
            case OPTIONS:
            case START1:
            case START2:
            case TRANSITION:
            case GAME_OVER:
                repaint();
                break;
            case RUN1a:
            case RUN1b:
            case RUN2a:
            case RUN2b:
            case RUN1c:
            case RUN2c:
                move();
                checkCollision();
                repaint();
                break;
            case PAUSE:
                if (previousState == State.RUN1a || previousState == State.RUN1b || previousState == State.RUN1c)
                {
                    ball1.doNotMove();
                }
                else if (previousState == State.RUN2a || previousState == State.RUN2b || previousState == State.RUN2c)
                {
                    ball1.doNotMove();
                    ball2.doNotMove();
                }
                repaint();
                break;
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run()
    {
        // game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true)
        {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / ns;
            lastTime = currentTime;
            if (delta >= 1)
            {
                checkState();
                delta--;
            }
        }
    }

    public void newBall1()
    {
        ball1 = new Ball(paddle1.x + PADDLE_WIDTH / 2 - BALL_DIAMETER / 2, paddle1.y - BALL_DIAMETER - 10,
                BALL_DIAMETER, BALL_DIAMETER, 1);
    }

    public void newBall2()
    {
        ball2 = new Ball(paddle2.x + PADDLE_WIDTH / 2 - BALL_DIAMETER / 2, paddle2.y - BALL_DIAMETER - 10,
                BALL_DIAMETER, BALL_DIAMETER, 2);
    }

    public void newPaddle1()
    {
        paddle1 = new Paddle(GAME_WIDTH / 4 - PADDLE_WIDTH / 2, GAME_HEIGHT - PADDLE_HEIGHT - 45, PADDLE_WIDTH,
                PADDLE_HEIGHT, 1);
    }

    public void newPaddle2()
    {
        paddle2 = new Paddle(GAME_WIDTH * 3/4 - PADDLE_WIDTH / 2, GAME_HEIGHT - PADDLE_HEIGHT - 45, PADDLE_WIDTH,
                PADDLE_HEIGHT, 2);
    }

    public void move()
    {
        paddle1.move();
        if (remaining1 > 0)
        {
            ball1.move();
        }
        if (state == State.RUN2a || state == State.RUN2b || state == State.RUN2c)
        {
            paddle2.move();
            if (remaining2 > 0)
            {
                ball2.move();
            }
        }
    }

    public void checkCollision()
    {
        // Stop paddle at window edges
        if (paddle1.x <= 0)
        {
            paddle1.x = 0;
        }
        if (paddle1.x >= (GAME_WIDTH - PADDLE_WIDTH - 1))
        {
            paddle1.x = GAME_WIDTH - PADDLE_WIDTH - 1;
        }
        if (state == State.RUN2a || state == State.RUN2b || state == State.RUN2c)
        {
            if (paddle2.x <= 0)
            {
                paddle2.x = 0;
            }
            if (paddle2.x >= (GAME_WIDTH - PADDLE_WIDTH - 1))
            {
                paddle2.x = GAME_WIDTH - PADDLE_WIDTH - 1;
            }
        }

        // Bounce balls off the side and top edges
        if (state == State.RUN1a || state == State.RUN1b || state == State.RUN1c)
        {
            if (ball1.xPaused <= 0 || ball1.xPaused >= GAME_WIDTH - BALL_DIAMETER)
            {
                if (remaining1 > 0)
                {
                    se.playBounceSound();
                }
                ball1.xVelocity *= -1;
            }
            if (ball1.yPaused <= 0)
            {
                if (remaining1 > 0)
                {
                    se.playBounceSound();
                }
                ball1.yVelocity *= -1;
            }
        }
        else if (state == State.RUN2a || state == State.RUN2b || state == State.RUN2c)
        {
            if (ball1.xPaused <= 0 || ball1.xPaused >= GAME_WIDTH - BALL_DIAMETER)
            {
                if (remaining1 > 0)
                {
                    se.playBounceSound();
                }
                ball1.xVelocity *= -1;
            }
            if (ball1.yPaused <= 0)
            {
                if (remaining1 > 0)
                {
                    se.playBounceSound();
                }
                ball1.yVelocity *= -1;
            }
            if (ball2.xPaused <= 0 || ball2.xPaused >= GAME_WIDTH - BALL_DIAMETER)
            {
                if (remaining2 > 0)
                {
                    se.playBounceSound();
                }
                ball2.xVelocity *= -1;
            }
            if (ball2.yPaused <= 2)
            {
                if (remaining2 > 0)
                {
                    se.playBounceSound();
                }
                ball2.yVelocity *= -1;
            }
        }

        // Bounce balls off paddles
        if (state == State.RUN1a || state == State.RUN1b || state == State.RUN1c)
        {
            if (ball1.intersects(paddle1))
            {
                se.playBounceSound();

                // Check if the ball intersected the paddle from the top or the sides and bounce it accordingly
                Rectangle insect = paddle1.intersection(ball1);

                boolean vertical = false;
                boolean horizontal = false;
                boolean isLeft = false;
                boolean isTop = false;

                if (insect.x == paddle1.x)
                {
                    horizontal = true; isLeft = true;
                }
                else if (insect.x + insect.width == paddle1.x + brickWidth)
                {
                    horizontal = true;
                }
                if (insect.y == paddle1.y)
                {
                    vertical = true; isTop = true;
                }
                else if (insect.y + insect.height == paddle1.y + brickHeight)
                {
                    vertical = true;
                }

                if (horizontal && vertical)
                {
                    if (insect.width > insect.height)
                    {
                        horizontal = false;
                    }
                    else
                    {
                        vertical = false;
                    }
                }

                if (horizontal)
                {
                    ball1.xVelocity *= -1;
                    if (isLeft)
                    {
                        ball1.xPaused = paddle1.x - BALL_DIAMETER;
                    }
                    else
                    {
                        ball1.xPaused = paddle1.x + brickWidth;
                    }
                }
                else if (vertical)
                {
                    ball1.yVelocity *= -1;
                    if (isTop)
                    {
                        ball1.yPaused = paddle1.y - BALL_DIAMETER;
                    }
                    else
                    {
                        ball1.yPaused = paddle1.y + brickHeight;
                    }
                }


                // speed up the ball1 with each collision
                if (ball1.xVelocity > 0)
                {
                    if (ball1.xVelocity < ball1.maxSpeed)
                    {
                        ball1.xVelocity++;
                    }
                }
                else
                {
                    if (Math.abs(ball1.xVelocity) < ball1.maxSpeed)
                    {
                        ball1.xVelocity--;
                    }
                }
                if (ball1.yVelocity > 0)
                {
                    if (ball1.yVelocity < ball1.maxSpeed)
                    {
                        ball1.yVelocity++;
                    }
                }
                else
                {
                    if (Math.abs(ball1.yVelocity) < ball1.maxSpeed)
                    {
                        ball1.yVelocity--;
                    }
                }
                ball1.setXDirection(ball1.xVelocity);
                ball1.setYDirection(ball1.yVelocity);
            }
        }
        else if (state == State.RUN2a || state == State.RUN2b || state == State.RUN2c)
        {
            if (ball1.intersects(paddle1))
            {
                se.playBounceSound();

                // Check if the ball intersected the paddle from the top or the sides and bounce it accordingly
                Rectangle insect = paddle1.intersection(ball1);

                boolean vertical = false;
                boolean horizontal = false;
                boolean isLeft = false;
                boolean isTop = false;

                if (insect.x == paddle1.x)
                {
                    horizontal = true; isLeft = true;
                }
                else if (insect.x + insect.width == paddle1.x + brickWidth)
                {
                    horizontal = true;
                }
                if (insect.y == paddle1.y)
                {
                    vertical = true; isTop = true;
                }
                else if (insect.y + insect.height == paddle1.y + brickHeight)
                {
                    vertical = true;
                }

                if (horizontal && vertical)
                {
                    if (insect.width > insect.height)
                    {
                        horizontal = false;
                    }
                    else
                    {
                        vertical = false;
                    }
                }

                if (horizontal)
                {
                    ball1.xVelocity *= -1;
                    if (isLeft)
                    {
                        ball1.xPaused = paddle1.x - BALL_DIAMETER;
                    }
                    else
                    {
                        ball1.xPaused = paddle1.x + brickWidth;
                    }
                }
                else if (vertical)
                {
                    ball1.yVelocity *= -1;
                    if (isTop)
                    {
                        ball1.yPaused = paddle1.y - BALL_DIAMETER;
                    }
                    else
                    {
                        ball1.yPaused = paddle1.y + brickHeight;
                    }
                }

                // speed up the ball1 with each collision
                if (ball1.xVelocity > 0)
                {
                    if (ball1.xVelocity < ball1.maxSpeed)
                    {
                        ball1.xVelocity++;
                    }
                }
                else
                {
                    if (Math.abs(ball1.xVelocity) < ball1.maxSpeed)
                    {
                        ball1.xVelocity--;
                    }
                }
                if (ball1.yVelocity > 0)
                {
                    if (ball1.yVelocity < ball1.maxSpeed)
                    {
                        ball1.yVelocity++;
                    }
                }
                else
                {
                    if (Math.abs(ball1.yVelocity) < ball1.maxSpeed)
                    {
                        ball1.yVelocity--;
                    }
                }
                ball1.setXDirection(ball1.xVelocity);
                ball1.setYDirection(ball1.yVelocity);
            }
            if (ball2.intersects(paddle2))
            {
                se.playBounceSound();

                // Check if the ball intersected the paddle from the top or the sides and bounce it accordingly
                Rectangle insect = paddle2.intersection(ball2);

                boolean vertical = false;
                boolean horizontal = false;
                boolean isLeft = false;
                boolean isTop = false;

                if (insect.x == paddle2.x)
                {
                    horizontal = true; isLeft = true;
                }
                else if (insect.x + insect.width == paddle2.x + brickWidth)
                {
                    horizontal = true;
                }
                if (insect.y == paddle2.y)
                {
                    vertical = true; isTop = true;
                }
                else if (insect.y + insect.height == paddle2.y + brickHeight)
                {
                    vertical = true;
                }

                if (horizontal && vertical)
                {
                    if (insect.width > insect.height)
                    {
                        horizontal = false;
                    }
                    else
                    {
                        vertical = false;
                    }
                }

                if (horizontal)
                {
                    ball2.xVelocity *= -1;
                    if (isLeft)
                    {
                        ball2.xPaused = paddle2.x - BALL_DIAMETER;
                    }
                    else
                    {
                        ball2.xPaused = paddle2.x + brickWidth;
                    }
                }
                else if (vertical)
                {
                    ball2.yVelocity *= -1;
                    if (isTop)
                    {
                        ball2.yPaused = paddle2.y - BALL_DIAMETER;
                    }
                    else
                    {
                        ball2.yPaused = paddle2.y + brickHeight;
                    }
                }

                // speed up the ball1 with each collision
                if (ball2.xVelocity > 0)
                {
                    if (ball2.xVelocity < ball2.maxSpeed)
                    {
                        ball2.xVelocity++;
                    }
                }
                else
                {
                    if (Math.abs(ball2.xVelocity) < ball2.maxSpeed)
                    {
                        ball2.xVelocity--;
                    }
                }
                if (ball2.yVelocity > 0)
                {
                    if (ball2.yVelocity < ball2.maxSpeed)
                    {
                        ball2.yVelocity++;
                    }
                }
                else
                {
                    if (Math.abs(ball2.yVelocity) < ball2.maxSpeed)
                    {
                        ball2.yVelocity--;
                    }
                }
                ball2.setXDirection(ball2.xVelocity);
                ball2.setYDirection(ball2.yVelocity);
            }
        }

        // Check if ball passed paddle and reduce ball count
        if (state == State.RUN1a || state == State.RUN1b || state == State.RUN1c)
        {
            if (ball1.intersects(count1.endLine))
            {
                count1.setRemaining(--remaining1);
                if (remaining1 == 0)
                {
                    se.playGameOverSound();
                    state = State.GAME_OVER;
                }
                else
                {
                    se.playBreakSound();
                }
                newBall1();
            }
        }
        else if (state == State.RUN2a || state == State.RUN2b || state == State.RUN2c)
        {
            if (ball1.intersects(count1.endLine))
            {
                if (remaining1 == 0)
                {
                    count1.setRemaining(0);
                }
                else
                {
                    if (remaining1 > 0)
                    {
                        se.playBreakSound();
                        newBall1();
                    }
                    count1.setRemaining(--remaining1);
                }
            }

            if (ball2.intersects(count2.endLine))
            {
                if (remaining2 == 0)
                {
                    count2.setRemaining(0);
                }
                else
                {
                    if (remaining2 > 0)
                    {
                        se.playBreakSound();
                        newBall2();
                    }
                    count2.setRemaining(--remaining2);
                }
            }

            // If no players have any balls remaining, go to game over
            if (remaining1 <= 0 && remaining2 <= 0)
            {
                se.playGameOverSound();
                state = State.GAME_OVER;
            }
        }

        // Check if ball collides with brick
        for (Rectangle brick: bricks.brickMap.keySet())
        {
            if (ball1.intersects(brick))
            {
                if (!bricks.brickMap.get(brick))
                {
                    se.playBreakSound();
                    Rectangle insect = brick.intersection(ball1);

                    boolean vertical = false;
                    boolean horizontal = false;
                    boolean isLeft = false;
                    boolean isTop = false;

                    if (insect.x == brick.x)
                    {
                        horizontal = true; isLeft = true;
                    }
                    else if (insect.x + insect.width == brick.x + brickWidth)
                    {
                        horizontal = true;
                    }
                    if (insect.y == brick.y)
                    {
                        vertical = true; isTop = true;
                    }
                    else if (insect.y + insect.height == brick.y + brickHeight)
                    {
                        vertical = true;
                    }

                    if (horizontal && vertical)
                    {
                        if (insect.width > insect.height)
                        {
                            horizontal = false;
                        }
                        else
                        {
                            vertical = false;
                        }
                    }

                    if (horizontal)
                    {
                        ball1.xVelocity *= -1;
                        if (isLeft)
                        {
                            ball1.xPaused = brick.x - BALL_DIAMETER;
                        }
                        else
                        {
                            ball1.xPaused = brick.x + brickWidth;
                        }
                    }
                    else if (vertical)
                    {
                        ball1.yVelocity *= -1;
                        if (isTop)
                        {
                            if (brick.y - BALL_DIAMETER > 0)
                            {
                                ball1.yPaused = brick.y - BALL_DIAMETER;
                            }
                        }
                        else
                        {
                            if (brick.y + brickHeight > 0)
                            {
                                ball1.yPaused = brick.y + brickHeight;
                            }
                        }
                    }
                    bricks.brickMap.put(brick, true);
                    broken1 += 1;
                    System.out.println(broken1 + "    " + broken2);
                    if (state == State.RUN1a || state == State.RUN2a)
                    {
                        if (broken1 + broken2 == 50)
                        {
                            se.playGameOverSound();
                            state = State.GAME_OVER;
                        }
                    }
                    else if (state == State.RUN1b || state == State.RUN2b)
                    {
                        if (broken1 + broken2 == 100)
                        {
                            se.playGameOverSound();
                            state = State.GAME_OVER;
                        }
                    }
                    else if (state == State.RUN1c)
                    {
                        if (i == 0 || i == 1)
                        {
                            if ((broken1 + broken2) % 50 == 0 && (broken1 + broken2) % 100 != 0)
                            {
                                se.playGameStartSound();
                                if (remaining1 > 0)
                                {
                                    newBall1();
                                    i += 1;
                                }
                            }
                        }
                        else if ((i % 2 == 0 && i % 4 != 0) || ((i-1) % 2 == 0 && (i-1) % 4 != 0))
                        {
                            if ((broken1 + broken2) % 100 == 0)
                            {
                                se.playGameStartSound();
                                if (remaining1 > 0)
                                {
                                    newBall1();
                                    i += 1;
                                }
                            }
                        }
                        else if ((i % 4 == 0) || ((i-1) % 4 == 0))
                        {
                            if ((broken1 + broken2) % 50 == 0 && (broken1 + broken2) % 100 != 0)
                            {
                                se.playGameStartSound();
                                if (remaining1 > 0)
                                {
                                    newBall1();
                                    i += 1;
                                }
                            }
                        }
                    }
                    else if (state == State.RUN2c)
                    {
                        if (i == 0 || i == 1)
                        {
                            if ((broken1 + broken2) % 50 == 0 && (broken1 + broken2) % 100 != 0)
                            {
                                se.playGameStartSound();
                                i += 1;
                                System.out.println("50 / 150 total, i = " + i);
                                if (remaining1 > 0)
                                {
                                    newBall1();
                                }
                                if (remaining2 > 0)
                                {
                                    newBall2();
                                }
                            }
                        }
                        else if ((i % 2 == 0 && i % 4 != 0) || ((i-1) % 2 == 0 && (i-1) % 4 != 0))
                        {
                            if ((broken1 + broken2) % 100 == 0)
                            {
                                se.playGameStartSound();
                                i += 1;
                                System.out.println("200 total, i = " + i);
                                if (remaining1 > 0)
                                {
                                    newBall1();
                                }
                                if (remaining2 > 0)
                                {
                                    newBall2();
                                }
                            }
                        }
                        else if ((i % 4 == 0) || ((i-1) % 4 == 0))
                        {
                            if ((broken1 + broken2) % 50 == 0 && (broken1 + broken2) % 100 != 0)
                            {
                                se.playGameStartSound();
                                i += 1;
                                if (remaining1 > 0)
                                {
                                    newBall1();
                                }
                                if (remaining2 > 0)
                                {
                                    newBall2();
                                }
                            }
                        }
                    }
                }
            }
            else if (ball2.intersects(brick))
            {
                if (!bricks.brickMap.get(brick))
                {
                    se.playBreakSound();
                    Rectangle insect = brick.intersection(ball2);

                    boolean vertical = false;
                    boolean horizontal = false;
                    boolean isLeft = false;
                    boolean isTop = false;

                    if (insect.x == brick.x)
                    {
                        horizontal = true; isLeft = true;
                    }
                    else if (insect.x + insect.width == brick.x + brickWidth)
                    {
                        horizontal = true;
                    }
                    if (insect.y == brick.y)
                    {
                        vertical = true; isTop = true;
                    }
                    else if (insect.y + insect.height == brick.y + brickHeight)
                    {
                        vertical = true;
                    }

                    if (horizontal && vertical)
                    {
                        if (insect.width > insect.height)
                        {
                            horizontal = false;
                        }
                        else
                        {
                            vertical = false;
                        }
                    }

                    if (horizontal)
                    {
                        ball2.xVelocity *= -1;
                        if (isLeft)
                        {
                            ball2.xPaused = brick.x - BALL_DIAMETER;
                        }
                        else
                        {
                            ball2.xPaused = brick.x + brickWidth;
                        }
                    }
                    else if (vertical)
                    {
                        ball2.yVelocity *= -1;
                        if (isTop)
                        {
                            if (brick.y - BALL_DIAMETER > 0)
                            {
                                ball2.yPaused = brick.y - BALL_DIAMETER;
                            }
                        }
                        else
                        {
                            if (brick.y + brickHeight > 0)
                            {
                                ball2.yPaused = brick.y + brickHeight;
                            }
                        }
                    }
                    bricks.brickMap.put(brick, true);
                    broken2 += 1;
                    System.out.println(broken1 + "    " + broken2);
                    if (state == State.RUN1a || state == State.RUN2a)
                    {
                        if (broken1 + broken2 == 50)
                        {
                            se.playGameOverSound();
                            state = State.GAME_OVER;
                        }
                    }
                    else if (state == State.RUN1b || state == State.RUN2b)
                    {
                        if (broken1 + broken2 == 100)
                        {
                            se.playGameOverSound();
                            state = State.GAME_OVER;
                        }
                    }
                    else if (state == State.RUN2c)
                    {
                        if (i == 0 || i == 1)
                        {
                            if ((broken1 + broken2) % 50 == 0 && (broken1 + broken2) % 100 != 0)
                            {
                                se.playGameStartSound();
                                i += 1;
                                System.out.println("50 / 150 total, i = " + i);
                                if (remaining1 > 0)
                                {
                                    newBall1();
                                }
                                if (remaining2 > 0)
                                {
                                    newBall2();
                                }
                            }
                        }
                        else if ((i % 2 == 0 && i % 4 != 0) || ((i-1) % 2 == 0 && (i-1) % 4 != 0))
                        {
                            if ((broken1 + broken2) % 100 == 0)
                            {
                                se.playGameStartSound();
                                i += 1;
                                System.out.println("200 total, i = " + i);
                                if (remaining1 > 0)
                                {
                                    newBall1();
                                }
                                if (remaining2 > 0)
                                {
                                    newBall2();
                                }
                            }
                        }
                        else if ((i % 4 == 0) || ((i-1) % 4 == 0))
                        {
                            if ((broken1 + broken2) % 50 == 0 && (broken1 + broken2) % 100 != 0)
                            {
                                se.playGameStartSound();
                                i += 1;
                                if (remaining1 > 0)
                                {
                                    newBall1();
                                }
                                if (remaining2 > 0)
                                {
                                    newBall2();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void paint(Graphics g)
    {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public class AL extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            se.keyPressed(e);

            if (state == State.RUN1a || state == State.RUN1b || state == State.RUN1c)
            {
                ball1.keyPressed(e);
                paddle1.keyPressed(e);
            }
            else if (state == State.RUN2a || state == State.RUN2b || state == State.RUN2c)
            {
                ball1.keyPressed(e);
                ball2.keyPressed(e);
                paddle1.keyPressed(e);
                paddle2.keyPressed(e);
            }

            // If escape is pressed when the game is paused, quit the game
            if (state == State.PAUSE)
            {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    System.exit(0);
                }
            }

            // If P or Escape is pressed when the game is running, pause
            if (e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
                if (state == State.RUN1a || state == State.RUN1b || state == State.RUN1c || state == State.RUN2a ||
                state == State.RUN2b || state == State.RUN2c)
                {
                    se.playPauseSound();
                    previousState = state;
                    state = State.PAUSE;
                }
            }

            if (state == State.TITLE)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    se.playGameStartSound();
                    state = State.OPTIONS;
                }
            }

            if (state == State.TRANSITION)
            {
                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                {
                    se.playGameStartSound();
                    state = stateToBe;
                }
                else if (e.getKeyCode() == KeyEvent.VK_B)
                {
                    options.playerMode1Selected = options.playerMode2Selected = options.gameOption50Selected =
                            options.gameOption100Selected = options.gameOptionInfiniteSelected = false;
                    state = State.OPTIONS;
                }
            }

            if (state == State.START1 || state == State.START2 || state == State.PAUSE)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    se.playGameStartSound();
                    if (state == State.PAUSE)
                    {
                        state = previousState;
                    }
                    else if (options.playerMode1Selected && options.gameOption50Selected)
                    {
                        state = State.RUN1a;
                    }
                    else if (options.playerMode1Selected && options.gameOption100Selected)
                    {
                        state = State.RUN1b;
                    }
                    else if (options.playerMode1Selected && options.gameOptionInfiniteSelected)
                    {
                        state = State.RUN1c;
                    }
                    else if (options.playerMode2Selected && options.gameOption50Selected)
                    {
                        state = State.RUN2a;
                    }
                    else if (options.playerMode2Selected && options.gameOption100Selected)
                    {
                        state = State.RUN2b;
                    }
                    else if (options.playerMode2Selected && options.gameOptionInfiniteSelected)
                    {
                        state = State.RUN2c;
                    }
                }
            }

            else if (state == State.GAME_OVER)
            {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    System.exit(0);
                }
            }
        }

        public void keyReleased(KeyEvent e)
        {
            paddle1.keyReleased(e);
            if (options.playerMode2Selected)
            {
                paddle2.keyReleased(e);
            }
        }
    }

    public class ML extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            if (state == State.TITLE)
            {
                if (title.mouseClicked(e) == 0)
                {
                    se.playGameStartSound();
                    state = State.OPTIONS;
                }
            }

            else if (state == State.OPTIONS)
            {
                if (options.mouseClicked(e) == 0)
                {
                    se.playGameStartSound();
                    state = State.TRANSITION;
                    stateToBe = State.START1;
                }
                else if (options.mouseClicked(e) == 1)
                {
                    se.playGameStartSound();
                    state = State.TRANSITION;
                    stateToBe = State.START2;
                }
            }

            else if (state == State.START1 || state == State.START2)
            {
                if (startScreen.mouseClicked(e) == 0)
                {
                    se.playGameStartSound();
                    if (options.playerMode1Selected && options.gameOption50Selected)
                    {
                        state = State.RUN1a;
                    }
                    else if (options.playerMode1Selected && options.gameOption100Selected)
                    {
                        state = State.RUN1b;
                    }
                    else if (options.playerMode1Selected && options.gameOptionInfiniteSelected)
                    {
                        state = State.RUN1c;
                    }
                    else if (options.playerMode2Selected && options.gameOption50Selected)
                    {
                        state = State.RUN2a;
                    }
                    else if (options.playerMode2Selected && options.gameOption100Selected)
                    {
                        state = State.RUN2b;
                    }
                    else if (options.playerMode2Selected && options.gameOptionInfiniteSelected)
                    {
                        state = State.RUN2c;
                    }
                }
                else if (startScreen.mouseClicked(e) == 1)
                {
                    System.exit(0);
                }
            }

            else if (state == State.PAUSE)
            {
                if (pause.mouseClicked(e) == 0)
                {
                    se.playGameStartSound();
                    state = previousState;
                }
            }

            else if (state == State.GAME_OVER)
            {
                if (gameOver.mouseClicked(e) == 0)
                {
                    se.playGameStartSound();
                    options.playerMode1Selected = options.playerMode2Selected = options.gameOption50Selected =
                            options.gameOption100Selected = options.gameOptionInfiniteSelected = false;
                    state = State.OPTIONS;
                    remaining1 = remaining2 = count1.ballsLeft = count2.ballsLeft = 5;
                    broken1 = broken2 = 0;
                    i = 0;
                    bricks.brickMap.clear();
                    newPaddle1(); newPaddle2();
                    newBall1(); newBall2();
                }
                else if (gameOver.mouseClicked(e) == 1)
                {
                    System.exit(0);
                }
            }
        }

        public void mouseMoved(MouseEvent e)
        {
            if (state == State.OPTIONS)
            {
                options.mouseMoved(e);
            }

            else if (state == State.START1 || state == State.START2)
            {
                startScreen.mouseMoved(e);
            }

            else if (state == State.GAME_OVER)
            {
                gameOver.mouseMoved(e);
            }
        }
    }
}
