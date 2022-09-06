public class BrickBreaker
{
    public static void main(String[] args)
    {
        try
        {
            // This runs the game
            new GameFrame();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
