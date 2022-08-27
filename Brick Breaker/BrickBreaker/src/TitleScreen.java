import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class TitleScreen extends Rectangle
{
    Font nameFont;
    Font pressFont;
    int yLocation;
    int xLocation;
    ArrayList<Color> colors = new ArrayList<>();
    Random rand;
    Rectangle rect;
    BufferedImage image;

    public TitleScreen(int x, int y, int width, int height)
    {
        super(x, y, width, height);

        rand = new Random();

        nameFont = new Font("OCR A Extended", Font.BOLD, 100);
        pressFont = new Font("OCR A Extended", Font.PLAIN, 20);

        colors.add(Color.blue); colors.add(Color.orange); colors.add(Color.cyan); colors.add(Color.yellow);
        colors.add(Color.magenta); colors.add(Color.pink); colors.add(Color.red); colors.add(Color.green);

        rect = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g)
    {
        drawBg(g); drawB(g); drawR(g); drawI(g); drawC(g); drawK(g); drawSpace(g); drawB2(g); drawR2(g); drawE(g);
        drawA(g); drawK2(g); drawE2(g); drawR3(g); drawPress(g);
    }

    public void drawGeneral(Graphics g, int letterNumber)
    {
        // Set the colour to be a random color from the arraylist
        g.setColor(colors.get(rand.nextInt(colors.size())));
        g.setFont(nameFont);

        yLocation = GamePanel.GAME_HEIGHT / 2 - g.getFontMetrics(nameFont).getHeight() / 4;
        xLocation = GamePanel.GAME_WIDTH / 2 - g.getFontMetrics(nameFont).stringWidth("BRICK BREAKER")/2 +
                letterNumber * g.getFontMetrics(nameFont).stringWidth("B");
    }

    public void drawBg(Graphics g)
    {
        try
        {
            // Image is Concrete block vector created by macrovector from https://www.freepik.com/vectors/concrete-block
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("TitleImage.jpg")));
            g.drawImage(image, 0, 0, null);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void drawB(Graphics g)
    {
        drawGeneral(g, 0);
        g.drawString("B", xLocation, yLocation);
    }
    public void drawR(Graphics g)
    {
        drawGeneral(g, 1);
        g.drawString("R", xLocation, yLocation);
    }
    public void drawI(Graphics g)
    {
        drawGeneral(g, 2);
        g.drawString("I", xLocation, yLocation);
    }
    public void drawC(Graphics g)
    {
        drawGeneral(g, 3);
        g.drawString("C", xLocation, yLocation);
    }
    public void drawK(Graphics g)
    {
        drawGeneral(g, 4);
        g.drawString("K", xLocation, yLocation);
    }
    public void drawSpace(Graphics g)
    {
        drawGeneral(g, 5);
        g.setColor(Color.black);
        g.drawString(" ", xLocation, yLocation);
    }
    public void drawB2(Graphics g)
    {
        drawGeneral(g, 6);
        g.drawString("B", xLocation, yLocation);
    }
    public void drawR2(Graphics g)
    {
        drawGeneral(g, 7);
        g.drawString("R", xLocation, yLocation);
    }
    public void drawE(Graphics g)
    {
        drawGeneral(g, 8);
        g.drawString("E", xLocation, yLocation);
    }
    public void drawA(Graphics g)
    {
        drawGeneral(g, 9);
        g.drawString("A", xLocation, yLocation);
    }
    public void drawK2(Graphics g)
    {
        drawGeneral(g, 10);
        g.drawString("K", xLocation, yLocation);
    }
    public void drawE2(Graphics g)
    {
        drawGeneral(g, 11);
        g.drawString("E", xLocation, yLocation);
    }
    public void drawR3(Graphics g)
    {
        drawGeneral(g, 12);
        g.drawString("R", xLocation, yLocation);
    }
    public void drawPress(Graphics g)
    {
        g.setFont(pressFont);
        g.setColor(Color.white);
        String instructions = "Press Enter or click anywhere to continue";
        g.drawString(instructions, GamePanel.GAME_WIDTH/2 - g.getFontMetrics(pressFont).stringWidth(instructions)/2,
                GamePanel.GAME_HEIGHT / 2 - g.getFontMetrics().getHeight() + 50);
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
