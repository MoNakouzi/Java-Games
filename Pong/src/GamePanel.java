import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable
{

    static final int GAME_WIDTH = 1000;
    // Copy the ratio of a real ping pong table (height = 5/9 the width)
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.555555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_HEIGHT = 100;
    static final int PADDLE_WIDTH = 25;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Paddle paddle1;
    Paddle paddle2;
    Score score;
    Ball ball;

    SoundEffect se = new SoundEffect();
    public enum State {START, SET_TARGET, RUNNING, PAUSE, GAME_OVER}
    private State state;
    public MainMenu mainMenu;
    public PauseScreen screen;
    public GameOver gameOver;
    public SetTarget setTarget;
    int targetScore;


    public GamePanel()
    {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);

        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.addMouseListener(new ML());
        this.addMouseMotionListener(new ML());

        // set panel centered in JFRAME
        this.setPreferredSize(SCREEN_SIZE);

        this.state = State.SET_TARGET;
        this.mainMenu = new MainMenu();
        this.setTarget = new SetTarget();
        this.screen = new PauseScreen(0, 0, 1000, 600);
        this.gameOver = new GameOver();

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall()
    {
        Random rand = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), rand.nextInt(GAME_HEIGHT - BALL_DIAMETER - 50),
                BALL_DIAMETER, BALL_DIAMETER);
    }
    public void newPaddles()
    {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
                PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2),
                PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }
    public void paint(Graphics g)
    {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }
    public void draw(Graphics g)
    {
        switch (state)
        {
            case START:
                paddle1.draw(g);
                paddle2.draw(g);
                ball.draw(g);
                score.draw(g);
                mainMenu.draw(g);
                break;
            case SET_TARGET:
                paddle1.draw(g);
                paddle2.draw(g);
                ball.draw(g);
                score.draw(g);
                setTarget.draw(g);
                break;
            case RUNNING:
                paddle1.draw(g);
                paddle2.draw(g);
                ball.draw(g);
                score.draw(g);
                break;
            case PAUSE:
                paddle1.draw(g);
                paddle2.draw(g);
                ball.draw(g);
                score.draw(g);
                screen.draw(g);
                break;
            case GAME_OVER:
                paddle1.draw(g);
                paddle2.draw(g);
                ball.draw(g);
                score.draw(g);
                gameOver.draw(g);
                // Write which player won
                Font winnerFont = new Font("OCR A Extended", Font.PLAIN, 50);
                g.setFont(winnerFont);
                String winner;
                if (score.player1 == targetScore)
                {
                    winner = "Player 1 Wins!";
                    g.drawString(winner, (GAME_WIDTH/2 - g.getFontMetrics(winnerFont).stringWidth(winner) / 2),
                            (g.getFontMetrics(winnerFont).getHeight() + 10));
                }
                else if (score.player2 == targetScore)
                {
                    winner = "Player 2 Wins!";
                    g.drawString(winner, (GAME_WIDTH/2 - g.getFontMetrics(winnerFont).stringWidth(winner) / 2),
                            (g.getFontMetrics(winnerFont).getHeight() + 10));
                }
                break;
            default:
                throw new RuntimeException("Unknown state: " + state);
        }
    }
    public void move()
    {
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    public void checkCollision()
    {
        // Stop paddles at window edges
        if (paddle1.y <= 0)
        {
            paddle1.y = 0;
        }
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
        {
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }
        if (paddle2.y <= 0)
        {
            paddle2.y = 0;
        }
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
        {
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }

        // Bounce ball at top and bottom window edges
        if (ball.yStationary <= 0 || ball.yStationary >= (GAME_HEIGHT - BALL_DIAMETER))
        {
            ball.yVelocity *= -1;
        }
        // Bounce ball off paddles
        if (paddle1.intersects(ball) || ball.intersects(paddle2))
        {
            se.playBounceSound();
            ball.xVelocity *= -1;
            // speed up the ball with each collision
            if (ball.xVelocity > 0)
            {
                ball.xVelocity++;
            }
            else
            {
                ball.xVelocity--;
            }
            if (ball.yVelocity > 0)
            {
                ball.yVelocity++;
            }
            else
            {
                ball.yVelocity--;
            }
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        // Check if ball went past paddle
        if (ball.xStationary <= -BALL_DIAMETER / 2)
        {
            score.player2++;
            if (score.player2 < targetScore)
            {
                se.playPointSound();
            }
            else if (score.player2 == targetScore)
            {
                se.playGameOverSound();
                state = State.GAME_OVER;
            }
            newPaddles();
            newBall();
        }
        if (ball.xStationary >= GAME_WIDTH - BALL_DIAMETER/2)
        {
            score.player1++;
            if (score.player1 < targetScore)
            {
                se.playPointSound();
            }
            else if (score.player1 == targetScore)
            {
                se.playGameOverSound();
                state = State.GAME_OVER;
            }
            newPaddles();
            newBall();
        }
    }

    public void checkState()
    {
        switch (state)
        {
            case START:
            case SET_TARGET:
            case GAME_OVER:
                repaint();
                break;
            case RUNNING:
                move();
                checkCollision();
                repaint();
                break;
            case PAUSE:
                ball.doNotMove();
                repaint();
                break;
            default:
                throw new RuntimeException("Unknown state: " + state);
        }
    }

    public void run() {
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

    public class AL extends KeyAdapter
    {
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
            // Exit the program when ESC is pressed
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
                System.exit(0);
            }

            if (e.getKeyCode() == KeyEvent.VK_P)
            {
                if (state == State.RUNNING)
                {
                    se.playPauseSound();
                    state = State.PAUSE;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                if (state == State.START || state == State.PAUSE)
                {
                    se.playGameStartSound();
                    state = State.RUNNING;
                }
            }
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }

    public class ML extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            if (state == State.START)
            {
                if (mainMenu.mouseClicked(e) == 0)
                {
                    se.playGameStartSound();
                    score.player1 = score.player2 = 0;
                    state = State.RUNNING;
                }
                else if (mainMenu.mouseClicked(e) == 1)
                {
                    System.exit(0);
                }
            }
            else if (state == State.SET_TARGET)
            {
                if (setTarget.mouseClicked(e) != 0)
                {
                    targetScore = setTarget.mouseClicked(e);
                    se.playGameStartSound();
                    state = State.START;
                }
            }
            else if (state == State.GAME_OVER)
            {
                if (gameOver.mouseClicked(e) == 0)
                {
                    se.playGameStartSound();
                    score.player1 = score.player2 = 0;
                    state = State.SET_TARGET;
                }
                else if (gameOver.mouseClicked(e) == 1)
                {
                    System.exit(0);
                }
            }
        }

        public void mouseMoved(MouseEvent e)
        {
            mainMenu.mouseMoved(e);
            gameOver.mouseMoved(e);
            setTarget.mouseMoved(e);
        }
    }
}
