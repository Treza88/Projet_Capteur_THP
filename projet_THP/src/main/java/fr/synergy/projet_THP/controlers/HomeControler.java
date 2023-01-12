package fr.synergy.projet_THP.controlers;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Controller
public class HomeControler {

//@RequestMapping("/")
//    public String index(Model model){
//    System.out.println("je suis a l'index de mon site");
//    return "index.html";
//}
@GetMapping({"/","admin", "admin/index"})
    public String admin(){
    return "admin/index.html";
}

    @GetMapping("/login")
    public String index(Model model){
    return "login";}


}
