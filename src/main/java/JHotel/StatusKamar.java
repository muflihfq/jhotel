package JHotel;
/**
 * Enum Status kamar, berfungsi sebagai pilihan status kamar
 * pada kamar hotel 
 * 
 * @author muflih fathan q
 * @version 05/20/2018
 */
 
public enum StatusKamar
{
    Booked("Booked"),Processed("Processed"),Vacant("Vacant");
    
    private String deskripsi;

    /**
     * metode untuk memasukkan status kamar
     *
     * @param deskripsi
     */
    StatusKamar(String deskripsi)
    {
        this.deskripsi = deskripsi;
    }

    /**
     *
     * @return status kamar
     */
    public String toString()
    {
        return deskripsi;
    }
    
}
