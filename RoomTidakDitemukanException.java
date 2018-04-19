public class RoomTidakDitemukanException extends Exception
{

    private Hotel hotel_error;
    private Room room_error;

    public RoomTidakDitemukanException(Hotel hotel_input,Room room_input)
    {
        super("Kamar yang terletak di  :");
        room_error = room_input;
        hotel_error = hotel_input;
    }

    public String getPesan()
    {
        String pesn =  super.getMessage() + hotel_error + " dan dengan nomor kamar  " + room_error + " tidak ditemukan   ";
        return pesn;
    }
}
