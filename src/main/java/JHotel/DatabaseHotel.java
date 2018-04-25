package JHotel;
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
    private static ArrayList<Hotel> HOTEL_DATABASE = new ArrayList<Hotel>();
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
    public static boolean addHotel(Hotel baru) throws HotelSudahAdaException
    {
        for(Hotel h : HOTEL_DATABASE)

        {

           // System.out.println("1"+h.getLokasi());
           // System.out.println("2"+baru.getLokasi());
            if(h.getID() == baru.getID() || h.getLokasi().getDeskripsi().equals(baru.getLokasi().getDeskripsi()))
            {
              //  System.out.println("\n\n\n\n\ngagal\n\n\n\n\n");
                throw new HotelSudahAdaException(h);
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

    public static boolean removeHotel(int id) throws HotelTidakDitemukanException
    {
        int i = 0;
        try {
            if(id > HOTEL_DATABASE.size())
            {
                throw new HotelTidakDitemukanException(id);
            }

            while (HOTEL_DATABASE.size() > i) {
                i++;
                if(i == HOTEL_DATABASE.size()) {
                    Room r = DatabaseRoom.getRoom(HOTEL_DATABASE.get(i),HOTEL_DATABASE.get(i).getNama());
                    throw new RoomTidakDitemukanException(HOTEL_DATABASE.get(i),r);
                }

                HOTEL_DATABASE.get(i);
                if(HOTEL_DATABASE.get(i).getID() == id) {
                    Room r = DatabaseRoom.getRoom(HOTEL_DATABASE.get(i),HOTEL_DATABASE.get(i).getNama());
                    DatabaseRoom .removeRoom(HOTEL_DATABASE.get(i),r.getNomorKamar());
                    HOTEL_DATABASE.remove(i);
                    return true;

                }


            }
        }
        catch (RoomTidakDitemukanException e) {
            Room r = DatabaseRoom.getRoom(HOTEL_DATABASE.get(i),HOTEL_DATABASE.get(i).getNama());
            RoomTidakDitemukanException re = new RoomTidakDitemukanException(r.getHotel(),r);
        }

        return false;
    }/*
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
        return false;*/
    }
    
    /**
     * metoded untuk mengambil data hotel  pada database
     * 
     * 
     * 
     */


