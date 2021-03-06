package com.kirak.to.booking;

import com.fasterxml.jackson.annotation.*;
import com.kirak.to.abstr.BasicIntTo;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Kir on 13.08.2017.
 */
public class AdminBookingTo extends BasicIntTo implements Serializable {

    private boolean active;

    private String dateAdded;

    private LocalDate inDate;

    private LocalDate outDate;

    private Integer hotelId;

    private String hotelName;

    private Integer userId;

    public AdminBookingTo(){}

    public AdminBookingTo(@JsonProperty("id") Integer id,
                          @JsonProperty("active") boolean active,
                          @JsonProperty("dateAdded") String dateAdded,
                          @JsonProperty("inDate") LocalDate inDate,
                          @JsonProperty("outDate") LocalDate outDate,
                          @JsonProperty("hotelId") Integer hotelId,
                          @JsonProperty("hotelName") String hotelName,
                          @JsonProperty("userId") Integer userId) {
        super(id);
        this.active = active;
        this.dateAdded = dateAdded;
        this.inDate = inDate;
        this.outDate = outDate;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.userId = userId;
    }

    
    public boolean isActive() {
        return active;
    }
    
    public LocalDate getInDate() {
        return inDate;
    }

    public LocalDate getOutDate() {
        return outDate;
    }
    
    public Integer getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setInDate(LocalDate inDate) {
        this.inDate = inDate;
    }

    public void setOutDate(LocalDate outDate) {
        this.outDate = outDate;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

//    @Override
//    public String toString() {
//        return "AdminBookingTo{" +
//                "id=" + id +
//                ", active=" + active +
//                ", dateAdded=" + dateAdded +
//                ", inDate=" + inDate +
//                ", outDate=" + outDate +
//                ", hotelId=" + hotelId +
//                ", hotelName='" + hotelName + '\'' +
//                ", userId=" + userId +
//                '}';
//    }
}
