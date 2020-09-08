package jobflowproject.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class JobDailyOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    private LocalDateTime date;

    private Integer description;

    @ManyToOne
    @JoinColumn(name = "website_id")
    private  Website website;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    private String city;
}