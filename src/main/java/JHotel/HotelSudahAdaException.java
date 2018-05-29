package JHotel;

/**
 * Class ini berfungsi sebagai handling error
 * pada penambahan hotel ketika hotel telah ada
 *
 * @author muflih fathan q
 * @version 05/20/2018
 */

public class HotelSudahAdaException extends Exception
{


    private Hotel hotel_error;
    /**
     * cosntructor class
     *
     * @param hotel_input
     *
     */
    public HotelSudahAdaException(Hotel hotel_input)
    {
        super("Hotel dengan nama :");
        hotel_error = hotel_input;
    }
    /**
     * metode untuk mencetak pesan error
     *
     * @return String pesan
     *
     */

    public String getPesan()
    {
        String pesn =  super.getMessage() + hotel_error.getNama() + "sudah terdaftar ";
        return pesn;
    }
}
