import java.awt.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Brick extends Rectangle
{
    int brickWidth = 75;
    int brickHeight = 25;
    // A map to store the rectangle (brick) and a boolean to check if this brick has been destroyed
    // If yes, the boolean is set to true (in GamePanel), and the brick is not drawn or collided with.
    Map<Rectangle, Boolean> brickMap = new ConcurrentHashMap<>();
    Boolean allBroken50 = false;
    Boolean allBroken100 = false;

    public Brick(int x, int y, int width, int height)
    {
        super(x, y, width, height);
    }

    public void drawBrick(Graphics g, Color bg, Color light, Color dark, int x, int y, Rectangle brick)
    {
        Graphics2D g2 = (Graphics2D) g;

        if (!brickMap.get(brick))
        {
            g2.setPaint(bg);
            g2.fill(brick);

            //noinspection IntegerDivisionInFloatingPointContext
            GradientPaint gloss = new GradientPaint(x, y, light, x, y + brickHeight / 2, bg);
            g2.setPaint(gloss);
            g2.fillRoundRect(x, y, brickWidth, brickHeight / 2, 4, 4);

            g2.setColor(dark);
            g2.setStroke(new BasicStroke(2));
            g2.drawRect(x, y, brickWidth, brickHeight);
        }
    }

    public void drawRedBrick(Graphics g, int x, int y)
    {
        Color background = new Color(222, 15, 12);
        Color lightRed = new Color(211, 142, 141);
        Color darkRed = new Color(187, 37, 35);

        Rectangle toDraw = new Rectangle(x, y, brickWidth, brickHeight);
        if (!brickMap.containsKey(toDraw))
        {
            brickMap.put(toDraw, false);
        }
        drawBrick(g, background, lightRed, darkRed, x, y, toDraw);
    }

    public void drawOrangeBrick(Graphics g, int x, int y)
    {
        Color background = new Color(255, 100, 39);
        Color lightOrange = new Color(243, 187, 162);
        Color darkOrange = new Color(225, 100, 26);

        Rectangle toDraw = new Rectangle(x, y, brickWidth, brickHeight);
        if (!brickMap.containsKey(toDraw))
        {
            brickMap.put(toDraw, false);
        }
        drawBrick(g, background, lightOrange, darkOrange, x, y, toDraw);
    }

    public void drawYellowBrick(Graphics g, int x, int y)
    {
        Color background = new Color(220, 209, 1);
        Color lightYellow = new Color(236, 234, 216);
        Color darkYellow = new Color(159, 153, 33);

        Rectangle toDraw = new Rectangle(x, y, brickWidth, brickHeight);
        if (!brickMap.containsKey(toDraw))
        {
            brickMap.put(toDraw, false);
        }
        drawBrick(g, background, lightYellow, darkYellow, x, y, toDraw);
    }

    public void drawGreenBrick(Graphics g, int x, int y)
    {
        Color background = new Color(8, 219, 8);
        Color lightGreen = new Color(192, 208, 192);
        Color darkGreen = new Color(21, 183, 18);

        Rectangle toDraw = new Rectangle(x, y, brickWidth, brickHeight);
        if (!brickMap.containsKey(toDraw))
        {
            brickMap.put(toDraw, false);
        }
        drawBrick(g, background, lightGreen, darkGreen, x, y, toDraw);
    }

    public void drawBlueBrick(Graphics g, int x, int y)
    {
        Color background = new Color(15, 201, 229);
        Color lightBlue = new Color(203, 218, 225);
        Color darkBlue = new Color(36, 132, 186);

        Rectangle toDraw = new Rectangle(x, y, brickWidth, brickHeight);
        if (!brickMap.containsKey(toDraw))
        {
            brickMap.put(toDraw, false);
        }
        drawBrick(g, background, lightBlue, darkBlue, x, y, toDraw);
    }

    public void drawDarkBlueBrick(Graphics g, int x, int y)
    {
        Color background = new Color(0, 106, 227);
        Color lightBlue = new Color(161, 186, 220);
        Color darkBlue = new Color(33, 72, 160);

        Rectangle toDraw = new Rectangle(x, y, brickWidth, brickHeight);
        if (!brickMap.containsKey(toDraw))
        {
            brickMap.put(toDraw, false);
        }
        drawBrick(g, background, lightBlue, darkBlue, x, y, toDraw);
    }

    public void drawPurpleBrick(Graphics g, int x, int y)
    {
        Color background = new Color(218, 0, 209);
        Color lightPurple = new Color(229, 207, 226);
        Color darkPurple = new Color(193, 2, 153);

        Rectangle toDraw = new Rectangle(x, y, brickWidth, brickHeight);
        if (!brickMap.containsKey(toDraw))
        {
            brickMap.put(toDraw, false);
        }
        drawBrick(g, background, lightPurple, darkPurple, x, y, toDraw);
    }

    public void draw50Bricks(Graphics g)
    {
        int startX = 7;
        int startY = 10;
        int spacing = 4;
        for (int i = 0; i < 15; i++)
        {
            drawRedBrick(g, startX, startY);
            startX += brickWidth + spacing;
            if (i < 7)
            {
                startY += brickHeight * 1.5;
            }
            else
            {
                startY -= brickHeight * 1.5;
            }
        }

        startX = 7 + brickWidth + spacing;
        startY = brickHeight / 2 + spacing + 2;
        for (int i = 0; i < 13; i++)
        {
            drawOrangeBrick(g, startX, startY);
            startX += brickWidth + spacing;
            if (i < 6)
            {
                startY += brickHeight * 1.5;
            }
            else
            {
                startY -= brickHeight * 1.5;
            }
        }

        startX = 7 + brickWidth * 3 + spacing * 3;
        startY = 10;
        for (int i = 0; i < 9; i++)
        {
            drawYellowBrick(g, startX, startY);
            startX += brickWidth + spacing;
            if (i < 4)
            {
                startY += brickHeight * 1.5;
            }
            else
            {
                startY -= brickHeight * 1.5;
            }
        }

        startX = 7 + brickWidth * 4 + spacing * 4;
        startY = brickHeight / 2 + spacing + 2;
        for (int i = 0; i < 7; i++)
        {
            drawGreenBrick(g, startX, startY);
            startX += brickWidth + spacing;
            if (i < 3)
            {
                startY += brickHeight * 1.5;
            }
            else
            {
                startY -= brickHeight * 1.5;
            }
        }

        startX = 7 + brickWidth * 6 + spacing * 6;
        startY = 10;
        for (int i = 0; i < 3; i++)
        {
            drawBlueBrick(g, startX, startY);
            startX += brickWidth + spacing;
            if (i < 1)
            {
                startY += brickHeight * 1.5;
            }
            else
            {
                startY -= brickHeight * 1.5;
            }
        }

        startX = 7 + brickWidth * 7 + spacing * 7;
        startY = brickHeight / 2 + spacing + 2;
        drawDarkBlueBrick(g, startX, startY);

        startX = 7;
        startY = 10 + brickHeight + spacing;
        drawOrangeBrick(g, startX, startY);

        startX = brickWidth * 15 - spacing - 8;
        startY = 7 + brickHeight;
        drawOrangeBrick(g, startX, startY);
    }

    public void draw100Bricks(Graphics g)
    {
        // 4 rows of 14 bricks, 3 rows of 13 bricks, alternating, starting with 14 bricks
        // 5 blocks at the lost row
        int startX14;
        int startX13;
        int startY = 10;
        int brickSpacing = 5;

        for (int i = 0; i < 4; i++)
        {
            startX14 = 40;
            startX13 = 80;

            if (startY != 10)
            {
                startY += brickHeight + brickSpacing;
            }

            for (int j = 0; j < 2; j++)
            {
                drawRedBrick(g, startX14, startY);
                startX14 += brickWidth + brickSpacing;
                drawOrangeBrick(g, startX14, startY);
                startX14 += brickWidth + brickSpacing;
                drawYellowBrick(g, startX14, startY);
                startX14 += brickWidth + brickSpacing;
                drawGreenBrick(g, startX14, startY);
                startX14 += brickWidth + brickSpacing;
                drawBlueBrick(g, startX14, startY);
                startX14 += brickWidth + brickSpacing;
                drawDarkBlueBrick(g, startX14, startY);
                startX14 += brickWidth + brickSpacing;
                drawPurpleBrick(g, startX14, startY);
                startX14 += brickWidth + brickSpacing;
            }

            if (i == 3)
            {
                break;
            }

            startY += brickHeight + brickSpacing;

            for (int k = 0; k < 2; k++)
            {
                drawRedBrick(g, startX13, startY);
                startX13 += brickWidth + brickSpacing;
                drawOrangeBrick(g, startX13, startY);
                startX13 += brickWidth + brickSpacing;
                drawYellowBrick(g, startX13, startY);
                startX13 += brickWidth + brickSpacing;
                drawGreenBrick(g, startX13, startY);
                startX13 += brickWidth + brickSpacing;
                drawBlueBrick(g, startX13, startY);
                startX13 += brickWidth + brickSpacing;
                drawDarkBlueBrick(g, startX13, startY);
                startX13 += brickWidth + brickSpacing;
                if (k == 0)
                {
                    drawPurpleBrick(g, startX13, startY);
                    startX13 += brickWidth + brickSpacing;
                }
            }
        }

        startX13 = 80 + brickWidth * 4 + brickSpacing * 4;
        startY += brickHeight + brickSpacing;
        drawBlueBrick(g, startX13, startY);
        startX13 += brickWidth + brickSpacing;
        drawDarkBlueBrick(g, startX13, startY);
        startX13 += brickWidth + brickSpacing;
        drawPurpleBrick(g, startX13, startY);
        startX13 += brickWidth + brickSpacing;
        drawRedBrick(g, startX13, startY);
        startX13 += brickWidth + brickSpacing;
        drawOrangeBrick(g, startX13, startY);
    }

    public void drawInfiniteBricks(Graphics g)
    {
        if (!allBroken50 && !allBroken100)
        {
            draw50Bricks(g);
            for (Boolean broken : brickMap.values())
            {
                if (!broken)
                {
                    allBroken50 = false;
                    break;
                }
                else
                {
                    allBroken50 = true;
                }
            }
        }

        if (!allBroken100 && allBroken50)
        {
            draw100Bricks(g);
            for (Boolean broken : brickMap.values())
            {
                if (!broken)
                {
                    allBroken100 = false;
                    break;
                }
                else
                {
                    allBroken100 = true;
                }
            }
        }
        else if (allBroken100 && allBroken50)
        {
            allBroken50 = false;
            allBroken100 = false;
            brickMap.clear();
        }
    }
}
