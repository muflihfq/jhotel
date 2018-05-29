package JHotel;

/**
 * Class ini berfungsi sebagai handling error
 * pada penghapusan pelanggan ketika pelanggan tidak ditemukan
 *
 * @author muflih fathan q
 * @version 05/20/2018
 */
public class PelangganTidakDitemukanException extends Exception
{


    private int pelanggan_error;

    /**
     * cosntructor class
     *
     * @param pelanggan_input
     *
     */
    public PelangganTidakDitemukanException(int pelanggan_input)
    {
        super("Data customer dengan ID :");
        pelanggan_error = pelanggan_input;
    }

    /**
     * metode untuk mencetak pesan error
     *
     * @return String pesan
     *
     */
    public String getPesan()
    {
        String pesn =  super.getMessage() + pelanggan_error + " tidak ditemukan ";
        return pesn;
    }
}
