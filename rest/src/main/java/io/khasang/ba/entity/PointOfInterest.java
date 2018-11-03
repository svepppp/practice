package io.khasang.ba.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalTime;

/**
 *
 * POI - Point of Interest. This is object include any params i.e. address, geographical coordinates,
 * include category etc.
 */

@Entity
@Table(name = "poi")
public class Poi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ColumnDefault(value = "'безымянный'")
    private String category;

    @Column(columnDefinition = "TIME")
    @ColumnDefault(value = "'00:00:00'")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startWork;

    @ColumnDefault(value = "0")
    private int workTime;
    private String address;

    //Geographic coordinates latitude
    @ColumnDefault(value = "0.000000")
    private double latitude;

    //Geographic coordinates longitude
    @ColumnDefault(value = "0.000000")
    private double longitude;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalTime getStartWork() {
        return startWork;
    }

    public void setStartWork(LocalTime startWork) {
        this.startWork = startWork;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime >= 0 ? workTime : 0;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
