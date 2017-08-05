package com.kirak.util.model;

import com.kirak.model.Apartment;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by Kir on 21.06.2017.
 */
public class BookingUtil {

    //Long id, boolean active, LocalDateTime dateAdded, LocalDateTime inDate, LocalDateTime outDate,
    //Double sum, Short personNum, Short extraBeds, User user, Apartment apartment, Hotel hotel

    //Long id, boolean active, LocalDateTime dateAdded, LocalDateTime inDate, LocalDateTime outDate,
    //Double sum, Short personNum, Short extraBeds, User user, Apartment apartment, Hotel hotel


    public static Double calculateBookingSum(Apartment apartment, LocalDate startDate, LocalDate endDate){

        return apartment.getPrice() * DAYS.between(startDate, endDate);
    }

}
