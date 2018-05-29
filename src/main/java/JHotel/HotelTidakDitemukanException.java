package JHotel;

/**
 * Class ini berfungsi sebagai handling error
 * pada penghapusan hotel ketika hotel tidak ditemukan
 *
 * @author muflih fathan q
 * @version 05/20/2018
 */
public class HotelTidakDitemukanException extends Exception
{


    private int hotel_error;
    /**
     * cosntructor class
     *
     * @param hotel_input
     *
     */
    public HotelTidakDitemukanException(int hotel_input)
    {
        super("Hotel dengan ID :");
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
        String pesn =  super.getMessage() + hotel_error +  " tidak ditemukan ";
        return pesn;
    }
}
