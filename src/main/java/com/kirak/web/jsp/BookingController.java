package com.kirak.web.jsp;

import com.kirak.model.Apartment;
import com.kirak.model.Booking;
import com.kirak.model.User;
import com.kirak.model.UserRole;
import com.kirak.service.*;
import com.kirak.to.BookingTo;
import com.kirak.to.HotelTo;
import com.kirak.util.model.*;
import com.kirak.web.AuthorizedUser;
import com.kirak.web.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Kir on 04.08.2017.
 */

@Controller
public class BookingController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private CityService cityService;

    @Autowired
    private AptTypeService aptTypeService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("citiesFive", RegionUtil.getFiveCitiesByHotelAmount(cityService.getAll()));
        model.addAttribute("hotelsFive", HotelUtil.getFiveHotelsByRating(hotelService.getAll()));
        return "index";
    }

    @GetMapping(value = "/get_by_city")
    public String searchByCity(@RequestParam("city") String city, Model model) {
        model.addAttribute("hotels", HotelUtil.getAllByCity(hotelService.getAll(), Integer.parseInt(city)));
        model.addAttribute("city", cityService.get(Integer.parseInt(city)));
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        return "hotels";
    }

    @GetMapping(value = "/search")
    public String searchHotelsByRegion(@RequestParam("region") String region, Model model){
        List<HotelTo> hotelsFound = HotelUtil.getAllByRegionAsTo(region, hotelService.getAll());
        if(!hotelsFound.isEmpty()) {
            model.addAttribute("hotels", hotelsFound);
            model.addAttribute("region", region);
        } else{
            model.addAttribute("badRegion", region);
        }
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        return "hotels";
    }

    @PostMapping(value = "/parametric_search")
    public String searchHotelsByParams(@RequestParam("location") String location, @RequestParam("personNum") String personNum,
                                       @RequestParam("inDate") String inDate, @RequestParam("outDate") String outDate,
                                       @RequestParam("category") String category, @RequestParam("priceFrom") String priceFrom,
                                       @RequestParam("priceTo") String priceTo, Model model){
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        model.addAttribute("hotels", HotelUtil.filterAvailableHotelsByRequest(location, hotelService.getAll(),
                LocalDate.parse(inDate), LocalDate.parse(outDate), Short.parseShort(personNum), category,
                Double.parseDouble(priceFrom), Double.parseDouble(priceTo)));
        return "hotels";
    }

    @GetMapping(value = "/inspect_hotel")
    public String hotel(@RequestParam("id") String hotelId, Model model) {
        HotelUtil.addUniqueHotelParams(hotelService.get(Integer.parseInt(hotelId)), model);
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        model.addAttribute("apartments", apartmentService.getAllByHotel(Integer.parseInt(hotelId)));
        model.addAttribute("hotel", HotelUtil.asTo(hotelService.get(Integer.parseInt(hotelId))));
        return "hotel";
    }

    @PostMapping(value = "/check_hotel_overall")
    public String checkOverallAvailability(@RequestParam ("hotelId") String hotelId, @RequestParam ("inDate") String inDate,
                                           @RequestParam ("outDate") String outDate, @RequestParam ("category") String category,
                                           @RequestParam ("personNum") String personNum, Model model) {
        LocalDate checkInDate = LocalDate.parse(inDate);
        LocalDate checkOutDate = LocalDate.parse(outDate);

        model.addAttribute("apartments", ApartmentUtil.findHotelApartmentsByRequest(hotelService
                .get(Integer.parseInt(hotelId)), checkInDate, checkOutDate, Short.parseShort(personNum), category));

        HotelUtil.addUniqueHotelParams(hotelService.get(Integer.parseInt(hotelId)), model);
        model.addAttribute("hotelId", hotelId);
        model.addAttribute("inDate", inDate);
        model.addAttribute("outDate", outDate);
        model.addAttribute("category", outDate);
        model.addAttribute("personNum", personNum);
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        return "hotel";
    }

    @PostMapping(value = "/check_hotel_apt")
    public String checkApartmentAvailability(@RequestParam ("apartmentId") String apartmentId, @RequestParam("hotelId") String hotelId,
                                             @RequestParam ("inDate") String inDate, @RequestParam ("outDate") String outDate, Model model) {

        Map<Apartment, Integer> availableApartment = ApartmentUtil.isHotelApartmentAvailableByRequest(apartmentService
                        .get(Integer.parseInt(apartmentId)), LocalDate.parse(inDate), LocalDate.parse(outDate));

        if(!availableApartment.isEmpty()){
            model.addAttribute("availableApartment", availableApartment.keySet().iterator().next());
            model.addAttribute("availableApartmentCount", availableApartment.entrySet().iterator().next());
        } else {
            model.addAttribute("requestedApartment", apartmentService.get(Integer.parseInt(apartmentId)));
        }
        model.addAttribute("hotel", hotelService.get(Integer.parseInt(hotelId)));
        model.addAttribute("inDate", inDate);
        model.addAttribute("outDate", outDate);
        return "hotel";
    }

    @PostMapping(value = "/check_booking")
    public String checkBooking(@RequestParam ("hotelId") String hotelId, @RequestParam ("apartmentId") String apartmentId,
                                      @RequestParam ("inDate") String inDate, @RequestParam ("outDate") String outDate,
                                      @RequestParam ("personNum") String personNum, Model model) {

        LocalDate checkInDate = LocalDate.parse(inDate);
        LocalDate checkOutDate = LocalDate.parse(outDate);
        double sum = BookingUtil.calculateBookingSum(apartmentService.get(Integer.parseInt(apartmentId)), checkInDate, checkOutDate);

        BookingTo bookingTo = new BookingTo(hotelService.get(Integer.parseInt(hotelId)),
                apartmentService.get(Integer.parseInt(apartmentId)), checkInDate, checkOutDate, Short.parseShort(personNum), sum);

        model.addAttribute("booking", bookingTo);
        model.addAttribute("hotelId", hotelId);
        model.addAttribute("apartmentId", apartmentId);
        model.addAttribute("inDate", inDate);
        model.addAttribute("outDate", outDate);
        model.addAttribute("personNum", personNum);
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        return "book";
    }

    @GetMapping(value = "/book")
    public String book(@RequestParam ("id") String apartmentId, Model model) {



        model.addAttribute("hotel", HotelUtil.asTo
                (hotelService.get(apartmentService.get(Integer.parseInt(apartmentId)).getHotel().getId())));
        model.addAttribute("apartment", apartmentService.get(Integer.parseInt(apartmentId)));

        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        return "book";
    }

    @PostMapping(value = "/confirm_anonymous_booking")
    public String confirmAnonymousBooking(@RequestParam ("id") String hotelId, @RequestParam ("apartmentId") String apartmentId,
                                          @RequestParam ("inDate") String inDate, @RequestParam ("outDate") String outDate,
                                          @RequestParam ("userName") String userName, @RequestParam ("userPhone") String userPhone,
                                          @RequestParam ("userEmail") String userEmail, @RequestParam ("personNum") String personNum,
                                          @RequestParam ("sum") String sum, Model model) {

        User user = new User(userName, userEmail, userPhone, UserRole.ROLE_USER);
        userService.save(user);

        Booking booking = new Booking(true, LocalDateTime.now(), LocalDateTime.parse(inDate),
                LocalDateTime.parse(outDate), Double.parseDouble(sum), Short.parseShort(personNum),
                (short) 0, userService.get(AuthorizedUser.getId()),
                apartmentService.get(Integer.parseInt(apartmentId)), hotelService.get(Integer.parseInt(hotelId)));

        model.addAttribute("booking", bookingService.save(booking, AuthorizedUser.getId(),
                Integer.parseInt(hotelId)));
        return "confirmation";
    }

    @PostMapping(value = "/confirm_customer_booking")
    public String confirmCustomerBooking(@RequestParam ("id") String hotelId, @RequestParam ("apartmentId") String apartmentId,
                                         @RequestParam ("inDate") String inDate, @RequestParam ("outDate") String outDate,
                                         @RequestParam ("personNum") String personNum, @RequestParam ("sum") String sum, Model model) {
        int userId = AuthorizedUser.getId();

        Booking booking = new Booking(true, LocalDateTime.now(), LocalDateTime.parse(inDate),
                LocalDateTime.parse(outDate), Double.parseDouble(sum), Short.parseShort(personNum),
                (short) 0, userService.get(AuthorizedUser.getId()),
                apartmentService.get(Integer.parseInt(apartmentId)), hotelService.get(Integer.parseInt(hotelId)));

        //model.addAttribute("booking", bookingService.save(booking, ))
        return "confirmation";
    }

}
