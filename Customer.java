
/**
 * Write a description of class Customer here.
 *
* @author muflih fathan q
 * @version 03/01/2018
 */
public class Customer
{
    // instance variables - replace the example below with your own
    protected String nama;
    protected int id;

    /**
     * Constructor for objects of class Customer
     */
    public Customer(int id, String nama)
    {
       this.id = id;
       this.nama = nama;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int getID()
    {
        return id;
    }
    
    public String getNama()
    {
        return nama;
    }
    
    public void setID(int id)
    {
       this.id = id;
    }
    
    public void setNama(String nama)
    {
       this.nama = nama;
    }
    
    public void printData()
    {
        System.out.println("Nama :" +nama);
        System.out.println("Id :" +id);
    }
}
