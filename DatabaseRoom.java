import java.util.ArrayList;

/**
 * Class DatabaseRoom berfungsi sebagai penyimpanan informasi
 * terkait kamar pada hotel
 *
 * @author muflih fathan q
 * @version 03/10/2018
 */
public class DatabaseRoom {
    // instance variables - replace the example below with your own
    // private static String[] list_room;
    private static ArrayList<Room> ROOM_DATABASE = new ArrayList<Room>();


    /**
     * Constructor for objects of class DatabaseRoom
     */
    public DatabaseRoom() {
        // initialise instance variables
        //x = 0;
    }

    /**
     * metode untuk membuat kamar telah terisi customer
     */
    public static ArrayList<Room> getRoomDatabase() {
        return ROOM_DATABASE;
    }

    public static boolean addRoom(Room baru) throws RoomSudahAdaException {
        for (Room r : ROOM_DATABASE) {
            if (r.getHotel().equals(baru.getHotel()) && r.getNomorKamar().equals(baru.getNomorKamar())) {
                throw new RoomSudahAdaException(r);
            }
        }
        ROOM_DATABASE.add(baru);
        return true;
    }

    /**
     * metode untuk membuat kamar menjadi kososng
     */
    public static Room getRoom(Hotel hotel, String nomor_kamar) {
        for (Room r : ROOM_DATABASE) {
            if (r.getHotel().equals(hotel) && r.getNomorKamar().equals(nomor_kamar)) {
                return r;
            }
        }
        return null;
    }

    public static ArrayList<Room> getRoomsFromHotel(Hotel hotel) {
        ArrayList<Room> GET_ROOM = new ArrayList<Room>();
        for (Room r : ROOM_DATABASE) {
            if (r.getHotel().equals(hotel)) {
                GET_ROOM.add(r);
            }
        }
        return GET_ROOM;
    }

    public static ArrayList<Room> getVacantRooms() {
        ArrayList<Room> GET_VACANT = new ArrayList<Room>();
        for (Room r : ROOM_DATABASE) {
            if (r.getStatusKamar() == StatusKamar.Vacant) {
                GET_VACANT.add(r);
            }
        }
        return GET_VACANT;
    }

    public static boolean removeRoom(Hotel hotel, String nomor_kamar) throws RoomTidakDitemukanException {
       // boolean balik = false;
        //System.out.println("\n\n\n\n\ngagal\n\n\n\n\n");
        for (Room r : ROOM_DATABASE) {

            //System.out.println("\n\n\n\n\ngagal1\n\n\n\n\n");
            if (r.getHotel().equals(hotel) && r.getNomorKamar().equals(nomor_kamar)) {

               // System.out.println("\n\n\n\n\ngagal2\n\n\n\n\n");
                if (DatabasePesanan.getPesanan(r) == null) {

                    //System.out.println("\n\n\n\n\ngagal3\n\n\n\n\n");
                    ROOM_DATABASE.remove(r);
                    return true;
                }
            }
            else {
                Administrasi.pesananDibatalkan(r);

                //System.out.println("\n\n\n\n\ngagal4\n\n\n\n\n");
                throw new RoomTidakDitemukanException(r.getHotel(), r);
            }


        }

        //System.out.println("\n\n\n\n\ngagal42\n\n\n\n\n");

        return false;
    }

        /**
         * metode untuk mengambil dataase kamar pada hotel
         *
         *
         *
         */



}

