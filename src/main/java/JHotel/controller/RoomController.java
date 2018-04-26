package JHotel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class RoomController {

    @RequestMapping("/vacantrooms")
    public ArrayList<Room> vacantRooms()
    {
        ArrayList<Room> RoomKosong = DatabaseRoom.getVacantRooms();
        return RoomKosong;
    }

    @RequestMapping("/room/{id_hotel}/{room_no}")
    public Room getRoom(@PathVariable("id_hotel")  int id_hotel,@PathVariable("room_no") String nomor_kamar)
    {

        Room room = DatabaseRoom.getRoom(DatabaseHotel.getHotel(id_hotel),nomor_kamar);
        return room;
    }
}
