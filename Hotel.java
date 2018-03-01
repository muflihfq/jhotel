
/**
 * Class hotel berikut berfungsi untuk mendapatkan dan memasukkan
 * data-data hotel.
 *
 * @author muflih fathan q
 * @version 03/01/2018
 */
public class Hotel
{
    // instance variables - replace the example below with your own
    private String nama;
    private Lokasi lokasi;
    private int bintang;

    /**
     * konstruktor pada class hotel
     * @param nama, lokasi, bintang
     */
    public Hotel(String nama, Lokasi lokasi, int bintang)
    {
        this.nama = nama;
        this.lokasi = lokasi;
        this.bintang = bintang;
    }

    /**
     * metode ini berfungsi untuk mengambil nilai/data
     * terkait kualitas hotel (dengan skala bintang)
     *
     * @return bintang - bintang merupakan instance variabel yang menunjukkan kualitas hotel.
     */
    public int getBintang()
    {
        return bintang;
    }
    
    /**
     * metode ini berfungsi untuk mengambil nilai/data
     * nama hotel
     *
     * @return nama - nama merupakan variabel yang menunjukkan nama hotel
     */
    public String getNama()
    {
        return nama;
    }
    
    /**
     * metode ini berfungsi untuk mengambil nilai/data
     * terkait lokasi hotel
     *
     * @return lokasi
     * lokasi merupakan variabel yang menunjukkan lokasi hotel berada
     * lokasi merupakan data yang berhubungan dengan kelas lokasi
     */
    public Lokasi getLokasi()
    {
        return lokasi;
    }
    
    /**
     * metode ini berfungsi untuk menginput nama hotel
     *
     * @param nama - nama hotel
     */
    public void setNama(String nama)
    {
        this.nama = nama;
    }
    
    /**
     * metode ini berfungsi untuk menginput lokasi hotel
     *
     * @param lokasi - lokasi hotel,yang terkait dengan kelas lokasi
     *                  sehingga lokasi pada kelas harus didefinisikan terlebih dahulu
     */
    public void setLokasi (Lokasi lokasi)
    {
        this.lokasi = lokasi;
    }
    
    /**
     * metode ini berfungsi untuk menginput kualitas hotel
     *
     * @param bintang - kualitas hotel (dengan angka/jumlah)
     */
    public void setBintang(int bintang)
    {
        this.bintang = bintang;
    }
    
    /**
     * metode ini berfungsi untuk mencetak data
     *
     * 
     */
    public void printData()
    {
        
    }
    
    
}
