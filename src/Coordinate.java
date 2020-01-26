public class Coordinate {
    public double x,y,z;
    private int mode;

    public Coordinate(double x, double y)
    {
        this.x = x; this.y = y; this.z = 0;
        mode = 2;
    }

    public Coordinate(double x, double y, double z)
    {
        this.x = x; this.y = y; this.z = z;
        mode = 3;
    }

    public String toString()
    {
        if(mode == 3)
            return "{" + x + "," + y + "," + z + "}";
        else
            return "{" + x + "," + y + "}";
    }
}