package com.kirak.to;

import com.kirak.to.booking.ChartTo;
import com.kirak.to.booking.ManagerBookingTo;
import com.kirak.util.session.SessionObjectsIdGenerator;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Kir on 19.08.2017.
 */

public class ManagerObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer hotelId;

    private Integer managerId;

    private List<ApartmentTo> objectApartmentTos;

    private List<ManagerBookingTo> objectManagerBookingTos;

    private List<ChartTo> objectChartTos;

    private List<VoteTo> objectVotes;

    public ManagerObject(Integer managerId,
                         Integer hotelId,
                         List<ApartmentTo> objectApartmentTos,
                         List<ManagerBookingTo> objectManagerBookingTos,
                         List<ChartTo> objectChartTos,
                         List<VoteTo> objectVotes) {
        this.id = SessionObjectsIdGenerator.getNewId();
        this.hotelId = hotelId;
        this.managerId = managerId;
        this.objectApartmentTos = objectApartmentTos;
        this.objectManagerBookingTos = objectManagerBookingTos;
        this.objectChartTos = objectChartTos;
        this.objectVotes = objectVotes;
    }

    public Integer getId() {
        return id;
    }

    public List<ApartmentTo> getObjectApartmentTos() {
        return objectApartmentTos;
    }

    public void setObjectApartmentTos(List<ApartmentTo> objectApartmentTos) {
        this.objectApartmentTos = objectApartmentTos;
    }

    public List<ManagerBookingTo> getObjectManagerBookingTos() {
        return objectManagerBookingTos;
    }

    public void setObjectManagerBookingTos(List<ManagerBookingTo> objectManagerBookingTos) {
        this.objectManagerBookingTos = objectManagerBookingTos;
    }

    public List<ChartTo> getObjectChartTos() {
        return objectChartTos;
    }

    public void setObjectChartTos(List<ChartTo> objectChartTos) {
        this.objectChartTos = objectChartTos;
    }

    public List<VoteTo> getObjectVotes() {
        return objectVotes;
    }

    public void setObjectVotes(List<VoteTo> objectVotes) {
        this.objectVotes = objectVotes;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return "ManagerObject{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", managerId=" + managerId +
                ", objectApartmentTos=" + objectApartmentTos +
                ", objectManagerBookingTos=" + objectManagerBookingTos +
                ", objectChartTos=" + objectChartTos +
                ", objectVotes=" + objectVotes +
                '}';
    }
}
