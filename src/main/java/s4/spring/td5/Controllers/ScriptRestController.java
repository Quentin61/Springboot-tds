package s4.spring.td5.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td5.entities.*;
import s4.spring.td5.repositories.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ScriptRestController
{
    @Autowired
    private ScriptRepository scriptRepository;

    @GetMapping("/rest/scripts/")
    public List<Script> read()
    {
        return this.scriptRepository.findAll();
    }

    @GetMapping("/rest/script/{id}")
    public Script read(@PathVariable String id)
    {
        return this.scriptRepository.getOne(Integer.parseInt(id));
    }

    @PostMapping("/rest/script/create")
    public Script create(@RequestBody Script script)
    {
        this.scriptRepository.save(script);
        return script;
    }

    @PutMapping("/rest/scripts/update")
    public RedirectView add(@RequestBody Script script)
    {
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

    @DeleteMapping("/rest/script/delete")
    public RedirectView delete(@RequestBody Script script)
    {
        this.scriptRepository.delete(script);
        return new RedirectView("/scripts/index");
    }

    @PostMapping("/rest/search")
    public List<Script> search(@RequestBody SearchObject object)
    {
        List<Script> scripts = new ArrayList<>();
        switch (object.getType())
        {
            case("title"):
            {
                scripts = this.scriptRepository.findByTitleSearch(object.getSearch());
                break;
            }
            case("description"):
            {
                scripts = this.scriptRepository.findByDescriptionSearch(object.getSearch());
                break;
            }
            case("content"):
            {
                scripts = this.scriptRepository.findByContentSearch(object.getSearch());
                break;
            }

            default:
            {
                scripts = this.scriptRepository.findByAllSearch(object.getSearch());
                break;
            }
        }
        return scripts;
    }

}
