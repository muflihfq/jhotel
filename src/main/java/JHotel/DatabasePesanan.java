package JHotel;
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
    private static ArrayList<Pesanan> PESANAN_DATABASE = new ArrayList<Pesanan>();
    private static int LAST_PESANAN_ID = 0;

    /**
     * Constructor untuk objek pada class DatabasePesanan
     */
    public DatabasePesanan()
    {
        
    }

    public static ArrayList<Pesanan> getPesananDatabase()
    {
        return PESANAN_DATABASE;
    }

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
    public static boolean addPesanan(Pesanan baru) throws PesananSudahAdaException
    {

        for(Pesanan p : PESANAN_DATABASE )
        {
            if(p.getPelanggan().equals(baru.getPelanggan()) && baru.getStatusAktif() == true)
            {
                System.out.println("Pesanan tidak dapat diproses");
                throw new PesananSudahAdaException(baru);
               // return false;

            }

        }
        PESANAN_DATABASE.add(baru);
        LAST_PESANAN_ID = baru.getID();
        return true;
    }
    
    /**
     * metode untuk menghapus pesanan ke database
     *
     * @param  pesan
     * 
     */
    public static boolean removePesanan(Pesanan pesan) throws PesananTidakDitemukanException
    {
       // System.out.println("\n\n\n\n\n\ngagalbn\n\n\n\n\n");
        for (Pesanan p : PESANAN_DATABASE) {
            if (p.equals(pesan)) {
                if (p.getRoom() != null) {
                    Administrasi.pesananDibatalkan(p);
              //      System.out.println("\n\n\n\n\n\ngagalvvvv\n\n\n\n\n");
                } else {
                    if (p.getStatusAktif()) {
            //            System.out.println("\n\n\n\n\n\nbbbbgagal\n\n\n\n\n");
                        p.setStatusAktif(false);
                    }
                }


                if (PESANAN_DATABASE.remove(p)) {
          //          System.out.println("\n\n\n\n\n\ngagaljkjkj\n\n\n\n\n");
                    return true;
                }
            }
        }
        //System.out.println("\n\n\n\n\n\ngagal\n\n\n\n\n");
        throw new PesananTidakDitemukanException(pesan);

    }


    
    /**
     * metode untuk mengambil data pesanan
     *
     * @param
     * 
     */
    public static Pesanan getPesanan(int id)
    {


        for(Pesanan p : PESANAN_DATABASE)
        {
            if(p.getID() == id)
            {
                return p;
            }
        }
        return null;


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
