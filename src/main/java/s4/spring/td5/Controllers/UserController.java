package s4.spring.td5.Controllers;

import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td5.repositories.UserRepository;

@Controller
public class UserController
{
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/log")
    public String log()
    {
        return null;
    }
}
