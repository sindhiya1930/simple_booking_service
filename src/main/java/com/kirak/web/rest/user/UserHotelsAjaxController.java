package com.kirak.web.rest.user;

import com.kirak.service.*;
import com.kirak.to.HotelTo;
import com.kirak.web.abstr.HotelAbstractController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kir on 03.08.2017.
 */

@RestController
@RequestMapping("/user/hotels")
public class UserHotelsAjaxController extends HotelAbstractController {

    public UserHotelsAjaxController(HotelService hotelService, CountryService countryService, CityService cityService) {
        super(hotelService, countryService, cityService);
    }

    @Override
    @GetMapping(value = "/{id}")
    public HotelTo get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HotelTo> getHotelsForUser() {
        return super.getHotelsForUser();
    }
}
