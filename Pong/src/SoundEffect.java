import javax.sound.sampled.*;

public class SoundEffect
{
    Clip clip;

    public void playGameStartSound()
    {
        try
        {
            AudioInputStream sound = AudioSystem.getAudioInputStream(getClass().getResource("GameStartSound.wav"));
            clip = AudioSystem.getClip();
            clip.open(sound);
            clip.setFramePosition(0);
            clip.start();
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
            AudioInputStream sound = AudioSystem.getAudioInputStream(getClass().getResource("PauseSound.wav"));
            clip = AudioSystem.getClip();
            clip.open(sound);
            clip.setFramePosition(0);
            clip.start();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void playPointSound()
    {
        try
        {
            AudioInputStream sound = AudioSystem.getAudioInputStream(getClass().getResource("PointSound.wav"));
            clip = AudioSystem.getClip();
            clip.open(sound);
            clip.setFramePosition(0);
            clip.start();
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
            AudioInputStream sound = AudioSystem.getAudioInputStream(getClass().getResource("BounceSound.wav"));
            clip = AudioSystem.getClip();
            clip.open(sound);
            clip.setFramePosition(0);
            clip.start();
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
            AudioInputStream sound = AudioSystem.getAudioInputStream(getClass().getResource("GameOverSound.wav"));
            clip = AudioSystem.getClip();
            clip.open(sound);
            clip.setFramePosition(0);
            clip.start();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
