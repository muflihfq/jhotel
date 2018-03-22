
/**
 * Class untuk menunjukkan lokasi atau tempat
 *
 * @author muflih fathan q
 * @version 03/10/2018
 */
public class Lokasi
{
    // instance variables - replace the example below with your own
    private float x_coord;
    private float y_coord;
    private String deskripsiLokasi;

    /**
     * Constructor untuk objek pada lokasi
     */
    public Lokasi(float x_coord,float y_coord,String deskripsiLokasi)
    {
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.deskripsiLokasi = deskripsiLokasi;
    }

    /**
     * metode untuk menampilkan koordinat x lokasi
     *
     * 
     * @return x_coord - koordinasi x lokasi
     */
    public float getX()
    {
        return x_coord;
    }
    
    /**
     * metode untuk menampilkan koordinat y lokasi
     *
     * 
     * @return y_coord - koordinasi y lokasi
     */
    public float getY()
    {
        return y_coord;
    }
    
    /**
     * metode untuk mengambil deskripsi dari lokasi
     * 
     * @return deskrispiLokasi berisi deskripsi lokasi terkait
     */
    public String getDeskripsi()
    {
        return deskripsiLokasi;
    }
    
    /**
     * metode untuk memasukkan koordinat x lokasi
     *
     * 
     * @param x_coord - koordinasi x lokasi
     */
    public void setX(float x_coord)
    {
        this.x_coord = x_coord;
    }
    
    /**
     * metode untuk memasukkan koordinat y lokasi
     *
     * 
     * @param y_coord - koordinasi y lokasi
     */

    public void setY(float y_coord)
    {
        this.y_coord = y_coord;
    }
    
    /**
     * metode untuk memasukkan data deskripsi dari lokasi
     * 
     * @param deskrispiLokasi berisi deskripsi lokasi terkait
     */
    public void setDeskripsi(String deskripsi)
    {
        deskripsiLokasi = deskripsi;
    }
    
    public String toString()
    {
        return "\nKoordinat X : "+x_coord+
               "\nKoordinat Y : "+y_coord+
               "\nDeskripsi   : "+deskripsiLokasi;
    }
    
    /*
     * metode untuk mencetak data
     * 
     
    public void printData()
    {
        System.out.println("X koordinat :" +x_coord);
        System.out.println("Y koordinat :" +y_coord);
        System.out.println("Deskripsi Lokasi :" +deskripsiLokasi);
        
    }
    */
    
}
