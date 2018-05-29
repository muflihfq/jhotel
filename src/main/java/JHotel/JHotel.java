package JHotel;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication

/**
 * Merupakan main class dari package j hotel
 *
 * @author muflih fathan q
 * @version 03/01/2018
 */
public class JHotel {
    // instance variables - replace the example below with your own
    //private int x;

    /**
     * Constructor pada class JHotel
     */
    public JHotel() {

    }

    /**
     * metode untuk menjalankan package
     *
     * @param
     */

    public static void main(String[] args) {

/*
        try {
            Lokasi Depok = new Lokasi(23, 32, "Depok");
            DatabaseHotel.addHotel(new Hotel("Aston", Depok, 4));


            *//*ArrayList<Hotel> Hotel = new DatabaseHotel().getHotelDatabase();
            for (Hotel h : Hotel) {
                System.out.println(h);
            }*//*

        } catch (HotelSudahAdaException e) {
            String psn = e.getPesan();
            System.out.println(psn);
        }*/

    /*    try {
           // DatabaseRoom.addRoom(new DoubleRoom(DatabaseHotel.getHotel(1),"301",StatusKamar.Vacant));
       //     DatabaseRoom.addRoom(new SingleRoom(DatabaseHotel.getHotel(1),"601",StatusKamar.Vacant));
           DatabaseRoom.addRoom(new PremiumRoom(DatabaseHotel.getHotel(1),"401",StatusKamar.Vacant));


//            ArrayList<Room> room = DatabaseRoom.getRoomDatabase();
//
//            for (Room r : room) {
//                System.out.println(r);
//            }
        } catch (RoomSudahAdaException e) {
            String psn = e.getPesan();
            System.out.println(psn);
        }*/


        SpringApplication.run(JHotel.class , args);


    }
}
