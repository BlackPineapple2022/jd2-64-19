package by.academy.it.travelcompany;

import by.academy.it.travelcompany.entity.Excursion;
import by.academy.it.travelcompany.entity.Sightseeing;
import by.academy.it.travelcompany.entity.TravelPoint;
import by.academy.it.travelcompany.entity.TravelPointDetail;
import by.academy.it.travelcompany.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.HashSet;

public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //ONE TO ONE RELATIONS
        TravelPointDetail travelPointDetail = new TravelPointDetail();
        travelPointDetail.setCountry("Russian Federation");
        travelPointDetail.setCity("St. Petersburg");
        travelPointDetail.setAddress("Rubinshteina 24");

        TravelPoint travelPoint1 = new TravelPoint(null, "First travel point", travelPointDetail,new ArrayList<Excursion>(),null);
        travelPointDetail.setTravelPoint(travelPoint1);

        session.save(travelPoint1);
        session.getTransaction().commit();

        //MANY TO MANY RELATIONS
        session.beginTransaction();
        TravelPoint travelPoint2 = new TravelPoint(null, "Second travel point", null,new ArrayList<Excursion>(),null);
        TravelPoint travelPoint3 = new TravelPoint(null, "Third travel point", null,new ArrayList<Excursion>(),null);

        Excursion excursion12 = new Excursion(null,"1-2",new ArrayList<TravelPoint>());
        Excursion excursion123 = new Excursion(null,"1-2-3",new ArrayList<TravelPoint>());

        excursion12.getTravelPoints().add(travelPoint1);
        excursion12.getTravelPoints().add(travelPoint2);

        excursion123.getTravelPoints().add(travelPoint1);
        excursion123.getTravelPoints().add(travelPoint2);
        excursion123.getTravelPoints().add(travelPoint3);

        travelPoint1.getExcursions().add(excursion12);
        travelPoint1.getExcursions().add(excursion123);

        travelPoint2.getExcursions().add(excursion12);
        travelPoint2.getExcursions().add(excursion123);

        travelPoint3.getExcursions().add(excursion123);

        session.save(travelPoint2);
        session.save(travelPoint3);

        session.save(excursion12);
        session.save(excursion123);

        //ONE TO MANY RELATIONS

        Sightseeing sightseeing = new Sightseeing(null,"RUSSIAN ROCK TRACKING",new ArrayList<>());
        session.save(sightseeing);

        TravelPoint travelPoint4 = new TravelPoint(null, "4 travel point", null,new ArrayList<Excursion>(),null);
        TravelPoint travelPoint5 = new TravelPoint(null, "5 travel point", null,new ArrayList<Excursion>(),null);

        session.save(travelPoint4);
        session.save(travelPoint5);


        travelPoint4.setSightseeing(sightseeing);
        travelPoint5.setSightseeing(sightseeing);

        sightseeing.getTravelPoints().add(travelPoint4);
        sightseeing.getTravelPoints().add(travelPoint5);

        session.getTransaction().commit();

        session.close();
        HibernateUtil.shutdown();
    }
}
