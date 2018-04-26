package JHotel;
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
    private static ArrayList<Customer> CUSTOMER_DATABASE = new ArrayList<Customer>();


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
    public static boolean addCustomer(Customer baru) throws PelangganSudahAdaException
    {
        //System.out.println(baru);

        for(int i = 0; i < CUSTOMER_DATABASE.size();i++)
        {
            Customer id    = CUSTOMER_DATABASE.get(i);
            Customer email = CUSTOMER_DATABASE.get(i);
            if(id.getID() == baru.getID() || email.getEmail().equals(baru.getEmail()))
            {

                throw new PelangganSudahAdaException(baru);

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
        //System.out.println(id);
        for(int i = 0;i <= CUSTOMER_DATABASE.size();i++)
        {
            int ID = CUSTOMER_DATABASE.get(i).getID();
            if(ID == id)
            {
                return CUSTOMER_DATABASE.get(id-1);
            }
        }
        return null;
        /*
        for(Customer c : CUSTOMER_DATABASE)
        {

        }
        return null;*/
    }
    /**
     * metode untuk menghapus pesanan ke database
     *
     * @param  id id customer
     * 
     */
    public static boolean removeCustomer(int id) throws PelangganTidakDitemukanException {
        int i = 0;
        try {

            if(id > CUSTOMER_DATABASE.size())
            {
                throw new PelangganTidakDitemukanException(id);
            }
            while (CUSTOMER_DATABASE.size() > i) {
                i++;
                if(i == CUSTOMER_DATABASE.size()) {
                    throw new PelangganTidakDitemukanException(CUSTOMER_DATABASE.get(i).getID());
                }

                CUSTOMER_DATABASE.get(i);
                if(CUSTOMER_DATABASE.get(i).getID() == id) {
                    Pesanan p = DatabasePesanan.getPesananAktif(CUSTOMER_DATABASE.get(i));
                    DatabasePesanan.removePesanan(p);
                    CUSTOMER_DATABASE.remove(i);

                }


            }
        }
        catch (PesananTidakDitemukanException e) {
            Pesanan p = DatabasePesanan.getPesananAktif(CUSTOMER_DATABASE.get(i));
            PesananTidakDitemukanException pe = new PesananTidakDitemukanException(p);
        }

        return false;
        }
            /*
            for (Customer c : CUSTOMER_DATABASE) {
                if (c.getID() == id) {
                    Pesanan p = DatabasePesanan.getPesananAktif(c);
                    DatabasePesanan.removePesanan(p);
                    CUSTOMER_DATABASE.remove(c);
                    return true;
                }
                else
                {
                    throw new PelangganTidakDitemukanException(c);
                }
            }


            }


            catch (PesananTidakDitemukanException e)
            {
                throw new PesananTidakDitemukanException();
            }*/

    
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

    public static Customer getCustomerLogin(String email, String password)
    {
        for(Customer c : CUSTOMER_DATABASE)
        {
            if(c.getEmail().equals(email) && c.getPassword().equals(password))
            {
                return DatabaseCustomer.getCustomer(c.getID());
            }
        }
        return null;
    }
    
}
