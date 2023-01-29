package fr.synergy.projet_THP.controlers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class Forbiden_403 {
    @GetMapping("403")
    public String error_403(){
        return "403";
    }
}
