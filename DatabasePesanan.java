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
    public static boolean removePesanan(Pesanan pesan)
    {
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


           for( int i = 0; i < PESANAN_DATABASE.size();i++)
           {
               if(PESANAN_DATABASE.get(i).getID() == id)
                {
                    return PESANAN_DATABASE.get(id);

                }
                else
                {
                    return null;
                }
           }


        return null;
    }

    public static Pesanan getPesanan(Room kamar)
    {
        return null;
    }
    /**
     * metode untuk mengambil data pesanan dari database
     *
     * 
     * 
     */
    
    public static Pesanan getPesananAktif(Customer pelanggan)
    {
        return null;
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
