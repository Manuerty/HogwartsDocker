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

            // Crear la casa Slytherin
            House slytherinHouse = new House();
            slytherinHouse.setId(1); // Asumimos que el id de la casa Slytherin es 1
            slytherinHouse.setName("Slytherin");

            // Crear el headTeacher (profesor jefe de la casa)
            Person headTeacher = new Person();
            headTeacher.setId(4); // ID del profesor
            headTeacher.setFirstName("Severus");
            headTeacher.setLastName("Snape");
            // Asignar la casa al profesor
            headTeacher.setHouse(slytherinHouse);

            // Asignar el headTeacher a la casa Slytherin
            slytherinHouse.setHeadTeacher(headTeacher);

            // Crear personas (James Sirius Potter, Albus Severus Potter y Lily Luna Potter)
            Person james = new Person();
            james.setId(1);
            james.setFirstName("James Sirius");
            james.setLastName("Potter");
            james.setHouse(slytherinHouse);

            Person albus = new Person();
            albus.setId(2);
            albus.setFirstName("Albus Severus");
            albus.setLastName("Potter");
            albus.setHouse(slytherinHouse);

            Person lily = new Person();
            lily.setId(3);
            lily.setFirstName("Lily Luna");
            lily.setLastName("Potter");
            lily.setHouse(slytherinHouse);

            // Persistir las personas en la base de datos
            em.persist(headTeacher); // Persistir el headTeacher
            em.persist(slytherinHouse); // Persistir la casa
            em.persist(james); // Persistir James
            em.persist(albus); // Persistir Albus
            em.persist(lily); // Persistir Lily

            // Crear el profesor del curso Potions (por ejemplo, Horace Slughorn)
            Person potionsTeacher = new Person();
            potionsTeacher.setId(5); // ID del profesor de Potions
            potionsTeacher.setFirstName("Horace");
            potionsTeacher.setLastName("Slughorn");
            potionsTeacher.setHouse(slytherinHouse); // El profesor puede estar en la misma casa

            // Persistir el profesor de Potions
            em.persist(potionsTeacher);

            // Crear el curso "Potions" y asignar el profesor
            Course potions = new Course();
            potions.setId(1);
            potions.setName("Potions");
            potions.setTeacher(potionsTeacher); // Asignar el profesor a Potions

            // Persistir el curso
            em.persist(potions);

            // Matrícula de los estudiantes en el curso de Potions
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

            // Persistir las matrículas
            em.persist(jamesEnrollment);
            em.persist(albusEnrollment);
            em.persist(lilyEnrollment);

            // Confirmar la transacción
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
