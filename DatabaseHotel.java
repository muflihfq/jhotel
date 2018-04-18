import java.util.ArrayList;

/**
 * Class DatabaseHotel berfungsi sebagai database dari hotel
 * yang ada
 *
 * @author muflih fathan q
 * @version 03/10/2018
 */
public class DatabaseHotel
{
    // instance variables - replace the example below with your own
    private static ArrayList<Hotel> HOTEL_DATABASE;
    private static int LAST_HOTEL_ID;

    /**
     * Constructor for objects of class DatabaseHotel
     */
    public DatabaseHotel()
    {
        // initialise instance variables
        
    }

    public static ArrayList<Hotel> getHotelDatabase()
    {
        return HOTEL_DATABASE;
    }

    public int getLastHotelID()
    {
        return LAST_HOTEL_ID;
    }


    /**
     * metoded untuk menambahkan hotel baru pada database
     *
     *
     * @param  baru  baru merupakan hotel baru
     * yang ingin ditambahkan
     *
     */
    public static boolean addHotel(Hotel baru)
    {
        for(Hotel h : HOTEL_DATABASE)
        {
            if(h.getID() == baru.getID())
            {
                return false;
            }
        }

        HOTEL_DATABASE.add(baru);
        LAST_HOTEL_ID = baru.getID();
        return true;
    }

    public static Hotel getHotel(int id)
    {
        for(Hotel h : HOTEL_DATABASE)
        {
            if(h.getID() == id)
            {
                Hotel hotel = HOTEL_DATABASE.get(id);
                return hotel;
            }
        }
        return null;
    }
    /**
     * metoded untuk menghapus hotel pada database
     * 
     * 
     * @param  id id dari hotel yang ingin dihapus
     * 
     */

    public static boolean removeHotel(int id)
    {
        for(Hotel h : HOTEL_DATABASE)
        {
            if(h.getID() == id)
            {
                ArrayList<Room> KAMAR = DatabaseRoom.getRoomsFromHotel(h);
                for(Room kamar : KAMAR)
                {
                    DatabaseRoom.removeRoom(h,kamar.getNomorKamar());
                    return true;
                }


            }
        }
        return false;
    }
    
    /**
     * metoded untuk mengambil data hotel  pada database
     * 
     * 
     * 
     */

}
