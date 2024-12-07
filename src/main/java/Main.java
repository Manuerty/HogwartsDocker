import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            House slytherinHouse = new House();
            slytherinHouse.setId(1);
            slytherinHouse.setName("Slytherin");

            House gryffindorHouse = new House();
            gryffindorHouse.setId(2);
            gryffindorHouse.setName("Gryffindor");

            Person gryffindorHeadTeacher = new Person();
            gryffindorHeadTeacher.setId(1);
            gryffindorHeadTeacher.setFirstName("Minerva");
            gryffindorHeadTeacher.setLastName("McGonagall");
            gryffindorHeadTeacher.setHouse(gryffindorHouse);

            gryffindorHouse.setHeadTeacher(gryffindorHeadTeacher);


            Person headTeacher = new Person();
            headTeacher.setId(2);
            headTeacher.setFirstName("Severus");
            headTeacher.setLastName("Snape");

            headTeacher.setHouse(slytherinHouse);

            slytherinHouse.setHeadTeacher(headTeacher);


            Person james = new Person();
            james.setId(3);
            james.setFirstName("James Sirius");
            james.setLastName("Potter");
            james.setHouse(slytherinHouse);

            Person albus = new Person();
            albus.setId(4);
            albus.setFirstName("Albus Severus");
            albus.setLastName("Potter");
            albus.setHouse(slytherinHouse);

            Person lily = new Person();
            lily.setId(5);
            lily.setFirstName("Lily Luna");
            lily.setLastName("Potter");
            lily.setHouse(slytherinHouse);

            em.persist(headTeacher);
            em.persist(gryffindorHeadTeacher);
            em.persist(gryffindorHouse);
            em.persist(slytherinHouse);
            em.persist(james);
            em.persist(albus);
            em.persist(lily);

            Person potionsTeacher = new Person();
            potionsTeacher.setId(6);
            potionsTeacher.setFirstName("Horace");
            potionsTeacher.setLastName("Slughorn");
            potionsTeacher.setHouse(slytherinHouse);

            Person astronomyTeacher = new Person();
            astronomyTeacher.setId(7);
            astronomyTeacher.setFirstName("Aurora");
            astronomyTeacher.setLastName("Sinistra");
            astronomyTeacher.setHouse(gryffindorHouse);

            em.persist(astronomyTeacher);

            em.persist(potionsTeacher);

            Course potions = new Course();
            potions.setId(1);
            potions.setName("Potions");
            potions.setTeacher(potionsTeacher);

            Course Astronomy = new Course();
            Astronomy.setId(2);
            Astronomy.setName("Astronomy");
            Astronomy.setTeacher(astronomyTeacher);

            em.persist(Astronomy);
            em.persist(potions);

            Enrollment jamesEnrollment = new Enrollment();
            jamesEnrollment.setId(new EnrollmentId(james.getId(), potions.getId()));
            jamesEnrollment.setPersonEnrollment(james);
            jamesEnrollment.setCourseEnrollment(potions);

            Enrollment albusEnrollment = new Enrollment();
            albusEnrollment.setId(new EnrollmentId(albus.getId(), potions.getId()));
            albusEnrollment.setPersonEnrollment(albus);
            albusEnrollment.setCourseEnrollment(potions);

            Enrollment lilyEnrollment = new Enrollment();
            lilyEnrollment.setId(new EnrollmentId(lily.getId(), potions.getId()));
            lilyEnrollment.setPersonEnrollment(lily);
            lilyEnrollment.setCourseEnrollment(potions);

            Enrollment lilysAstronomyEnrollment = new Enrollment();
            lilysAstronomyEnrollment.setId(new EnrollmentId(lily.getId(), Astronomy.getId()));
            lilysAstronomyEnrollment.setPersonEnrollment(lily);
            lilysAstronomyEnrollment.setCourseEnrollment(Astronomy);

            em.persist(lilysAstronomyEnrollment);
            em.persist(jamesEnrollment);
            em.persist(albusEnrollment);
            em.persist(lilyEnrollment);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
