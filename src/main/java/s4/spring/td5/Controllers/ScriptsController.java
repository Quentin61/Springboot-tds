package s4.spring.td5.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td5.entities.Script;
import s4.spring.td5.entities.User;
import s4.spring.td5.repositories.ScriptRepository;
import s4.spring.td5.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class ScriptsController
{
    @Autowired
    private ScriptRepository scriptRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("scripts/new")
    public String newScript()
    {
        return "scripts/new";
    }

    @PostMapping("/scripts/submit/")
    public RedirectView add(/*@PathVariable(name = "id") String id,*/@RequestParam String title, @RequestParam String description, @RequestParam String content,@RequestParam String creationDate,HttpSession session)
    {
        /*Script script = this.scriptRepository.findById(Integer.parseInt(id));
        if(script==null)
        {*/
            Script script = new Script();
        /*}*/
        script.setTitle(title);
        script.setDescription(description);
        script.setContent(content);
        script.setCreationDate(creationDate);
        User user = (User)session.getAttribute("user");
        user = this.userRepository.findById(user.getId());
        List<Script> scripts = user.getScripts();
        scripts.add(script);
        user.setScripts(scripts);
        this.userRepository.save(user);
        return new RedirectView("/scripts/index");
    }

    @GetMapping("/scripts/{id}")
    public String alter(ModelMap model, @PathVariable String id)
    {
        Script script = this.scriptRepository.findById(Integer.parseInt(id));
        model.put("script",script);
        return "scripts/edit";
    }

    @GetMapping("/scripts/index")
    public String index(ModelMap model, HttpSession session)
    {
        User user = (User)session.getAttribute("user");
        if(user!=null)
        {
            List<Script> script = this.scriptRepository.findAll();
            user = this.userRepository.findById(user.getId());
            model.put("scripts",user.getScripts());
            return "scripts/index";
        }
        else
        {
            return "index";
        }
    }
}
