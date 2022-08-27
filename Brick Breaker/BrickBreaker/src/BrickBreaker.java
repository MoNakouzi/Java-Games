public class BrickBreaker
{
    public static void main(String[] args)
    {
        try
        {
            new GameFrame();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
