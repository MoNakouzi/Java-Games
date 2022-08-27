import java.awt.*;
import java.awt.event.MouseEvent;

public class OptionsScreen
{
    Rectangle playerMode1Button;
    boolean playerMode1Highlight; // This is to highlight a button if the mouse is over it
    boolean playerMode1Selected; // This is to keep the highlight on when an option is selected
    Rectangle playerMode2Button;
    boolean playerMode2Highlight; // This is to highlight a button if the mouse is over it
    boolean playerMode2Selected; // This is to keep the highlight on when an option is selected
    Rectangle gameOption50Button;
    boolean gameOption50Highlight; // This is to highlight a button if the mouse is over it
    boolean gameOption50Selected; // This is to keep the highlight on when an option is selected
    Rectangle gameOption100Button;
    boolean gameOption100Highlight; // This is to highlight a button if the mouse is over it
    boolean gameOption100Selected; // This is to keep the highlight on when an option is selected
    Rectangle gameOptionInfiniteButton;
    boolean gameOptionInfiniteHighlight; // This is to highlight a button if the mouse is over it
    boolean gameOptionInfiniteSelected; // This is to keep the highlight on when an option is selected
    Font playModeFont;
    Font gameModeFont;
    Font select;
    Font details;
    String forTransition;

    public OptionsScreen()
    {
        int playerModeWidth = 350;
        int playerModeHeight = 175;
        int yLocationPlayer = (GamePanel.GAME_HEIGHT / 4) - (playerModeHeight / 2);

        playerMode1Button = new Rectangle(GamePanel.GAME_WIDTH / 4 - playerModeWidth / 2, yLocationPlayer,
                playerModeWidth, playerModeHeight);
        playerMode2Button = new Rectangle(GamePanel.GAME_WIDTH * 3/4 - playerModeWidth / 2, yLocationPlayer,
                playerModeWidth, playerModeHeight);

        playModeFont = new Font("OCR A Extended", Font.PLAIN, 60);

        playerMode1Highlight = playerMode2Highlight = false;

        int gameModeWidth = 100;
        int gameModeHeight = 100;
        int xLocationMode = 50;

        gameOption50Button = new Rectangle(xLocationMode,
                (int) (playerMode1Button.getY() + playerMode1Button.getHeight() + 75), gameModeWidth, gameModeHeight);
        gameOption100Button = new Rectangle(xLocationMode,
                (int) (playerMode1Button.getY() + playerMode1Button.getHeight() + gameModeHeight + 85), gameModeWidth,
                gameModeHeight);
        gameOptionInfiniteButton = new Rectangle(xLocationMode,
                (int) (playerMode1Button.getY() + playerMode1Button.getHeight() + gameModeHeight * 2 + 95),
                gameModeWidth, gameModeHeight);

        gameModeFont = new Font("OCR A Extended", Font.PLAIN, 50);

        gameOption50Highlight = gameOption100Highlight = gameOptionInfiniteHighlight = false;

        // Font for strings like "Choose your game mode"
        select = new Font("OCR A Extended", Font.PLAIN, 40);

        // Font for strings describing each game mode
        details = new Font("OCR A Extended", Font.PLAIN, 20);
    }

    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        // Draw and fill buttons according to mouse position
        g.setColor(Color.black);
        if (playerMode1Highlight || playerMode1Selected)
        {
            g.setColor(Color.white);
        }
        g2.fill(playerMode1Button);

        g.setColor(Color.black);
        if (playerMode2Highlight || playerMode2Selected)
        {
            g.setColor(Color.white);
        }
        g2.fill(playerMode2Button);

        g.setColor(Color.black);
        if (gameOption50Highlight || gameOption50Selected)
        {
            g.setColor(Color.white);
        }
        g2.fill(gameOption50Button);

        g.setColor(Color.black);
        if (gameOption100Highlight || gameOption100Selected)
        {
            g.setColor(Color.white);
        }
        g2.fill(gameOption100Button);

        g.setColor(Color.black);
        if (gameOptionInfiniteHighlight || gameOptionInfiniteSelected)
        {
            g.setColor(Color.white);
        }
        g2.fill(gameOptionInfiniteButton);

        g.setColor(Color.white);
        g2.draw(playerMode1Button); g2.draw(playerMode2Button); g2.draw(gameOption50Button);
        g2.draw(gameOption100Button); g2.draw(gameOptionInfiniteButton);

        // Write select text
        g.setFont(select);
        String playerMode = "Choose player mode:";
        int xSelectPosition = GamePanel.GAME_WIDTH / 2 - g.getFontMetrics(select).stringWidth(playerMode) / 2;
        int ySelectPosition = g.getFontMetrics(select).getHeight();
        g.drawString(playerMode, xSelectPosition, ySelectPosition);

        String gameMode = "Choose your game mode:";
        int xSelectPosition2 = (int) gameOption50Button.getX();
        int ySelectPosition2 = (int) gameOption50Button.getY() - 20;
        g.drawString(gameMode, xSelectPosition2, ySelectPosition2);

        // Write text within the player buttons
        g.setFont(playModeFont);

        g.setColor(Color.green);
        String p1 = "1-Player";
        int xPlayerPosition = (int) (playerMode1Button.getX() + playerMode1Button.getWidth() / 2 -
                g.getFontMetrics(playModeFont).stringWidth(p1) / 2);
        int yPlayerPosition = (int) (playerMode1Button.getY() + playerMode1Button.getHeight() / 2 +
                g.getFontMetrics(playModeFont).getHeight() / 4);
        g.drawString(p1, xPlayerPosition, yPlayerPosition);

        g.setColor(Color.red);
        String p2 = "2-Players";
        int xPlayerPosition2 = (int) (playerMode2Button.getX() + playerMode2Button.getWidth() / 2 -
                g.getFontMetrics(playModeFont).stringWidth(p2) / 2);
        g.drawString(p2, xPlayerPosition2, yPlayerPosition);

        // Write 50 and 100 within game mode buttons
        g.setFont(gameModeFont);

        String fifty = "50";
        String hundred = "100";

        int xModePosition50 = (int) (gameOption50Button.getX() + gameOption50Button.getWidth() / 2 -
                g.getFontMetrics(gameModeFont).stringWidth(fifty) / 2);
        int xModePosition100 = (int) (gameOption100Button.getX() + gameOption100Button.getWidth() / 2 -
                g.getFontMetrics(gameModeFont).stringWidth(hundred) / 2);
        int yModePosition50 = (int) (gameOption50Button.getY() + gameOption50Button.getHeight() / 2 +
                g.getFontMetrics(gameModeFont).getHeight() / 4);
        int yModePosition100 = (int) (gameOption100Button.getY() + gameOption100Button.getHeight() / 2 +
                g.getFontMetrics(gameModeFont).getHeight() / 4);

        g.setColor(Color.yellow);
        g.drawString(fifty, xModePosition50, yModePosition50);

        g.setColor(Color.green);
        g.drawString(hundred, xModePosition100, yModePosition100);

        // Draw infinity in last game mode button
        int infinityXPosition = (int) (gameOptionInfiniteButton.getX() + gameOptionInfiniteButton.getWidth() / 2 - 33);
        int infinityYPosition = (int) (gameOptionInfiniteButton.getY() + gameOptionInfiniteButton.getHeight() / 2 - 16);

        drawInfinity(g,  infinityXPosition, infinityYPosition);

        // Draw description of each game mode
        g.setColor(Color.white);
        g.setFont(details);
        String details50a = "Break 50 bricks before you run out of balls. You can challenge another player in ";
        String details50b = "2-Player mode to see who breaks the most bricks.";
        String details100a = "Break 100 bricks before you run out of balls. You can challenge another player in ";
        String details100b = "2-Player mode to see who breaks the most bricks.";
        String infiniteA = "THE BRICKS NEVER END! Destroy as many bricks as you can before your balls run out.";
        String infiniteB = "Challenge another player in 2-Player mode to see who can last the longest and break";
        String infiniteC = "the most bricks.";

        int x50Details = (int) (gameOption50Button.getX() + gameOption50Button.getWidth() + 25);
        int y50Details = yModePosition50 - 35;
        g.drawString(details50a, x50Details, y50Details);
        g.drawString(details50b, x50Details, y50Details + g.getFontMetrics(details).getHeight() + 10);

        int x100Details = (int) (gameOption50Button.getX() + gameOption100Button.getWidth() + 25);
        int y100Details = yModePosition100 - 35;
        g.drawString(details100a, x100Details, y100Details);
        g.drawString(details100b, x100Details, y100Details + g.getFontMetrics(details).getHeight() + 10);

        int xInfiniteDetails = (int) (gameOptionInfiniteButton.getX() + gameOptionInfiniteButton.getWidth() + 25);
        int yInfiniteDetails = infinityYPosition - 10;
        g.drawString(infiniteA, xInfiniteDetails, yInfiniteDetails);
        g.drawString(infiniteB, xInfiniteDetails, yInfiniteDetails + g.getFontMetrics(details).getHeight() + 10);
        g.drawString(infiniteC, xInfiniteDetails, yInfiniteDetails + g.getFontMetrics(details).getHeight()*2 + 20);
    }

    public void drawInfinity(Graphics g, int xStart, int yStart)
    {
        g.setColor(Color.red);
        Graphics2D g2 = (Graphics2D) g;
        int width = 35;
        int height = 32;
        g2.setStroke(new BasicStroke(5));
        g2.drawOval(xStart, yStart, width, height);
        g2.drawOval(xStart + width, yStart, width, height);
    }

    public int mouseClicked(MouseEvent e)
    {
        Point p = e.getPoint();

        if (playerMode1Button.contains(p))
        {
            playerMode2Selected = false;
            playerMode1Selected = true;
        }
        else if (playerMode2Button.contains(p))
        {
            playerMode2Selected = true;
            playerMode1Selected = false;
        }

        if (gameOption50Button.contains(p))
        {
            gameOption50Selected = true;
            gameOption100Selected = gameOptionInfiniteSelected = false;
        }
        else if (gameOption100Button.contains(p))
        {
            gameOption100Selected = true;
            gameOption50Selected = gameOptionInfiniteSelected = false;
        }
        else if (gameOptionInfiniteButton.contains(p))
        {
            gameOptionInfiniteSelected = true;
            gameOption50Selected = gameOption100Selected = false;
        }

        boolean show1 = ((this.playerMode1Selected && this.gameOption50Selected) ||
                (this.playerMode1Selected && this.gameOption100Selected) ||
                (this.playerMode1Selected && this.gameOptionInfiniteSelected));
        boolean show2 = ((this.playerMode2Selected && this.gameOption50Selected) ||
                (this.playerMode2Selected && this.gameOption100Selected) ||
                (this.playerMode2Selected && this.gameOptionInfiniteSelected));

        if ((this.playerMode1Selected && this.gameOption50Selected))
        {
            forTransition = "Options selected: 1-Player, 50 Bricks";
        }
        else if (this.playerMode1Selected && this.gameOption100Selected)
        {
            forTransition = "Options selected: 1-Player, 100 Bricks";
        }
        else if (this.playerMode1Selected && this.gameOptionInfiniteSelected)
        {
            forTransition = "Options selected: 1-Player, Infinite Bricks";
        }
        else if (this.playerMode2Selected && this.gameOption50Selected)
        {
            forTransition = "Options selected: 2-Players, 50 Bricks";
        }
        else if (this.playerMode2Selected && this.gameOption100Selected)
        {
            forTransition = "Options selected: 2-Players, 100 Bricks";
        }
        else if (this.playerMode2Selected && this.gameOptionInfiniteSelected)
        {
            forTransition = "Options selected: 2-Players, Infinite Bricks";
        }

        if (show1)
        {
            return 0;
        }
        else if (show2)
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

        playerMode1Highlight = playerMode1Button.contains(p);
        playerMode2Highlight = playerMode2Button.contains(p);

        gameOption50Highlight = gameOption50Button.contains(p);
        gameOption100Highlight = gameOption100Button.contains(p);
        gameOptionInfiniteHighlight = gameOptionInfiniteButton.contains(p);
    }
}
