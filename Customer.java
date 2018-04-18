import java.util.Date;
import java.util.*;
import java.util.regex.*;
import java.text.*;

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
    protected String email;
    protected Date dob;

    /**
     * Constructor for objects of class Customer
     */
    public Customer(String nama, int tahun, int bulan, int tanggal)
    {
        DatabaseCustomer db = new DatabaseCustomer();
        id = db.getLastCustomerID() + 1;
        this.dob = new Date(tahun, bulan, tanggal);
        this.nama = nama;
    }
    
    public Customer(String nama, Date dob )
    {
        DatabaseCustomer db = new DatabaseCustomer();
        id = db.getLastCustomerID() + 1;
        this.nama = nama;
        this.dob = dob;
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
    
    public String getEmail()
    {
        return email;
    }
    
    public Date getDOB()
    {
        DateFormat gantiFormat = new SimpleDateFormat("'DOB : 'dd MMMM yyyy");
        String result = gantiFormat.format(dob);
        
        
        System.out.println(result);
        return dob;
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
    
    public void setEmail(String email)
    {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9][A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            this.email = email;
        }
        
            
        
    }
    
    public void setDOB(Date dob)
    {
        this.dob = dob;
        
    }
    
    public String toString()
    {
        ArrayList<Customer> CUSTOMER = DatabaseCustomer.getCustomerDatabase();
        for(Customer c : CUSTOMER)
        {
            if(id == c.getID())
            {
                //Pesanan p = DatabasePesanan.getPesa;



                    return "\nCustomer ID   : "+id+
                            "\nName          : "+nama+
                            "\nE-mail        : "+email+
                            "\nDate of birth : "+dob+
                            "\nBooking order is in progress";

            }
        }


            return "\nCustomer ID   : "+id+
                   "\nName          : "+nama+
                   "\nE-mail        : "+email+
                   "\nDate of birth : "+dob;

    }
    /*
     * metode untuk mencetak data
     * 
     
    public void printData()
    {
        System.out.println("Nama Pelanggan:" +nama);
        System.out.println("Id Pelanggan :" +id);
    }
    
    */
}
