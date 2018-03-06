
/**
 * Merupakan main class dari package j hotel
 *
 * @author muflih fathan q
 * @version 03/01/2018
 */
public class JHotel
{
    // instance variables - replace the example below with your own
    //private int x;
   
    //public
    
    /**
     * Constructor pada class JHotel
     */
    public JHotel()
    {
       
    }
  
    /**
     * metode untuk menjalankan package
     *
     * @param  args
     * 
     */
    public static void main(String[] args)
    {
        Customer cust = new Customer(99,"muflih");
        //cust.setID(99);
        //cust.setNama("muflih");
        Lokasi tempat = new Lokasi(23,45,"Depok");
        
        Hotel hotel = new Hotel("Hotel",tempat,4);
        
        Pesanan pesan = new Pesanan(10000,cust);
        cust.printData();
        tempat.printData();
        hotel.printData();
        
        cust.setNama("fathan");
        cust.printData();
        
        
        
    }
}
