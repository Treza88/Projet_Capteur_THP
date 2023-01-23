package fr.synergy.projet_THP.controlers.admin;


import fr.synergy.projet_THP.services.SensExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/exterieur")
public class SensExtController {
    @Autowired
    SensExtService sensExtService;

    @GetMapping("")
    public String adminExt(Model model){
        return "admin/index.html";
    }







}
