import java.awt.*;
import java.awt.event.MouseEvent;

public class GameOver
{
    // Restart button
    private Rectangle restartButton;
    private final String restartText = "Restart";
    private boolean restartHighlight = false;

    // Quit button
    private Rectangle quitButton;
    private final String quitText = "Quit";
    private boolean quitHighlight = false;

    // GAME OVER text
    private final String gameOver = "GAME OVER";

    private final Font font;

    public GameOver()
    {
        int buttonWidth = 300;
        int buttonHeight = 150;
        int yLocation = (GamePanel.GAME_HEIGHT / 2) - (buttonHeight / 2);
        restartButton = new Rectangle((GamePanel.GAME_WIDTH / 4) - buttonWidth / 2, yLocation, buttonWidth, buttonHeight);
        quitButton = new Rectangle((GamePanel.GAME_WIDTH * 3/4) - buttonWidth / 2, yLocation, buttonWidth, buttonHeight);

        font = new Font("OCR A Extended", Font.PLAIN, 100);
    }

    public void draw(Graphics g)
    {
        // Create a translucent black background rectangle
        g.setColor(new Color(0, 0, 0, .92f));
        g.fillRect(0, 0, 1000, 600);

        Graphics2D g2 = (Graphics2D) g;
        g.setFont(font);

        // Draw a white outline button and fill it with white if the mouse is over it
        g.setColor(Color.black);
        if (restartHighlight)
        {
            g.setColor(Color.white);
        }
        g2.fill(restartButton);

        g.setColor(Color.black);
        if (quitHighlight)
        {
            g.setColor(Color.white);
        }
        g2.fill(quitButton);

        g.setColor(Color.white);
        g2.draw(restartButton);
        g2.draw(quitButton);

        Font restartFont = new Font("OCR A Extended", Font.PLAIN, 70);
        Font pauseFont = new Font("OCR A Extended", Font.PLAIN, 15);

        // get the string width and height
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
        g.setFont(pauseFont);
        // Write instructions
        String startTxt = "Click here to restart";
        int startWidth = g.getFontMetrics(pauseFont).stringWidth(startTxt);
        int startHeight = g.getFontMetrics(pauseFont).getHeight();
        g.drawString(startTxt, (int) (restartButton.getX() + restartButton.getWidth() / 2 - startWidth / 2),
                (int) (restartButton.getY() + restartButton.getHeight() / 2 + startHeight / 4 + 100));


        String quitTxt = "Click here or press ESC to quit";
        int quitWidth = g.getFontMetrics(pauseFont).stringWidth(quitTxt);
        int quitHeight = g.getFontMetrics(pauseFont).getHeight();
        g.drawString(quitTxt, (int) (quitButton.getX() + quitButton.getWidth() / 2 - quitWidth / 2),
                (int) (quitButton.getY() + quitButton.getHeight() / 2 + quitHeight / 4 + 100));

        // Write GAME OVER and which player won
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
