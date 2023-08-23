package sd4.repository;

import org.springframework.data.repository.query.Param;
import sd4.model.Entrant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository

public interface EntrantRepository extends CrudRepository<Entrant, Integer> {

    List<Entrant> findEntrantsById(Long id);

    @Query("select e.venueID from Entrant e where e.id = ?1")
    String findVenueIdByEntrantId(String id);






    @Query("select e from Entrant e where e.hostCountry like %?1% order by e.totalPoints desc")
    List<Entrant> getByCountryFilter(String countryKeyword);

    @Query("select e from Entrant e where e.dateOfFinal between :start AND :end order by e.totalPoints desc ")
    List<Entrant> getEntrantByDateOfFinal(@Param("start") Date start, @Param("end") Date end);

   // @Query("select e from Entrant e where e.hostCountry like %?1% AND e.dateOfFinal between :start AND :end AND e.venueID = :venueID")
    //List<Entrant> getEntrantBetweenDatesWhereVenueIdMatchesVenueNameAndMatchesCountry(String countryKeyword, @Param("start") Date start, @Param("end") Date end, @Param("venueID") String venueID);

    @Query("select e from Entrant e where e.artistCountry = :countryKeyword  AND e.dateOfFinal between :start AND :end AND e.venueID = :venueID")
    List<Entrant> getEntrantBetweenDatesWhereVenueIdMatchesVenueNameAndMatchesCountry(@Param("countryKeyword") String countryKeyword, @Param("start") Date start, @Param("end") Date end, @Param("venueID") String venueID);


    //also need venueID based on venueName. get this from venue table
    //see getvenuesbyvenueid


    List<Entrant> findEntrantsByDateOfFinalBetween(Date startDate, Date endDate);




    List<Entrant> findEntrantByDateOfFinalBetweenAndAndHostCountryOrderByTotalPointsDesc(Date startDate, Date endDate, String countryKeyword);

    List <Entrant> findEntrantById (String id);


    List<Entrant> findEntrantByDateOfFinalBetweenAndAndHostCountryAndTotalPointsGreaterThan(Date startDate, Date endDate, String countryKeyword, String points);







}
