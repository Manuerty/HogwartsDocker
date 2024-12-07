package models;

import jakarta.persistence.*;

@Entity
@Table(name = "house_points")
public class HousePoint {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "giver")
    private models.Person giver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver")
    private models.Person receiver;

    @Column(name = "points")
    private Integer points;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public models.Person getGiver() {
        return giver;
    }

    public void setGiver(models.Person giver) {
        this.giver = giver;
    }

    public models.Person getReceiver() {
        return receiver;
    }

    public void setReceiver(models.Person receiver) {
        this.receiver = receiver;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

}