package sd4.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sd4.model.Venue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface VenueRepository extends CrudRepository<Venue, Integer> {

    List<Venue> getEntrantByVenueID(String id);

    List<Venue> getVenueByVenueID(String id);

    @Query ("select v.name from Venue v")
    List<Venue> getVenueNames();

    List<Venue> findVenueByName(String name);

    List<Venue> getVenuesByVenueID(String id);

    @Query("select v.name from Venue  v where v.name = :id")
    String getVenueNameById(@Param("id") String id);

    @Query("select v.venueID from Venue v where v.name = :name")
    String getVenueIdByName(@Param("name") String name);










}
