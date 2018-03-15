
/**
 * Write a description of class PremiumRoom here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PremiumRoom extends Room
{
    // instance variables - replace the example below with your own
    private static TipeKamar TIPE_KAMAR = TipeKamar.Premium;
    private static double DISCOUNT = 0.3;

    /**
     * Constructor for objects of class PremiumRoom
     */
    public PremiumRoom(Hotel hotel, String nomor_kamar,
                boolean isAvailable, StatusKamar status_kamar)
    {
        super(hotel,nomor_kamar,isAvailable,status_kamar);
        
    }   

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public TipeKamar getTipeKamar()
    {
        return TIPE_KAMAR;
    }
    
    public double getDiscount()
    {
        return DISCOUNT;
    }
    
    public void setDailyTariff(double dailytariff)
    {
        //this.dailytariff = dailytariff;
        //* DISCOUNT;
        dailyTariff = dailytariff * DISCOUNT;
        //dailyTariff = dailytariff;
        
        
    }
    
}
