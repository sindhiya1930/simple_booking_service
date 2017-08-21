package com.kirak.web.rest.manager;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.service.CityService;
import com.kirak.service.CountryService;
import com.kirak.service.HotelService;
import com.kirak.service.UserService;
import com.kirak.to.HotelTo;
import com.kirak.util.model.HotelUtil;
import com.kirak.web.View;
import com.kirak.web.abstr.HotelAbstractController;
import com.kirak.web.session.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Kir on 17.08.2017.
 */

@RestController
@RequestMapping(value = "/manager/objects")
public class ManagerHotelsController extends HotelAbstractController {

    @Autowired
    private UserService userService;

    @Autowired
    public ManagerHotelsController(HotelService hotelService, CountryService countryService, CityService cityService) {
        super(hotelService, countryService, cityService);
    }

    @PostMapping
    public void createOrUpdate(@Valid HotelTo hotelTo) {
        if (hotelTo.isNew()) {
            super.create(HotelUtil.createNewFromTo(hotelTo, super.getAllCountries(), super.getAllCities(),
                    userService.get(AuthorizedUser.getId())));
        } else {
            super.update(hotelTo, hotelTo.getId());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public HotelTo get(@PathVariable("id") Integer id) {
        return super.get(id);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HotelTo> getHotelsForManager() {
        return super.getHotelsForManager();
    }
}
