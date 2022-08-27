import java.awt.*;
import java.awt.event.MouseEvent;

public class GameOver
{
    // Restart button
    Rectangle restartButton;
    final String restartText = "Restart";
    boolean restartHighlight = false;

    // Quit button
    Rectangle quitButton;
    final String quitText = "Quit";
    boolean quitHighlight = false;

    // GAME OVER text
    final String gameOver = "GAME OVER";

    Font font;
    Font restartFont;
    Font instructionFont;
    Font smallerFont;
    Font evenSmaller;

    public GameOver()
    {
        int buttonWidth = 325;
        int buttonHeight = 155;
        int yLocation = (GamePanel.GAME_HEIGHT * 4 / 5) - (buttonHeight / 2);
        restartButton = new Rectangle((GamePanel.GAME_WIDTH / 4) - buttonWidth / 2, yLocation, buttonWidth, buttonHeight);
        quitButton = new Rectangle((GamePanel.GAME_WIDTH * 3/4) - buttonWidth / 2, yLocation, buttonWidth, buttonHeight);

        font = new Font("OCR A Extended", Font.PLAIN, 100);
        restartFont = new Font("OCR A Extended", Font.PLAIN, 70);
        smallerFont = new Font("OCR A Extended", Font.PLAIN, 50);
        evenSmaller = new Font("OCR A Extended", Font.PLAIN, 40);
        instructionFont = new Font("OCR A Extended", Font.PLAIN, 15);
    }

    public void drawWin1(Graphics g, int broken1)
    {
        drawButtons(g);

        g.setFont(smallerFont);
        String win = "YOU WIN!";
        g.drawString(win, GamePanel.GAME_WIDTH / 2 - g.getFontMetrics(smallerFont).stringWidth(win) / 2,
                g.getFontMetrics(restartFont).getHeight() + g.getFontMetrics(smallerFont).getHeight());

        // draw horizontal line
        int yLine = g.getFontMetrics(restartFont).getHeight() + g.getFontMetrics(smallerFont).getHeight() + 12;
        g.drawLine(0, yLine, GamePanel.GAME_WIDTH, yLine);

        drawBroken1(g, GamePanel.GAME_WIDTH / 2 - g.getFontMetrics(smallerFont).stringWidth("Player 1 Score:") / 2,
                g.getFontMetrics(restartFont).getHeight() + g.getFontMetrics(smallerFont).getHeight() * 2 + 5,
                broken1, GamePanel.GAME_WIDTH / 2 -  g.getFontMetrics(font).stringWidth(String.valueOf(broken1)) / 2,
                GamePanel.GAME_WIDTH / 2 - g.getFontMetrics(evenSmaller).stringWidth("BRICKS BROKEN") / 2);
    }

    public void drawLose1(Graphics g, int broken1)
    {
        drawButtons(g);

        g.setFont(smallerFont);
        String lose = "YOU LOSE!";
        g.drawString(lose, GamePanel.GAME_WIDTH / 2 - g.getFontMetrics(smallerFont).stringWidth(lose) / 2,
                g.getFontMetrics(restartFont).getHeight() + g.getFontMetrics(smallerFont).getHeight());

        // draw horizontal line
        int yLine = g.getFontMetrics(restartFont).getHeight() + g.getFontMetrics(smallerFont).getHeight() + 12;
        g.drawLine(0, yLine, GamePanel.GAME_WIDTH, yLine);

        drawBroken1(g, GamePanel.GAME_WIDTH / 2 - g.getFontMetrics(smallerFont).stringWidth("Player 1 Score:") / 2,
                g.getFontMetrics(restartFont).getHeight() + g.getFontMetrics(smallerFont).getHeight() * 2 + 5,
                broken1, GamePanel.GAME_WIDTH / 2 -  g.getFontMetrics(font).stringWidth(String.valueOf(broken1)) / 2,
                GamePanel.GAME_WIDTH / 2 - g.getFontMetrics(evenSmaller).stringWidth("BRICKS BROKEN") / 2);
    }

    public void drawWin2(Graphics g, int broken1, int broken2)
    {
        drawButtons(g);

        g.setFont(smallerFont);
        String win = "YOU WIN!";
        g.drawString(win, GamePanel.GAME_WIDTH / 2 - g.getFontMetrics(smallerFont).stringWidth(win) / 2,
                g.getFontMetrics(restartFont).getHeight() + g.getFontMetrics(smallerFont).getHeight());

        // draw a horizontal line and a line down the middle
        int yLine = g.getFontMetrics(restartFont).getHeight() + g.getFontMetrics(smallerFont).getHeight() + 12;
        g.drawLine(0, yLine, GamePanel.GAME_WIDTH, yLine);
        int xLine = GamePanel.GAME_WIDTH / 2;
        g.drawLine(xLine, yLine, xLine, (int) restartButton.getY());

        drawBroken1(g, GamePanel.GAME_WIDTH / 4 - g.getFontMetrics(smallerFont).stringWidth("Player 1 Score:") / 2,
                g.getFontMetrics(restartFont).getHeight() + g.getFontMetrics(smallerFont).getHeight() * 2 + 5,
                broken1, GamePanel.GAME_WIDTH / 4 - g.getFontMetrics(font).stringWidth(String.valueOf(broken1)) / 2,
                GamePanel.GAME_WIDTH / 4 - g.getFontMetrics(evenSmaller).stringWidth("BRICKS BROKEN") / 2);

        drawBroken2(g, GamePanel.GAME_WIDTH * 3/4 - g.getFontMetrics(smallerFont).stringWidth("Player 2 Score:") / 2,
                g.getFontMetrics(restartFont).getHeight() + g.getFontMetrics(smallerFont).getHeight() * 2 + 5,
                broken2,GamePanel.GAME_WIDTH * 3 / 4 - g.getFontMetrics(font).stringWidth(String.valueOf(broken1)) / 2,
                GamePanel.GAME_WIDTH * 3 / 4 - g.getFontMetrics(evenSmaller).stringWidth("BRICKS BROKEN") / 2);
    }



    public void drawLose2(Graphics g, int broken1, int broken2)
    {
        drawButtons(g);

        g.setFont(smallerFont);
        String lose = "YOU LOSE!";
        g.drawString(lose, GamePanel.GAME_WIDTH / 2 - g.getFontMetrics(smallerFont).stringWidth(lose) / 2,
                g.getFontMetrics(restartFont).getHeight() + g.getFontMetrics(smallerFont).getHeight());

        // draw a horizontal line and a line down the middle
        int yLine = g.getFontMetrics(restartFont).getHeight() + g.getFontMetrics(smallerFont).getHeight() + 12;
        g.drawLine(0, yLine, GamePanel.GAME_WIDTH, yLine);
        int xLine = GamePanel.GAME_WIDTH / 2;
        g.drawLine(xLine, yLine, xLine, (int) restartButton.getY());

        drawBroken1(g, GamePanel.GAME_WIDTH / 4 - g.getFontMetrics(smallerFont).stringWidth("Player 1 Score:") / 2,
                g.getFontMetrics(restartFont).getHeight() + g.getFontMetrics(smallerFont).getHeight() * 2 + 5,
                broken1, GamePanel.GAME_WIDTH / 4 - g.getFontMetrics(font).stringWidth(String.valueOf(broken1)) / 2,
                GamePanel.GAME_WIDTH / 4 - g.getFontMetrics(evenSmaller).stringWidth("BRICKS BROKEN") / 2);

        drawBroken2(g, GamePanel.GAME_WIDTH * 3/4 - g.getFontMetrics(smallerFont).stringWidth("Player 2 Score:") / 2,
                g.getFontMetrics(restartFont).getHeight() + g.getFontMetrics(smallerFont).getHeight() * 2 + 5,
                broken2,GamePanel.GAME_WIDTH * 3 / 4 - g.getFontMetrics(font).stringWidth(String.valueOf(broken1)) / 2,
                GamePanel.GAME_WIDTH * 3 / 4 - g.getFontMetrics(evenSmaller).stringWidth("BRICKS BROKEN") / 2);
    }

    public void drawBroken1(Graphics g, int x, int y, int broken1, int xBroken, int xBroke)
    {
        String score1 = "Player 1 Score:";
        g.setFont(smallerFont);
        g.drawString(score1, x, y);

        g.setFont(font);
        int yBroken = y + g.getFontMetrics(font).getHeight() + 30;
        g.drawString(String.valueOf(broken1), xBroken, yBroken);

        g.setFont(evenSmaller);
        String broke = "BRICKS BROKEN";
        int yBroke = yBroken + g.getFontMetrics(evenSmaller).getHeight() + 15;
        g.drawString(broke, xBroke, yBroke);
    }

    public void drawBroken2(Graphics g, int x, int y, int broken2, int xBroken, int xBroke)
    {
        String score2 = "Player 2 Score:";
        g.setFont(smallerFont);
        g.drawString(score2, x, y);

        g.setFont(font);
        int yBroken = y + g.getFontMetrics(font).getHeight() + 30;
        g.drawString(String.valueOf(broken2), xBroken, yBroken);

        g.setFont(evenSmaller);
        String broke = "BRICKS BROKEN";
        int yBroke = yBroken + g.getFontMetrics(evenSmaller).getHeight() + 15;
        g.drawString(broke, xBroke, yBroke);
    }

    public void drawButtons(Graphics g) {
        // Create a translucent black background rectangle
        g.setColor(new Color(0, 0, 0, 0.5f));
        g.fillRect(0, 0, GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT);

        Graphics2D g2 = (Graphics2D) g;
        g.setFont(font);

        // Draw a white outline button and fill it with white if the mouse is over it
        g.setColor(Color.black);
        if (restartHighlight) {
            g.setColor(Color.white);
        }
        g2.fill(restartButton);

        g.setColor(Color.black);
        if (quitHighlight) {
            g.setColor(Color.white);
        }
        g2.fill(quitButton);

        g.setColor(Color.white);
        g2.draw(restartButton);
        g2.draw(quitButton);

        int strQuitWidth = g.getFontMetrics(font).stringWidth(quitText);
        int strHeight = g.getFontMetrics(font).getHeight();

        // Write the QUIT text
        g.setColor(Color.red);
        g.drawString(quitText, (int) (quitButton.getX() + quitButton.getWidth() / 2 - strQuitWidth / 2),
                (int) (restartButton.getY() + restartButton.getHeight() / 2 + strHeight / 4));

        // Write the RESTART text
        g.setFont(restartFont);
        int strPlayWidth = g.getFontMetrics(restartFont).stringWidth(restartText);
        int restartHeight = g.getFontMetrics(restartFont).getHeight();
        g.setColor(Color.green);
        g.drawString(restartText, (int) (restartButton.getX() + restartButton.getWidth() / 2 - strPlayWidth / 2),
                (int) (restartButton.getY() + restartButton.getHeight() / 2 + restartHeight / 4));

        g.setColor(Color.white);
        g.setFont(instructionFont);
        // Write instructions
        String startTxt = "Click here to restart";
        int startWidth = g.getFontMetrics(instructionFont).stringWidth(startTxt);
        int startHeight = g.getFontMetrics(instructionFont).getHeight();
        g.drawString(startTxt, (int) (restartButton.getX() + restartButton.getWidth() / 2 - startWidth / 2),
                (int) (restartButton.getY() + restartButton.getHeight() / 2 + startHeight / 4 + 100));


        String quitTxt = "Click here or press ESC to quit";
        int quitWidth = g.getFontMetrics(instructionFont).stringWidth(quitTxt);
        int quitHeight = g.getFontMetrics(instructionFont).getHeight();
        g.drawString(quitTxt, (int) (quitButton.getX() + quitButton.getWidth() / 2 - quitWidth / 2),
                (int) (quitButton.getY() + quitButton.getHeight() / 2 + quitHeight / 4 + 100));

        g.setFont(restartFont);
        int xGameOver = GamePanel.GAME_WIDTH / 2 - g.getFontMetrics(restartFont).stringWidth(gameOver) / 2;
        int yGameOver = g.getFontMetrics(restartFont).getHeight() - 10;
        g.drawString(gameOver, xGameOver, yGameOver);
    }

    public int mouseClicked(MouseEvent e)
    {
        Point p = e.getPoint();

        if (restartButton.contains(p))
        {
            return 0;
        }
        else if (quitButton.contains(p))
        {
            return 1;
        }
        else
        {
            return 2;
        }
    }

    public void mouseMoved(MouseEvent e)
    {
        Point p = e.getPoint();
        restartHighlight = restartButton.contains(p);
        quitHighlight = quitButton.contains(p);
    }
}
