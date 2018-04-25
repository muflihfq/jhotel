package JHotel;

public class PelangganTidakDitemukanException extends Exception
{


    private int pelanggan_error;

    public PelangganTidakDitemukanException(int pelanggan_input)
    {
        super("Data customer dengan ID :");
        pelanggan_error = pelanggan_input;
    }

    public String getPesan()
    {
        String pesn =  super.getMessage() + pelanggan_error + " tidak ditemukan ";
        return pesn;
    }
}
