import java.util.Random;

public class ComputerPlayer extends Player
{
    public ComputerPlayer(String name)
    {
        super(name);
    }

    /**
     * Randomly chooses a Location that has not been
     *   attacked (Location loc is ignored).  Marks
     *   the attacked Location on the guess board
     *   with a positive number if the enemy Player
     *   controls a ship at the Location attacked;
     *   otherwise, if the enemy Player does not
     *   control a ship at the attacked Location,
     *   guess board is marked with a negative number.
     *
     * If the enemy Player controls a ship at the attacked
     *   Location, the ship must add the Location to its
     *   hits taken.  Then, if the ship has been sunk, it
     *   is removed from the enemy Player's list of ships.
     *
     * Return true if the attack resulted in a ship sinking;
     *   false otherwise.
     *
     * @param enemy The Player to attack.
     * @param loc The Location to attack.
     * @return
     */
    @Override
    public boolean attack(Player enemy, Location loc)
    {
        int row = (int) (Math.random() * 10);
        int col = (int) (Math.random() * 10);

        while(getGuessBoard()[row][col] != 0)  //Makes sure the location hasn't been guessed already
        {
            row = (int) (Math.random() * 10);
            col = (int) (Math.random() * 10);
        }

        Location location = new Location(row, col);

        if(enemy.hasShipAtLocation(location))
        {
            getGuessBoard()[row][col] = 1;     //Registers a hit
            enemy.getShip(location).takeHit(location);

            if(enemy.getShip(location).isSunk()) //If the ship has been sunk
            {
                enemy.removeShip(enemy.getShip(location));   //Remove that ship
                        return true;
            }
        }
        else
            getGuessBoard()[row][col] = -1;    //Registers a miss

        return false;
    }

    /**
     * Construct an instance of
     *
     *   AircraftCarrier,
     *   Destroyer,
     *   Submarine,
     *   Cruiser, and
     *   PatrolBoat
     *
     * and add them to this Player's list of ships.
     */
    @Override
    public void populateShips()
    {
        Location[] ac = new Location[5];
        for(int i = 0; i < ac.length; i++)
        {
            ac[i] = new Location(1, i);
        }
        AircraftCarrier aircraftCarrier = new AircraftCarrier(ac);

        Location[] cr = new Location[3];
        for(int i = 0; i < cr.length; i++)
        {
            cr[i] = new Location(8, 7 + i);
        }
        Cruiser cruiser = new Cruiser(cr);

        Location[] de = new Location[4];
        for(int i = 0; i < de.length; i++)
        {
            de[i] = new Location(i + 3, 4);
        }
        Destroyer destroyer = new Destroyer(de);

        Location[] pb = new Location[2];
        for(int i = 0; i < pb.length; i++)
        {
            pb[i] = new Location(6 - i, 6);
        }
        PatrolBoat patrolBoat = new PatrolBoat(pb);

        Location[] su = new Location[3];
        for(int i = 0; i < su.length; i++)
        {
            su[i] = new Location(7, i);
        }
        Submarine submarine = new Submarine(su);

        addShip(aircraftCarrier); //Puts all the different ships into the List ships
        addShip(cruiser);
        addShip(destroyer);
        addShip(patrolBoat);
        addShip(submarine);
     }
}
