package com.kirak.to.booking;

import com.fasterxml.jackson.annotation.*;
import com.kirak.model.Apartment;
import com.kirak.model.Hotel;
import com.kirak.model.abstraction.BaseLongEntity;
import com.kirak.to.abstr.BasicLongTo;
import com.kirak.util.DateTimeUtil;
import com.kirak.web.View;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Kir on 25.06.2017.
 */
public class SubBookingTo extends BasicLongTo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer aptId;

    private String stringAptType;

    private Double aptPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate aptInDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate aptOutDate;

    private Double sum;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime edited;

    public SubBookingTo(){}

    public SubBookingTo(@JsonProperty("id") Long id,
                        @JsonProperty("aptId") Integer aptId,
                        @JsonProperty("aptCategory") String stringAptType,
                        @JsonProperty("aptPrice") Double aptPrice,
                        @JsonProperty("inDate") LocalDate aptInDate,
                        @JsonProperty("outDate") LocalDate aptOutDate,
                        @JsonProperty("sum") Double sum,
                        @JsonProperty("edited") LocalDateTime edited) {
        super(id);
        this.stringAptType = stringAptType;
        this.aptPrice = aptPrice;
        this.aptInDate = aptInDate;
        this.aptOutDate = aptOutDate;
        this.sum = sum;
        this.aptId = aptId;
        this.edited = edited;
    }

    
    public String getStringAptType() {
        return stringAptType;
    }

    public Double getAptPrice() {
        return aptPrice;
    }
    
    public LocalDate getAptInDate() {
        return aptInDate;
    }
    
    public LocalDate getAptOutDate() {
        return aptOutDate;
    }
    
    public Double getSum() {
        return sum;
    }
    
    public Integer getAptId() {
        return aptId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setAptId(Integer aptId) {
        this.aptId = aptId;
    }

    public void setStringAptType(String stringAptType) {
        this.stringAptType = stringAptType;
    }

    public void setAptPrice(Double aptPrice) {
        this.aptPrice = aptPrice;
    }

    public void setAptInDate(LocalDate aptInDate) {
        this.aptInDate = aptInDate;
    }

    public void setAptOutDate(LocalDate aptInDate) {
        this.aptOutDate = aptInDate;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @JsonGetter
    @JsonView(View.JsonUI.class)
    @JsonFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    public LocalDateTime getEdited() {
        return edited;
    }

    @JsonFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    public void setEdited(LocalDateTime edited) {
        this.edited = edited;
    }

    @Override
    public String toString() {
        return "SubBookingTo{" +
                "id=" + id +
                ", aptId=" + aptId +
                ", stringAptType='" + stringAptType +
                ", aptPrice=" + aptPrice +
                ", inDate=" + aptInDate +
                ", outDate=" + aptOutDate +
                ", sum=" + sum +
                '}';
    }
}
