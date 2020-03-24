package s4.spring.td5.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td5.entities.Script;
import s4.spring.td5.repositories.ScriptRepository;

@Controller
public class ScriptsController
{
    @Autowired
    private ScriptRepository scriptRepository;

    @GetMapping("scripts/new")
    public String newScript()
    {
        return "scripts/new";
    }

    @PostMapping("/scripts/submit")
    public RedirectView add(@RequestParam String id,@RequestParam String title, @RequestParam String description, @RequestParam String content,@RequestParam String creationDate)
    {
        Script script = this.scriptRepository.findById(Integer.parseInt(id));
        if(script==null)
        {
            script = new Script();
        }
        script.setTitle(title);
        script.setDescription(description);
        script.setContent(content);
        script.setCreationDate(creationDate);
        this.scriptRepository.save(script);
        return new RedirectView("/scripts/index");
    }

    @GetMapping("/scripts/{id}")
    public String String(ModelMap model, @PathVariable String id)
    {
        Script script = this.scriptRepository.findById(Integer.parseInt(id));
        model.put("script",script);
        return "scripts/edit";
    }
}
