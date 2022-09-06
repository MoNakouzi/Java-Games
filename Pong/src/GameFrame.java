import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame
{
    GamePanel panel;

    public GameFrame()
    {
        //This created a new panel
        panel = new GamePanel();
        this.setTitle("Pong Game");
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add edges if window was resized
        setResizable(true);
        this.setLayout(new GridBagLayout());
        this.add(panel);

        this.pack(); // size is set to preferred size according to game panel (so no need to resize frame)
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
