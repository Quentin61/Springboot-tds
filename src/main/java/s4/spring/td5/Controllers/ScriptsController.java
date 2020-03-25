package s4.spring.td5.Controllers;

import org.apache.catalina.startup.Catalina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td5.entities.*;
import s4.spring.td5.repositories.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class ScriptsController
{
    @Autowired
    private ScriptRepository scriptRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @GetMapping("scripts/new")
    public String newScript(ModelMap model)
    {
        List<Language> lang = this.languageRepository.findAll();
        List<Category> cat = this.categoryRepository.findAll();
        model.put("languages", lang);
        model.put("categories", cat);
        return "scripts/new";
    }

    @PostMapping(value = {"/scripts/submit", "/scripts/submit/{id}"})
    public RedirectView add(@PathVariable(required = false) String id,@RequestParam String title, @RequestParam String description, @RequestParam String content,@RequestParam String creationDate,HttpSession session,@RequestParam String language,@RequestParam String category)
    {
        Script script = this.scriptRepository.findById(Integer.parseInt(id));
        if(script==null)
        {
            script = new Script(title,description,content,creationDate);
        }
        else
        {
            script.setTitle(title);
            script.setCreationDate(creationDate);
            script.setContent(content);
            script.setDescription(description);
        }
        Language language1 = this.languageRepository.findByName(language);
        Category category1 = this.categoryRepository.findByName(category);
        User user = (User)session.getAttribute("user");
        user = this.userRepository.findById(user.getId());
        List<Script> scripts = user.getScripts();
        scripts.add(script);
        script.setUser(user);
        script.setCategory(category1);
        script.setLanguage(language1);
        user.setScripts(scripts);
        this.userRepository.save(user);
        return new RedirectView("/scripts/index");
    }

    @GetMapping("/scripts/{id}")
    public String alter(ModelMap model, @PathVariable String id)
    {
        Script script = this.scriptRepository.findById(Integer.parseInt(id));
        List<Language> lang = this.languageRepository.findAll();
        List<Category> cat = this.categoryRepository.findAll();
        model.put("languages", lang);
        model.put("categories", cat);
        model.put("script",script);
        return "scripts/edit";
    }

    @GetMapping("/scripts/index")
    public String index(ModelMap model, HttpSession session)
    {
        init();
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

    public void init()
    {
        Language lang1 = new Language("java");
        Language lang2 = new Language("php");
        Language lang3 = new Language("c");

        Category cat1 = new Category("category 1");
        Category cat2 = new Category("category 2");
        Category cat3 = new Category("category 3");

        if(this.languageRepository.findAll().size()==0)
        {
            this.languageRepository.save(lang1);
            this.languageRepository.save(lang2);
            this.languageRepository.save(lang3);
        }
        if(this.categoryRepository.findAll().size()==0)
        {
            this.categoryRepository.save(cat1);
            this.categoryRepository.save(cat2);
            this.categoryRepository.save(cat3);
        }
    }
}
