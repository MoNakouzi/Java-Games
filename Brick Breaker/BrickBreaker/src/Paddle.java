import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle
{
    int id; // to differentiate players 1/2
    int xVelocity;
    int speed = 10; // pixels moved when a key is pressed

    public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id)
    {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    public void draw(Graphics g)
    {
        // paddle (rectangle) at GAME_WIDTH / 2 - PADDLE_WIDTH / 2, GAME_HEIGHT - 25
        // paddle (circle 1, to make a rounded rectangle) at GAME_WIDTH / 2 + PADDLE_WIDTH - radius/2, GAME_HEIGHT - 50
        // paddle (circle 2) at GAME_WIDTH / 2 + radius/2, GAME_HEIGHT - 50
        // radius = 25
        if (id == 1)
        {
            g.setColor(Color.red);
        }
        else
        {
            g.setColor(new Color(30, 158, 227));
        }
        g.fillRoundRect(x, y, width, height, height, height + 1);
    }

    public void move()
    {
        x += xVelocity;
    }

    public void setXDirection(int xDirection)
    {
        xVelocity = xDirection;
    }

    public void keyPressed(KeyEvent e)
    {
        switch(id)
        {
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_A) // If the code of the key pressed = the code of when 'W' is pressed
                {
                    setXDirection(-speed);
                    move();
                }
                // If the code of the key pressed = the code of when 'S' is pressed (so if 'S' is pressed)
                else if (e.getKeyCode() == KeyEvent.VK_D)
                {
                    setXDirection(speed);
                    move();
                }
                break;
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_LEFT) // if UP arrow is pressed
                {
                    setXDirection(-speed);
                    move();
                }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT) // if DOWN arrow is pressed
                {
                    setXDirection(speed);
                    move();
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e)
    {
        switch(id)
        {
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_A) // If the code of the key pressed = the code of when 'W' is pressed
                {
                    setXDirection(0); // when key is released stop moving
                    move();
                }
                // If the code of the key pressed = the code of when 'S' is pressed (so if 'S' is pressed)
                else if (e.getKeyCode() == KeyEvent.VK_D)
                {
                    setXDirection(0);
                    move();
                }
                break;
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_LEFT) // if UP arrow is pressed
                {
                    setXDirection(0);
                    move();
                }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT) // if DOWN arrow is pressed
                {
                    setXDirection(0);
                    move();
                }
                break;
        }
    }
}
