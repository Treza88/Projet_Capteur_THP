package fr.synergy.projet_THP.controlers.api;

import fr.synergy.projet_THP.apiJoinClass.ApiTempJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiTempController {
    @Autowired
    private ApiTempJoin apiTempJoins;

    @GetMapping("api/temps")
    @CrossOrigin("*")
    public Iterable<ApiTempJoin> tempAllCaptLast24(){
        return apiTempJoins.findByIdInvOrderLast24Temp();
    }



}
