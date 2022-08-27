import java.awt.*;

public class RemainingCount
{
    Font font;
    Font font2;
    int ballsLeft;
    Rectangle endLine; // If a ball touches this line, the player loses a point
    boolean move = true;
    boolean faster = true;
    boolean faster2 = false;
    boolean faster3 = true;

    public RemainingCount(int remaining)
    {
        font = new Font("OCR A Extended", Font.PLAIN, 25);
        font2 = new  Font("OCR A Extended", Font.PLAIN, 20);
        this.ballsLeft = remaining;

        endLine = new Rectangle(0, GamePanel.GAME_HEIGHT - 40, GamePanel.GAME_WIDTH, 1);
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.white);
        g.setFont(font);
        String player1 = "P1:";
        g.drawString(player1, 5, GamePanel.GAME_HEIGHT - 15);

        g.fillOval(g.getFontMetrics(font).stringWidth(player1) + 10, GamePanel.GAME_HEIGHT - 35, 20, 20);

        g.drawString("X" + ballsLeft, g.getFontMetrics(font).stringWidth(player1) + 37,GamePanel.GAME_HEIGHT - 15);

        g.drawLine(0, GamePanel.GAME_HEIGHT - 45, GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT - 45);

        String moveBall = "Move paddle to start";
        if (move)
        {
            g.drawString(moveBall, GamePanel.GAME_WIDTH / 2 - g.getFontMetrics(font).stringWidth(moveBall) / 2,
                    GamePanel.GAME_HEIGHT - 15);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            move = false;
                        }
                    },
                    3000
            );
        }

        g.setFont(font2);
        String moreBricks = "The faster ball, the more likely it is to destroy more bricks";
        if (!move && faster && faster3)
        {
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            faster2 = true;
                        }
                    },
                    3000
            );

            if (faster2)
            {
                g.drawString(moreBricks, GamePanel.GAME_WIDTH / 2 - g.getFontMetrics(font2).stringWidth(moreBricks)/2,
                        GamePanel.GAME_HEIGHT - 15);
            }

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            faster2 = false;
                            faster3 = false;
                        }
                    },
                    7000
            );
        }
    }

    public void draw2(Graphics g)
    {
        String left = "X" + ballsLeft;
        String player2 = "P2:";

        g.setColor(Color.white);
        g.setFont(font);

        g.drawString(left, GamePanel.GAME_WIDTH - g.getFontMetrics(font).stringWidth(left) - 5,
                GamePanel.GAME_HEIGHT - 15);
        g.fillOval(GamePanel.GAME_WIDTH - g.getFontMetrics(font).stringWidth(left) - 30,
                GamePanel.GAME_HEIGHT - 35, 20, 20);

        g.drawString(player2, GamePanel.GAME_WIDTH - g.getFontMetrics(font).stringWidth(left) - 78,
                GamePanel.GAME_HEIGHT - 15);
    }

    public void setRemaining(int remaining)
    {
        this.ballsLeft = remaining;
    }
}
