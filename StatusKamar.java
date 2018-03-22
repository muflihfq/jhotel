/**
 * Enum Status kamar, berfungsi sebagai pilihan status kamar
 * pada kamar hotel 
 * 
 * @author muflih fathan q
 * @version 03/10/2018
 */
 
public enum StatusKamar
{
    Booked("Booked"),Processed("Processed"),Vacant("Vacant");
    
    private String deskripsi;

    StatusKamar(String deskripsi)
    {
        this.deskripsi = deskripsi;
    }

    public String toString()
    {
        return deskripsi;
    }
    
}
