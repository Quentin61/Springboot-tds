package s4.spring.td5.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td5.entities.*;
import s4.spring.td5.repositories.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ScriptRestController
{
    @Autowired
    private ScriptRepository scriptRepository;

    @GetMapping("/rest/scripts/")
    public List<Script> read(HttpSession session)
    {
        User user = (User)session.getAttribute("user");
        if(user!=null) {
            return this.scriptRepository.findAll();
        }
        return null;
    }

    @GetMapping("/rest/script/{id}")
    public Script read(@PathVariable String id, HttpSession session)
    {
        User user = (User)session.getAttribute("user");
        if(user!=null) {
            return this.scriptRepository.getOne(Integer.parseInt(id));
        }
        return null;
    }

    @PostMapping("/rest/script/create")
    public Script create(@RequestBody Script script,HttpSession session)
    {
        User user = (User)session.getAttribute("user");
        if(user!=null) {
            this.scriptRepository.save(script);
            return script;
        }
        return null;
    }

    @PutMapping("/rest/scripts/update")
    public RedirectView add(@RequestBody Script script, HttpSession session)
    {
        User user = (User)session.getAttribute("user");
        if(user!=null) {
            Script script1 = this.scriptRepository.findById(script.getId());
            script1.setTitle(script.getTitle());
            script1.setCreationDate(script.getCreationDate());
            script1.setDescription(script.getDescription());
            script1.setContent(script.getContent());
            script1.setHistory(script.getHistory());
            script1.setCategory(script.getCategory());
            script1.setHistory(script.getHistory());
            script1.setUser(script.getUser());
            this.scriptRepository.save(script1);
            return new RedirectView("/scripts/index");
        }
        else
        {
            return new RedirectView("index");
        }
    }

    @DeleteMapping("/rest/script/delete")
    public RedirectView delete(@RequestBody Script script, HttpSession session)
    {
        User user = (User)session.getAttribute("user");
        if(user!=null) {
            this.scriptRepository.delete(script);
            return new RedirectView("/scripts/index");
        }
        else
        {
            return new RedirectView("index");
        }
    }

}
