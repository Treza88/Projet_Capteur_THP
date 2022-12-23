package fr.synergy.projet_THP.controlers.admin.SensCave;

import fr.synergy.projet_THP.Services.SensCaveService;
import fr.synergy.projet_THP.entities.SensCaveEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("admin/cave")
public class SensCaveControler {

    @Autowired
    SensCaveService sensCaveService;

    @GetMapping("add")
    public String adminCaveAdd(SensCaveEntity sensCaveEntity, Model model){
        model.addAttribute("page", "cave/add.html");

        return "admin/index.html";
    }

    @GetMapping("")
    public String adminCave(Model model){
        model.addAttribute("cave",sensCaveService.findByIdInvOrderLast24());
        model.addAttribute("page", "cave/index.html");
        return "admin/index.html";
    }
@GetMapping("{id}")
    public String adminCaveById(@PathVariable Long id,Model model){
    Optional<SensCaveEntity> cave = sensCaveService.findById(id);
    System.out.println(cave.get().getIdCave());
    System.out.println(cave.get().getTempCave());
    System.out.println(cave.get().getHumidCave());
//    System.out.println(cave.get().getIdDate());
//    System.out.println(cave.get().getIdLieu());
        if (cave.isPresent()){
            model.addAttribute("c",cave.get());
            model.addAttribute("page","cave/detail.html");
            return "admin/index";
    }


        return "admin";
}



}
