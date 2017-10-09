package njl.controller;

import njl.model.entities.User;
import njl.model.repositories.IUserRepository;
import njl.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class UserController {

    ArrayList<User> users = new ArrayList<>();
    @Autowired
    IUserRepository userRepo = new UserRepository();

    @GetMapping("/")
    public String start(Model model){
        return "start";
    }

    @GetMapping("/index")
    public String index(Model model){
        users = userRepo.readAll();
        model.addAttribute("us", users);
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute User us){
        userRepo.create(us);
        return "redirect:/";
    }

    @GetMapping("/details")
    public String details(@RequestParam("id") int user_id, Model model)
    {
        model.addAttribute("us", userRepo.read(user_id));
        return "details";
    }

    @GetMapping("/login")
    public String login(@RequestParam("id") int user_id, Model model)
    {
        model.addAttribute("us", userRepo.login(user_id));
        return "login";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") String user_id, Model model){
        int intId = Integer.parseInt(user_id);
        model.addAttribute("us", userRepo.read(intId));
        return "update";
    }

    @PostMapping("login")
    public String update(@ModelAttribute User us){
        userRepo.updatepw(us);
        if(userRepo.updatepw(us) != null) {
            return "super";
        }
        return "redirect:/";
    }

    @PostMapping("update")
    public String updatepw(@ModelAttribute User us){
        userRepo.updatepw(us);
        if(userRepo.updatepw(us) != null) {
            if(us.getPassword1().equals(us.getPassword2())) {
                us.setPassword(us.getPassword1());
                userRepo.update(us);
            }
        }
        return "redirect:/";
    }

    /*@GetMapping("/delete")
    public String delete(@RequestParam("id") int user_id){
        userRepo.delete(user_id);
        return "redirect:/";
    }*/

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String user_id, Model model){
        int intId = Integer.parseInt(user_id);
        model.addAttribute("us", userRepo.read(intId));
        return "delete";
    }

    @PostMapping("delete")
    public String delete(@ModelAttribute User us){
        userRepo.updatepw(us);
        if(userRepo.updatepw(us) != null) {
            userRepo.delete(us.getUser_id());
            //return "redirect:/";
        }
        return "redirect:/";
    }
}
