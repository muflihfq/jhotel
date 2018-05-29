package JHotel;
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

    private String nama;
    private int id;
    private String email;
    private Date dob;
    private String password;

    /**
     * Constructor for objects of class Customer
     */
    public Customer(String nama, int tanggal, int bulan, int tahun,String email,String password)
    {
        DatabaseCustomer db = new DatabaseCustomer();
        id = db.getLastCustomerID() + 1;
        this.dob = new Date(tahun, bulan, tanggal);
        this.nama = nama;
        this.password = password;
        this.email = email;
    }
    /**
     * Constructor for objects of class Customer
     */
    public Customer(String nama, Date dob,String email,String password   )
    {
        DatabaseCustomer db = new DatabaseCustomer();
        id = db.getLastCustomerID() + 1;
        this.nama = nama;
        this.dob = dob;
        this.email = email;
        this.password = password;
    }
    /**
     * Constructor for objects of class Customer
     */
    public Customer(){}

    /**
     * metode untuk  mengambil password dari customer
     *
     * @return password    identifikasi dari customer
     */
    public String getPassword()
    {
        return password;
    }
    /**
     * metode untuk menampilkan atau mengambil ID dari customer
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
     * metode untuk mengambil email dari customer
     *
     * @return id    identifikasi dari customer
     */
    public String getEmail()
    {
        return email;
    }
    /**
     * metode untuk mengambil tanggal lahir dari customer
     *
     * @return id    identifikasi dari customer
     */
    public Date getDOB()
    {
        DateFormat gantiFormat = new SimpleDateFormat("'DOB : 'dd MMMM yyyy");
        String result = gantiFormat.format(dob);
        
        
        System.out.println(result);
        return dob;
    }
    /**
     * metode untuk memberikan password  dari customer
     *
     *
     * @param password    password baru untuk customer
     */
    public void setPassword(String password)
    {
        this.password = password;
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
     * metode untuk memberikan atau mengganti variabel email dari customer
     *
     *
     * @param email    identifikasi dari customer
     */
    public void setEmail(String email)
    {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9][A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            this.email = email;
        }
        
            
        
    }
    /**
     * metode untuk memasukkan atau mengganti
     * variabel tanggal lahir dari customer
     *
     * @param dob   identifikasi dari customer
     */
    public void setDOB(Date dob)
    {
        this.dob = dob;
        
    }

    /**
     * metode untuk mencetak data
     *
     * @return String pesan
     *
     */
    public String toString()
    {
        ArrayList<Customer> CUSTOMER = DatabaseCustomer.getCustomerDatabase();
        for(Customer c : CUSTOMER)
        {
            if(id == c.getID())
            {
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

}
