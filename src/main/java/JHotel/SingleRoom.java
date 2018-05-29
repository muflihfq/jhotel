package JHotel;

/**
 * Class Single Room, class untuk membuat
 * objek kamar baru bertipe single
 *
 * @author muflih fathan q
 * @version 20/05/2018
 */
public class SingleRoom extends Room
{
    // instance variables - replace the example below with your own
    private static TipeKamar TIPE_KAMAR = TipeKamar.Single;

    /**
     * Constructor for objects of class SingleRoom
     *
     * @param hotel - hotel dimana kamar berada
     * @param nomor_kamar
     * @param status_kamar status kamar apakah kosong atau dipesan
     */
    public SingleRoom(Hotel hotel, String nomor_kamar,
                 StatusKamar status_kamar)
    {
        // initialise instance variables
        super(hotel,nomor_kamar,status_kamar);
   
    }

    /**
     * Constructor for objects of class SingleRoom
     */

     public SingleRoom(){}

    /**
     * metode untuk mendapatkan tipe kamar
     * dari kamar
     *
     * @return TIPE_KAMAR
     */
    public TipeKamar getTipeKamar()
    {
        return TIPE_KAMAR;
    }
}
