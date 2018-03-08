
/**
 * Write a description of class Administrasi here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
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
    
    public static void roomAmbilPesanan(Pesanan pesan, Room kamar)
    {
        kamar.setStatusKamar(StatusKamar.Booked);
        kamar.setPesanan(pesan);
        
        
    }
    
    public static void roomLepasPesanan(Room kamar)
    {
        kamar.setStatusKamar(StatusKamar.Vacant);
        kamar.setPesanan(null);
    }
    
    public static void pesananDibatalkan(Room kamar)
    {
        //kamar.setPesanan(null);
        Pesanan pesan = kamar.getPesanan();
        pesan.setStatusSelesai(false);
        pesan.setStatusDiproses(false);
        pesan.setRoom(null);
        
        roomLepasPesanan(kamar);
    }
    
    public static void pesananSelesai(Room kamar)
    {
        roomLepasPesanan(kamar);
        
        Pesanan pesan = kamar.getPesanan();
        pesan.setStatusSelesai(true);
        pesan.setStatusDiproses(false);
        pesan.setRoom(null);
        
    }
    
    public static void pesananDibatalkan(Pesanan pesan)
    {
        roomLepasPesanan(pesan.getRoom());
        
        pesan.setStatusSelesai(true);
        pesan.setStatusDiproses(false);
        pesan.setRoom(null);
    }
    
    public static void pesananSelesai(Pesanan pesan)
    {
        pesan.setStatusSelesai(true);
        pesan.setStatusDiproses(false);
        pesan.setRoom(null);
        
         roomLepasPesanan(pesan.getRoom());
    }
}
