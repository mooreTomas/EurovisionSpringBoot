package sd4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import sd4.model.Venue;
import org.springframework.stereotype.Service;
import sd4.repository.VenueRepository;

@Service
public class    VenueService {
    @Autowired

    private VenueRepository venueRepo;

    public List<Venue> findAllVenues(){
        return (List<Venue>) venueRepo.findAll();
    }

    public List<Venue> getVenueByVenueID(String id) {
        return venueRepo.getVenueByVenueID(String.valueOf(Integer.parseInt(id)));
    }

    public List<Venue> getVenueNames(){
        return venueRepo.getVenueNames();
    }

    public List<Venue> findVenueByName(String name){
        return venueRepo.findVenueByName(name);
    }

    public List<Venue> getVenuesByVenueID(String id){
        return venueRepo.getVenuesByVenueID(id);
    }

    public String getVenueNameById(String id){
        return venueRepo.getVenueNameById(id);
    }

    public String getVenueIdByName(String name){
        return venueRepo.getVenueIdByName(name);
    }












}


