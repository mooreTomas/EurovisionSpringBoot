package sd4.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import sd4.model.Entrant;
import sd4.repository.EntrantRepository;
import org.springframework.stereotype.Service;

@Service
public class    EntrantService {
    @Autowired

    private EntrantRepository entrantRepo;

    public List<Entrant> findAllEntrants(){
        return (List<Entrant>) entrantRepo.findAll();
    }


    public List<Entrant> getByCountryFilter(String countryKeyword){
        return entrantRepo.getByCountryFilter(countryKeyword);
    }

    public List<Entrant> getByDateFilter(Date dateKeyword1, Date dateKeyword2){
        return entrantRepo.getEntrantByDateOfFinal(dateKeyword1, dateKeyword2);
    }

    public List<Entrant> findEntrantsByDateOfFinalBetween(Date startDate, Date endDate){
        return entrantRepo.findEntrantsByDateOfFinalBetween(startDate, endDate);
    }

    public List<Entrant>  findEntrantById(String id){
        return entrantRepo.findEntrantById(id);
    }





    public List<Entrant>  getByCountryAndDate(Date startDate, Date endDate, String countryKeyword){
        return entrantRepo.findEntrantByDateOfFinalBetweenAndAndHostCountryOrderByTotalPointsDesc(startDate, endDate, countryKeyword);
    }

    public List<Entrant> getByCountryAndDateAndTotalPointsGreaterThan(Date startDate, Date endDate, String countryKeyword, String points){
        return entrantRepo.findEntrantByDateOfFinalBetweenAndAndHostCountryAndTotalPointsGreaterThan(startDate, endDate, countryKeyword, points);
    }

    public String findVenueIdByEntrantId(String id){
        return entrantRepo.findVenueIdByEntrantId(id);

    }


    public List<Entrant> getEntrantBetweenDatesWhereVenueIdMatchesVenueNameAndMatchesCountry(String countryKeyword, Date startDate, Date endDate, String venueId){
        return entrantRepo.getEntrantBetweenDatesWhereVenueIdMatchesVenueNameAndMatchesCountry(countryKeyword, startDate, endDate, venueId);
    }

    public Optional<Entrant> findOne(Long id){
        return entrantRepo.findById(Math.toIntExact(id));
    }


    public void saveEntrant(Entrant a) {
        entrantRepo.save(a);
    }



}
