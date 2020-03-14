package s4.spring.td2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td2.entities.Organization;
import s4.spring.td2.repositories.OrgaRepository;

import java.util.List;

@RestController
public class RestOrgasController {
    @Autowired
    private OrgaRepository orgaRepository;

    @GetMapping("/rest/orgas/")
    public List<Organization> read() {
        return this.orgaRepository.findAll();
    }

    @GetMapping("/rest/orgas/{id}")
    public Organization read(@PathVariable("id") String id) {
        return this.orgaRepository.findById(Integer.parseInt(id));
    }

    @PostMapping("/rest/orgas/create")
    public Organization create(@RequestBody Organization organization) {
        this.orgaRepository.save(organization);
        return organization;
    }

    @PutMapping("/rest/orgas/update")
    public RedirectView update(@RequestBody Organization organization) {
        Organization orga = this.orgaRepository.findById(organization.getId());
        orga.setDomain(organization.getDomain());
        orga.setName(organization.getName());
        orga.setAliases(organization.getAliases());
        this.orgaRepository.save(orga);
        return new RedirectView("/orgas/index");
    }

    @DeleteMapping("/rest/orgas/delete")
    public void delete(@RequestBody Organization organization) {
        this.orgaRepository.delete(organization);
    }

    @GetMapping("rest/orgas/{member}/{id}")
    public String get(@PathVariable("member") String member, @PathVariable("id") String id) {
        return null;
    }
}
