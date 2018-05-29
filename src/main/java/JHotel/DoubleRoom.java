package JHotel;
/**
 * Class Double Room, class untuk membuat
 * objek kamar baru bertipe double
 *
 * @author muflih fathan q
 * @version 20/05/2018
 */
public class DoubleRoom extends Room
{

    private static TipeKamar TIPE_KAMAR = TipeKamar.Double;
    private Customer customer;

    /**
     * Constructor for objects of class DoubleRoom
     *
     * @param hotel - hotel dimana kamar berada
     * @param nomor_kamar
     * @param status_kamar
     */
    public DoubleRoom(Hotel hotel, String nomor_kamar, StatusKamar status_kamar)
    {
        super(hotel,nomor_kamar,status_kamar);
    }
    /**
     * Constructor for objects of class DoubleRoom
     */
    public DoubleRoom(){}

    /**
     * metode untuk mendapatkan pelanggan
     * yang memesan kamar
     *
     * @return customer
     */
    public Customer getCustomer()
    {
        return customer;
    }

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

    /**
     * metode untuk memberikan pelanggan
     * ke dalama kamar
     *
     * @param customer
     */
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
    
    
}
