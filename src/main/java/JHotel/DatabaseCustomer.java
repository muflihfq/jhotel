package JHotel;
import java.sql.*;
import java.util.ArrayList;

/**
 * Class untuk menyimpan database customer
 *
 * @author muflih fathan q
 * @version 05/20/2018
 */
public class    DatabaseCustomer
{
    private static int LAST_CUSTOMER_ID;
    private static final String url  = "jdbc:postgresql://localhost:5432/jhotel";
    private static final String userpass = "muflih";


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
        //select database, ngecek customer
        boolean sudahAda = false;
        try {
            Class.forName("org.postgresql.Driver");

            Connection c = DriverManager.getConnection(url, userpass, userpass);
            Statement stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT id,email from customer");
            System.out.println("berhasil");
            while(rs.next()){

                if(rs.getInt("id") == baru.getID() || rs.getString("email").equals(baru.getEmail()))
                {
                    //jika sudah ada
                    sudahAda = true;
                    throw new PelangganSudahAdaException(baru);

                }
            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //jika customer belum ada, memasukkan customer baru
        if(!sudahAda) {
            LAST_CUSTOMER_ID = baru.getID();
            System.out.println("double\n\n\n");
            //insert customer database
            java.util.Date utilDate = baru.getDOB();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            try {
                Connection c = DriverManager.getConnection(url, userpass, userpass);

                String query = "INSERT INTO customer VALUES (?,?,?,?,?);";
                PreparedStatement stmt = c.prepareStatement(query);
                stmt.setInt(1, baru.getID());
                stmt.setString(2, baru.getNama());
                stmt.setString(3, baru.getPassword());
                stmt.setString(4, baru.getEmail());
                stmt.setDate(5, sqlDate);

                stmt.executeUpdate();
                stmt.close();
                c.close();


            } catch (Exception e) {
                e.getMessage();
            }
            return true;
        }
    return false;
    }

    /**
     * metode untuk mendapatkan id customer terakhir
     * @return
     */
    public static int getLastCustomerID()
    {
        int idDb = 0;
        //mengambil id customer terakhir
        try {
            Class.forName("org.postgresql.Driver");

            Connection c = DriverManager.getConnection(url, userpass, userpass);

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select id from customer order by id desc limit 1;");
            while(rs.next()){
                 idDb = rs.getInt("id");

            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return idDb;
    }

    /**
     * metode untuk mengambil customer pada database
     * @param id
     * @return
     */
    public static Customer getCustomer(int id)
    {

        Customer getCustomer = new Customer();
        //mengambil customer dari database
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            PreparedStatement stmt = c.prepareStatement("select * from customer where id = ?");
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                //membuat objek customer batu untuk di-return
                getCustomer = new Customer(rs.getString("nama"),Date.valueOf(rs.getString("dob")),
                        rs.getString("email"),rs.getString("password"));
                getCustomer.setID(rs.getInt("id"));
            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return getCustomer;
    }

    /**
     * metode untuk menghapus customer dari database
     *
     * @param  id id customer
     *
     */
    public static boolean removeCustomer(int id) throws PelangganTidakDitemukanException {
        Boolean pelangganAda = false;
        Boolean berhasilDelete = false;
        Customer getCustomer = new Customer();
        try {
            Class.forName("org.postgresql.Driver");
            //mencari customer dengan id sebagai parameter
            Connection c = DriverManager.getConnection(url, userpass, userpass);
            PreparedStatement stmt = c.prepareStatement("select * from customer where id = ?");
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){

                if(rs.getString("nama") == null){
                    //jika pelanggan tidak ditemukan throw exception
                    pelangganAda = false;
                    throw new PelangganTidakDitemukanException(id);
                }
                //jika ditemukan, buat objek customer baru
                getCustomer = new Customer(rs.getString("nama"),Date.valueOf(rs.getString("dob")),
                        rs.getString("email"),rs.getString("password"));
                getCustomer.setID(rs.getInt("id"));


            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //jika pelanggan ada
        if(pelangganAda) {
            //hapus pesanan customer
            try {
                Pesanan p = DatabasePesanan.getPesananAktif(getCustomer);
                DatabasePesanan.removePesanan(p);
            } catch (PesananTidakDitemukanException e) {
                e.getPesan();
            }

            //hapus customer
            try {
                Class.forName("org.postgresql.Driver");
                Connection c = DriverManager.getConnection(url, userpass, userpass);

                PreparedStatement stmt = c.prepareStatement("delete from pesanan_customercustomer where id_customer= ?");
                stmt.setInt(1, id);
                int i = stmt.executeUpdate();
                if(i > 0){
                    berhasilDelete = true;
                }

                stmt.close();
                c.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return berhasilDelete;
        }
        return berhasilDelete;
    }


    /**
     * metode untuk mengambil data customer dari database
     *
     *
     * @return customer_database
     */
    public static ArrayList<Customer> getCustomerDatabase()
    {
         ArrayList<Customer> CUSTOMER_DATABASE = new ArrayList<Customer>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);
            //mengambil semua customer yang ada pada database
            PreparedStatement stmt = c.prepareStatement("select * from customer;");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                //membuat objek customer dan memasukkan ke dalam arraylist
                Customer getCustomer = new Customer(rs.getString("nama"),Date.valueOf(rs.getString("dob")),
                        rs.getString("email"),rs.getString("password"));
                getCustomer.setID(rs.getInt(1));
                CUSTOMER_DATABASE.add(getCustomer);
            }

            rs.close();
            stmt.close();
            c.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return CUSTOMER_DATABASE;
    }

    /**
     * metode untuk melakuka login
     *
     * @param email
     * @param password
     * @return
     */
    public static Customer getCustomerLogin(String email, String password)
    {

        int idDB = 0;
        boolean loginBerhasil = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            //mengecek apakah customer ada sesuai parameter
            PreparedStatement stmt = c.prepareStatement("select * from customer where email = ? and password = ?");
            stmt.setString(1,email);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){

                if(rs.getString("nama") != null){
                    //jika customer ditemukan
                    idDB = rs.getInt("id");
                    loginBerhasil = true;

                }

            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //jika login berhasil mengambil data customer
       if(loginBerhasil){
           return DatabaseCustomer.getCustomer(idDB);
       }
       return null;
    }

    public static boolean getAdminLogin(String email, String password)
    {
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            //mengecek apakah customer ada sesuai parameter
            PreparedStatement stmt = c.prepareStatement("select * from admin where email = ? and password = ?");
            stmt.setString(1,email);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){

                if(rs.getString("email") != null){
                    //jika customer ditemukan
                    return true;

                }

            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
