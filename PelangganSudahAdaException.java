public class PelangganSudahAdaException extends Exception
{


    private Customer pelanggan_error;

    public PelangganSudahAdaException(Customer pelanggan_input)
    {
        super("Pelanggan dengan Data :");
        pelanggan_error = pelanggan_input;
    }

    public String getPesan()
    {
       String pesn =  super.getMessage() + pelanggan_error + " sudah terdaftar ";
       return pesn;
    }
}
