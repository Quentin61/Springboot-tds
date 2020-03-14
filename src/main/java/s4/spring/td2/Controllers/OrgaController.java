package s4.spring.td2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td2.entities.Organization;
import s4.spring.td2.repositories.OrgaRepository;

import java.util.List;

import static org.apache.coyote.http11.Constants.a;

@Controller
public class OrgaController
{
    @Autowired
    private OrgaRepository orgaRepository;
    @GetMapping("/orgas/index")
    public String viewIndex(ModelMap model)
    {
        List<Organization> all = this.orgaRepository.findAll();
        model.put("all",all);
        /*for (Organization a: all
             ) {
            this.orgaRepository.delete(a);
        }*/
        return "orga/index";
    }

    @GetMapping("/orgas/new")
    public String viewNewOrgas()
    {
        return "orga/new";
    }

    @GetMapping("/orgas/add")
    public RedirectView newOrga(@RequestParam String nom,@RequestParam String domaine,@RequestParam String aliases)
    {
        Organization organization = new Organization();
        organization.setName(nom);
        organization.setDomain(domaine);
        organization.setAliases(aliases);
        this.orgaRepository.save(organization);
        return new RedirectView("index");
    }

    @GetMapping("/orgas/alter")
    public RedirectView editOrga(@RequestParam int id,@RequestParam String nom,@RequestParam String domaine,@RequestParam String aliases)
    {
        Organization organization = this.orgaRepository.findById(id);
        organization.setDomain(domaine);
        organization.setName(nom);
        organization.setAliases(aliases);
        this.orgaRepository.save(organization);
        return new RedirectView("/orgas/index");
    }

    @GetMapping("/orgas/edit/{id}")
    public String viewEditOrgas(ModelMap modelMap,@PathVariable("id")String id)
    {
        modelMap.put("orga",this.orgaRepository.findById(Integer.parseInt(id)));
        return "orga/edit";
    }

    @GetMapping("/orgas/display/{id}")
    public String viewDisplayOrgas(ModelMap modelMap,@PathVariable("id")String id)
    {
        modelMap.put("orga",this.orgaRepository.findById(Integer.parseInt(id)));
        return "orga/show";
    }

    @GetMapping("/orgas/remove/{id}")
    public RedirectView removeOrga(@PathVariable("id")String id)
    {
        this.orgaRepository.delete(this.orgaRepository.findById(Integer.parseInt(id)));
        return new RedirectView("../index");
    }

    @PostMapping("/orgas/search")
    public String searchorga(ModelMap modelMap, @RequestParam String nom)
    {
        List<Organization> all = this.orgaRepository.findBy(nom);
        modelMap.put("all",all);
        return "orga/index";
    }

}
