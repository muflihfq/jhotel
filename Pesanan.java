import java.util.Date;
import java.util.*;
import java.util.regex.*;
import java.text.*;

/**
 * Class pesanan berikut, berfungsikan untuk melakukan pemesanan
 * dengan memasukkan harga yang dipesan dan pemesannya.
 *
 * @author muflih fathan q
 * @version 03/10/2018
 */
public class Pesanan
{
    // instance variables - replace the example below with your own
    private int id;
    private double biaya;
    private double jumlahHari;
    private Customer pelanggan;
    private boolean isAktif;
    //private String nama_pelanggan;
    //private TipeKamar tipe_kamar;
    private boolean isDiproses;
    private boolean isSelesai;
    private Room kamar;
    private Date tanggalPesan;
   

    
   
    /**
     * constructor untuk objek pada class pesanan
     *
     * @param biaya
     * @param pelanggan 
     * 
     */
    public Pesanan(double jumlahHari,Customer pelanggan)
    {
      this.jumlahHari = jumlahHari;
      this.pelanggan = pelanggan;
      isAktif = true;
      tanggalPesan = new Date();
      DatabasePesanan db = new DatabasePesanan();
      id = db.getLastPesananID() + 1;

      //this.kamar = kamar;
      //this.tanggalPesan = new Date(tahun, bulan,hari);
      
    }
    
//    public Pesanan(double jumlahHari,Customer pelanggan,Room kamar,Date tanggalPesan)
//    {
//      this.jumlahHari = jumlahHari;
//      this.pelanggan = pelanggan;
//      this.kamar = kamar;
//      this.tanggalPesan = tanggalPesan;
//
//
//      biaya = kamar.getDailyTariff() * jumlahHari;
//      }
    
    /**
     * metode untuk menampilkan harga pesanan
     * @return biaya - biaya adalah harga 
     * 
     */
    public int getID()
    {
        return id;
    }
    public double getBiaya()
    
    {
         return biaya;
        
    }
    
     /**
     * metode untuk menampilkan jumlah hari
     * @return JumlahHari
     * 
     */
    public double getJumlahHari()
    
    {
         return jumlahHari;
        
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

    public boolean getStatusAktif()
    {
        return isAktif;
    }
    
    /*
     * metode untuk menampilkan nama pelanggan
     *
     * @return nama_pelanggan - data terkait nama pelanggan
     * 
     
    public String getNamaPelanggan(){
     return nama_pelanggan;  
    }
    */
    
    /*
     * metode untuk menampilkan tipe kamar
     *
     * @return tipe_kamar
     * jenis kamar yang besangkutan
     * 
     
    public TipeKamar getTipeKamar()
    {
       return tipe_kamar;
    }
    */
    
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
     * metode untuk menampilkan kamar
     *
     * @return kamar
     * 
     * 
     */
    public Room getRoom()
    {
        return kamar;
    }
    
    /**
     * metode untuk menampilkan kamar
     *
     * @return kamar
     * 
     * 
     */
    public Date getTanggalPesan()
    {
        DateFormat gantiFormat = new SimpleDateFormat("'DOB : 'dd MMMM yyyy");
        String result = gantiFormat.format(tanggalPesan);
        
        
        System.out.println(result);
        return tanggalPesan;
        
      
    }

    public void setID(int id)
    {
        this.id = id;
    }
    
    /**
     * metode untuk memasukkan harga pesanan
     *
     * @param biaya - biaya adalah harga
     * 
     */
    public void setBiaya()
    {
       biaya = kamar.getDailyTariff() * jumlahHari;
       
       
    }
    
    
    /**
     * metode untuk memasukkan jumlah hari yang dipesan
     *
     * @param jumlahHari jumlah hari yang dipesan customer
     * 
     */
    public void setJumlahHari(double jumlahHari)
    {
       this.jumlahHari = jumlahHari;
    }
    
    /**
     * metode untuk memasukkan nama pemesan
     *
     * @param pelanggan 
     * pelanggan adalah nama pemesan atau customer
     * 
     */
    public void setPelanggan(Customer pelanggan)
    {
       this.pelanggan = pelanggan;
    }
    

    public void setStatusAktif(boolean aktif)
    {
        isAktif = aktif;
    }
    /*
     * metode untuk memasukkan nama pelanggan
     * 
     * @param nama_pelanggan 
     * data terkait nama pelanggan
     * 
    
    public void setNamaPelanggan(String nama_pelanggan)
    {
        this.nama_pelanggan = nama_pelanggan;
    }
     */
    
    /*
     * metode untuk memasukkan tipe kamar
     *
     * @param tipe kamar
     * data terkait jenis kamar
     * 
     
    public void setTipeKamar(TipeKamar tipe_kamar)
    {
        this.tipe_kamar = tipe_kamar;
    }
    */
    
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
     *
     * 
     */
    
    
    public void setRoom(Room kamar)
    {
        this.kamar = kamar;
    }
    
    /**
     * metode untuk memasukkan nama pemesan
     *
     * @param pelanggan 
     * pelanggan adalah nama pemesan atau customer
     * 
     */
    public void setTanggalPesan(Date tanggalPesan)
    {
       this.tanggalPesan = tanggalPesan;
       
    }
    
    public String toString()
    {
        String final_status = "KOSONG";
        
        if(isDiproses == true && isSelesai == false)
        {
            final_status = "DIPROSES";
        }
        
        else if (isDiproses == false && isSelesai == false)
        {
           final_status = "KOSONG";
        }
        
        else if(isDiproses == false && isSelesai == true)
        {
            final_status = "SELESAI";
        }


        if(kamar == null)
        {
            return "\nDibuat oleh " + pelanggan.getNama() +
                    " Status :" + final_status;
        }
        else
        {
            return "\nDibuat oleh " + pelanggan.getNama() +
                    " Status :" + final_status+
                    "\nNama Hotel :"+kamar.getHotel().getNama()+
                    "\nTipe Kamar :"+kamar.getTipeKamar()+
                    "\nNomor Kamar :"+kamar.getNomorKamar()+
                    "\nJumlah Hari :"+jumlahHari;
        }
    }
    
    /*
     * metode untuk mencetak data
     *
     * 
     * 
     *
    public void printData()
    {
        System.out.println("Nama Pelanggan :" +pelanggan.getNama());
        System.out.println("Jumlah Hari :" +jumlahHari);
        System.out.println("Status diproses :" +isDiproses);
        System.out.println("Status Selesai :" +isSelesai);
        
        System.out.println("Biaya :" +biaya);
        //System.out.println("Pelanggan :" +pelanggan);
        //System.out.println(
        //System.out.println isDiproses;
        //System.out.println isSelesai;
    }
    */
}
