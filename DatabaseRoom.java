import java.util.ArrayList;

/**
 * Class DatabaseRoom berfungsi sebagai penyimpanan informasi
 * terkait kamar pada hotel
 *
 * @author muflih fathan q
 * @version 03/10/2018
 */
public class DatabaseRoom
{
    // instance variables - replace the example below with your own
    // private static String[] list_room;
    private static ArrayList<Room> ROOM_DATABASE = new ArrayList<Room>();


    /**
     * Constructor for objects of class DatabaseRoom
     */
    public DatabaseRoom()
    {
        // initialise instance variables
        //x = 0;
    }

    /**
     * metode untuk membuat kamar telah terisi customer
     * 
     *
     */
    public static ArrayList<Room> getRoomDatabase()
    {
        return ROOM_DATABASE;
    }

    public static boolean addRoom(Room baru)
    {
        for(Room r : ROOM_DATABASE)
        {
            if(r.getHotel().equals(baru.getHotel()) && r.getNomorKamar().equals(baru.getNomorKamar()))
            {
                return false;
            }
        }
        ROOM_DATABASE.add(baru);
        return true;
    }
    
    /**
     * metode untuk membuat kamar menjadi kososng
     * 
     *
     * 
     */
    public static Room getRoom(Hotel hotel,String nomor_kamar)
    {
        for (Room r : ROOM_DATABASE)
        {
            if(r.getHotel().equals(hotel) && r.getNomorKamar().equals(nomor_kamar))
            {
                return r;
            }
        }
        return null;
    }

    public static ArrayList<Room> getRoomsFromHotel(Hotel hotel)
    {
        ArrayList<Room> GET_ROOM = new ArrayList<Room>();
        for (Room r : ROOM_DATABASE)
        {
            if(r.getHotel().equals(hotel))
            {
                GET_ROOM.add(r);
            }
        }
        return GET_ROOM;
    }

    public static ArrayList<Room> getVacantRooms()
    {
        ArrayList<Room> GET_VACANT= new ArrayList<Room>();
        for (Room r : ROOM_DATABASE)
        {
            if(r.getStatusKamar() == StatusKamar.Vacant)
            {
                GET_VACANT.add(r);
            }
        }
        return GET_VACANT;
    }

    public static boolean removeRoom(Hotel hotel, String nomor_kamar)
    {
        boolean balik =false;
        for (Room r : ROOM_DATABASE)
        {
            if(r.getHotel().equals(hotel) && r.getNomorKamar().equals(nomor_kamar))
            {
                if(DatabasePesanan.getPesanan(r) == null)
                {
                    ROOM_DATABASE.remove(r);
                    balik = true;
                }

                else
                {
                    Administrasi.pesananDibatalkan(r);
                    balik = false;
                }
            }
        }
        return balik;
    }
    
    /**
     * metode untuk mengambil dataase kamar pada hotel
     * 
     * 
     * 
     */

}
