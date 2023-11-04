public class HumanPlayer extends Player
{
    public HumanPlayer(String name)
    {
        super(name);
    }

    /**
     * Attack the specified Location loc.  Marks
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
     * @param enemy
     * @param loc
     * @return
     */
    @Override
    public boolean attack(Player enemy, Location loc)
    {
        int row = loc.getRow();
        int col = loc.getCol();

        if(enemy.hasShipAtLocation(loc))
        {
            getGuessBoard()[row][col] = 1;
            enemy.getShip(loc).takeHit(loc);

            if(enemy.getShip(loc).isSunk())
            {
                enemy.removeShip(enemy.getShip(loc));
                return true;
            }
        }
        else
            getGuessBoard()[row][col] = -1;

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
            ac[i] = new Location(0, i);
        }
        AircraftCarrier aircraftCarrier = new AircraftCarrier(ac);

        Location[] cr = new Location[3];
        for(int i = 0; i < cr.length; i++)
        {
            cr[i] = new Location(9, 7 + i);
        }
        Cruiser cruiser = new Cruiser(cr);

        Location[] de = new Location[4];
        for(int i = 0; i < de.length; i++)
        {
            de[i] = new Location(i + 2, 4);
        }
        Destroyer destroyer = new Destroyer(de);

        Location[] pb = new Location[2];
        for(int i = 0; i < pb.length; i++)
        {
            pb[i] = new Location(5 - i, 6);
        }
        PatrolBoat patrolBoat = new PatrolBoat(pb);

        Location[] su = new Location[3];
        for(int i = 0; i < su.length; i++)
        {
            su[i] = new Location(8, i);
        }
        Submarine submarine = new Submarine(su);

        addShip(aircraftCarrier);
        addShip(cruiser);
        addShip(destroyer);
        addShip(patrolBoat);
        addShip(submarine);
    }
}
