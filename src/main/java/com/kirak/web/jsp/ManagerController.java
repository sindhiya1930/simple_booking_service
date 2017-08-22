package com.kirak.web.jsp;

import com.kirak.model.*;
import com.kirak.service.*;
import com.kirak.to.ManagerObject;
import com.kirak.util.model.ManagerObjectUtil;
import com.kirak.web.ModelUtil;
import com.kirak.web.session.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Kir on 03.08.2017.
 */

@Controller
public class ManagerController {

    @Autowired
    private AptTypeService aptTypeService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    @Qualifier("managerObjectService")
    private ManagerObjectService managerObjectService;


    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/hotel_manager/manage_object")
    public String manageObject(Model model, @RequestParam("id") int hotelId){
        //int hotelId = Integer.parseInt(id);
        Hotel hotel = hotelService.get(hotelId);

        int managerId = AuthorizedUser.id();

        List<Booking> activeHotelBookings = bookingService.getAll().stream()
                .filter(booking -> Objects.equals(booking.getHotel().getId(), hotelId)
                        && Objects.equals(booking.getHotel().getManager().getId(), managerId))
                .filter(booking -> booking.getSuperBooking().isActive())
                .collect(Collectors.toList());

        ManagerObject managerObject = ManagerObjectUtil.createManagerObjectFromHotelId(managerId,
                new ArrayList<>(hotel.getApartments()),
                new ArrayList<>(activeHotelBookings),
                new ArrayList<>(hotel.getSuperBookings()),
                new ArrayList<>(hotel.getVotes()));

        managerObjectService.addManagerObject(managerObject);

        ModelUtil.getManagerView(model, aptTypeService.getAll(), countryService.getAll(), cityService.getAll(),
                managerObject.getObjectApartmentTos());
        return "object";
    }



    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/hotel_manager/object/show_chart")
    public String showChart(Model model) {
        int hotelManagerId = AuthorizedUser.id();
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        ModelUtil.getManagerView(model, aptTypeService.getAll(), countryService.getAll(), cityService.getAll(),
                managerObject.getObjectApartmentTos());
        return "objectHotelChart";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/hotel_manager/object/show_super_bookings")
    public String showBookings(Model model) {
        int hotelManagerId = AuthorizedUser.id();
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        ModelUtil.getManagerView(model, aptTypeService.getAll(), countryService.getAll(), cityService.getAll(),
                managerObject.getObjectApartmentTos());
        return "objectSuperBookings";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/hotel_manager/object/show_hotel_votes")
    public String showObjectVotes(Model model) {
        int hotelManagerId = AuthorizedUser.id();
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        ModelUtil.getManagerView(model, aptTypeService.getAll(), countryService.getAll(), cityService.getAll(),
                managerObject.getObjectApartmentTos());
        return "objectHotelVotes";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/hotel_manager/object/show_apartments")
    public String showApartments(Model model) {
        int hotelManagerId = AuthorizedUser.id();
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        ModelUtil.getManagerView(model, aptTypeService.getAll(), countryService.getAll(), cityService.getAll(),
                managerObject.getObjectApartmentTos());
        return "objectApartments";
    }


}
