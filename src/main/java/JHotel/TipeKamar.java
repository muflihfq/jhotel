package JHotel;
/**
 * Enum tipe kamar, berfungsi sebagai pilihan tipe kamar yang 
 * tersedia pada hotel 
 * 
 * @author muflih fathan q
 * @version 05/20/2018
 */
public enum TipeKamar
{
    Single("Single"),Double("Double"),Premium("Premium");



    private String deskripsi;

    /**
     * metode untuk memasukkan tipe kamar
     * @param deskripsi
     */
    TipeKamar(String deskripsi)
    {
        this.deskripsi = deskripsi;
    }

    /**
     *
     * @return tipe kamar
     */
    public String toString()
    {
        return deskripsi;
    }
    
}


