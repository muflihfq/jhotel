import java.util.ArrayList;
import java.util.Arrays;

/**
 * class ini berfungsi sebagai database
 * atau penyimpanan data terkait pemesanan
 *
 * @author muflih fathan q
 * @version 03/10/2018
 */
public class DatabasePesanan
{
    // instance variables - replace the example below with your own
    //private static Pesanan list_pesanan;
    private static ArrayList<Pesanan> PESANAN_DATABASE;
    private static int LAST_PESANAN_ID = 0;

    /**
     * Constructor untuk objek pada class DatabasePesanan
     */
    public DatabasePesanan()
    {
        
    }
/*
    public static ArrayList<Pesanan> getPesananDatabase();
    {
        return null;
    }*/

    public static int getLastPesananID()
    {
        return LAST_PESANAN_ID;
    }
    /**
     * metode untuk menambah pesanan ke database
     *
     * @param  baru
     * 
     */
    public static boolean addPesanan(Pesanan baru)
    {
        boolean balik;

        if(baru.getStatusAktif()) {
            System.out.println("Pesanan tidak dapat diproses");
            balik = false;
        }
        else
        {
            PESANAN_DATABASE.add(baru);
            balik = true;
        }
        return balik;
    }
    
    /**
     * metode untuk menghapus pesanan ke database
     *
     * @param  pesan
     * 
     */
    public static boolean removeRoom(Pesanan pesan) {
        for (Pesanan p : PESANAN_DATABASE) {
            if (p.equals(pesan)) {
                if (p.getRoom() != null) {
                    Administrasi.pesananDibatalkan(p);
                } else {
                    if (p.getStatusAktif()) {
                        p.setStatusAktif(false);
                    }
                }
            }

            if (PESANAN_DATABASE.remove(p)) {
                return true;
            }
        }
        return false;
    }


    
    /**
     * metode untuk mengambil data pesanan
     *
     * @param
     * 
     */
    public static Pesanan getPesanan(int id)
    {
        Pesanan balik = null;

           for( int i = 0; i < PESANAN_DATABASE.size();i++)
           {
               if(PESANAN_DATABASE.get(i).getID() == id)
                {
                    balik = PESANAN_DATABASE.get(i);

                }
                else
                {
                    balik = null;
                }
           }


           return balik;
    }

    public static Pesanan getPesanan(Room kamar)
    {
        Pesanan balik = null;
        for( int i = 0; i < PESANAN_DATABASE.size();i++)
        {
            if(PESANAN_DATABASE.get(i).getRoom() == kamar)
            {
                balik = PESANAN_DATABASE.get(i);

            }
            else
            {
                balik = null;
            }
        }
    return balik;
    }

    /**
     * metode untuk mengambil data pesanan dari database
     *
     * 
     * 
     */
    
    public static Pesanan getPesananAktif(Customer pelanggan)
    {
        Pesanan balik = null;
        for( int i = 0; i < PESANAN_DATABASE.size();i++)
        {
            if(PESANAN_DATABASE.get(i).getStatusAktif() == true)
            {
                balik = PESANAN_DATABASE.get(i);

            }
            else
            {
                balik = null;
            }
        }
    return balik;

    }
    
    /**
     * metode untuk menambah pesanan ke database
     *
     * @param  pesan
     * 
     */
    /*public static void pesananDibatalkan(Pesanan pesan)
    {
       
    }*/
    
}
