package JHotel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class    RoomController {

    @RequestMapping("/vacantrooms")
    public ArrayList<Room> vacantRooms()
    {
        ArrayList<Room> RoomKosong = DatabaseRoom.getVacantRooms();
        return RoomKosong;
    }

    @RequestMapping("/allroom")
    public ArrayList<Room> listRoom(){
        ArrayList<Room> r = DatabaseRoom.getRoomDatabase();
        return r;
    }
    @RequestMapping("/room/{id_hotel}/{room_no}")
    public Room getRoom(@PathVariable("id_hotel")  int id_hotel,@PathVariable("room_no") String nomor_kamar)
    {

        Room room = DatabaseRoom.getRoom(DatabaseHotel.getHotel(id_hotel),nomor_kamar);
        return room;
    }

    @RequestMapping(value = "/hapusroom",method = RequestMethod.POST)
    public boolean hapusRoom(@RequestParam("nomor_kamar") String nomor_kamar,
                             @RequestParam("id_hotel") int id_hotel){
        Hotel h = DatabaseHotel.getHotel(id_hotel);
        try {
            if (DatabaseRoom.removeRoom(h,nomor_kamar)) {
                return true;
            }
        }
        catch (RoomTidakDitemukanException e){
            e.getPesan();
        }
        return false;

    }
}
