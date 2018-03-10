
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


    private String tipe;

    TipeKamar(String tipe)
    {
        this.tipe = tipe;
    }

    public String toString()
    {
        return tipe;
    }
    
}


