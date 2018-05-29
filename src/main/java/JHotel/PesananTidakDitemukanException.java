package JHotel;

/**
 * Class ini berfungsi sebagai handling error
 * pada penghapusan pesanan ketika pesanan tidak ditemukan
 *
 * @author muflih fathan q
 * @version 05/20/2018
 */
public class PesananTidakDitemukanException extends Exception
{


    private Pesanan pesanan_error;

    /**
     * cosntructor class
     *
     * @param pesanan_input
     *
     */
    public PesananTidakDitemukanException(Pesanan pesanan_input)
    {
        super("Pesanan yang dipesan oleh :");
        pesanan_error = pesanan_input;
    }

    /**
     * metode untuk mencetak pesan error
     *
     * @return String pesan
     *
     */
    public String getPesan()
    {
        String pesn =  super.getMessage() + pesanan_error.getPelanggan().getNama() + "tidak ditemukan";
        return pesn;
    }
}
