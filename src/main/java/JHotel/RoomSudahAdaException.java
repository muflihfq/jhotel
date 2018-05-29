package JHotel;

/**
 * Class ini berfungsi sebagai handling error
 * pada penambahan room ketika pesanan room sudah ada
 *
 * @author muflih fathan q
 * @version 05/20/2018
 */
public class RoomSudahAdaException extends Exception
{


    private Room room_error;

    /**
     * cosntructor class
     *
     * @param room_input
     *
     */
    public RoomSudahAdaException(Room room_input)
    {
        super("Kamar dengan nomor ruangn :");
        room_error = room_input;
    }

    /**
     * metode untuk mencetak pesan error
     *
     * @return String pesan
     *
     */
    public String getPesan()
    {
        String pesn =  super.getMessage() + room_error.getNomorKamar() + " pada " + room_error.getHotel() + " sudah terdaftar ";
        return pesn;
    }
}
