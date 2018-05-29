package JHotel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import jdk.nashorn.internal.ir.ObjectNode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class CustomerController {

    @RequestMapping("/")
    public String indexPage(@RequestParam(value="name", defaultValue="world") String name) {
        return "Hello " + name;
    }

    @RequestMapping(value = "/newcustomer", method = RequestMethod.POST)
    public Customer newCust(@RequestParam(value="name") String name, @RequestParam(value="email") String email
                            ,@RequestParam(value="password") String password){


        Customer customer = new Customer(name, 10, 10, 2000, email,password);
        try {
            DatabaseCustomer.addCustomer(customer);
        } catch(Exception ex) {
            ex.getMessage();
            return null;
        };

        return customer;
    }

    @RequestMapping("/getcustomer/{id}")
    public Customer getCust(@PathVariable int id) {
        Customer customer = DatabaseCustomer.getCustomer(id);
        return customer;
    }
    @RequestMapping(value = "/logincust", method = RequestMethod.POST)
    public Customer loginCust(@RequestParam (value = "email") String email,
                              @RequestParam (value = "password") String password)
    {
        Customer customer = DatabaseCustomer.getCustomerLogin(email,password);

        return  customer;
    }

    @RequestMapping(value = "/loginadmin", method = RequestMethod.POST)
    public String loginAdmin(@RequestParam (value = "email") String email,
                               @RequestParam (value = "password") String password)
    {
        String jsonString = null;
        Boolean admin = DatabaseCustomer.getAdminLogin(email,password);
        String loginadmin = "\nLogin :" + admin;

        if(admin) {
            com.fasterxml.jackson.databind.node.ObjectNode node = JsonNodeFactory.instance.objectNode();
            node.put("login", "admin");
            jsonString = node.toString();
        }
        return jsonString;
    }
    @RequestMapping("/allcustomer")
    public ArrayList<Customer> listCustomer(){
        ArrayList<Customer> c = DatabaseCustomer.getCustomerDatabase();
        return c;
    }

    @RequestMapping(value = "/hapuscustomer",method = RequestMethod.POST)
    public boolean hapusCustomer(@RequestParam("id_customer") int id_customer){

        try {
            if (DatabaseCustomer.removeCustomer(id_customer)) {
                return true;
            }
        }
        catch (PelangganTidakDitemukanException e){
            e.getPesan();
        }
        return false;

    }

}