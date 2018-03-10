
/**
 * Write a description of class Customer here.
 *
* @author muflih fathan q
 * @version 03/10/2018
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
     * metode untuk menampilkan atau mengambil ID dari customer
     *
     * 
     * @return id    identifikasi dari customer
     */
    public int getID()
    {
        return id;
    }
    
    /**
     * metode untuk menampilkan atau mengambil nama dari customer
     *
     * 
     * @return nama    nama customer
     */
    public String getNama()
    {
        return nama;
    }
    
    /**
     * metode untuk memasukkan ID dari customer
     *
     * 
     * @param id    identifikasi dari customer
     */
    public void setID(int id)
    {
       this.id = id;
    }
    
    /**
     * metode untuk memasukkan nama dari customer
     *
     * 
     * @param nama    nama customer
     */
    public void setNama(String nama)
    {
       this.nama = nama;
    }
    
    /**
     * metode untuk mencetak data
     * 
     */
    public void printData()
    {
        System.out.println("Nama Pelanggan:" +nama);
        System.out.println("Id Pelanggan :" +id);
    }
}
