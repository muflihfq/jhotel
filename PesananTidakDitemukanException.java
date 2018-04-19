public class PesananTidakDitemukanException extends Exception
{


    private Pesanan pesanan_error;

    public PesananTidakDitemukanException(Pesanan pesanan_input)
    {
        super("Pesanan yang dipesan oleh :");
        pesanan_error = pesanan_input;
    }

    public String getPesan()
    {
        String pesn =  super.getMessage() + pesanan_error.getPelanggan().getNama() + "tidak ditemukan";
        return pesn;
    }
}
