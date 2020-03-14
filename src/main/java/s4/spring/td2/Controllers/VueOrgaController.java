package s4.spring.td2.Controllers;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import io.github.jeemv.springboot.vuejs.utilities.JsArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import s4.spring.td2.repositories.OrgaRepository;

@Controller
public class VueOrgaController
{
    @Autowired
    private VueJS vue;

    @Autowired
    private OrgaRepository orgaRepository;

    @GetMapping("/vuejs/orga/index")
    public String index(ModelMap model)
    {
        String restUrl = "/vuejs/orga/";
        model.put("vue",this.vue);
        vue.addDataRaw("headers","[" +
                "{text: Identifiant, align: 'start', value: id}," +
                "{text: Nom, align: 'start', value: name}," +
                "{text: Domaine, align: 'start', value: domain}," +
                "{text: Alias, align: 'start', value: aliases}" +
                "]");
        vue.addData("orgas", this.orgaRepository.findAll());
        vue.addMethod("deleteItem","var self=this;" + Http.delete(restUrl, (Object) "{data: item}", JsArray.remove("self.orgas", "item")), "item");
        return "vueJsOrga/index";
    }
}
