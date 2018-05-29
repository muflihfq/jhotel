package JHotel;

/**
 * Class ini berfungsi sebagai handling error
 * pada penambahan pesanan ketika pesanan sudah ada dan masih aktif
 *
 * @author muflih fathan q
 * @version 05/20/2018
 */
public class PesananSudahAdaException extends Exception
{


    private Pesanan pesanan_error;

    /**
     * cosntructor class
     *
     * @param pesanan_input
     *
     */
    public PesananSudahAdaException(Pesanan pesanan_input)
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
        String pesn =  super.getMessage() + pesanan_error.getPelanggan().getNama() + "sudah melakukan pemesanan ";
        return pesn;
    }
}
