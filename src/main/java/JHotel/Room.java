package JHotel;
/**
 * Class room berikut, memiliki fungsi sebagai penyimpanan data
 * terkait informasi-informasi seputar kamar pada hotel
 *
 * @author muflih fathan q
 * @version 05/20/2018
 */
public abstract class Room
{

    private Hotel hotel;
    private String nomor_kamar;
    public double dailyTariff;
    private StatusKamar status_kamar;

    

    /**
     * Constructor for objects of class Room
     *
     * @param hotel - hotel dimana kamar berada
     * @param nomor_kamar
     * @param status_kamar
     */
    public Room(Hotel hotel, String nomor_kamar,
                 StatusKamar status_kamar)
    {
        this.hotel = hotel;
        this.nomor_kamar = nomor_kamar;
        this.status_kamar = StatusKamar.Vacant;
        dailyTariff = 1000;
    }

    /**
     * Constructor for objects of class Room
     */
     public Room(){}

    /**
     * Metode untuk mengambil objek hotel
     * 
     * @return hotel objek hotel
     */
    public Hotel getHotel()
    {
        return hotel;
    }

    /**
     * Metode untuk mengambil nomor kamar pada hotel
     * 
     * @return nomor_kamar nomor kamar pada hotel
     */
    public String getNomorKamar()
    {
        return nomor_kamar;
    }

    /**
     * Metode untuk mengambil biaya harian yang berlaku pada
     * kamar
     * 
     * @return dailyTariff biaya harian kamar
     */
    public double getDailyTariff()
    {
        return dailyTariff;
    }
    
    /**
     * Metode untuk mengambil nilai status kamar
     * apakah booked, processed atau vacant
     * 
     * @return status_kamar merupakan enum dengan 3 pilihan diatas
     */
    public StatusKamar getStatusKamar()
    {
        return status_kamar;
    }
    
    /**
     * Metode untuk mengambil nilai dari tipe kamar
     * yang diimplementasikan pada class yang meng-extend room
     */
    public abstract TipeKamar getTipeKamar();

    /**
     * Metode untuk memasukkan objek hotel
     * 
     * @param hotel objek hotel
     */
    public void setHotel(Hotel hotel)
    {
        this.hotel = hotel;
    }

    /**
     * Metode untuk memasukkan nomor_kamar pada hotel
     * 
     * @param nomor_kamar nomor kamar pada hotel
     */
    public void setNomorKamar(String nomor_kamar)
    {
        this.nomor_kamar = nomor_kamar;
    }

    /**
     * Metode untuk memasukkan jumlah biaya pada kamar
     * 
     * @param dailyTariff biaya harian yang berlaku pada kamar
     */
    public void setDailyTariff(double dailyTariff)
    {
        this.dailyTariff = dailyTariff;
    }
    
    /**
     * Metode untuk memasukkan status kamar pada hotel
     * 
     * @param status_kamar enum terkait status kamar
     */
    public void setStatusKamar(StatusKamar status_kamar)
    {
       this.status_kamar = status_kamar;
    }



    /**
     * metode untuk mencetak data
     *
     * @return String pesan
     *
     */

    public String toString()
    {
        //DatabasePesanan.getPesanan(kamar);
        if(true)
       {
           return "\nNama Hotel   : "+hotel.getNama()+
                  "\nTipeKamar    : "+getTipeKamar()+
                  "\nHarga        : "+dailyTariff+
                  "\nStatus Kamar : "+status_kamar;
       }
       else
       {
            return "\nNama Hotel   : "+hotel.getNama()+
                   "\nTipeKamar    : "+getTipeKamar()+
                   "\nHarga        : "+dailyTariff+
                   "\nStatus Kamar : "+status_kamar+
                   "\nPelanggan    : ";
       }
    }
}
