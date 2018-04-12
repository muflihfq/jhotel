  
/**
 * Class Administrasi berfungsi sebagai tempat pemrosean pesanan
 * kamar pada hotel
 * 
 * @author muflih fathan q
 * @version 03/10/2018
 */
public class Administrasi
{
    // instance variables - replace the example below with your own
    //private int x;

    /**
     * Constructor for objects of class Administrasi
     */
    public Administrasi()
    {
        // initialise instance variables
        //x = 0;
    }

    /**
     * metode untuk pemrosesan pesanan
     * 
     * @param  kamar  info kamar yang dipesan
     * @param pesan     pemesan kamar
     */
    public static void pesananDitugaskan(Pesanan pesan, Room kamar)
    {
        pesan.setStatusDiproses(true);
        pesan.setStatusSelesai(false);
        pesan.setRoom(kamar);
        
        
        roomAmbilPesanan(pesan,kamar);
        //pesan.setStatusDiproses = true;
        //pesan.setStatusSelesai = false;
    }
    
    /**
     * metode untuk meng-assign kamar kepada pemesan
     * 
     * @param  kamar  info kamar yang dipesan
     * @param pesan     pemesan kamar
     */
    public static void roomAmbilPesanan(Pesanan pesan, Room kamar)
    {
        kamar.setStatusKamar(StatusKamar.Booked);
        kamar.setPesanan(pesan);
        
        
    }
    
    /**
     * metode untuk mengkosongkan kamar, jika pesanan dibatalkan
     * dan jika pemesan telah meniggalkan kamar
     * @param  kamar  info kamar yang dipesan
     * 
     */
    public static void roomLepasPesanan(Room kamar)
    {
        kamar.setStatusKamar(StatusKamar.Vacant);
        kamar.setPesanan(null);
    }
    
    /**
     * metode untuk membatalkan pesanan
     * 
     * @param  kamar  info kamar yang dipesan
     * 
     */
    public static void pesananDibatalkan(Room kamar)
    {
        //kamar.setPesanan(null);
        Pesanan pesan = kamar.getPesanan();
        pesan.setStatusSelesai(false);
        pesan.setStatusDiproses(false);
        pesan.setRoom(null);
        pesan.setStatusAktif(false);
        
        roomLepasPesanan(kamar);
    }
    
    /**
     * metode untuk menyelesaikan pesanan, jika pelanggan
     * telah check out dari hotel
     * 
     * @param  kamar  info kamar yang dipesan
     */
    public static void pesananSelesai(Room kamar)
    {
        
        
        Pesanan pesan = kamar.getPesanan();
        pesan.setStatusSelesai(true);
        pesan.setStatusDiproses(false);
        pesan.setRoom(null);
        pesan.setStatusAktif(false);


        roomLepasPesanan(kamar);
    }
    
    /**
     * metode untuk membatalkan pesanan
     * 
     * @param pesan info pemesan kamar
     */
    public static void pesananDibatalkan(Pesanan pesan)
    {
        roomLepasPesanan(pesan.getRoom());
        
        pesan.setStatusSelesai(true);
        pesan.setStatusDiproses(false);
        pesan.setRoom(null);
    }
    
    /**
     * metode untuk menyelesaikan pesanan, jika pelanggan
     * telah check out dari hotel
     * 
     * @pesan info pemesan kamar
     */
    public static void pesananSelesai(Pesanan pesan)
    {
        roomLepasPesanan(pesan.getRoom());
        
        pesan.setStatusSelesai(true);
        pesan.setStatusDiproses(false);
        pesan.setRoom(null);
        
        
    }
}
