package s4.spring.td5.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td5.entities.User;
import s4.spring.td5.repositories.UserRepository;

@Controller
@SessionAttributes("user")
public class LogController
{
    @ModelAttribute("user")
    public User getUser(User user){
        return new User();
    }
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String log()
    {
        return "log";
    }

    @PostMapping("/log")
    public RedirectView login(@RequestParam String login, @RequestParam String password)
    {
        User user = this.userRepository.findByLogin(login);
        if(user!=null)
        {
            if(user.getPassword().equals(password))
            {
                //TODO ajouter la variable de session ici
                return new RedirectView("/index");
            }
            else
            {
                return new RedirectView("/login");
            }
        }
        else
        {
            return new RedirectView("/login");
        }
    }

    @GetMapping("/index")
    public String index(@ModelAttribute ("user") User user)
    {
        if(user.getLogin()!=null) //verifier si la varible de session n'est pas null
        {
            return "index";
        }
        return null;
    }
    @GetMapping("logout")
    public RedirectView logout()
    {
        //TODO retirer la variable de session ici
        return new RedirectView("/login");
    }
}
