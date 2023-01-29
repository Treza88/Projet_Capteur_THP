package fr.synergy.projet_THP.apiJoinClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ApiTempJoin {

    private Float tempExt;
    private Float tempInt;
    private Float tempCave;
    private Calendar dateServer;
    private Float humidExt;
    private Float humidInt;
    private Float humidCave;
    private Float pressExt;

    @Autowired
    EntityManager em;


    public Iterable<ApiTempJoin> findByIdInvOrderLast24Temp() {
//        Iterable<TestEmbed> it = tempRepository.findByIdInvOrderLast24Temp();
//        System.out.println(it);
        String req = "SELECT temp_ext, temp_int, temp_cave, humid_ext, humid_int, humid_cave, press_ext, date_server \n" +
                "FROM sens_ext e \n" +
                "JOIN sens_int i ON e.id_date=i.id_date  \n" +
                "JOIN sens_cave c on e.id_date=c.id_date\n" +
                "JOIN sens_date d on e.id_date=d.id_date\n" +
                "ORDER BY id_ext DESC LIMIT 24";

        Query query = em.createNativeQuery(req);
        List<Object[]> all = query.getResultList();
        List<ApiTempJoin> apiTempJoins = new ArrayList<>();

        for (Object[] o : all) {
            ApiTempJoin t = new ApiTempJoin();
            t.setTempExt((Float) o[0]);
            t.setTempInt((Float) o[1]);
            t.setTempCave((Float) o[2]);
            t.setHumidExt((Float) o[3]);
            t.setHumidInt((Float) o[4]);
            t.setHumidCave((Float) o[5]);
            t.setPressExt((Float) o[6]);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.format(o[7]);
            t.setDateServer(sdf.getCalendar());
            apiTempJoins.add(t);
        }
        System.out.println(apiTempJoins);

        return apiTempJoins;
    }

    public Iterable<ApiTempJoin> findByDay(String day) {

        String req = String.format("SELECT temp_ext, temp_int, temp_cave, humid_ext, humid_int, humid_cave, press_ext, date_server\n" +
                "FROM sens_ext e\n" +
                "JOIN sens_int i ON e.id_date=i.id_date\n" +
                "JOIN sens_cave c on e.id_date=c.id_date\n" +
                "JOIN sens_date d on e.id_date=d.id_date\n" +
                "where date(date_server)=\"%s\"",day);

        Query query = em.createNativeQuery(req);
        List<Object[]> all = query.getResultList();
        List<ApiTempJoin> apiTempJoins = new ArrayList<>();
        for (Object[] o : all) {
            ApiTempJoin t = new ApiTempJoin();
            t.setTempExt((Float) o[0]);
            t.setTempInt((Float) o[1]);
            t.setTempCave((Float) o[2]);
            t.setHumidExt((Float) o[3]);
            t.setHumidInt((Float) o[4]);
            t.setHumidCave((Float) o[5]);
            t.setPressExt((Float) o[6]);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.format(o[7]);
            t.setDateServer(sdf.getCalendar());
            apiTempJoins.add(t);
        }
        System.out.println(apiTempJoins);

        return apiTempJoins;
    }


}
