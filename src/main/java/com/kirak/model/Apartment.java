package com.kirak.model;

import com.kirak.model.abstraction.BaseIntEntity;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Kir on 30.05.2017.
 */


@NamedQueries({
        @NamedQuery(name = Apartment.DELETE, query = "DELETE FROM Apartment a WHERE a.id=:apartmentId " +
                "AND a.hotel.id=:hotelId"),
        @NamedQuery(name = Apartment.GET_ALL_BY_PRICE, query = "SELECT a FROM Apartment a WHERE a.hotel.id=:hotelId " +
                "ORDER BY a.price ASC")
})

@Entity
@Table(name = "apartment")
public class Apartment extends BaseIntEntity {

    public static final String GET_ALL_BY_PRICE = "Apartment.getAllByHotel";
    public static final String DELETE = "Apartment.delete";

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apt_type_id")
    @NotNull
    private AptType type;

    @NotNull
    @Column(name = "overallQuantity")
    private Short overallQuantity;

    @NotNull
    @Column(name = "reservedQuantity")
    private Short reservedQuantity;

    @Column(name = "price", precision=8, scale=2)
    @NotNull
    private Double price;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    public Apartment(Integer id, AptType type, Short overallQuantity, Short reservedQuantity, Double price, Hotel hotel) {
        super(id);
        this.type = type;
        this.overallQuantity = overallQuantity;
        this.reservedQuantity = reservedQuantity;
        this.price = price;
        this.hotel = hotel;
    }

    public AptType getType() {
        return type;
    }

    public void setType(AptType type) {
        this.type = type;
    }

    public Short getOverallQuantity() {
        return overallQuantity;
    }

    public void setOverallQuantity(Short overallQuantity) {
        this.overallQuantity = overallQuantity;
    }

    public Short getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(Short reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Apartment{" +
                "id='" + getId() + '\'' +
                ", type='" + type + '\'' +
                ", overall numbers quantity=" + overallQuantity +
                ", reserved numbers quantity=" + reservedQuantity +
                ", hotel=" + hotel +
                ", price=" + price +
                '}';
    }
}
