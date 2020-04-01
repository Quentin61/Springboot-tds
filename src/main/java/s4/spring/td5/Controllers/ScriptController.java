package s4.spring.td5.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import s4.spring.td5.repositories.ScriptRepository;

@Controller
public class ScriptController
{
    @Autowired
    private VueJS vueJS;

    @Autowired
    private ScriptRepository scriptRepository;

    @ModelAttribute(name = "vue")
    private VueJS getVue() {
        return this.vueJS;
    }

    @GetMapping("script/search")
    public String scripts()
    {
        String restUrl = "/rest/search";
        vueJS.addDataRaw("choices", "[{text:'Title', value:'title'}, {text:'Description', value:'description'},{text:'Content', value:'content'}]");
        vueJS.addDataRaw("headers", "[{text:'Title', value:'title'},{text:'Description', value:'description'},{ text: 'Category', value: 'category.name'},{ text: 'Language', value: 'language.name'}]");
        vueJS.addMethod("searchBy","let self=this;"+Http.post(restUrl,(Object)"{'search': this.search, 'type': this.type}","self.scripts = response.data;"));
        vueJS.addData("scripts",(Object)"scripts");
        vueJS.addData("search");
        vueJS.addData("type");
        vueJS.addData("scripts",this.scriptRepository.findAll());
        vueJS.addWatcher("search", "this.searchBy();");
        return "vueJS/index";
    }
}
