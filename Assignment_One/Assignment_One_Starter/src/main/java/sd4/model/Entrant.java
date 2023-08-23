package sd4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Entrant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="venue_ID")
    private Integer venueID;
    
    private String logo;


    @NotBlank(message = "{entrant.hostCityNotEmpty}")
    @Column(name="host_City")
    private String hostCity;

    @DateTimeFormat (pattern = "dd/MM/yyyy")

    @Temporal(TemporalType.DATE)
    @Column(name="date_Of_Final")
    private Date dateOfFinal;


    @NotBlank(message = "{entrant.hostCountryNotEmpty}")
    @Column(name="hostCountry")
    private String hostCountry;

    private String section;

    @NotBlank(message = "{entrant.artistNameNotEmpty}")
    @Size(min=5, message = "{entrant.artistNameFiveChars}")
    private String artist;
    private String song;

    @NotBlank(message = "{entrant.artistCountryNotEmpty}")
    @Column(name="artist_Country")
    private String artistCountry;

    @Column(name="running_Order")
    private String runningOrder;

    @Column(name="total_Points")
    private Integer totalPoints;

    private String rank;
    private String qualified;
}
