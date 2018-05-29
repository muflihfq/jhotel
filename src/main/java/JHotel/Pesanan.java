package JHotel;
import java.util.Date;
import java.util.*;
import java.util.regex.*;
import java.text.*;

/**
 * Class pesanan berikut, berfungsikan untuk melakukan pemesanan
 * dengan memasukkan harga yang dipesan dan pemesannya.
 *
 * @author muflih fathan q
 * @version 05/20/2018
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
    private int id_hotel;


    /**
     * constructor untuk objek pada class pesanan
     *
     */
    public Pesanan(){}

    /**
     * constructor untuk objek pada class pesanan
     *
     * @param jumlahHari - jumlah hari pesanan
     * @param pelanggan - pelangggan yang memesan
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
      DatabaseRoom dbR = new DatabaseRoom();
      DatabaseHotel dbH = new DatabaseHotel();

      id_hotel = dbH.getLastHotelID();
      ArrayList<Room> HOTEL = dbR.getRoomsFromHotel(new DatabaseHotel().getHotel(id_hotel));
      if(HOTEL.get(0).getTipeKamar().equals(TipeKamar.Premium)){
        HOTEL.get(0).setDailyTariff(HOTEL.get(0).getDailyTariff());
          biaya = HOTEL.get(0).getDailyTariff() * jumlahHari;
    }
        else{
        biaya = HOTEL.get(0).getDailyTariff() * jumlahHari;
    }

    }

    
    /**
     * metode untuk mengambil id dari pesanan
     * @return id - nomor identifikasi pesanan
     * 
     */
    public int getID()
    {
        return id;
    }

    /**
     * metode untuk mengambil harga pesanan
     * @return biaya - biaya adalah harga
     *
     */
    public double getBiaya()
    {
         return biaya;
    }
    
     /**
     * metode untuk mengambil jumlah hari
     * @return JumlahHari -jumlah hari yang dipesan oleh pelanggan
     * 
     */
    public double getJumlahHari()
    {
         return jumlahHari;
    }
    
    /**
     * metode untuk mengambil objek pememsan
     *
     * @return pelanggan - pelanggan adalah orang yang memesan
     * 
     */
    public Customer getPelanggan()
    {
        return pelanggan;
    }

    /**
     * metode untuk mengambil status aktif dari pesanan
     *
     * @return isAktif - merupakan variabel yang mengindikasikan status pesanan
     *
     */
    public boolean getStatusAktif()
    {
        return isAktif;
    }

    /**
     * metode untuk mengambil status pemesan sedang diproses
     *
     * @retun isDiproses - isDiproses menyatakan bahwa pesanan telah selesai di proses
     * 
     */
    public boolean getStatusDiproses()
    {
        return isDiproses;
    }

    /**
     * metode untuk mengambil status pemesan telah selesai
     *
     * @retun isSelesai - isSelesai menyatakan bahwa pesanan telah selesai di proses
     * 
     */
    public boolean getStatusSelesai()
    {
        return isSelesai;
    }

    /**
     * metode untuk mengambil objek kamar yang dipesan
     *
     * @return kamar - kamar merupakan objek room yang dipesan
     * 
     * 
     */
    public Room getRoom()
    {
        return kamar;
    }
    
    /**
     * metode untuk mengambil tanggal pemesanan
     *
     * @return tanggalPesan
     *
     */
    public Date getTanggalPesan()
    {
        DateFormat gantiFormat = new SimpleDateFormat("'DOB : 'dd MMMM yyyy");
        String result = gantiFormat.format(tanggalPesan);
        
        System.out.println(result);
        return tanggalPesan;
    }

    /**
     * metode untuk memasukkan ID pesanan
     *
     * @param  id - nilai identifikasi pesanan
     */
    public void setID(int id)
    {
        this.id = id;
    }
    
    /**
     * metode untuk memasukkan harga pesanan
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
     * metode untuk memasukkan objek pelanggan yang memesan
     *
     * @param pelanggan 
     * pelanggan adalah nama pemesan atau customer
     * 
     */
    public void setPelanggan(Customer pelanggan)
    {
       this.pelanggan = pelanggan;
    }

    /**
     * metode untuk memasukkan status pesanan
     *
     * @param aktif - aktif menandakan pesanan aktif
     *
     */
    public void setStatusAktif(boolean aktif)
    {
        isAktif = aktif;
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
     * metode untuk memasukkan objek kamar pada pesanan
     *
     * @param kamar - kamar merupakan objek kamar yang telah dipesan
     */
    public void setRoom(Room kamar)
    {
        this.kamar = kamar;
    }

    /**
     * metode untuk memasukkan tanggal pemesanan
     *
     * @param tanggalPesan
     */
    public void setTanggalPesan(Date tanggalPesan)
    {
       this.tanggalPesan = tanggalPesan;
    }

    /**
     * metode untuk mencetak data
     *
     * @eturn String pesan
     *
     */

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
}
