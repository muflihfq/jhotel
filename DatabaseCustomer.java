import java.util.ArrayList;

/**
 * Class untuk menyimpan database customer
 *
* @author muflih fathan q
 * @version 03/10/2018
 */
public class DatabaseCustomer
{
    // instance variables - replace the example below with your own
    private static int LAST_CUSTOMER_ID;
    private static ArrayList<Customer> CUSTOMER_DATABASE;

    /**
     * Constructor untuk class DatabaseCustomer
     */
    public DatabaseCustomer()
    {
        
    }

    /**
     * metode untuk menambahkan customer
     *
     * @param baru - baru merupakan variabel untuk customer baru
     * 
     */
    public static boolean addCustomer(Customer baru)
    {
        for(Customer c : CUSTOMER_DATABASE)
        {
            if(c.getID() == baru.getID())
            {
                return false;
            }
        }
        LAST_CUSTOMER_ID = baru.getID();
        CUSTOMER_DATABASE.add(baru);
        return true;
    }

    public static int getLastCustomerID()
    {
        return LAST_CUSTOMER_ID;
    }
    public static Customer getCustomer(int id)
    {
        for(Customer c : CUSTOMER_DATABASE)
        {
            if(c.getID() == id)
            {
                return CUSTOMER_DATABASE.get(id);
            }
        }
        return null;
    }
    /**
     * metode untuk menghapus pesanan ke database
     *
     * @param  id id customer
     * 
     */
    public static boolean removeCustomer(int id)
    {
        for(Customer c : CUSTOMER_DATABASE)
        {
            if(c.getID() == id)
            {
                Pesanan p = DatabasePesanan.getPesananAktif(c);
                DatabasePesanan.removePesanan(p);
                CUSTOMER_DATABASE.remove(c);
                return true;
            }
        }

        return false;
    }
    
    /**
     * metode untuk mengambil data customer dari database
     *
     * 
     * 
     */
    public static ArrayList<Customer> getCustomerDatabase()
    {
        return CUSTOMER_DATABASE;
    }
    
}
