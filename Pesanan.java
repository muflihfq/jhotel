
/**
 * Class pesanan berikut, berfungsikan untuk melakukan pemesanan
 * dengan memasukkan harga yang dipesan dan pemesannya.
 *
 * @author muflih fathan q
 * @version 03/01/2018
 */
public class Pesanan
{
    // instance variables - replace the example below with your own
    private double biaya;
    private Customer pelanggan;
    private String nama_pelanggan;
    private String jenis_kamar;
    private boolean isDiproses;
    private boolean isSelesai;
    private Room kamar;
   

    
   
    /**
     * constructor untuk objek pada class pesanan
     *
     * @param biaya
     * @param pelanggan 
     * 
     */
    public Pesanan(double biaya,Customer pelanggan)
      
   {
      this.biaya = biaya;
      this.pelanggan = pelanggan;
    }
    
   /**
     * metode untuk menampilkan nama pelanggan
     *
     * @return nama_pelanggan - data terkait nama pelanggan
     * 
     */
    public String getNama(){
     return nama_pelanggan;  
    }
    
    /**
     * metode untuk menampilkan harga pesanan
     * @return biaya - biaya adalah harga 
     * 
     */
    public double getBiaya()
    
    {
         return biaya;
        
    }
    
    /**
     * metode untuk menampilakan pemesan
     *
     * @return pelanggan - pelanggan adalah orang yang memesan
     * 
     */
    public Customer getPelanggan()
    {
        return pelanggan;
    
    }
    
    /**
     * metode untuk menampilkan status pemesan sedang diproses
     *
     * @retun isDiproses - isDiproses menyatakan bahwa pesanan telah selesai di proses
     * 
     */
    public boolean getStatusDiproses()
    {
        return isDiproses;
    
    }
    
    public Room getRoom()
    {
        return kamar;
    }
    
    
    /**
     * metode untuk menampilkan status pemesan telah selesai
     *
     * @retun isSelesai - isSelesai menyatakan bahwa pesanan telah selesai di proses
     * 
     */
    public boolean getStatusSelesai()
    {
        return isSelesai;
    
    }
    
    /**
     * metode untuk memasukkan harga pesanan
     *
     * @param biaya - biaya adalah harga
     * 
     */
    public void setBiaya(double biaya)
    {
       this.biaya = biaya;
    }
    
    /**
     * metode untuk memasukkan nama pemesan
     *
     * @param baru - baru adalah nama pemesan atau customer
     * 
     */
    public void setPelanggan(Customer baru)
    {
       baru = pelanggan;
    }
    
    /**
     * metode untuk memasukkan status pesanan
     *
     * @param diproses - diproses menandakan pesanan sedang diproses
     * 
     */
    public void setStatusDiproses(boolean diproses)
    {
        isDiproses = diproses;
    }
    
    /**
     * metode untuk memasukkan status pesanan
     *
     * @param diproses - diproses menandakan pesanan sedang diproses
     * 
     */
    public void setStatusSelesai(boolean diproses)
    {
        isSelesai = diproses; 
    }
    
    /**
     * metode untuk memasukkan nama pelanggan
     *
     * @param nama - nama adalah nama pelanggan
     * 
     */
    public String setNama(String nama)
    {
        this.nama_pelanggan = nama_pelanggan;
        return nama_pelanggan;
    }
    
    public void setRoom(Room kamar)
    {
        this.kamar = kamar;
    }
    
    /**
     * metode untuk mencetak data
     *
     * 
     * 
     */
    public void printData()
    {
         System.out.println("Biaya :" +biaya);
         System.out.println("Pelanggan :" +pelanggan);
         System.out.println("nama pelanggan" +nama_pelanggan);
         System.out.println("Jenis Kamar :" +jenis_kamar);
         //System.out.println isDiproses;
         //System.out.println isSelesai;
    }
}
