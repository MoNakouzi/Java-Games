import java.awt.*;

public class TransitionScreen
{
    Rectangle background;
    Font next;
    Font instructions;

    public TransitionScreen()
    {
        background = new Rectangle(0, 0, GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT);

        next = new Font("OCR A Extended", Font.PLAIN, 50);
        instructions = new Font("OCR A Extended", Font.PLAIN, 25);
    }

    public void draw(Graphics g)
    {
        // Draw a translucent background
        g.setColor(new Color(0, 0, 0, 0.0000001f));
        Graphics2D g2 = (Graphics2D) g;
        g2.fill(background);

        g.setColor(Color.white);
        g.setFont(instructions);
        String instruction = "Press Space to continue";
        String instruction2 = "Press B to go back";

        int x1 = GamePanel.GAME_WIDTH / 2 - g.getFontMetrics(instructions).stringWidth(instruction) / 2;
        int y1 = GamePanel.GAME_HEIGHT / 2 + g.getFontMetrics(next).getHeight();
        g.drawString(instruction, x1, y1);

        int x2 = GamePanel.GAME_WIDTH / 2 - g.getFontMetrics(instructions).stringWidth(instruction2) / 2;
        int y2 = GamePanel.GAME_HEIGHT / 2 + g.getFontMetrics(next).getHeight() +
                g.getFontMetrics(instructions).getHeight();
        g.drawString(instruction2, x2, y2);
    }
}
