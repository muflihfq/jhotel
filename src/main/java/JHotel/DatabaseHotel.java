package JHotel;
import java.sql.*;
import java.util.ArrayList;



/**
 * Class DatabaseHotel berfungsi sebagai database dari hotel
 * yang ada
 *
 * @author muflih fathan q
 * @version 05/20/2018
 */
public class DatabaseHotel {

    private static int LAST_HOTEL_ID;
    private static final String url = "jdbc:postgresql://localhost:5432/jhotel";
    private static final String userpass = "muflih";

    /**
     * Constructor for objects of class DatabaseHotel
     */
    public DatabaseHotel() {
        // initialise instance variables

    }

    /**
     * metode untuk mendapatkan seluruh hotel yang ada
     * @return hotel_database
     */
    public static ArrayList<Hotel> getHotelDatabase() {
         ArrayList<Hotel> HOTEL_DATABASE = new ArrayList<Hotel>();
        //ambil semua hotel yang ada pada database
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);
            Statement stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("select * from hotel;");

            while (rs.next()) {
                //mengolah data array sql
                String lokasiArray = rs.getString("lokasi");
                StringBuilder sb = new StringBuilder(lokasiArray);
                sb.deleteCharAt(16);
                sb.deleteCharAt(0);
                String lok = String.valueOf(sb);
                String[] lokasi = lok.split(",");

                //membuat objek hotel untuk dimasukkan kedalam array list
                Hotel getHotel = new Hotel(rs.getString("nama"), new Lokasi(Float.valueOf(lokasi[0]),
                        Float.valueOf(lokasi[1]), lokasi[2]), rs.getInt("bintang"));
                getHotel.setID(rs.getInt("id"));
                HOTEL_DATABASE.add(getHotel);
            }

            rs.close();
            stmt.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HOTEL_DATABASE;
    }

    /**
     * metode untuk mendapatkan id hotel terakhir pada database
     * @return
     */
    public int getLastHotelID() {
        int idDb = 0;
        //mengambil id hotel terakhir
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);
            Statement stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("select id from hotel order by id desc limit 1;");

            while (rs.next()) {
                idDb = rs.getInt("id");
            }

            rs.close();
            stmt.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idDb;//return LAST_HOTEL_ID;
    }


    /**
     * metoded untuk menambahkan hotel baru pada database
     *
     * @param baru baru merupakan hotel baru
     *             yang ingin ditambahkan
     */
    public static boolean addHotel(Hotel baru) throws HotelSudahAdaException {
        boolean sudahAda = false;
        //mengecek apakah hotel telah terdaftar
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);
            Statement stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT id from hotel");
            while (rs.next()) {

                if (baru.getID() == rs.getInt("id")) {
                    sudahAda = true;
                    throw new HotelSudahAdaException(baru);

                }
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //jika belum ada, tambahkan hotel
        if (!sudahAda) {
            LAST_HOTEL_ID = baru.getID();
            //insert customer database
            try {
                Class.forName("org.postgresql.Driver");
                Connection c = DriverManager.getConnection(url, userpass, userpass);
                //mnengolah objek lokasi untuk dimasukkan ke dalam bentuk array sql
                String[] lokasi = {String.valueOf(baru.getLokasi().getX()), String.valueOf(baru.getLokasi().getY()), baru.getLokasi().getDeskripsi()};
                Array lokasiArray = c.createArrayOf("text", lokasi);


                String query = "INSERT INTO hotel VALUES (?,?,?,?);";
                PreparedStatement stmt = c.prepareStatement(query);
                stmt.setInt(1, baru.getID());
                stmt.setString(2, baru.getNama());
                stmt.setArray(3, lokasiArray);
                stmt.setInt(4, baru.getBintang());

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
     * metode untuk mendapatkan hotel
     * @param id
     * @return hotel
     */
    public static Hotel getHotel(int id) {
        Hotel getHotel = new Hotel();
        //mengambil hotel pada database
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            PreparedStatement stmt = c.prepareStatement("select * from hotel where id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                //Mengolah array sql ke array java
                String lokasiArray = rs.getString("lokasi");
                StringBuilder sb = new StringBuilder(lokasiArray);
                sb.deleteCharAt(16);
                sb.deleteCharAt(0);
                String lok = String.valueOf(sb);
                String[] lokasi = lok.split(",");

                //membuat objek hotel untuk dikembalikan
                getHotel = new Hotel(rs.getString("nama"), new Lokasi(Float.valueOf(lokasi[0]),
                        Float.valueOf(lokasi[1]), lokasi[2]), rs.getInt("bintang"));
                getHotel.setID(rs.getInt("id"));

            }

            rs.close();
            stmt.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getHotel;
    }

    /**
     * metoded untuk menghapus hotel pada database
     * 
     * 
     * @param  id id dari hotel yang ingin dihapus
     * 
     */

    public static boolean removeHotel(int id) throws HotelTidakDitemukanException {
        //menghapus hotel yang ingin dihapus
        Boolean berhasilDelete = false;
        try {
            Class.forName("org.postgresql.Driver");

            Connection c = DriverManager.getConnection(url, userpass, userpass);
            PreparedStatement stmt = c.prepareStatement("delete from hotel where id = ?");
            stmt.setInt(1, id);
            int i = stmt.executeUpdate();
            if(i > 0){
                berhasilDelete = true;
            }

            stmt.close();
            c.close();
        } catch (SQLException e) {
            e.getErrorCode();
            e.printStackTrace();
            System.out.println("gagalselect");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return berhasilDelete;
    }
}


