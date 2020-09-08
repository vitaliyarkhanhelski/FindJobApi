package jobflowproject.data;

import jobflowproject.model.JobDailyOffer;
import jobflowproject.model.Tag;
import jobflowproject.model.Website;
import jobflowproject.repository.JobDailyOfferRepository;
import jobflowproject.repository.TagRepository;
import jobflowproject.repository.WebsiteRepository;
import jobflowproject.service.PracujPlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class DataCommandLineApp implements CommandLineRunner {

    private TagRepository tagRepository;

    private WebsiteRepository websiteRepository;

    private JobDailyOfferRepository jobDailyOfferRepository;

    @Autowired
    private PracujPlService pracujPlService;

    public DataCommandLineApp(TagRepository tagRepository, WebsiteRepository websiteRepository, JobDailyOfferRepository jobDailyOfferRepository) {
        this.tagRepository = tagRepository;
        this.websiteRepository = websiteRepository;
        this.jobDailyOfferRepository = jobDailyOfferRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Website website;
        Tag tag = null;
        if (!websiteRepository.findWebsite("pracuj").isPresent())
            website = websiteRepository.save(new Website("Pracuj", "http://pracuj.pl"));
        else website = websiteRepository.findWebsite("pracuj").get();

        String[] tags = {"java", "ruby", "project manager", "hr", "sprzątaczka", "programista"};
        String[] cities = {"warszawa", "kraków", "poznań", "gdańsk", "szczecin", "wrocław"};
        for (String tagName : tags) {
            if (!tagRepository.findByName(tagName).isPresent())
                tag = tagRepository.save(new Tag(tagName));
            else tag = tagRepository.findByName(tagName).get();
            addJobDailyOffer(tag, website, "warszawa");
        }




    }


    private void addJobDailyOffer(Tag tag, Website website, String city) {
        try {
            Integer numberOfOffers = pracujPlService.getNumberOfJobs(tag.getName(), city);
            JobDailyOffer jobDailyOffer = new JobDailyOffer();
            jobDailyOffer.setCity(city);
            jobDailyOffer.setDate(LocalDate.now());
            jobDailyOffer.setTag(tag);
            jobDailyOffer.setWebsite(website);
            jobDailyOffer.setNumber(numberOfOffers);
            jobDailyOfferRepository.save(jobDailyOffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

