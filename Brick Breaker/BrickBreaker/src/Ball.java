import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Ball extends Rectangle
{
    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed = 3;
    int maxSpeed = 5;
    int xPaused = x;
    int yPaused = y;
    int id;

    public Ball(int x, int y, int width, int height, int id)
    {
        super(x, y, width, height);
        random = new Random();

        this.id = id;

        setXDirection(0);
        setYDirection(0);
    }

    public void setXDirection(int randomXDirection)
    {
        xVelocity = randomXDirection;
    }
    public void setYDirection(int randomYDirection)
    {
        yVelocity = randomYDirection;
    }

    public void move()
    {
        x = xPaused += xVelocity;
        y = yPaused += yVelocity;
    }

    public void draw(Graphics g)
    {
        if (id == 2)
        {
            g.setColor(Color.cyan);
        }
        else if (id == 1)
        {
            g.setColor(Color.pink);
        }
        g.fillOval(x, y, width, height);
    }

    public void doNotMove()
    {
        x = xPaused;
        y = yPaused;
    }

    public void keyPressed(KeyEvent e)
    {
        if (xVelocity == 0)
        {
            if (id == 1)
            {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    setXDirection(initialSpeed);
                    setYDirection(initialSpeed * -1);
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    setXDirection(initialSpeed * -1);
                    setYDirection(initialSpeed * -1);
                }
            }
            else
            {
                if (e.getKeyCode() == KeyEvent.VK_D)
                {
                    setXDirection(initialSpeed);
                    setYDirection(initialSpeed * -1);
                }
                else if (e.getKeyCode() == KeyEvent.VK_A)
                {
                    setXDirection(initialSpeed * -1);
                    setYDirection(initialSpeed * -1);
                }
            }
        }
    }
}
