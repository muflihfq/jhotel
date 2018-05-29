package JHotel;

/**
 * Class ini berfungsi sebagai handling error
 * pada penambahan pelanggan ketika pelanggan sudah ada
 *
 * @author muflih fathan q
 * @version 05/20/2018
 */
public class PelangganSudahAdaException extends Exception
{


    private Customer pelanggan_error;

    /**
     * cosntructor class
     *
     * @param pelanggan_input
     *
     */
    public PelangganSudahAdaException(Customer pelanggan_input)
    {
        super("Pelanggan dengan Data :");
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
       String pesn =  super.getMessage() + pelanggan_error + " sudah terdaftar ";
       return pesn;
    }
}
