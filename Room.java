
/**
 * Class room berikut, memiliki fungsi sebagai penyimpanan data
 * terkait informasi-informasi seputar kamar pada hotel
 *
 * @author muflih fathan q
 * @version 03/10/2018
 */
public abstract class Room
{
    // instance variables - replace the example below with your own
    private Hotel hotel;
    //private int id;
    private String nomor_kamar;
    private boolean isAvailable;
    //private Customer customer;
    public double dailyTariff;
    private StatusKamar status_kamar;
    private Pesanan pesan;
    

    /**
     * Constructor for objects of class Room
     */
    public Room(Hotel hotel, String nomor_kamar,
                boolean isAvailable, StatusKamar status_kamar)
    {
        this.hotel = hotel;
        this.nomor_kamar = nomor_kamar;
        this.isAvailable = isAvailable;
        //this.customer = customer;
        this.dailyTariff = dailyTariff;
        this.status_kamar = status_kamar;
        
    }

    /**
     * Metode untuk mengambil nama hotel
     * 
     * @return hotel nama hotel
     */
    public Hotel getHotel()
    {
        // put your code here
        return hotel;
    }
    
    /*/**
     * Metode untuk mengambil id hotel
     * 
     * @return id id hotel
     *
    //public int getID()
    {
        return id;
    }
    */
   
   
   
    
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
     * Metode untuk mengambil status ketersediaan kamar
     * 
     * @return isAvailable status ketersediaan kamar
     */
    public boolean getStatusAvailable()
    {
        return isAvailable;
    }
    
    /*/**
     * Metode untuk mengambil nama atau informasi terkait pemesan
     * 
     * @return customer informasi terkait pemesan kamar
     
    public Customer getCustomer()
    {
        return customer;
        
    }
    */
    
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
     * Metode untuk mengambil info pesanan
     * 
     * @return pesan info terkait pesanan
     */
    public Pesanan getPesanan()
    {
        return pesan;
        
    }
    
    public abstract TipeKamar getTipeKamar();
    
        
    
    
    /**
     * Metode untuk memasukkan nama hotel
     * 
     * @param hotel nama hotel
     */
    public void setHotel(Hotel hotel)
    {
        this.hotel = hotel;
    
    }
    
    /*
     * Metode untuk memasukkan id hotel
     * 
     * @param id id hotel
     
    public void getID(int id)
    {
        this.id = id;
    }
    */
    
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
     * Metode untuk memasukkan status ketersediaan kamar
     * pada hotel
     * 
     * @param isAvailable status ketersediaan kamar
     */
    public void setStatusAvailable(boolean isAvailable)
    {
        this.isAvailable = isAvailable;
    }
    
    /*
     * Metode untuk memasukkan informasi terkait customerr
     * yang memesan kamar
     * 
     * @param customer info terkait customer
     
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
    */
    
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
     * Metode untuk memasukkan pesanan yang dipesan.
     * 
     * @param pesan info terkait pesanan yang dipesan oleh 
     * customer
     */
    public void setPesanan(Pesanan pesan)
    {
        this.pesan = pesan;
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
        System.out.println("Nama Hotel :" +hotel.getNama());
        System.out.println("Nomor Kamar :" +nomor_kamar);
        System.out.println("Status Ketersediaan :" +isAvailable);
        //System.out.println("Tipe Kamar :"/* +TipeKamar.*);
        System.out.println("Harga Kamar :" +dailyTariff);
        System.out.println("Status Kamar :" +status_kamar);
        
    }
    */
           
     
    
    
}
