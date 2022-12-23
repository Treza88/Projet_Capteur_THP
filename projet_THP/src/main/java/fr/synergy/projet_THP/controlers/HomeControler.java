package fr.synergy.projet_THP.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeControler {

@RequestMapping("/")
    public String index(Model model){
    System.out.println("je suis a l'index de mon site");
    return "index.html";
}
@RequestMapping("admin")
    public String admin(){
    return "admin/index.html";
}
}
