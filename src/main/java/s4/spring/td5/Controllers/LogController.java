package s4.spring.td5.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td5.entities.User;
import s4.spring.td5.repositories.UserRepository;

import javax.persistence.EntityResult;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LogController
{
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String log()
    {
        init();
        return "index";
    }

    @PostMapping("/log")
    public RedirectView login(@RequestParam String login, @RequestParam String password, HttpSession session)
    {
        List<User> users = this.userRepository.findAll();
        User user = this.userRepository.findByLogin(login);
        if(user!=null)
        {
            if(user.getPassword().equals(password))
            {
                session.setAttribute("user", user);
                System.out.println(session.getAttribute("user"));
                return new RedirectView("/scripts/index");
            }
            else
            {
                return new RedirectView("/index");
            }
        }
        else
        {
            return new RedirectView("/index");
        }
    }

    @GetMapping("logout")
    public RedirectView logout(HttpSession session)
    {
        session.removeAttribute("user");
        return new RedirectView("/index");
    }

    @GetMapping("newUser")
    public  String newUser()
    {
        return "/signup";
    }

    @PostMapping("signup")
    public RedirectView signup(@RequestParam String identity, @RequestParam String login, @RequestParam String email,@RequestParam String password)
    {
        if(identity.equals("") || login.equals("") ||email.equals("") || password.equals(""))
        {
            return new RedirectView("newUser");
        }
        else
        {
            User user = new User(login,password, email, identity);
            this.userRepository.save(user);
            return new RedirectView("index");
        }
    }

    public void init()
    {
        if(this.userRepository.findAll().size()==0)
        {
            User user = new User("admin","1234","admin@spring.com","Admin");
            this.userRepository.save(user);
        }
    }
}
