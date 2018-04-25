package JHotel;
/**
 * Enum tipe kamar, berfungsi sebagai pilihan tipe kamar yang 
 * tersedia pada hotel 
 * 
 * @author muflih fathan q
 * @version 03/10/2018
 */
public enum TipeKamar
{
    Single("Single"),Double("Double"),Premium("Premium");


    //private String tipe;
    private String deskripsi;

    TipeKamar(String deskripsi)
    {
        this.deskripsi = deskripsi;
    }

    public String toString()
    {
        return deskripsi;
    }
    
}


