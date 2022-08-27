import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame
{
    GamePanel panel;

    public GameFrame()
    {
        panel = new GamePanel();
        this.setTitle("Brick Breaker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setBackground(Color.black);
        this.getContentPane().setBackground(Color.black);

        this.setResizable(true);
        this.setLayout(new GridBagLayout());
        this.add(panel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
