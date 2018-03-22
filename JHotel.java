import java.util.*;
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
        
        
     Customer cust = new Customer(12, "luffy", new GregorianCalendar(2018,2,20).getTime());
     //Pesanan pesan = new   //cust.getDOB();
        
        Lokasi tempat = new Lokasi(23,45,"Depok");    
        Hotel rumah = new Hotel("Hotel",tempat,4);
        Room single = new SingleRoom(rumah,"23",true,StatusKamar.Vacant);
        Pesanan pesan = new Pesanan(3,cust,single,new GregorianCalendar(2018,2,20).getTime());
       
        System.out.println("Customer-----------");
        System.out.println(cust.toString());
        System.out.println("Pesanan-----------");
        System.out.println(pesan.toString());
        
        System.out.println("\n\ntoString kelas Room untuk kondisi pertama : \n\n");
        System.out.println(single.toString());
        single.setStatusAvailable(false);
        System.out.println("\n\ntoString kelas Room untuk kondisi kedua   : \n\n");
        System.out.println(single.toString());
        
        pesan.setStatusDiproses(true);
        pesan.setStatusSelesai(false);
        
        System.out.println("\n\ntoString kelas Pesanan untuk kondisi pertama : \n\n");
        System.out.println(pesan.toString());

        pesan.setStatusDiproses(false);
        pesan.setStatusSelesai(false);

        System.out.println("\n\ntoString kelas Pesanan untuk kondisi kedua   : \n\n");
        System.out.println(pesan.toString());
        
        pesan.setStatusDiproses(false);
        pesan.setStatusSelesai(true);

        System.out.println("\n\ntoString kelas Pesanan untuk kondisi ketiga   : \n\n");
        System.out.println(pesan.toString());
        
        /*L;
        Customer customer = new Customer(99,"muflih");
        
        Pesanan pesan = new Pesanan(4,customer,signle);
        signle.setDailyTariff(3400);
        System.out.println("----------Pesanan Single--------");
        tempat.printData();
        customer.printData();
        rumah.printData();
        
        System.out.println("Hasil Pesananan");
        Administrasi method1 = new Administrasi();
        method1.pesananDitugaskan(pesan,signle);
        pesan.printData();
        signle.printData();
        
        
        System.out.println(signle instanceof SingleRoom);
        Room doble = new DoubleRoom(rumah,"24",true,StatusKamar.Vacant);
        
        pesan.setJumlahHari(2);
        pesan.setPelanggan(customer);
        pesan.setRoom(doble);
        
        System.out.println("----------Pesanan Double--------");
        
        tempat.printData();
        customer.printData();
        rumah.printData();
        
        Administrasi method2 = new Administrasi();
        method2.pesananDitugaskan(pesan,doble);
        System.out.println("Hasil Pesananan");
        pesan.printData();
        doble.printData();
        System.out.println(doble instanceof DoubleRoom);
        
        /*
         * 
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
        
        */
        
    }
}
