package fr.synergy.projet_THP.controlers.api;

import fr.synergy.projet_THP.apiJoinClass.ApiGetDay;
import fr.synergy.projet_THP.apiJoinClass.ApiTempJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

public class ApiTempController {
    @Autowired
    private ApiTempJoin apiTempJoins;
//    @Autowired
//    private ApiGetDay apiGetDay;
    @GetMapping("api/last24")
    @CrossOrigin("*")
    public Iterable<ApiTempJoin> dataLast24(){
        return apiTempJoins.findByIdInvOrderLast24Temp();
    }

    @GetMapping("api/{day}")
    @CrossOrigin("*")
    public Iterable<ApiTempJoin> data1day(@PathVariable String day){
        return apiTempJoins.findByDay(day);
    }

//    @PostMapping(value = "/api/getDay", consumes = {"application/json"})
//    @CrossOrigin("*")
//    public ApiGetDay getDay(@RequestBody ApiGetDay apiGetDay){
//    String  oneDay = apiGetDay.getGetDay();
//    System.out.println(oneDay);
//    data1day(oneDay);
//    return apiGetDay;
//}
}
