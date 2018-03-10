
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
     * @param  String[]
     * 
     */
    public static void main(String[] args)
    {
        Customer customer = new Customer(99,"muflih");
        Pesanan pesan = new Pesanan(10000,customer);
        Lokasi tempat = new Lokasi(23,45,"Depok");
        pesan.setTipeKamar(TipeKamar.Single);
        
        Hotel hotel = new Hotel("Hotel",tempat,4);
        StatusKamar status = StatusKamar.Vacant;
        
        Room kamar = new Room(hotel,"131",true,customer,100,
                             status);
                
        tempat.printData();
        customer.printData();
        hotel.printData();
        
        
        System.out.println("-----------------------------");
        System.out.println("Pesanan Ditugaskan");
        
        //method 1 pesanan ditugaskan
        Administrasi method1 = new Administrasi();
        method1.pesananDitugaskan(pesan,kamar);
        
        System.out.println("Room");
        kamar.printData();
        System.out.println("Pesanan");
        pesan.printData();
        
        System.out.println("-----------------------------");
        System.out.println("Pesanan Dibatalkan");
        
        //method 2 pesanan dibatalkan
        method1.pesananDibatalkan(kamar);
        System.out.println("Room");
        kamar.printData();
        System.out.println("Pesanan");
        pesan.printData();
        method1.pesananDitugaskan(pesan,kamar);
        
        
        System.out.println("-----------------------------");
        System.out.println("Pesanan Selesai");
        
        //method 3 pesanan selesai
        method1.pesananSelesai(kamar);
        System.out.println("Room");
        kamar.printData();
        System.out.println("Pesanan");
        pesan.printData();
        method1.pesananDitugaskan(pesan,kamar);
        
        System.out.println("-----------------------------");
        System.out.println("Pesanan Dibatalkan");
        //method 4 pesanan dibatalkan
        method1.pesananDibatalkan(pesan);
        System.out.println("Room");
        kamar.printData();
        System.out.println("Pesanan");
        pesan.printData();
        method1.pesananDitugaskan(pesan,kamar);
        
        System.out.println("-----------------------------");
        System.out.println("Pesanan Selesai");
        //method 5 pesanan selesai
        method1.pesananSelesai(pesan);
        System.out.println("Room");
        kamar.printData();
        System.out.println("Pesanan");
        pesan.printData();
        method1.pesananDitugaskan(pesan,kamar);
        
        
        
    }
}
