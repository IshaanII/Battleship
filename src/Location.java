public class Location
{
    private int row;
    private int col;

    public Location(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Location)  //Makes sure obj can be cast into Location
        {
            Location location = (Location) obj;
            if(row == location.getRow() && col == location.getCol())
                return true;
        }

        return false;
    }
}
