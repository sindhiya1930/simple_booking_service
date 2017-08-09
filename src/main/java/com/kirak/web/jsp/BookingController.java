package com.kirak.web.jsp;

import com.kirak.model.*;
import com.kirak.service.*;
import com.kirak.to.BookingTo;
import com.kirak.to.HotelTo;
import com.kirak.to.Placement;
import com.kirak.util.model.*;
import com.kirak.web.AuthorizedUser;
import com.kirak.web.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by Kir on 04.08.2017.
 */

@Controller
//@Scope("session")
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
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
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

    @GetMapping(value = "/parametric_search")
    public String searchHotelPlacementsByParams(@RequestParam("location") String location,
                                                @RequestParam("personNum") String personNum, @RequestParam("inDate") String inDate,
                                                @RequestParam("outDate") String outDate, @RequestParam("category") String category,
                                                @RequestParam("apartmentNum") String apartmentNum, Model model){
        ModelUtil.addUniqueFilterParams(model, aptTypeService);

        model.addAttribute("placements", HotelUtil.filterAvailablePlacementsByRequest(location, hotelService.getAll(),
                Short.parseShort(personNum), Integer.parseInt(apartmentNum),
                LocalDate.parse(inDate), LocalDate.parse(outDate), category));
        model.addAttribute("placementPersonNum", personNum);
        model.addAttribute("placementApartmentNum", apartmentNum);
        model.addAttribute("placementInDate", inDate);
        model.addAttribute("placementOutDate", outDate);
        return "hotels";
    }

    @GetMapping(value = "/inspect_placement")
    public String placement(@RequestParam("id") String placementId, @RequestParam("personNum") String placementPersonNum,
                            @RequestParam("apartmentNum") String placementApartmentNum, @RequestParam("inDate") String inDate,
                            @RequestParam("outDate") String outDate, @RequestParam Model model) {
        int hotelId = PlacementUtil.getHotelIdFromPlacementId(Integer.parseInt(placementId));
        double placementSum = PlacementUtil.calculateBookingSumForPlacement(PlacementUtil.getPlacementFromId(Integer.parseInt(placementId)));
        HotelUtil.addUniqueHotelParams(hotelService.get(hotelId), model);
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        model.addAttribute("placement", PlacementUtil.getPlacementFromId(Integer.parseInt(placementId)));
        model.addAttribute("placementSum", placementSum);
        model.addAttribute("placementApartmentNum", placementApartmentNum);
        model.addAttribute("placementPersonNum", placementPersonNum);
        model.addAttribute("placementInDate", inDate);
        model.addAttribute("placementOutDate", outDate);
        model.addAttribute("apartments", apartmentService.getAllByHotel(hotelId));
        model.addAttribute("hotel", HotelUtil.asHotelTo(hotelService.get(hotelId)));
        return "hotel";
    }

    @GetMapping(value = "/inspect_hotel")
    public String hotel(@RequestParam("id") String hotelId, Model model) {
        HotelUtil.addUniqueHotelParams(hotelService.get(Integer.parseInt(hotelId)), model);
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        model.addAttribute("apartments", apartmentService.getAllByHotel(Integer.parseInt(hotelId)));
        model.addAttribute("hotel", HotelUtil.asHotelTo(hotelService.get(Integer.parseInt(hotelId))));
        return "hotel";
    }

    @PostMapping(value = "/check_hotel_overall")
    public String checkOverallAvailability(@RequestParam ("hotelId") String hotelId, @RequestParam ("inDate") String inDate,
                                           @RequestParam ("outDate") String outDate, @RequestParam ("category") String category,
                                           @RequestParam ("personNum") String personNum, Model model) {
        LocalDate checkInDate = LocalDate.parse(inDate);
        LocalDate checkOutDate = LocalDate.parse(outDate);

//        model.addAttribute("apartments", ApartmentUtil.findHotelApartmentsByRequest(hotelService
//                .get(Integer.parseInt(hotelId)), checkInDate, checkOutDate, Short.parseShort(personNum), category));

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
    public String checkApartmentAvailability(@RequestParam ("apartmentId") String apartmentId,
                                             @RequestParam("hotelId") String hotelId, @RequestParam ("aptInDate") String inDate,
                                             @RequestParam ("aptOutDate") String outDate, Model model) {

        Map<Apartment, Integer> availableApartmentMap = ApartmentUtil.isHotelApartmentAvailableByRequest(apartmentService
                        .get(Integer.parseInt(apartmentId)), LocalDate.parse(inDate), LocalDate.parse(outDate));

        if(!availableApartmentMap.isEmpty()){
            model.addAttribute("availableAptNum", availableApartmentMap.values().iterator().next());

            Placement placement = PlacementUtil.convertAvailableApartmentToPlacement(availableApartmentMap.keySet().iterator().next());
            model.addAttribute("placement", placement);
            model.addAttribute("placementSum", PlacementUtil.calculateBookingSumForPlacement(placement));
            model.addAttribute("placementApartmentNum", 1);
            model.addAttribute("placementPersonNum", apartmentService.get(Integer.parseInt(apartmentId)).getType().getPersonNum());
            model.addAttribute("placementInDate", inDate);
            model.addAttribute("placementOutDate", outDate);
        } else {
            model.addAttribute("notAvailableApartment", apartmentService.get(Integer.parseInt(apartmentId)));
        }
        model.addAttribute("apartments", apartmentService.getAllByHotel(Integer.parseInt(hotelId)));
        model.addAttribute("hotel", hotelService.get(Integer.parseInt(hotelId)));
        return "hotel";
    }

    @PostMapping(value = "/check_booking")
    public String checkBooking(@RequestParam ("bookingHotelId") String hotelId, @RequestParam ("bookingSum") String sum,
                               @RequestParam ("bookingApartmentNum") String apartmentNum, @RequestParam ("bookingPlacementId") String placementId,
                               @RequestParam ("bookingInDate") String inDate, @RequestParam ("bookingOutDate") String outDate,
                               @RequestParam ("bookingPersonNum") String personNum, Model model) {

        model.addAttribute("hotel", hotelService.get(Integer.parseInt(hotelId)));
        model.addAttribute("placement", PlacementUtil.getPlacementFromId(Integer.parseInt(placementId)));
        model.addAttribute("placementSum", sum);
        model.addAttribute("placementApartmentNum", apartmentNum);
        model.addAttribute("placementPersonNum", personNum);
        model.addAttribute("placementInDate", inDate);
        model.addAttribute("placementOutDate", outDate);
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        return "book";
    }

    @PostMapping(value = "/confirm_anonymous_booking")
    public String confirmAnonymousBooking(@RequestParam ("bookingHotelId") String hotelId, @RequestParam ("bookingSum") String sum,
                                          @RequestParam ("bookingApartmentNum") String apartmentNum,
                                          @RequestParam ("bookingPlacementId") String placementId,
                                          @RequestParam ("bookingInDate") String inDate, @RequestParam ("bookingOutDate") String outDate,
                                          @RequestParam ("bookingPersonNum") String personNum, @RequestParam ("userName") String userName,
                                          @RequestParam ("userPhone") String userPhone, @RequestParam ("userEmail") String userEmail, Model model) {

        User user = new User(userName, userEmail, userPhone, UserRole.ROLE_USER);
        userService.save(user);

        //        SuperBooking(boolean active, LocalDateTime dateAdded, Short extraBeds, Double overallSum,
//                Short overallPersonNum, User user, Set<Booking> bookings)

//        BookingTo bookingTo = new BookingTo(hotelService.get(Integer.parseInt(hotelId)),
//                apartmentService.get(Integer.parseInt(apartmentId)), checkInDate, checkOutDate, Short.parseShort(personNum), sum);

//        Booking booking = new Booking(true, LocalDateTime.now(), LocalDateTime.parse(inDate),
//                LocalDateTime.parse(outDate), Double.parseDouble(sum), Short.parseShort(personNum),
//                (short) 0, userService.get(AuthorizedUser.getId()),
//                apartmentService.get(Integer.parseInt(apartmentId)), hotelService.get(Integer.parseInt(hotelId)));
//        model.addAttribute("booking", bookingService.save(booking, AuthorizedUser.getId(),
//                Integer.parseInt(hotelId)));
        return "confirmation";
    }




//
//    @PostMapping(value = "/confirm_customer_booking")
//    public String confirmCustomerBooking(@RequestParam ("id") String hotelId, @RequestParam ("apartmentId") String apartmentId,
//                                         @RequestParam ("inDate") String inDate, @RequestParam ("outDate") String outDate,
//                                         @RequestParam ("personNum") String personNum, @RequestParam ("sum") String sum, Model model) {
//        int userId = AuthorizedUser.getId();

//        Booking booking = new Booking(true, LocalDateTime.now(), LocalDateTime.parse(inDate),
//                LocalDateTime.parse(outDate), Double.parseDouble(sum), Short.parseShort(personNum),
//                (short) 0, userService.get(AuthorizedUser.getId()),
//                apartmentService.get(Integer.parseInt(apartmentId)), hotelService.get(Integer.parseInt(hotelId)));

        //model.addAttribute("booking", bookingService.save(booking, ))
//        return "confirmation";
//    }

    //    @GetMapping(value = "/book")
//    public String book(@RequestParam ("id") String apartmentId, Model model) {
//        model.addAttribute("hotel", HotelUtil.asHotelTo
//                (hotelService.get(apartmentService.get(Integer.parseInt(apartmentId)).getHotel().getId())));
//        model.addAttribute("apartment", apartmentService.get(Integer.parseInt(apartmentId)));
//
//        ModelUtil.addUniqueFilterParams(model, aptTypeService);
//        return "book";
//    }

}
