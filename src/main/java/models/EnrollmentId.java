package models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class EnrollmentId implements java.io.Serializable {
    private static final long serialVersionUID = 3659297791518032390L;
    @Column(name = "person_enrollment", nullable = false)
    private Integer personEnrollment;

    @Column(name = "course_enrollment", nullable = false)
    private Integer courseEnrollment;

    public EnrollmentId(Integer id, Integer id1) {
        this.personEnrollment = id;
        this.courseEnrollment = id1;
    }

    public EnrollmentId() {

    }

    public Integer getPersonEnrollment() {
        return personEnrollment;
    }

    public void setPersonEnrollment(Integer personEnrollment) {
        this.personEnrollment = personEnrollment;
    }

    public Integer getCourseEnrollment() {
        return courseEnrollment;
    }

    public void setCourseEnrollment(Integer courseEnrollment) {
        this.courseEnrollment = courseEnrollment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EnrollmentId entity = (EnrollmentId) o;
        return Objects.equals(this.personEnrollment, entity.personEnrollment) &&
                Objects.equals(this.courseEnrollment, entity.courseEnrollment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personEnrollment, courseEnrollment);
    }

}