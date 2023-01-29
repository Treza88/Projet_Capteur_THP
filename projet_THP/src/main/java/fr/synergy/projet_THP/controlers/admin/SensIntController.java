package fr.synergy.projet_THP.controlers.admin;


import fr.synergy.projet_THP.services.SensIntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/interieur")
public class SensIntController {
    @Autowired
    SensIntService sensIntService;

    @GetMapping("")
    public String adminInt(Model model){
        model.addAttribute("int",sensIntService.findByIdInvOrderLast24());
        model.addAttribute("page", "interieur/index.html");
        return "admin/index.html";
    }
}
