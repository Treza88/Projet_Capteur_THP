package fr.synergy.projet_THP.controlers.admin;

import fr.synergy.projet_THP.Services.BackUserService;
import fr.synergy.projet_THP.entities.BackUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/users")
public class BackUserAdmin {

    @Autowired
    private BackUserService backUserService;

@GetMapping("")
    public String adminUserAdmin(Model model){
    model.addAttribute("users",backUserService.findAll());
    model.addAttribute("page","user/index.html");
    return "admin/index.html";
}

@GetMapping("add")
    public String userAdminAdd(BackUserEntity backUserEntity, Model model){

    model.addAttribute("page","user/add.html");
    return "admin/index.html";
}
}
