package fr.synergy.projet_THP.controlers.admin;


import fr.synergy.projet_THP.entities.BackUserEntity;
import fr.synergy.projet_THP.entities.SensExtEntity;
import fr.synergy.projet_THP.services.SensExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/exterieur")
public class SensExtController {
    @Autowired
    SensExtService sensExtService;

    @GetMapping("")
    public String adminExt(Model model){
        model.addAttribute("ext",sensExtService.findByIdInvOrderLast24());
        model.addAttribute("page", "exterieur/index.html");
        return "admin/index.html";
    }
    @GetMapping("{id}")
    public String sensExtById(@PathVariable Long id, Model model) {
        Optional<SensExtEntity> sensExt = sensExtService.findById(id);
        if (sensExt.isPresent()) {
            model.addAttribute("ext", sensExt.get());
            model.addAttribute("page", "exterieur/detail.html");
            return "admin/index";
        }
        return "admin";
    }






}
