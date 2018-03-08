
/**
 * Enumeration class StatusKamar - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum StatusKamar
{
    Booked("Booked"),Processed("Processed"),Vacant("Vacant");
    
    private String status;
    
    StatusKamar(String status)
    {
        this.status = status;
    }
    
    public String toString()
    {
        return status;
    }
    
}
