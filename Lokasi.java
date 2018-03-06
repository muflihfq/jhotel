
/**
 * Class untuk menunjukkan lokasi atau tempat
 *
 * @author muflih fathan q
 * @version 03/01/2018
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
    
    public String getDeskripsi()
    {
        return deskripsiLokasi;
    }
    
    public void setX(float x_coord)
    {
        this.x_coord = x_coord;
    }
    
    public void setY(float y_coord)
    {
        this.y_coord = y_coord;
    }
    
    public void setDeskripsi(String deskripsi)
    {
        deskripsiLokasi = deskripsi;
    }
    
    public void printData()
    {
        System.out.println("X koordinat :" +x_coord);
        System.out.println("Y koordinat :" +y_coord);
        System.out.println("Deskripsi :" +deskripsiLokasi);
        
    }
    
}
