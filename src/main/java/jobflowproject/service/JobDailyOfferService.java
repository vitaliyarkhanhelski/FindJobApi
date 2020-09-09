package jobflowproject.service;

import jobflowproject.model.JobDailyOffer;
import jobflowproject.model.Tag;
import jobflowproject.model.Website;
import jobflowproject.repository.JobDailyOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JobDailyOfferService {

    @Autowired
    private PracujPlService pracujPlService;

    @Autowired
    private JobDailyOfferRepository jobDailyOfferRepository;

    public void addJobDailyOffer(Tag tag, Website website, String city) {
        try {
            Integer numberOfOffers = pracujPlService.getNumberOfJobs(tag.getName(), city);

            JobDailyOffer jobDailyOffer = JobDailyOffer.builder()
                    .city(city)
                    .date(LocalDateTime.now())
                    .number(numberOfOffers)
                    .tag(tag)
                    .website(website)
                    .build();

            jobDailyOfferRepository.save(jobDailyOffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
