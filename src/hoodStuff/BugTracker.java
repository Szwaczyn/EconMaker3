package hoodStuff;

/**
 * Created $(DATE)
 */
public class BugTracker
{
    Object x;
    int iterator;

    public BugTracker(int x)
    {
        this.x = x;
        this.iterator = 1;
    }

    public BugTracker(long x, int xz)
    {
        this.x = x;
        this.iterator = 1;
    }

    public BugTracker(String x)
    {
        this.x = x;
        this.iterator = 1;
    }

    public BugTracker(double x)
    {
        this.x = x;
        this.iterator = 1;
    }

    public BugTracker(float x)
    {
        this.x = x;
        this.iterator = 1;
    }

    public BugTracker(boolean x)
    {
        this.x = x;
        this.iterator = 1;
    }

    public BugTracker(){ this.x = 0; this.iterator = 1; }

    public void showCondition(Object object, String nameOfObject)
    {
        System.out.println("Aktualny stan obiektu " + nameOfObject + " to: " + object);
    }

    public void show()
    {
        System.out.println("Aktualny stan obiektu to: " + x + "  // wystąpienie numer - " + iterator);
        iterator++;
    }

    public void dot()
    {
        System.out.println("I am here");
    }
}
