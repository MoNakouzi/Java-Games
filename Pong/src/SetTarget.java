import java.awt.*;
import java.awt.event.MouseEvent;

public class SetTarget
{
    Rectangle button3;
    boolean button3Highlight;
    Rectangle button5;
    boolean button5Highlight;
    Rectangle button7;
    boolean button7Highlight;
    Font numbers;
    Font instructions;
    Font controls;
    Font controlDetails;

    public SetTarget()
    {
        int buttonWidth = 100;
        int buttonHeight = 100;
        int yLocation =  GamePanel.GAME_HEIGHT / 2 - buttonHeight / 2;
        button3 = new Rectangle(GamePanel.GAME_WIDTH / 4 - 50, yLocation, buttonWidth, buttonHeight);
        button5 = new Rectangle(GamePanel.GAME_WIDTH / 2 - 50, yLocation, buttonWidth, buttonHeight);
        button7 = new Rectangle(GamePanel.GAME_WIDTH * 3/4 - 50, yLocation, buttonWidth, buttonHeight);

        numbers = new Font("OCR A Extended", Font.PLAIN, 50);
        instructions = controls = new Font("OCR A Extended", Font.PLAIN, 25);
        controlDetails = new Font("OCR A Extended", Font.PLAIN, 20);

        button3Highlight = button5Highlight = button7Highlight = false;
    }

    public void draw(Graphics g)
    {
        // Create a translucent black background rectangle
        g.setColor(new Color(0, 0, 0, .85f));
        g.fillRect(0, 0, 1000, 600);

        Graphics2D g2 = (Graphics2D) g;

        g.setColor(Color.black);
        if (button3Highlight)
        {
            g.setColor(Color.white);
        }
        g2.fill(button3);

        g.setColor(Color.black);
        if (button5Highlight)
        {
            g.setColor(Color.white);
        }
        g2.fill(button5);

        g.setColor(Color.black);
        if (button7Highlight)
        {
            g.setColor(Color.white);
        }
        g2.fill(button7);

        g.setColor(Color.white);
        g2.draw(button3); g2.draw(button5); g2.draw(button7);

        g.setColor(Color.yellow);
        g.setFont(numbers);
        g.drawString("3", (int) (button3.getX() + button3.getWidth() / 2 - g.getFontMetrics(numbers).stringWidth("3") / 2),
                (int) (button3.getY() + button3.getHeight() / 2 + g.getFontMetrics(numbers).getHeight() / 4));
        g.setColor(Color.green);
        g.drawString("5", (int) (button5.getX() + button5.getWidth() / 2 - g.getFontMetrics(numbers).stringWidth("5") / 2),
                (int) (button5.getY() + button5.getHeight() / 2 + g.getFontMetrics(numbers).getHeight() / 4));
        g.setColor(Color.red);
        g.drawString("7", (int) (button7.getX() + button7.getWidth() / 2 - g.getFontMetrics(numbers).stringWidth("7")/2),
                (int) (button7.getY() + button7.getHeight() / 2 + g.getFontMetrics(numbers).getHeight() / 4));

        g.setColor(Color.white);
        g.setFont(instructions);
        String instruction = "Select goal, first to this number of points wins:";

        g.drawString(instruction, GamePanel.GAME_WIDTH / 2 - g.getFontMetrics(instructions).stringWidth(instruction)/2,
                (int) (button3.getY() - g.getFontMetrics(instructions).getHeight() - 25));

        g.setFont(controls);
        String control = "CONTROLS:";

        g.drawString(control, GamePanel.GAME_WIDTH / 2 - g.getFontMetrics(controls).stringWidth(control)/2,
                (int) (button3.getY() + button3.getHeight() + g.getFontMetrics(controls).getHeight() + 25));

        g.setFont(controlDetails);
        String control1a = "Player 1 (Blue Paddle):";
        String control1b = "   W to move paddle up";
        String control1c = "   S ARROW to move paddle down";
        String control2a = "Player 2 (Red Paddle):";
        String control2b = "   UP ARROW to move paddle up";
        String control2c = "   DOWN ARROW to move paddle down";

        int yLocationA = (int) (button3.getY() + button3.getHeight() + g.getFontMetrics(controls).getHeight() * 3 + 25);
        int yLocationB = (int) (button3.getY() + button3.getHeight() + g.getFontMetrics(controls).getHeight() * 5 + 25);
        int yLocationC = (int) (button3.getY() + button3.getHeight() + g.getFontMetrics(controls).getHeight() * 7 + 25);

        // Write player 1 control instructions
        g.drawString(control1a, 10, yLocationA);
        g.drawString(control1b, 10, yLocationB);
        g.drawString(control1c, 10, yLocationC);

        // Write player 2 control instructions
        // use the width of control2c to align the lines
        g.drawString(control2a, GamePanel.GAME_WIDTH - g.getFontMetrics(controlDetails).stringWidth(control2c) - 25,
                yLocationA);
        g.drawString(control2b, GamePanel.GAME_WIDTH - g.getFontMetrics(controlDetails).stringWidth(control2c) - 25,
                yLocationB);
        g.drawString(control2c, GamePanel.GAME_WIDTH - g.getFontMetrics(controlDetails).stringWidth(control2c) - 25,
                yLocationC);

        String esc = "ESC - Quit Game";
        String pause = "P - Pause";
        g.setFont(controls);
        g.drawString(esc, 10, g.getFontMetrics(controls).getHeight() + 10);
        g.drawString(pause, GamePanel.GAME_WIDTH - g.getFontMetrics(controls).stringWidth(pause) - 10,
                g.getFontMetrics(controlDetails).getHeight() + 10);
    }

    public int mouseClicked(MouseEvent e)
    {
        Point p = e.getPoint();


        if (button3.contains(p))
        {
            return 3;
        }
        else if (button5.contains(p))
        {
            return 5;
        }
        else if (button7.contains(p))
        {
            return 7;
        }
        else
        {
            return 0;
        }
    }

    public void mouseMoved(MouseEvent e)
    {
        Point p = e.getPoint();

        button3Highlight = button3.contains(p);
        button5Highlight = button5.contains(p);
        button7Highlight = button7.contains(p);
    }
}
