import java.util.Date;
import java.util.*;
import java.util.regex.*;

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
    public Customer(int tahun, int bulan, int tanggal)
    {
        this.dob = new Date(tahun, bulan, tanggal);
    }
    
    public Customer(int id, String nama, GregorianCalendar kalender )
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
    
    public String getEmail()
    {
        return email;
    }
    
    public Date getDOB()
    {
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
        return null;
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
