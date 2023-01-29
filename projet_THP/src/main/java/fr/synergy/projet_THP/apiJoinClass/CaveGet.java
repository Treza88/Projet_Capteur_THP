//package fr.synergy.projet_THP.apiJoinClass;
//
//import fr.synergy.projet_THP.entities.SensCaveEntity;
//import fr.synergy.projet_THP.entities.SensDateEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CaveGet {
//
//    @Autowired
//    private SensCaveEntity sensCaveEntity;
//
//    @Autowired
//    EntityManager em;
//
//
//    public Iterable<SensCaveEntity> findCaveByDay(String day) {
//
//        String req = String.format("SELECT e.*, date_server\n" +
//                "FROM sens_cave e\n" +
//                "JOIN sens_date d on e.id_date=d.id_date\n" +
//                "where date(date_server)=\"%s\"",day);
//
//        javax.persistence.Query query = em.createNativeQuery(req);
//        List<Object[]> all = query.getResultList();
//        List<SensCaveEntity> sensCaveEntities = new ArrayList<>();
//        List<SensDateEntity> sensDateEntities = new ArrayList<>();
//        List listCaveDate = new ArrayList<>();
//        for (Object[] o : all) {
//            SensCaveEntity c = new SensCaveEntity();
//            SensDateEntity d = new SensDateEntity();
//            c.setTempCave((Float) o[0]);
//            c.setHumidCave((Float) o[1]);
//            sensCaveEntities.add(c);
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            sdf.format(o[0]);
//            d.setDateServer(sdf.getCalendar());
//            sensDateEntities.add(d);
//            listCaveDate.add(sensCaveEntities);
//            listCaveDate.add(sensDateEntities);
//        }
//
//        System.out.println(sensCaveEntities);
//
//        return listCaveDate;
//    }
//}
