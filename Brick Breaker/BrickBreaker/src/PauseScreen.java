import java.awt.*;
import java.awt.event.MouseEvent;

public class PauseScreen extends Rectangle
{

    Rectangle rect;
    public PauseScreen(int x, int y, int width, int height)
    {
        super(x, y, width, height);
        rect = new Rectangle(x, y, width, height);
    }
    public void draw(Graphics g)
    {
        g.setColor(new Color(0, 0, 0, .75f));
        Graphics2D g2 = (Graphics2D)  g;
        g2.fill(rect);

        g.setColor(Color.white);
        g.setFont(new Font("OCR A Extended", Font.PLAIN, 100));
        String pause = "PAUSED";
        g.drawString(pause, GamePanel.GAME_WIDTH/2 - g.getFontMetrics().stringWidth(pause) / 2,
                GamePanel.GAME_HEIGHT / 2 - g.getFontMetrics().getHeight() / 2);

        g.setFont(new Font("OCR A Extended", Font.PLAIN, 35));
        String instructions = "Press Enter or click anywhere to continue";
        g.drawString(instructions, GamePanel.GAME_WIDTH/2 - g.getFontMetrics().stringWidth(instructions)/2,
                GamePanel.GAME_HEIGHT / 2 - g.getFontMetrics().getHeight() + 50);
        String quit = "Press Escape to quit";
        g.drawString(quit, GamePanel.GAME_WIDTH/2 - g.getFontMetrics().stringWidth(quit)/2,
                GamePanel.GAME_HEIGHT / 2 - g.getFontMetrics().getHeight() + 100);
    }

    public int mouseClicked(MouseEvent e)
    {
        Point p = e.getPoint();

        if (rect.contains(p))
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
}
