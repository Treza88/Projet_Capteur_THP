package fr.synergy.projet_THP.controlers.admin;

import fr.synergy.projet_THP.Services.SensExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/interieur")
public class SensIntController {
    @Autowired
    SensExtService sensIntService;

    @GetMapping("")
    public String adminnt(Model model){
        return "admin/index.html";
    }
}
