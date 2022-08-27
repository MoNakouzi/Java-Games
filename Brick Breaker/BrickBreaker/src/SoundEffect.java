import javax.sound.sampled.*;
import java.awt.event.KeyEvent;

public class SoundEffect
{
    Clip clip;
    boolean bouncePlayed = false;
    boolean breakPlayed = false;
    boolean mute = false;

    public void playGameStartSound()
    {
        try
        {
            if (!mute)
            {
                AudioInputStream sound = AudioSystem.getAudioInputStream(getClass().getResource("GameStartSound.wav"));
                clip = AudioSystem.getClip();
                clip.open(sound);
                clip.setFramePosition(0);
                clip.start();
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void playPauseSound()
    {
        try
        {
            if (!mute)
            {
                AudioInputStream sound = AudioSystem.getAudioInputStream(getClass().getResource("PauseSound.wav"));
                clip = AudioSystem.getClip();
                clip.open(sound);
                clip.setFramePosition(0);
                clip.start();
            }

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void playBounceSound()
    {
        try
        {
            if (!mute)
            {
                AudioInputStream sound = AudioSystem.getAudioInputStream(getClass().getResource("BounceSound.wav"));
                clip = AudioSystem.getClip();
                clip.open(sound);
                clip.setFramePosition(0);
                if (!bouncePlayed)
                {
                    clip.start();
                }
                bouncePlayed = true;

                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                bouncePlayed = false;
                            }
                        },
                        500
                );
            }

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void playGameOverSound()
    {
        try
        {
            if (!mute)
            {
                AudioInputStream sound = AudioSystem.getAudioInputStream(getClass().getResource("GameOverSound.wav"));
                clip = AudioSystem.getClip();
                clip.open(sound);
                clip.setFramePosition(0);
                clip.start();
            }

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void playBreakSound()
    {
        try
        {
            if (!mute)
            {
                AudioInputStream sound = AudioSystem.getAudioInputStream(getClass().getResource("BrickBreakingSound.wav"));
                clip = AudioSystem.getClip();
                clip.open(sound);
                clip.setFramePosition(0);
                if (!breakPlayed)
                {
                    clip.start();
                }
                breakPlayed = true;

                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                breakPlayed = false;
                            }
                        },
                        500
                );
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_M)
        {
            mute = !mute;
        }
    }
}
