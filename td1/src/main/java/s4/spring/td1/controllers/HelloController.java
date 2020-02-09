package s4.spring.td1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController
{
    @GetMapping("/hello") //signifie que si je fais une requête de type get, c'est une méthode qui va être utilisés
    public @ResponseBody String hello()
    {
        return "Hello There!!";
    }// il faut lancer
    @GetMapping("/view/hello")
    public String viewHello(ModelMap model)
    {
        model.put("message","Lamastico")
        ;return "hello";
    }
    @GetMapping("hello/{message}")
    public String viewHelloDyn(@PathVariable String message)
    {
        return "hello";
    }
}
