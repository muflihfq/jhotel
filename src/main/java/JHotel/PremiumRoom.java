package JHotel;

/**
 * Class Premium Room, class untuk membuat
 * objek kamar baru bertipe premium
 *
 * @author muflih fathan q
 * @version 20/05/2018
 */
public class PremiumRoom extends Room
{
    // instance variables - replace the example below with your own
    private static TipeKamar TIPE_KAMAR = TipeKamar.Premium;
    private static double DISCOUNT = 0.3;

    /**
     * Constructor for objects of class SingleRoom
     *
     * @param hotel - hotel dimana kamar berada
     * @param nomor_kamar
     * @param status_kamar status kamar apakah kosong atau dipesan
     */
    public PremiumRoom(Hotel hotel, String nomor_kamar, StatusKamar status_kamar)
    {
        super(hotel,nomor_kamar,status_kamar);
        
    }

    /**
     *  Constructor onstructor for objects of class PremiumRoom
     */
    public PremiumRoom(){}

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
     * metode untuk mendapatkan nilai diskon
     * dari kamar premium
     *
     * @return DISCOUNT
     */
    public double getDiscount()
    {
        return DISCOUNT;
    }

    /**
     * metode untuk memasukkan tarif setelah mendapat
     * potongan diskon
     *
     * @param dailytariff
     */
    public void setDailyTariff(double dailytariff)
    {

        dailyTariff = dailyTariff-(dailytariff * DISCOUNT);
        //return dailyTariff;

        
        
    }
    
}
