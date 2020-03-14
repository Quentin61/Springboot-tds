package s4.spring.td2.Controllers;


import io.github.jeemv.springboot.vuejs.VueJS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestVue {
    @Autowired
    private VueJS vue;

    @GetMapping("/testVue")
    public String index(ModelMap model)
    {
        vue.addData("message", "Test de message");
        vue.addMethod("docopy","this.copie=this.message;");
        vue.addData("names", new String[]{"Bob","BY"});
        model.put("vue", this.vue);
        vue.addData("copie","");
        return "index";
    }
}
