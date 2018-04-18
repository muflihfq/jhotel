import javax.xml.crypto.Data;
import java.rmi.server.LogStream;
import java.util.*;
/**
 * Merupakan main class dari package j hotel
 *
 * @author muflih fathan q
 * @version 03/01/2018
 */
public class JHotel
{
    // instance variables - replace the example below with your own
    //private int x;
    /**
     * Constructor pada class JHotel
     */
    public JHotel()
    {
       
    }
  
    /**
     * metode untuk menjalankan package
     *
     * @param
     * 
     */

    public static void main(String[] args) {
        DatabaseCustomer.addCustomer(new Customer("abi", 1997, 3, 12));
        DatabaseCustomer.addCustomer(new Customer("budi", 1997, 4, 23));
        DatabaseCustomer.addCustomer(new Customer("cece", 1997, 5, 10));
        ArrayList<Customer> Cust = new DatabaseCustomer().getCustomerDatabase();
        for (Customer c : Cust) {
            System.out.println(c);
        }

        Lokasi Depok = new Lokasi(23, 32, "Depok");
        DatabaseHotel.addHotel(new Hotel("Aston", Depok, 4));

        Lokasi Bogor = new Lokasi(56, 89, "Bogot");
        DatabaseHotel.addHotel(new Hotel("Hilton", Bogor, 5));

        Lokasi Jakarta = new Lokasi(1, 1, "Jakarta");
        DatabaseHotel.addHotel(new Hotel("Ibis", Jakarta, 3));

        ArrayList<Hotel> Hotel = new DatabaseHotel().getHotelDatabase();
        for (Hotel h : Hotel) {
            System.out.println(h);
        }

        ArrayList<Hotel> hotel = DatabaseHotel.getHotelDatabase();
        for (Hotel h : hotel) {
            if (h.getNama().equals("Hilton")) {
                DatabaseRoom.addRoom(new DoubleRoom(h, "E202", StatusKamar.Vacant));
            } else if (h.getNama().equals("Aston")) {
                DatabaseRoom.addRoom(new PremiumRoom(h, "C303", StatusKamar.Vacant));
            } else if (h.getNama().equals("Ibis")) {
                DatabaseRoom.addRoom(new SingleRoom(h, "A101", StatusKamar.Vacant));
                DatabaseRoom.addRoom(new PremiumRoom(h, "D404", StatusKamar.Vacant));
            }

        }

        ArrayList<Room> room = DatabaseRoom.getRoomDatabase();

        for (Room r : room) {
            System.out.println(r);
        }

        ArrayList<Customer> customer = DatabaseCustomer.getCustomerDatabase();
        Administrasi admin = new Administrasi();
        for (Customer c : customer) {
            if (c.getNama().equals("abi")) {
                DatabasePesanan.addPesanan(new Pesanan(12, c));

            } else if (c.getNama().equals("budi")) {
                DatabasePesanan.addPesanan(new Pesanan(3, c));
            } else if (c.getNama().equals("cece")) {
                DatabasePesanan.addPesanan(new Pesanan(7, c));
            }


        }

       /* ArrayList<Pesanan> pesan = DatabasePesanan.getPesananDatabase();
        for (Pesanan p : pesan)
        {
            System.out.println(p);
        }*/

        ArrayList<Pesanan> pesan1 = DatabasePesanan.getPesananDatabase();
        ArrayList<Room> room1 = DatabaseRoom.getRoomDatabase();

        for (Pesanan p : pesan1) {
            if (p.getID() == 1) {
                for (Room r : room1) {
                    if (r.getNomorKamar().equals("A101")) {
                        admin.pesananDitugaskan(p, r);
                    }
                }
            } else if (p.getID() == 2) {
                for (Room r : room1) {
                    if (r.getNomorKamar().equals("E202")) {
                        admin.pesananDitugaskan(p, r);
                    }
                }
            } else if (p.getID() == 3) {
                for (Room r : room1) {
                    if (r.getNomorKamar().equals("C303")) {
                        admin.pesananDitugaskan(p, r);
                    }
                }
            }


            System.out.println(p);

        }
        /*
            ArrayList<Pesanan> pesan2 = DatabasePesanan.getPesananDatabase();


            for (Pesanan p : pesan2) {
                if (p.getID() == 1) {
                    admin.pesananSelesai(p);
                } else if (p.getID() == 2) {
                    admin.pesananDibatalkan(p);
                } else if (p.getID() == 3) {
                    admin.pesananDibatalkan(p);
                }
                System.out.println(p);
            }*/


        admin.pesananSelesai(DatabasePesanan.getPesanan(1));
        admin.pesananDibatalkan(DatabasePesanan.getPesanan(2));

        /*Pesanan psn = DatabasePesanan.getPesanan(3);
        System.out.println("\n\n\n\n\n\n\n\n");
        System.out.println(psn);*/


        ArrayList<Pesanan> pesan2 = DatabasePesanan.getPesananDatabase();
        for(Pesanan p : pesan2)
        {
            System.out.println(p);
        }
        }
    }

        //DatabaseRoom.addRoom(new Room())
        /*
        DatabaseCustomer dbCus = new DatabaseCustomer();
        Customer luffy = new Customer("luffy", new GregorianCalendar(2018,2,20).getTime());
        dbCus.addCustomer(luffy);


        DatabaseHotel dbHotel = new DatabaseHotel();
        DatabaseRoom dbRoom = new DatabaseRoom();
        Lokasi tempat = new Lokasi(23,45,"Depok");    
        Hotel rumah = new Hotel("Hotel",tempat,4);
        dbHotel.addHotel(rumah);
        Room single = new SingleRoom(rumah,"23",true,StatusKamar.Vacant);
        dbRoom.addRoom(single);

        DatabasePesanan dbPesan = new DatabasePesanan();
        Pesanan pesan = new Pesanan(3,luffy);
        dbPesan.addPesanan(pesan);


        System.out.println("Customer-----------");
        System.out.println(luffy.toString());
        System.out.println("Pesanan-----------");
        System.out.println(pesan.toString());
        
        System.out.println("\n\ntoString kelas Room untuk kondisi pertama : \n\n");
        System.out.println(single.toString());

        System.out.println("\n\ntoString kelas Room untuk kondisi kedua   : \n\n");
        System.out.println(single.toString());
        
        pesan.setStatusDiproses(true);
        pesan.setStatusSelesai(false);
        
        System.out.println("\n\ntoString kelas Pesanan untuk kondisi pertama : \n\n");
        System.out.println(pesan.toString());

        pesan.setStatusDiproses(false);
        pesan.setStatusSelesai(false);

        System.out.println("\n\ntoString kelas Pesanan untuk kondisi kedua   : \n\n");
        System.out.println(pesan.toString());
        
        pesan.setStatusDiproses(false);
        pesan.setStatusSelesai(true);

        System.out.println("\n\ntoString kelas Pesanan untuk kondisi ketiga   : \n\n");
        System.out.println(pesan.toString());

        System.out.println("tes");
        */


