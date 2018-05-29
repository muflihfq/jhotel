package JHotel;
import java.sql.*;
import java.util.ArrayList;

/**
 * class ini berfungsi sebagai database
 * atau penyimpanan data terkait pemesanan
 *
 * @author muflih fathan q
 * @version 05/20/2018
 */
public class DatabasePesanan
{

    private static int LAST_PESANAN_ID = 0;
    private static final String url  = "jdbc:postgresql://localhost:5432/jhotel";
    private static final String userpass = "muflih";


    /**
     * Constructor untuk objek pada class DatabasePesanan
     */
    public DatabasePesanan(){}

    /**
     * metode untuk mengambil semua pesanan pada database
     * @return
     */
    public static ArrayList<Pesanan> getPesananDatabase()
    {
       ArrayList<Pesanan> PESANAN_DATABASE = new ArrayList<Pesanan>();
       Room getRoom; Customer getCustomer; Pesanan getPesanan;
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            //mengambil semua pesanan pada database
            Statement stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("select * from pesanan,pesanan_room_hotel,room,pesanan_customer,customer,hotel" +
                    " where pesanan_room_hotel.id_pesanan = pesanan.id and room.nomor_kamar = pesanan_room_hotel.nomor_kamar" +
                    " and customer.id = pesanan_customer.id_customer and pesanan_customer.id_pesanan = pesanan.id" +
                    " and hotel.id = pesanan_room_hotel.id_hotel;");

            while(rs.next()){
                //mengolah array sql menjadi array string
                String lokasiArray = rs.getString("lokasi");
                StringBuilder sb = new StringBuilder(lokasiArray);
                sb.deleteCharAt(16);
                sb.deleteCharAt(0);
                String lok = String.valueOf(sb);
                String[] lokasi = lok.split(",");

                //membuat objek customer, pesanan, hotel dan kamar
                getCustomer = new Customer(rs.getString(18),Date.valueOf(rs.getString("dob")),
                        rs.getString("email"),rs.getString("password"));
                getCustomer.setID(rs.getInt("id_customer"));
                Hotel getHotel = new Hotel(rs.getString(23), new Lokasi(Float.valueOf(lokasi[0]),
                        Float.valueOf(lokasi[1]), lokasi[2]), rs.getInt("bintang"));
                getHotel.setID(rs.getInt("id_hotel"));
                getPesanan = new Pesanan(rs.getDouble("jumlah_hari"),getCustomer);
                getPesanan.setID(rs.getInt("id_pesanan"));
                if(rs.getString("tipe_kamar").equals("Single")){

                    if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))){

                        getRoom = new SingleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Booked);
                    }
                    else if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))){
                        getRoom = new SingleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Vacant);
                    }
                    else{
                        getRoom = new SingleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Processed);
                    }

                }
                else if(rs.getString("tipe_kamar").equals("Double")){

                    if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))){

                        getRoom = new DoubleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Booked);
                    }
                    else if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))){
                        getRoom = new DoubleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Vacant);
                    }
                    else{
                        getRoom = new DoubleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Processed);
                    }

                }
                else {

                    if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))){

                        getRoom = new PremiumRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Booked);
                    }
                    else if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))){
                        getRoom = new PremiumRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Vacant);
                    }
                    else{
                        getRoom = new PremiumRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Processed);
                    }

                }
                //melakukan set kamar dan status berdasarkan database
                getPesanan.setRoom(getRoom);
                getPesanan.setStatusSelesai(rs.getBoolean("status_selesai"));
                getPesanan.setStatusAktif(rs.getBoolean("status_aktif"));
                getPesanan.setStatusDiproses(rs.getBoolean("status_proses"));
                PESANAN_DATABASE.add(getPesanan);
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
        return PESANAN_DATABASE;
    }

    /**
     * metode untuk mendapatkan id pesanan terakhir
     * @return id pesanan
     */
    public static int getLastPesananID()
    {
        int idDb = 0;
        //mengambil id pesanan terakhir
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select id from pesanan order by id desc limit 1;");

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
     * metode untuk menambah pesanan ke database
     *
     * @param  baru
     * 
     */
    public static boolean addPesanan(Pesanan baru,int id_hotel,String nomorkamar) throws PesananSudahAdaException {
        boolean sudahAda = false;
        //mencari apakah pesanan sudah ada
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select email,status_aktif from customer,pesanan,pesanan_customer " +
                    "where pesanan.id = pesanan_customer.id_pesanan and customer.id = pesanan_customer.id_customer;"
            );

            while (rs.next()) {
                if (rs.getString("email").equals(baru.getPelanggan().getEmail()) && rs.getBoolean(2))

                {
                    //jika sudah ada throw exception
                    sudahAda = true;
                    throw new PesananSudahAdaException(baru);
                }
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //jika pesanan belum ada, memasukkan pesanan baru
        if (!sudahAda) {

            LAST_PESANAN_ID = baru.getID();

            //mengolah array sql ke data sql
            java.util.Date utilDate = baru.getTanggalPesan();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            try {
                //insert pesaanan ke database
                Connection c = DriverManager.getConnection(url, userpass, userpass);

                String query = "INSERT INTO pesanan VALUES (?,?,?,?,true ,false,true);";
                PreparedStatement stmt = c.prepareStatement(query);
                stmt.setInt(1, baru.getID());
                stmt.setDouble(2, baru.getBiaya());
                stmt.setDouble(3, baru.getJumlahHari());
                stmt.setDate(4, sqlDate);

                stmt.executeUpdate();
                stmt.close();
                c.close();
            } catch (Exception e) {
                e.getMessage();
            }

            //memasukkan data ke dalam tabel relasi pesanan_customer
            try {
                Connection c = DriverManager.getConnection(url, userpass, userpass);
                String query = "INSERT INTO pesanan_customer VALUES (?,?);";
                PreparedStatement stmt = c.prepareStatement(query);
                stmt.setInt(1, baru.getID());
                stmt.setInt(2, baru.getPelanggan().getID());

                stmt.executeUpdate();
                stmt.close();
                c.close();

            } catch (Exception e) {
                e.getMessage();
            }
            try {
                //memasukkan data ke dalam tabel relasi pesanan_room
                Connection c = DriverManager.getConnection(url, userpass, userpass);

                String query = "INSERT INTO pesanan_room VALUES (?,?);";
                PreparedStatement stmt = c.prepareStatement(query);
                stmt.setInt(1, baru.getID());
                stmt.setString(2, nomorkamar);

                stmt.executeUpdate();
                stmt.close();
                c.close();

            } catch (Exception e) {
                e.getMessage();
            }

            try {
                //memasukkan data ke dalam tabel relasi pesanan_room_hotel
                Connection c = DriverManager.getConnection(url, userpass, userpass);

                String query = "INSERT INTO pesanan_room_hotel VALUES (?,?,?);";
                PreparedStatement stmt = c.prepareStatement(query);
                stmt.setInt(1, baru.getID());
                stmt.setString(2, nomorkamar);
                stmt.setInt(3, id_hotel);

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
     * metode untuk menghapus pesanan ke database
     *
     * @param  pesan
     * 
     */
    public static boolean removePesanan(Pesanan pesan) throws PesananTidakDitemukanException
    {
        Customer getCustomer;
        Pesanan getPesanan;
        Boolean pesananNull = false;
        Boolean berhasilDelete = false;
        //mencari pesanan yang ingin dihapus
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            PreparedStatement stmt = c.prepareStatement("select * from pesanan,pesanan_room,room,pesanan_customer,customer " +
                    "where pesanan.id = ? and pesanan_room.id_pesanan = pesanan.id and room.nomor_kamar = pesanan_room.nomor_kamar " +
                    "and customer.id = pesanan_customer.id_customer and pesanan_customer.id_pesanan = pesanan.id;");
            stmt.setInt(1,pesan.getID());
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                if(rs.wasNull())
                {   //jika pesanan tidak ditemukan, throw exception
                    pesananNull = true;
                    throw new PesananTidakDitemukanException(pesan);
                }
                else{
                    //membuat objek customer dan pesanan
                    getCustomer = new Customer(rs.getString("nama"),Date.valueOf(rs.getString("dob")),
                            rs.getString("email"),rs.getString("password"));
                    getCustomer.setID(rs.getInt("id_customer"));

                    getPesanan = new Pesanan(rs.getDouble("jumlah_hari"),getCustomer);
                    getPesanan.setID(rs.getInt("id_pesanan"));

                    if(rs.getString(9) != null){
                        //memabatalkan pesanan
                        Administrasi.pesananDibatalkan(getPesanan);
                    }
                    else {
                        if (getPesanan.getStatusAktif()) {
                            getPesanan.setStatusAktif(false);
                        }
                    }
                    pesananNull = false;
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
        //pesanan pada database siap dihapus
        if(!pesananNull) {
            //mengapus pesanan pada database
            try {
                Class.forName("org.postgresql.Driver");
                Connection c = DriverManager.getConnection(url, userpass, userpass);

                PreparedStatement stmt = c.prepareStatement("delete from pesanan where id = ?");
                stmt.setInt(1, pesan.getID());
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

        }
        return berhasilDelete;
    }


    /**
     * metode untuk mengambil data pesanan
     *
     * @param
     * 
     */
    public static Pesanan getPesanan(int id)
    {
        Pesanan getPesanan = new Pesanan();
        Customer getCustomer;
        Room getRoom;

        //mengambil pesanan yang dicari, termasuk pelanggan, kamar dan hotelnya
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            PreparedStatement stmt = c.prepareStatement("select * from pesanan,pesanan_room_hotel,room,pesanan_customer,customer,hotel " +
                    "where pesanan.id = ? and pesanan_room_hotel.id_pesanan = pesanan.id and room.nomor_kamar = pesanan_room_hotel.nomor_kamar "+
                    "and customer.id = pesanan_customer.id_customer and pesanan_customer.id_pesanan = pesanan.id " +
                    "and hotel.id = pesanan_room_hotel.id_hotel;");
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                //mengolah array sql menjadi array string
                String lokasiArray = rs.getString("lokasi");
                StringBuilder sb = new StringBuilder(lokasiArray);
                sb.deleteCharAt(16);
                sb.deleteCharAt(0);
                String lok = String.valueOf(sb);
                String[] lokasi = lok.split(",");

                //membuat objek customer, pesanan, hotel dan kamar
                getCustomer = new Customer(rs.getString(18),Date.valueOf(rs.getString("dob")),
                        rs.getString("email"),rs.getString("password"));
                getCustomer.setID(rs.getInt("id_customer"));
                Hotel getHotel = new Hotel(rs.getString(23), new Lokasi(Float.valueOf(lokasi[0]),
                        Float.valueOf(lokasi[1]), lokasi[2]), rs.getInt("bintang"));
                getHotel.setID(rs.getInt("id_hotel"));
                getPesanan = new Pesanan(rs.getDouble("jumlah_hari"),getCustomer);
                getPesanan.setID(rs.getInt("id_pesanan"));
                if(rs.getString("tipe_kamar").equals("Single")){

                    if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))){

                        getRoom = new SingleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Booked);
                    }
                    else if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))){
                        getRoom = new SingleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Vacant);
                    }
                    else{
                        getRoom = new SingleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Processed);
                    }

                }
                else if(rs.getString("tipe_kamar").equals("Double")){

                    if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))){

                        getRoom = new DoubleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Booked);
                    }
                    else if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))){
                        getRoom = new DoubleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Vacant);
                    }
                    else{
                        getRoom = new DoubleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Processed);
                    }

                }
                else {

                    if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))){

                        getRoom = new PremiumRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Booked);
                    }
                    else if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))){
                        getRoom = new PremiumRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Vacant);
                    }
                    else{
                        getRoom = new PremiumRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Processed);
                    }

                }
                //melakukan set kamar dan status berdasarkan database
               getPesanan.setRoom(getRoom);
                getPesanan.setStatusSelesai(rs.getBoolean("status_selesai"));
                getPesanan.setStatusAktif(rs.getBoolean("status_aktif"));
                getPesanan.setStatusDiproses(rs.getBoolean("status_proses"));

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
        return getPesanan;



    }

    /**
     * metode untuk mencari pesanan yang aktif dari kamar
     * @param kamar
     * @return
     */
    public static Pesanan getPesananAktif(Room kamar)
    {
        Pesanan getPesanan = new Pesanan();
        Customer getCustomer = new Customer();
        Room getRoom;

        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            PreparedStatement stmt = c.prepareStatement("select * from pesanan,pesanan_room_hotel,room,pesanan_customer,customer,hotel " +
                    "where room.nomor_kamar = ? and pesanan_room_hotel.id_pesanan = pesanan.id and room.nomor_kamar = pesanan_room_hotel.nomor_kamar "+
                    "and customer.id = pesanan_customer.id_customer and pesanan_customer.id_pesanan = pesanan.id " +
                    "and hotel.id = pesanan_room_hotel.id_hotel;");
            stmt.setString(1,kamar.getNomorKamar());
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                String lokasiArray = rs.getString("lokasi");
                StringBuilder sb = new StringBuilder(lokasiArray);
                sb.deleteCharAt(16);
                sb.deleteCharAt(0);
                String lok = String.valueOf(sb);
                String[] lokasi = lok.split(",");

                getCustomer = new Customer(rs.getString(18),Date.valueOf(rs.getString("dob")),
                        rs.getString("email"),rs.getString("password"));
                getCustomer.setID(rs.getInt("id_customer"));

                Hotel getHotel = new Hotel(rs.getString(23), new Lokasi(Float.valueOf(lokasi[0]),
                        Float.valueOf(lokasi[1]), lokasi[2]), rs.getInt("bintang"));
                getHotel.setID(rs.getInt("id_hotel"));

                getPesanan = new Pesanan(rs.getDouble("jumlah_hari"),getCustomer);
                getPesanan.setID(rs.getInt("id_pesanan"));

                if(rs.getString("tipe_kamar").equals("Single")){

                    if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))){

                        getRoom = new SingleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Booked);
                    }
                    else if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))){
                        getRoom = new SingleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Vacant);
                    }
                    else{
                        getRoom = new SingleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Processed);
                    }

                }
                else if(rs.getString("tipe_kamar").equals("Double")){

                    if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))){

                        getRoom = new DoubleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Booked);
                    }
                    else if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))){
                        getRoom = new DoubleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Vacant);
                    }
                    else{
                        getRoom = new DoubleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Processed);
                    }

                }
                else {

                    if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))){

                        getRoom = new PremiumRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Booked);
                    }
                    else if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))){
                        getRoom = new PremiumRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Vacant);
                    }
                    else{
                        getRoom = new PremiumRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Processed);
                    }

                }
                getPesanan.setRoom(getRoom);
                getPesanan.setStatusSelesai(rs.getBoolean("status_selesai"));
                getPesanan.setStatusAktif(rs.getBoolean("status_aktif"));
                getPesanan.setStatusDiproses(rs.getBoolean("status_proses"));

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
        return getPesanan;
    }

    /**
     * metode untuk mengambil pesanan aktid dari pelanggan
      * @param pelanggan
     * @return
     */
    public static Pesanan getPesananAktif(Customer pelanggan)
    {
        Pesanan getPesanan = new Pesanan();
        Customer getCustomer;
        Room getRoom;

        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            PreparedStatement stmt = c.prepareStatement("select * from pesanan,pesanan_room_hotel,room,pesanan_customer,customer,hotel " +
                    "where customer.id = ? and pesanan_room_hotel.id_pesanan = pesanan.id and room.nomor_kamar = pesanan_room_hotel.nomor_kamar "+
                    "and customer.id = pesanan_customer.id_customer and pesanan_customer.id_pesanan = pesanan.id " +
                    "and hotel.id = pesanan_room_hotel.id_hotel;");
            stmt.setInt(1,pelanggan.getID());
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String lokasiArray = rs.getString("lokasi");
                StringBuilder sb = new StringBuilder(lokasiArray);
                sb.deleteCharAt(16);
                sb.deleteCharAt(0);
                String lok = String.valueOf(sb);
                String[] lokasi = lok.split(",");

                getCustomer = new Customer(rs.getString(18),Date.valueOf(rs.getString("dob")),
                        rs.getString("email"),rs.getString("password"));
                getCustomer.setID(rs.getInt("id_customer"));

                Hotel getHotel = new Hotel(rs.getString(23), new Lokasi(Float.valueOf(lokasi[0]),
                        Float.valueOf(lokasi[1]), lokasi[2]), rs.getInt("bintang"));
                getHotel.setID(rs.getInt("id_hotel"));

                getPesanan = new Pesanan(rs.getDouble("jumlah_hari"),getCustomer);
                getPesanan.setID(rs.getInt("id_pesanan"));

                if(rs.getString("tipe_kamar").equals("Single")){

                    if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))){

                        getRoom = new SingleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Booked);
                    }
                    else if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))){
                        getRoom = new SingleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Vacant);
                    }
                    else{
                        getRoom = new SingleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Processed);
                    }

                }
                else if(rs.getString("tipe_kamar").equals("Double")){

                    if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))){

                        getRoom = new DoubleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Booked);
                    }
                    else if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))){
                        getRoom = new DoubleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Vacant);
                    }
                    else{
                        getRoom = new DoubleRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Processed);
                    }

                }
                else {

                    if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))){

                        getRoom = new PremiumRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Booked);
                    }
                    else if(rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))){
                        getRoom = new PremiumRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Vacant);
                    }
                    else{
                        getRoom = new PremiumRoom(getHotel,rs.getString("nomor_kamar"),StatusKamar.Processed);
                    }

                }
                getPesanan.setRoom(getRoom);
                getPesanan.setStatusSelesai(rs.getBoolean("status_selesai"));
                getPesanan.setStatusAktif(rs.getBoolean("status_aktif"));
                getPesanan.setStatusDiproses(rs.getBoolean("status_proses"));

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
        return getPesanan;

    }

    /**
     * metode untuk memasukkan status pesaanan
     * @param id
     * @param aktif
     * @param selesai
     * @param proses
     */
    public static boolean setStatusPesanan(int id,boolean aktif, boolean selesai, boolean proses)
    {
        Boolean berhasilUpdate = false;
        //update status pesanan
        try {
            Connection c = DriverManager.getConnection(url, userpass, userpass);
            String query = "update pesanan set status_aktif = ?,status_selesai = ?,status_proses = ?" +
                    "where id = ?;";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setBoolean(1,aktif);
            stmt.setBoolean(2,selesai);
            stmt.setBoolean(3,proses);
            stmt.setInt(4,id);

            int i = stmt.executeUpdate();
            if(i > 0){
                berhasilUpdate = true;
            }
            stmt.close();
            c.close();


        } catch (Exception e) {
            e.getMessage();
        }
        return berhasilUpdate;
    }
}
