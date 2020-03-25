package s4.spring.td5.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td5.entities.User;
import s4.spring.td5.repositories.UserRepository;

import javax.servlet.http.HttpSession;

@Controller
public class LogController
{
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String log()
    {
        if(this.userRepository.findAll().size()==0)
        {
            User user = new User();
            user.setLogin("admin");
            user.setPassword("1234");
            this.userRepository.save(user);
        }
        return "index";
    }

    @PostMapping("/log")
    public RedirectView login(@RequestParam String login, @RequestParam String password, HttpSession session)
    {
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
                return new RedirectView("/login");
            }
        }
        else
        {
            return new RedirectView("/login");
        }
    }

    @GetMapping("logout")
    public RedirectView logout(HttpSession session)
    {
        session.setAttribute("user", null);
        return new RedirectView("/index");
    }
}
