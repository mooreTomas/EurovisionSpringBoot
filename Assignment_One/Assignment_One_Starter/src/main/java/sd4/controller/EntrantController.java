package sd4.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import sd4.model.Entrant;
import sd4.model.Venue;
import sd4.service.EntrantService;
import sd4.service.VenueService;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Controller
// @RequestMapping("viewAllEntrants")

public class EntrantController {
    @Autowired

    private EntrantService entrantService;

    @Autowired
    private VenueService venueService;

    @GetMapping("/viewAllEntrants")
    public String displayAllEntrants(Model model, String countryKeyword, String startDate, String endDate, Locale locale) throws ParseException {
        List<Entrant> entrants;
        List<Venue> venues;
        venues = venueService.findAllVenues();
        List<Venue> venue;


        if (countryKeyword == null && startDate == null && endDate == null) {

            entrants = entrantService.findAllEntrants();
        } else if (countryKeyword == "" && startDate != null && endDate != null) {
            SimpleDateFormat startD = new SimpleDateFormat("yyyy-MM-dd");
            Date date = startD.parse(startDate);
            SimpleDateFormat endD = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = endD.parse(endDate);
            entrants = entrantService.getByDateFilter(date, date2);
        } else if (countryKeyword != null && startDate == "" && endDate == "") {

            entrants = entrantService.getByCountryFilter(countryKeyword);
        } else if (countryKeyword != null && startDate != null && endDate != null) {
            SimpleDateFormat startD = new SimpleDateFormat("yyyy-MM-dd");
            Date date = startD.parse(startDate);
            SimpleDateFormat endD = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = endD.parse(endDate);
            entrants = entrantService.getByCountryAndDate(date, date2, countryKeyword);
        } else {
            return "error";
        }


        model.addAttribute("entrants", entrants);
        model.addAttribute("venues", venues);
        model.addAttribute("locale", locale);
        //model.addAttribute("venue", venue);
        return "viewAllEntrants";
    }

    @GetMapping("/viewEntrantByVenueAndDates")
    public String displayEntrantsVenue(Model model, String artistCountry, String startDate2, String endDate2, String venueName) throws ParseException {
        List<Entrant> entrants;
        List<Venue> venues;
        venues = venueService.findAllVenues();
        List<Venue> venue;
        venue = venueService.findVenueByName(venueName);



            SimpleDateFormat startD = new SimpleDateFormat("yyyy-MM-dd");
            Date date = startD.parse(startDate2);
            SimpleDateFormat endD = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = endD.parse(endDate2);
            // entrants = entrantService.getByCountryAndDate(date, date2, countryKeyword);

            // need to get the events/venues corresponding to the date range and country input
            // get venueID by using the venueName (from Entrant table)
            // then use this id for main query

            String venueId = venueService.getVenueIdByName(venueName);




            entrants = entrantService.getEntrantBetweenDatesWhereVenueIdMatchesVenueNameAndMatchesCountry(artistCountry, date, date2, venueId);






        model.addAttribute("entrants", entrants);
        model.addAttribute("venues", venues);
        model.addAttribute("venue", venue);
        model.addAttribute("localDate");
        return "viewEntrantByVenueAndDates";


    }









    @GetMapping("/drillDown")
    public String drillDown(Model model, String id){
        List<Entrant> entrant;
        List<Venue> venue;
        venue = venueService.getVenueByVenueID(entrantService.findVenueIdByEntrantId(id));
        entrant = entrantService.findEntrantById(id);

        // GET VENUEid FOR


        // List<Venue> venue2;
       // venue2= venueService.getVenueByVenueID(String.valueOf(5));


        model.addAttribute("entrant", entrant);
        model.addAttribute("venue", venue);
        return "drillDown";
    }








    @GetMapping("/viewAllEntrants2")
    public String displayAllEntrants2(Model model, String startDate, String endDate) throws ParseException {
        List<Entrant> entrants;



        if (startDate  == null || endDate == null){
            entrants = entrantService.findAllEntrants();
        } else {
            SimpleDateFormat startD = new SimpleDateFormat("yyyy-MM-dd");
            Date date = startD.parse(startDate);
            SimpleDateFormat endD = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = endD.parse(endDate);
            entrants = entrantService.findEntrantsByDateOfFinalBetween(date, date2);
        }

        model.addAttribute("entrants", entrants);
        return "/viewAllEntrants2";
    }






    @GetMapping("/")
    public ModelAndView  displayAllEntrants3(ModelAndView modelAndView){
        List<Entrant> entrants;
        List<Venue> venues;


        entrants = entrantService.findAllEntrants();
        venues = venueService.findAllVenues();


        modelAndView.addObject("entrants", entrants);
        modelAndView.addObject("venues", venues);
        //modelAndView.addObject("standardDate", new Date());
        //modelAndView.addObject("localDateTime", LocalDateTime.now());
        //modelAndView.addObject("localDate", LocalDate.now());
        //modelAndView.addObject("timestamp", Instant.now());
       // modelAndView.setViewName("viewAllEntrants");

        return modelAndView;
    }


    @GetMapping("/editEntrant/{id}")
    public ModelAndView displayEditForm(@PathVariable("id") long id) {
        if (entrantService.findOne(id).isEmpty())
            return new ModelAndView("/error", "error", "Entrant not found");
        else
            return new ModelAndView("/editEntrant", "anEntrant", entrantService.findOne(id).get());
    }

    @PostMapping("/editEntrant")
    public ModelAndView editABook(@Valid @ModelAttribute("anEntrant") Entrant e, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("/editEntrant");
        }
        entrantService.saveEntrant(e);
        return new ModelAndView("redirect:/viewAllEntrants");

    }





    /* method for date search. combine these methods
*/






}
