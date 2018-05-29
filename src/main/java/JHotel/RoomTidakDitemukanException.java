package JHotel;

/**
 * Class ini berfungsi sebagai handling error
 * pada penghapusan room ketika room tidak ditemukan
 *
 * @author muflih fathan q
 * @version 05/20/2018
 */
public class RoomTidakDitemukanException extends Exception
{

    private Hotel hotel_error;
    private String room_error;

    /**
     * cosntructor class
     *
     * @param room_input
     * @param hotel_input
     *
     */
    public RoomTidakDitemukanException(Hotel hotel_input,String room_input)
    {
        super("Kamar yang terletak di  :");
        room_error = room_input;
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
        String pesn =  super.getMessage() + hotel_error + " dan dengan nomor kamar  " + room_error + " tidak ditemukan   ";
        return pesn;
    }
}
