package JHotel;
import java.sql.*;
import java.util.ArrayList;

/**
 * Class DatabaseRoom berfungsi sebagai penyimpanan informasi
 * terkait kamar pada hotel
 *
 * @author muflih fathan q
 * @version 05/20/2018
 */
public class DatabaseRoom {


    private static final String url = "jdbc:postgresql://localhost:5432/jhotel";
    private static final String userpass = "muflih";


    /**
     * Constructor for objects of class DatabaseRoom
     */
    public DatabaseRoom() {
    }


    /**
     * metode untuk membuat kamar telah terisi customer
     *
     * @return room_database
     */
    public static ArrayList<Room> getRoomDatabase() {
        ArrayList<Room> ROOM_DATABASE = new ArrayList<Room>();
        Room getSingle;
        Room getDouble;
        Room getPremium;
        //mengambil semua room yang ada pada database termasuk hotel
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select distinct * from room,room_hotel,hotel " +
                    "where room.nomor_kamar = room_hotel.nomor_kamar and hotel.id = room_hotel.id_hotel;");

            while (rs.next()) {
                //mengolah array sql menjadi array string
                String lokasiArray = rs.getString("lokasi");
                StringBuilder sb = new StringBuilder(lokasiArray);
                sb.deleteCharAt(16);
                sb.deleteCharAt(0);
                String lok = String.valueOf(sb);
                String[] lokasi = lok.split(",");

                //membuat objek hotel
                Hotel getHotel = new Hotel(rs.getString("nama"), new Lokasi(Float.valueOf(lokasi[0]),
                        Float.valueOf(lokasi[1]), lokasi[2]), rs.getInt("bintang"));
                getHotel.setID(rs.getInt("id"));

                //membuat objek room berdasarkan tipe kamar dan status kamarnya
                //jika tipe single
                if (rs.getString("tipe_kamar").equals("Single")) {
                    //jika status booked
                    if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))) {

                        getSingle = new SingleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Booked);
                    }
                    //jika status vacant
                    else if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))) {
                        getSingle = new SingleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Vacant);
                    }
                    //jika status prosesed
                    else {
                        getSingle = new SingleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Processed);
                    }
                    //memasukkan ke database
                    ROOM_DATABASE.add(getSingle);
                }
                //jika tipe double
                else if (rs.getString("tipe_kamar").equals("Double")) {
                    //jika status booked
                    if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))) {

                        getDouble = new DoubleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Booked);
                    }
                    //jika status vacant
                    else if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))) {
                        getDouble = new DoubleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Vacant);
                    }
                    //jika status prosesed
                    else {
                        getDouble = new DoubleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Processed);
                    }
                    //memasukkan ke database
                    ROOM_DATABASE.add(getDouble);
                }
                //jika tipe premium
                else {
                    //jika status booked
                    if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))) {

                        getPremium = new PremiumRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Booked);
                    }
                    //jika status vacant
                    else if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))) {
                        getPremium = new PremiumRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Vacant);
                    }
                    //jika status prosesed
                    else {
                        getPremium = new PremiumRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Processed);
                    }
                    //memasukkan ke database
                    ROOM_DATABASE.add(getPremium);
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

        return ROOM_DATABASE;
    }

    /**
     * menambahkan kamar baru
     *
     * @param baru
     * @return
     * @throws RoomSudahAdaException
     */
    public static boolean addRoom(Room baru) throws RoomSudahAdaException {
        boolean sudahAda = false;
        //melakukan cek apakah kamar sudah ada
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            PreparedStatement stmt = c.prepareStatement("select * from room,room_hotel,hotel " +
                    "where hotel.id = ? and hotel.id = room_hotel.id_hotel and room_hotel.nomor_kamar = room.nomor_kamar;");

            stmt.setInt(1, baru.getHotel().getID());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                //mengilah array sql menjadi array String
                String lokasiArray = rs.getString("lokasi");
                StringBuilder sb = new StringBuilder(lokasiArray);
                sb.deleteCharAt(16);
                sb.deleteCharAt(0);
                String lok = String.valueOf(sb);
                String[] lokasi = lok.split(",");

                //membuat objek hotel
                Hotel getHotel = new Hotel(rs.getString("nama"), new Lokasi(Float.valueOf(lokasi[0]),
                        Float.valueOf(lokasi[1]), lokasi[2]), rs.getInt("bintang"));
                getHotel.setID(rs.getInt("id"));

                //jika kamar sudah ada, throw exception
                if (getHotel.equals(baru.getHotel()) && rs.getString("nomor_kamar").equals(baru.getNomorKamar())) {
                    sudahAda = true;
                    throw new RoomSudahAdaException(baru);
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //jika kamar belum ada
        if (!sudahAda) {

            //insert kamar ke database

            try {
                Class.forName("org.postgresql.Driver");
                Connection c = DriverManager.getConnection(url, userpass, userpass);

                String query = "INSERT INTO room VALUES (?,?,?,?);";
                PreparedStatement stmt = c.prepareStatement(query);
                stmt.setString(1, baru.getNomorKamar());
                stmt.setDouble(2, baru.getDailyTariff());
                stmt.setString(3, String.valueOf(baru.getStatusKamar()));
                stmt.setString(4, String.valueOf(baru.getTipeKamar()));

                stmt.executeUpdate();
                stmt.close();
                c.close();


            } catch (Exception e) {
                e.getMessage();
            }

            //memasukkan data ke tabel relasi room dan hotel
            try {
                Class.forName("org.postgresql.Driver");
                Connection c = DriverManager.getConnection(url, userpass, userpass);

                String query = "INSERT INTO room_hotel VALUES (?,?);";
                PreparedStatement stmt = c.prepareStatement(query);
                stmt.setInt(1, baru.getHotel().getID());
                stmt.setString(2, baru.getNomorKamar());

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
     * metode untuk mendapatkan kamar
     *
     * @param hotel
     * @param nomor_kamar
     */
    public static Room getRoom(Hotel hotel, String nomor_kamar) {
        Room getSingle = new SingleRoom();
        Room getDouble = new DoubleRoom();
        Room getPremium = new PremiumRoom();
        int intReturn = 0;

        //mencari kamar sekaligus hotel
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            PreparedStatement stmt = c.prepareStatement("select * from room,room_hotel,hotel " +
                    "where hotel.id = ? and hotel.id = room_hotel.id_hotel and room.nomor_kamar = ?");
            stmt.setInt(1, hotel.getID());
            stmt.setString(2, nomor_kamar);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                //mengolah array sql menjadi array string
                String lokasiArray = rs.getString("lokasi");
                StringBuilder sb = new StringBuilder(lokasiArray);
                sb.deleteCharAt(16);
                sb.deleteCharAt(0);
                String lok = String.valueOf(sb);
                String[] lokasi = lok.split(",");

                //membuat objek hotel
                Hotel getHotel = new Hotel(rs.getString("nama"), new Lokasi(Float.valueOf(lokasi[0]),
                        Float.valueOf(lokasi[1]), lokasi[2]), rs.getInt("bintang"));
                getHotel.setID(rs.getInt("id"));


                //membuat objek room dengan melihat status dan tipe kamar
                if (rs.getString("tipe_kamar").equals("Single")) {
                    intReturn = 1; //tipe kamar single

                    if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))) {

                        getSingle = new SingleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Booked);
                    } else if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))) {
                        getSingle = new SingleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Vacant);
                    } else {
                        getSingle = new SingleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Processed);
                    }
                } else if (rs.getString("tipe_kamar").equals("Double")) {
                    intReturn = 2; //tipe kamar double

                    if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))) {

                        getDouble = new DoubleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Booked);
                    } else if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))) {
                        getDouble = new DoubleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Vacant);
                    } else {
                        getDouble = new DoubleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Processed);
                    }

                } else {
                    intReturn = 3; //tipe kamar premium

                    if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))) {

                        getPremium = new PremiumRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Booked);
                    } else if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))) {
                        getPremium = new PremiumRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Vacant);
                    } else {
                        getPremium = new PremiumRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Processed);
                    }
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
        //melakukan return
        if (intReturn == 1) {
            return getSingle;
        } else if (intReturn == 2) {
            return getDouble;
        } else {
            return getPremium;
        }
    }

    /**
     * mendapatkan seluruh kamar pada hotel yang dicari
     *
     * @param hotel
     * @return array list hotel
     */
    public static ArrayList<Room> getRoomsFromHotel(Hotel hotel) {
        ArrayList<Room> GET_ROOM = new ArrayList<Room>();
        Room getSingle;
        Room getDouble;
        Room getPremium;
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            PreparedStatement stmt = c.prepareStatement("select * from room,room_hotel,hotel " +
                    "where hotel.id = ? and hotel.id = room_hotel.id_hotel and room_hotel.nomor_kamar = room.nomor_kamar");
            stmt.setInt(1, hotel.getID());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                //mengolah array sql ke array string
                String lokasiArray = rs.getString("lokasi");
                StringBuilder sb = new StringBuilder(lokasiArray);
                sb.deleteCharAt(16);
                sb.deleteCharAt(0);
                String lok = String.valueOf(sb);
                String[] lokasi = lok.split(",");

                //membuat objek hotel
                Hotel getHotel = new Hotel(rs.getString("nama"), new Lokasi(Float.valueOf(lokasi[0]),
                        Float.valueOf(lokasi[1]), lokasi[2]), rs.getInt("bintang"));
                getHotel.setID(rs.getInt("id"));

                //membuat objek room dan menambahkan ke dalam database
                if (rs.getString("tipe_kamar").equals("Single")) {

                    if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))) {

                        getSingle = new SingleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Booked);
                    } else if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))) {
                        getSingle = new SingleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Vacant);
                    } else {
                        getSingle = new SingleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Processed);
                    }
                    GET_ROOM.add(getSingle);
                } else if (rs.getString("tipe_kamar").equals("Double")) {

                    if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))) {

                        getDouble = new DoubleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Booked);
                    } else if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))) {
                        getDouble = new DoubleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Vacant);
                    } else {
                        getDouble = new DoubleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Processed);
                    }
                    GET_ROOM.add(getDouble);
                } else {

                    if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Booked))) {

                        getPremium = new PremiumRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Booked);
                    } else if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))) {
                        getPremium = new PremiumRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Vacant);
                    } else {
                        getPremium = new PremiumRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Processed);
                    }
                    GET_ROOM.add(getPremium);
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
        return GET_ROOM;
    }

    /**
     * metode untuk mendapatkan kamar yang kosong
     *
     * @return array list kamar kosong
     */
    public static ArrayList<Room> getVacantRooms() {
        ArrayList<Room> GET_VACANT = new ArrayList<Room>();
        Room getSingle;
        Room getDouble;
        Room getPremium;

        //mencari room yang kosng
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            Statement stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("select distinct * from room,room_hotel,hotel " +
                    "where room.nomor_kamar = room_hotel.nomor_kamar and hotel.id = room_hotel.id_hotel;");

            while (rs.next()) {
                //mengolah array sql
                String lokasiArray = rs.getString("lokasi");
                StringBuilder sb = new StringBuilder(lokasiArray);
                sb.deleteCharAt(16);
                sb.deleteCharAt(0);
                String lok = String.valueOf(sb);
                String[] lokasi = lok.split(",");

                //membuat objek hotel
                Hotel getHotel = new Hotel(rs.getString("nama"), new Lokasi(Float.valueOf(lokasi[0]),
                        Float.valueOf(lokasi[1]), lokasi[2]), rs.getInt("bintang"));
                getHotel.setID(rs.getInt("id"));

                //membuat objek kamar dan memasukkan ke dalam array list
                if (rs.getString("tipe_kamar").equals("Single")) {

                    if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))) {
                        getSingle = new SingleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Vacant);
                        GET_VACANT.add(getSingle);
                    }

                } else if (rs.getString("tipe_kamar").equals("Double")) {

                    if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))) {
                        getDouble = new DoubleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Vacant);
                        GET_VACANT.add(getDouble);
                    }


                } else {

                    if (rs.getString("status_kamar").equals(String.valueOf(StatusKamar.Vacant))) {
                        getPremium = new PremiumRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Vacant);
                        GET_VACANT.add(getPremium);
                    }


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
        return GET_VACANT;
    }

    /**
     * metode untuk menghapus kamar
     *
     * @param hotel
     * @param nomor_kamar
     * @return
     * @throws RoomTidakDitemukanException
     */
    public static boolean removeRoom(Hotel hotel, String nomor_kamar) throws RoomTidakDitemukanException {
        Boolean berhasilDelete = false;
        Boolean roomNull = false;
        try {
            //mencari kamar yang ingin dihapus
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            PreparedStatement stmt = c.prepareStatement("select * from room,room_hotel,hotel where " +
                    "room.nomor_kamar = ? and room.nomor_kamar = room_hotel.nomor_kamar and " +
                    "hotel.id = room_hotel.id_hotel;");
            stmt.setString(1, nomor_kamar);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                if (rs.wasNull()) {
                    //jika kamar tidak ditemukan, throw exception
                    roomNull = true;
                    throw new RoomTidakDitemukanException(hotel, nomor_kamar);

                } else {

                    //mengolah array sql
                    String lokasiArray = rs.getString("lokasi");
                    StringBuilder sb = new StringBuilder(lokasiArray);
                    sb.deleteCharAt(16);
                    sb.deleteCharAt(0);
                    String lok = String.valueOf(sb);
                    String[] lokasi = lok.split(",");

                    //membuat objek hotel
                    Hotel getHotel = new Hotel(rs.getString("nama"), new Lokasi(Float.valueOf(lokasi[0]),
                            Float.valueOf(lokasi[1]), lokasi[2]), rs.getInt("bintang"));
                    getHotel.setID(rs.getInt("id_hotel"));

                    //jika ditemukan, batalkan pesanan
                    if (getHotel.equals(hotel) && rs.getString(1).equals(nomor_kamar)) {
                        if (rs.getString("tipe_kamar").equals("Single")) {
                            Room getSingle = new SingleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Booked);
                            Administrasi.pesananDibatalkan(getSingle);
                        } else if (rs.getString("tipe_kamar").equals("Double")) {
                            Room getDouble = new DoubleRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Booked);
                            Administrasi.pesananDibatalkan(getDouble);
                        } else {
                            Room getPremium = new PremiumRoom(getHotel, rs.getString("nomor_kamar"), StatusKamar.Booked);
                            Administrasi.pesananDibatalkan(getPremium);
                        }
                        roomNull = false;
                    }


                }

                rs.close();
                stmt.close();
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //jika kamar telah dibatalkan
        if (!roomNull) {
            try {
                //menghapus room dan juga pesanan
                Class.forName("org.postgresql.Driver");
                Connection c = DriverManager.getConnection(url, userpass, userpass);

                PreparedStatement stmt = c.prepareStatement("delete from pesanan_room where nomor_kamar = ?");
                stmt.setString(1, nomor_kamar);
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

    public static boolean setStatusKamar(String nomor_kamar,StatusKamar status_kamar)
    {
        Boolean berhasilUpdate = false;
        //meng-update status kamar
        try {
            Connection c = DriverManager.getConnection(url, userpass, userpass);

            String query = "update room set status_kamar = ? where nomor_kamar = ?;";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1,String.valueOf(status_kamar));
            stmt.setString(2,nomor_kamar);

            int i = stmt.executeUpdate();
            if(i > 0){
                //berhasil
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