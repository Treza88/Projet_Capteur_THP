package fr.synergy.projet_THP.controlers.admin;

import fr.synergy.projet_THP.apiJoinClass.RoleGetName;
import fr.synergy.projet_THP.apiJoinClass.UserRoleJoin;
import fr.synergy.projet_THP.services.BackUserService;
import fr.synergy.projet_THP.entities.BackUserEntity;
import fr.synergy.projet_THP.services.RoleService;
import fr.synergy.projet_THP.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/users")
public class BackUserAdmin {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRoleJoin userRoleJoin;
    @Autowired
    private RoleService roleService;
@Autowired
private RoleGetName roleGetName;
    @Autowired
    private BackUserService userService;

@GetMapping("")
    public String adminUserAdmin(Model model){
    model.addAttribute("userRole",userRoleJoin.findUserRole());
    model.addAttribute("page","user/index.html");
    System.out.println(userRoleJoin.findUserRole());
    return "admin/index.html";
}

@GetMapping("add")
    public String userAdminAdd(BackUserEntity backUserEntity, Model model){
model.addAttribute("role",roleService.findAll());
    model.addAttribute("page","user/add.html");
    return "admin/index.html";
}

    @GetMapping("{id}")
    public String adminUserById(@PathVariable Long id, Model model) {

        Optional<BackUserEntity> user = userService.findById(id);
        List roles =roleGetName.findRoleById(id);
        if (user.isPresent()) {
            System.out.println(user.get());
            model.addAttribute("roles", roles);
            model.addAttribute("user", user.get());
            model.addAttribute("page", "user/detail.html");
            return "admin/index";
        }
        return "admin";
    }

}
