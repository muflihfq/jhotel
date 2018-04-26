package JHotel;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class HotelController {

    @RequestMapping("/hotel")
    public ArrayList<Hotel> hotelsList(){
        ArrayList<Hotel> h = DatabaseHotel.getHotelDatabase();
        return h;
    }

    @RequestMapping("/hotel/{id_hotel}")
    public Hotel getHotel(@PathVariable int id_hotel){
        Hotel h = DatabaseHotel.getHotel(id_hotel);
        return h;
    }

}
