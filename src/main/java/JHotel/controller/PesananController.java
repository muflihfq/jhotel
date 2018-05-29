package JHotel;

import java.util.ArrayList;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator;
import org.springframework.web.bind.annotation.*;

@RestController
public class PesananController {

    @RequestMapping("/pesanancustomer/{id_customer}")
    public Pesanan pesananCust(@PathVariable int id_customer)
    {
        Pesanan p = DatabasePesanan.getPesananAktif(DatabaseCustomer.getCustomer(id_customer));
        return p;
    }

    @RequestMapping("/pesanan/{id_pesanan}")
    public Pesanan getPesanan(@PathVariable  int id_pesanan)
    {
        Pesanan p = DatabasePesanan.getPesanan(id_pesanan);
        return p;
    }

    @RequestMapping(value = "/bookpesanan", method = RequestMethod.POST)
    public Pesanan bookPesanan(@RequestParam(value = "jumlah hari") int jumlah_hari,
                               @RequestParam(value = "id_customer") int id_customer,
                               @RequestParam(value = "id_hotel") int id_hotel,
                               @RequestParam(value = "nomor kamar") String nomor_kamar)
    {
        try {
            Pesanan p = new Pesanan(jumlah_hari, DatabaseCustomer.getCustomer(id_customer));
            System.out.println(id_customer);
            DatabasePesanan db = new DatabasePesanan();
            db.addPesanan(p,id_hotel,nomor_kamar);

            Administrasi.pesananDitugaskan(DatabasePesanan.getPesananAktif(DatabaseCustomer.getCustomer(id_customer)),
                    DatabaseRoom.getRoom(DatabaseHotel.getHotel(id_hotel),nomor_kamar));

            DatabaseRoom.setStatusKamar(nomor_kamar,StatusKamar.Processed);

            Date date = new Date();
            p.setTanggalPesan(date);

            return DatabasePesanan.getPesananAktif(DatabaseCustomer.getCustomer(id_customer));
        }
        catch (PesananSudahAdaException e)
        {
            e.getPesan();
        }
        return null;
    }
    @RequestMapping(value = "/cancelpesanan",method = RequestMethod.POST)
    public Pesanan cancelPesanan(@RequestParam(value = "id_pesanan") int id_pesanan)
    {
        Administrasi.pesananDibatalkan(DatabasePesanan.getPesanan(id_pesanan));
        DatabaseRoom.setStatusKamar(DatabasePesanan.getPesanan(id_pesanan).getRoom().getNomorKamar(),StatusKamar.Vacant);
        return DatabasePesanan.getPesanan(id_pesanan);
    }

    @RequestMapping(value = "/finishpesanan",method = RequestMethod.POST)
    public Pesanan  selesaikanPesanan(@RequestParam(value = "id_pesanan") int id_pesanan)
    {
        Administrasi.pesananSelesai(DatabasePesanan.getPesanan(id_pesanan));
        DatabaseRoom.setStatusKamar(DatabasePesanan.getPesanan(id_pesanan).getRoom().getNomorKamar(),StatusKamar.Booked);
        return DatabasePesanan.getPesanan(id_pesanan);
    }

    @RequestMapping("/allpesanan")
    public ArrayList<Pesanan> listPesanan(){
        ArrayList<Pesanan> p = DatabasePesanan.getPesananDatabase();
        return p;
    }

    @RequestMapping(value = "/hapuspesanan",method = RequestMethod.POST)
    public boolean hapusPesanan(@RequestParam("id_pesanan") int id_pesanan){

        Pesanan p = DatabasePesanan.getPesanan(id_pesanan);
        try {
            if (DatabasePesanan.removePesanan(p)) {
                return true;
            }
        }
        catch (PesananTidakDitemukanException e){
            e.getPesan();
        }
        return false;

    }

}
