package s4.spring.td5.Controllers;


import com.sun.org.apache.xpath.internal.objects.XObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import io.github.jeemv.springboot.vuejs.utilities.JsArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import s4.spring.td5.entities.Script;
import s4.spring.td5.entities.SearchObject;

import javax.xml.ws.http.HTTPBinding;
import java.util.List;

@Controller
public class ScriptController
{
    @Autowired
    private VueJS vueJS;

    @ModelAttribute(name = "vue")
    private VueJS getVue() {
        return this.vueJS;
    }

    @GetMapping("script/search")
    public String scripts()
    {
        String restUrl = "/rest/search";
        vueJS.addDataRaw("choices", "[{text:'Title', value:'title'}, {text:'Description', value:'description'},{text:'Content', value:'content'}]");
        vueJS.addDataRaw("headers", "[{text:'Title', value:'title'},{text:'Description', value:'description'},{ text: 'Category', value: 'category'},{ text: 'Language', value: 'language'}]");
        vueJS.addMethod("searchBy","let self=this;"+Http.post(restUrl,(Object)"{'search': this.search, 'type': this.type}","self.scripts = response.data;"));
        vueJS.addData("scripts",(Object)"this.scripts");
        vueJS.addData("search");
        vueJS.addData("type");
        vueJS.addWatcher("search", "this.searchBy();");
        return "vueJS/index";
    }
}
