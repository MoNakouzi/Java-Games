import java.awt.*;
import java.awt.event.MouseEvent;

public class MainMenu
{
    // Play button
    Rectangle playButton;
    final String playButtonText = "Play";
    boolean playHighlight = false;

    // Quit button
    Rectangle quitButton;
    final String quitButtonText = "Quit";
    boolean quitButtonHighlight = false;

    Font font;
    Font instructionFont;
    Font controlsFont;
    Font bigControlsFont;
    Font buttonLetter;
    Font buttonLetter2;

    Color labelColour;

    public MainMenu()
    {
        int buttonWidth = 350;
        int buttonHeight = 175;
        int yLocation = (GamePanel.GAME_HEIGHT / 5) - (buttonHeight / 2);
        playButton = new Rectangle((GamePanel.GAME_WIDTH / 4) - buttonWidth / 2, yLocation, buttonWidth, buttonHeight);
        quitButton = new Rectangle((GamePanel.GAME_WIDTH * 3/4) - buttonWidth / 2, yLocation, buttonWidth, buttonHeight);

        font = new Font("OCR A Extended", Font.PLAIN, 120);
        instructionFont = new Font("OCR A Extended", Font.PLAIN, 15);
        controlsFont = new Font("OCR A Extended", Font.PLAIN, 20);
        bigControlsFont = new Font("OCR A Extended", Font.PLAIN, 40);
        buttonLetter = new Font("Times New Roman", Font.PLAIN, 50);
        buttonLetter2 = new Font("Times New Roman", Font.PLAIN, 30);

        labelColour = new Color(172, 234, 238);
    }

    public void drawGeneral(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g.setFont(font);

        // Draw a white outline button and fill it with white if the mouse is over it
        g.setColor(Color.black);
        if (playHighlight) {
            g.setColor(Color.white);
        }
        g2.fill(playButton);

        g.setColor(Color.black);
        if (quitButtonHighlight) {
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
        g.setFont(instructionFont);
        // Write instructions
        String startTxt = "Click here or press enter to play";
        int startWidth = g.getFontMetrics(instructionFont).stringWidth(startTxt);
        int startHeight = g.getFontMetrics(instructionFont).getHeight();
        g.drawString(startTxt, (int) (playButton.getX() + playButton.getWidth() / 2 - startWidth / 2),
                (int) (playButton.getY() + playButton.getHeight() / 2 + startHeight / 4 + 100));
        String quitTxt = "Click here to quit";
        int quitWidth = g.getFontMetrics(instructionFont).stringWidth(quitTxt);
        int quitHeight = g.getFontMetrics(instructionFont).getHeight();
        g.drawString(quitTxt, (int) (quitButton.getX() + quitButton.getWidth() / 2 - quitWidth / 2),
                (int) (quitButton.getY() + quitButton.getHeight() / 2 + quitHeight / 4 + 100));
    }

    public void drawArrows(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        g2.setFont(bigControlsFont);
        String controls = "Controls:";
        int controlsX = 10;
        int controlsY = (int) (playButton.y + playButton.getHeight() + g.getFontMetrics(instructionFont).getHeight() +
                g.getFontMetrics(bigControlsFont).getHeight() + 25);
        g2.drawString(controls, controlsX, controlsY);

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        int buttonDimension = 65;
        int spacing = 10;
        int xDownArrow = (int) ((playButton.x + playButton.getWidth()) / 2) + buttonDimension / 2;
        int xRightArrow = xDownArrow + buttonDimension + spacing;
        int xLeftArrow = xDownArrow - buttonDimension - spacing;
        int yRightArrow; int yDownArrow; int yLeftArrow;
        yRightArrow = yDownArrow = yLeftArrow = GamePanel.GAME_HEIGHT * 3/4;
        int yUpArrow = yDownArrow - spacing - buttonDimension;
        g2.drawRoundRect(xDownArrow, yDownArrow, buttonDimension, buttonDimension, 30, 18);
        g2.drawRoundRect(xRightArrow, yRightArrow, buttonDimension, buttonDimension, 30, 18);
        g2.drawRoundRect(xLeftArrow, yLeftArrow, buttonDimension, buttonDimension, 30, 18);
        g2.drawRoundRect(xDownArrow, yUpArrow, buttonDimension, buttonDimension, 30, 18);

        // drawWin1 down arrow
        g2.drawLine(xDownArrow + buttonDimension / 2 + 1, yDownArrow + 15,
                xDownArrow + buttonDimension / 2 + 1, yDownArrow + buttonDimension - 15);

        g2.drawLine(xDownArrow + buttonDimension / 2 + 1, yDownArrow + buttonDimension - 15,
                xDownArrow + buttonDimension / 2 - 9, yDownArrow + buttonDimension - 25);

        g2.drawLine(xDownArrow + buttonDimension / 2 + 2, yDownArrow + buttonDimension - 15,
                xDownArrow + buttonDimension / 2 + 12, yDownArrow + buttonDimension - 25);

        // drawWin1 up arrow
        g2.drawLine(xDownArrow + buttonDimension / 2 + 1, yUpArrow + 15,
                xDownArrow + buttonDimension / 2 + 1, yUpArrow + buttonDimension - 15);

        g2.drawLine(xDownArrow + buttonDimension / 2 + 1, yUpArrow + 15,
                xDownArrow + buttonDimension / 2 - 9, yUpArrow + 25);

        g2.drawLine(xDownArrow + buttonDimension / 2 + 2, yUpArrow + 15,
                xDownArrow + buttonDimension / 2 + 12, yUpArrow + 25);

        // drawWin1 right arrow
        g2.drawLine(xRightArrow + 15, yRightArrow + buttonDimension / 2 + 1,
                xRightArrow + buttonDimension - 15, yRightArrow + buttonDimension / 2 + 1);

        g2.drawLine(xRightArrow + buttonDimension - 15, yRightArrow + buttonDimension / 2 + 1,
                xRightArrow + buttonDimension - 25, yRightArrow + buttonDimension / 2 - 9);

        g2.drawLine(xRightArrow + buttonDimension - 15, yRightArrow + buttonDimension / 2 + 2,
                xRightArrow + buttonDimension - 25, yRightArrow + buttonDimension / 2 + 12);

        // drawWin1 left arrow
        g2.drawLine(xLeftArrow + 15, yLeftArrow + buttonDimension / 2 + 1,
                xLeftArrow + buttonDimension - 15, yLeftArrow + buttonDimension / 2 + 1);

        g2.drawLine(xLeftArrow + 15, yLeftArrow + buttonDimension / 2 + 1,
                xLeftArrow + 25, yLeftArrow + buttonDimension / 2 - 9);

        g2.drawLine(xLeftArrow + 15, yLeftArrow + buttonDimension / 2 + 2,
                xLeftArrow + 25, yLeftArrow + buttonDimension / 2 + 12);

        // Draw line to explain controls
        g2.setStroke(new BasicStroke(2));
        g2.setColor(labelColour);

        g2.drawLine(xLeftArrow + 20, yLeftArrow + 20, xLeftArrow - 30, yLeftArrow - 35);

        g2.drawLine(xRightArrow + buttonDimension - 20, yRightArrow + buttonDimension - 20,
                xRightArrow + buttonDimension + 20, yRightArrow + buttonDimension + 25);

        // Write control
        g2.setFont(controlsFont);
        g2.setColor(Color.white);
        String left = "Move red paddle left";
        String right = "Move red paddle right";

        g2.drawString(left, xLeftArrow - g.getFontMetrics(controlsFont).stringWidth(left) / 2 - 60,
                yLeftArrow - 45);

        g2.drawString(right, xRightArrow + buttonDimension -
                g.getFontMetrics(controlsFont).stringWidth(right) / 2, yRightArrow + buttonDimension + 55);
    }

    public void drawM(Graphics g, int xMute, int yMute)
    {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        int buttonDimension = 65;

        g2.drawRoundRect(xMute, yMute, buttonDimension, buttonDimension, 30, 18);

        g2.setFont(buttonLetter);
        g2.drawString("M", xMute + buttonDimension / 2 - g.getFontMetrics(buttonLetter).stringWidth("M") / 2 + 1,
                yMute + buttonDimension - g.getFontMetrics(buttonLetter).getHeight() / 4 - 3);

        g2.setStroke(new BasicStroke(2));
        g2.setColor(labelColour);
        g2.drawLine(xMute + 20, yMute + 10, xMute - 10, yMute - 25);

        g2.setFont(controlsFont);
        g2.setColor(Color.white);
        String mute = "Mute/unmute SFX";

        g2.drawString(mute, xMute - g.getFontMetrics(controlsFont).stringWidth(mute) / 2 - 40,
                yMute - 35);
    }

    public void drawPause(Graphics g, int xPause, int yPause)
    {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        int buttonDimension = 65;
        int yEsc = yPause - buttonDimension - 15;

        g2.drawRoundRect(xPause, yPause, buttonDimension, buttonDimension, 30, 18);
        g2.drawRoundRect(xPause, yEsc, buttonDimension, buttonDimension, 30, 18);

        g2.setFont(buttonLetter);
        g2.drawString("P", xPause + buttonDimension / 2 - g.getFontMetrics(buttonLetter).stringWidth("P") / 2+1,
                yPause + buttonDimension - g.getFontMetrics(buttonLetter).getHeight() / 4 - 3);

        g2.setFont(buttonLetter2);
        g2.drawString("ESC", xPause + buttonDimension / 2 -
                g.getFontMetrics(buttonLetter2).stringWidth("ESC") / 2+1,
                yEsc + buttonDimension - g.getFontMetrics(buttonLetter2).getHeight() / 2 - 5);

        g2.setStroke(new BasicStroke(2));
        g2.setColor(labelColour);
        g2.drawLine(xPause - 3, yEsc + buttonDimension / 2, xPause - 40, yEsc + buttonDimension / 2 + 25);
        g2.drawLine(xPause - 3, yPause + buttonDimension / 2, xPause - 40,
                yPause - buttonDimension / 2 + 30);

        g2.setFont(controlsFont);
        g2.setColor(Color.white);
        String pause = "Pause";

        g2.drawString(pause, xPause - g.getFontMetrics(controlsFont).stringWidth(pause) - 50,
                yPause - buttonDimension/4 + 5);
    }

    public void drawAD(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        // Draw the buttons
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        int buttonDimension = 65;
        int spacing = 10;
        int xS = (int) ((quitButton.x + quitButton.getWidth()) / 2) + buttonDimension * 6;
        int xD = xS + buttonDimension + spacing;
        int xA = xS - buttonDimension - spacing;
        int yD; int yS; int yA;
        yD = yS = yA = GamePanel.GAME_HEIGHT * 3/4 - 75;
        int yW = yS - spacing - buttonDimension;
        g2.drawRoundRect(xS, yS, buttonDimension, buttonDimension, 30, 18);
        g2.drawRoundRect(xD, yD, buttonDimension, buttonDimension, 30, 18);
        g2.drawRoundRect(xA, yA, buttonDimension, buttonDimension, 30, 18);
        g2.drawRoundRect(xS, yW, buttonDimension, buttonDimension, 30, 18);

        // Fill the rectangles with the letters of the buttons
        g2.setFont(buttonLetter);
        g2.drawString("A", xA + buttonDimension / 2 - g.getFontMetrics(buttonLetter).stringWidth("A") / 2,
                yA + buttonDimension - g.getFontMetrics(buttonLetter).getHeight() / 4 - 3);

        g2.drawString("S", xS + buttonDimension / 2 - g.getFontMetrics(buttonLetter).stringWidth("S") / 2 + 1,
                yS + buttonDimension - g.getFontMetrics(buttonLetter).getHeight() / 4 - 3);

        g2.drawString("D", xD + buttonDimension / 2 - g.getFontMetrics(buttonLetter).stringWidth("D") / 2 + 3,
                yD + buttonDimension - g.getFontMetrics(buttonLetter).getHeight() / 4 - 3);

        g2.drawString("W", xS + buttonDimension / 2 - g.getFontMetrics(buttonLetter).stringWidth("W") / 2 + 1,
                yW + buttonDimension - g.getFontMetrics(buttonLetter).getHeight() / 4 - 3);

        // Draw line to explain controls
        g2.setStroke(new BasicStroke(2));
        g2.setColor(labelColour);

        g2.drawLine(xA + 20, yA + 20, xA - 30, yA - 35);

        g2.drawLine(xD + buttonDimension - 20, yD + buttonDimension - 10,
                xD + buttonDimension + 20, yD + buttonDimension + 30);

        // Write control
        g2.setFont(controlsFont);
        g2.setColor(Color.white);
        String left = "Move blue paddle left";
        String right = "Move blue paddle right";

        g2.drawString(left, xA - g.getFontMetrics(controlsFont).stringWidth(left) / 2 - 75, yA - 45);

        g2.drawString(right, xD + buttonDimension - g.getFontMetrics(controlsFont).stringWidth(right) / 2 - 15,
                yD + buttonDimension + 55);

        g.setFont(instructionFont);
        String player1 = "Player 1 controls red paddle";
        String player2 = "Player 2 controls blue paddle";
        g.drawString(player1, 10,GamePanel.GAME_HEIGHT - 15);
        g.drawString(player2, GamePanel.GAME_WIDTH - g.getFontMetrics(instructionFont).stringWidth(player2) - 10,
                GamePanel.GAME_HEIGHT - 15);
    }

    public void draw(Graphics g)
    {
        drawGeneral(g);
        drawArrows(g);
        drawM(g, (int) (playButton.x + playButton.getWidth()) + 200, GamePanel.GAME_HEIGHT / 2 + 50);
        drawPause(g, quitButton.x + 285, GamePanel.GAME_HEIGHT / 2 + 175);
    }

    public void draw2(Graphics g)
    {
        drawGeneral(g);
        drawArrows(g);
        drawM(g, (int) (playButton.x + playButton.getWidth()), GamePanel.GAME_HEIGHT / 2 + 30);
        drawPause(g, quitButton.x - 85, GamePanel.GAME_HEIGHT / 2 + 175);
        drawAD(g);
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
