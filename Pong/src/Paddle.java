import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle
{
    int id; // 1 or 2 depending on our player
    int yVelocity;
    int speed = 7; // how many pixels the paddles move when a key is pressed

    public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id)
    {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    public void keyPressed(KeyEvent e)
    {
        switch(id)
        {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) // If the code of the key pressed = the code of when 'W' is pressed
                {
                    setYDirection(-speed);
                    move();
                }
                // If the code of the key pressed = the code of when 'S' is pressed (so if 'S' is pressed)
                else if (e.getKeyCode() == KeyEvent.VK_S)
                {
                    setYDirection(speed);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) // if UP arrow is pressed
                {
                    setYDirection(-speed);
                    move();
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) // if DOWN arrow is pressed
                {
                    setYDirection(speed);
                    move();
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e)
    {
        switch(id)
        {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) // If the code of the key pressed = the code of when 'W' is pressed
                {
                    setYDirection(0); // when key is released stop moving
                    move();
                }
                // If the code of the key pressed = the code of when 'S' is pressed (so if 'S' is pressed)
                else if (e.getKeyCode() == KeyEvent.VK_S)
                {
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) // if UP arrow is pressed
                {
                    setYDirection(0);
                    move();
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) // if DOWN arrow is pressed
                {
                    setYDirection(0);
                    move();
                }
                break;
        }
    }
    public void setYDirection(int yDirection)
    {
        yVelocity = yDirection;
    }
    public void move()
    {
        y += yVelocity;
    }
    public void draw (Graphics g)
    {
        if (id == 1)
        {
            g.setColor(Color.cyan);
        }
        else
        {
            g.setColor(Color.red);
        }
        g.fillRect(x, y, width, height);
    }
}
