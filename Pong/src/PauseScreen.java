import java.awt.*;

public class PauseScreen extends Rectangle
{
    public PauseScreen(int x, int y, int width, int height)
    {
        super(x, y, width, height);
    }
    public void draw(Graphics g)
    {
        g.setColor(new Color(0, 0, 0, .75f));
        g.fillRect(0, 0, 1000, 600);
        g.setColor(Color.white);
        g.setFont(new Font("OCR A Extended", Font.PLAIN, 100));
        g.drawString("PAUSED", 340, 275);
        g.setFont(new Font("OCR A Extended", Font.PLAIN, 35));
        g.drawString("Press Enter to continue", 280, 325);
    }
}
