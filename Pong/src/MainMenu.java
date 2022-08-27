import java.awt.*;
import java.awt.event.MouseEvent;

public class MainMenu
{
    // Play button
    private Rectangle playButton;
    private final String playButtonText = "Play";
    private boolean playHighlight = false;

    // Quit button
    private Rectangle quitButton;
    private final String quitButtonText = "Quit";
    private boolean quitButtonHighlight = false;

    private Font font;

    public MainMenu()
    {
        int buttonWidth = 300;
        int buttonHeight = 150;
        int yLocation = (GamePanel.GAME_HEIGHT / 2) - (buttonHeight / 2);
        playButton = new Rectangle((GamePanel.GAME_WIDTH / 4) - buttonWidth / 2, yLocation, buttonWidth, buttonHeight);
        quitButton = new Rectangle((GamePanel.GAME_WIDTH * 3/4) - buttonWidth / 2, yLocation, buttonWidth, buttonHeight);

        font = new Font("OCR A Extended", Font.PLAIN, 100);
    }

    public void draw(Graphics g)
    {
        // Create a translucent black background rectangle
        g.setColor(new Color(0, 0, 0, .85f));
        g.fillRect(0, 0, 1000, 600);

        Graphics2D g2 = (Graphics2D) g;
        g.setFont(font);

        // Draw a white outline button and fill it with white if the mouse is over it
        g.setColor(Color.black);
        if (playHighlight)
        {
            g.setColor(Color.white);
        }
        g2.fill(playButton);

        g.setColor(Color.black);
        if (quitButtonHighlight)
        {
            g.setColor(Color.white);
        }
        g2.fill(quitButton);

        g.setColor(Color.white);
        g2.draw(playButton);
        g2.draw(quitButton);


        // get the string width and height
        int strPlayWidth = g.getFontMetrics(font).stringWidth(playButtonText);
        int strQuitWidth = g.getFontMetrics(font).stringWidth(quitButtonText);
        int strHeight = g.getFontMetrics(font).getHeight();

        // Write the PLAY text
        g.setColor(Color.green);
        g.drawString(playButtonText, (int) (playButton.getX() + playButton.getWidth() / 2 - strPlayWidth / 2),
                (int) (playButton.getY() + playButton.getHeight() / 2 + strHeight / 4));

        // Write the QUIT text
        g.setColor(Color.red);
        g.drawString(quitButtonText, (int) (quitButton.getX() + quitButton.getWidth() / 2 - strQuitWidth / 2),
                (int) (playButton.getY() + playButton.getHeight() / 2 + strHeight / 4));

        g.setColor(Color.white);
        Font newFont = new Font("OCR A Extended", Font.PLAIN, 15);
        g.setFont(newFont);
        // Write instructions
        String startTxt = "Click here or press ENTER to play";
        int startWidth = g.getFontMetrics(newFont).stringWidth(startTxt);
        int startHeight = g.getFontMetrics(newFont).getHeight();
        g.drawString(startTxt, (int) (playButton.getX() + playButton.getWidth() / 2 - startWidth / 2),
                (int) (playButton.getY() + playButton.getHeight() / 2 + startHeight / 4 + 100));
        String quitTxt = "Click here or press ESC to quit";
        int quitWidth = g.getFontMetrics(newFont).stringWidth(quitTxt);
        int quitHeight = g.getFontMetrics(newFont).getHeight();
        g.drawString(quitTxt, (int) (quitButton.getX() + quitButton.getWidth() / 2 - quitWidth / 2),
                (int) (quitButton.getY() + quitButton.getHeight() / 2 + quitHeight / 4 + 100));
        String pauseTxt = "When started, press P to pause";
        int pauseHeight = g.getFontMetrics(newFont).getHeight();
        g.drawString(pauseTxt, (int) (playButton.getX() + playButton.getWidth() / 2 - startWidth / 2 + 10),
                (int) (playButton.getY() + playButton.getHeight() / 2 + startHeight + pauseHeight / 4 + 100 ));
    }

    public int mouseClicked(MouseEvent e)
    {
        Point p = e.getPoint();

        if (playButton.contains(p))
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
        playHighlight = playButton.contains(p);
        quitButtonHighlight = quitButton.contains(p);
    }
}
