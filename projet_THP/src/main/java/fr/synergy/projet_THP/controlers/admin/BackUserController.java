package fr.synergy.projet_THP.controlers.admin;

import fr.synergy.projet_THP.apiJoinClass.RoleGetName;
import fr.synergy.projet_THP.apiJoinClass.UserRoleJoin;
import fr.synergy.projet_THP.entities.RoleEntity;
import fr.synergy.projet_THP.services.BackUserService;
import fr.synergy.projet_THP.entities.BackUserEntity;
import fr.synergy.projet_THP.services.RoleService;
import fr.synergy.projet_THP.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/users")
public class BackUserController {

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
    @GetMapping("add")
    public String userAdminAddGet(BackUserEntity backUserEntity, Model model){
        model.addAttribute("role",roleService.findAll());
        model.addAttribute("page","user/add.html");
        return "admin/index.html";
    }
    @PostMapping("add")
    public String userAdminAddPost(@Valid BackUserEntity userEntity, String name, BindingResult bindingResult, Model model){
if (!userEntity.getPassword().equals(userEntity.getVerifPassword())){
bindingResult.rejectValue("verifPassword","userEntity.verifPassword", "Les mots de passe ne sont pas identique");
}
if (bindingResult.hasErrors()){
    model.addAttribute("page","user/add.html");
return "admin/index.html";
        }
model.addAttribute("role",roleService.findRoleByName(name));
userEntity.setPassword(hash(userEntity.getPassword()));
userService.save(userEntity);
    return "redirect:/admin/users";
    }
    public String hash(String password){
return new BCryptPasswordEncoder().encode(password);
    }

    @GetMapping("{id}/delete")
    public String userAdminDelete(@PathVariable Long id){
    userService.deleteById(id);
    return "redirect:/admin/users";

    }


}
