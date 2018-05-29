package JHotel;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class HotelController {

    @RequestMapping("/allhotel")
    public ArrayList<Hotel> hotelsList(){
        ArrayList<Hotel> h = DatabaseHotel.getHotelDatabase();
        return h;
    }

    @RequestMapping("/hotel/{id_hotel}")
    public Hotel getHotel(@PathVariable int id_hotel){
        Hotel h = DatabaseHotel.getHotel(id_hotel);
        return h;
    }

    @RequestMapping(value = "/hapushotel",method = RequestMethod.POST)
    public boolean hapusHotel(@RequestParam("id_hotel") int id_hotel){

        try {
            if (DatabaseHotel.removeHotel(id_hotel)) {
                return true;
            }
        }
        catch (HotelTidakDitemukanException e){
            e.getPesan();
        }
        return false;

    }
}
