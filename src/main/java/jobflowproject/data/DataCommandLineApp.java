package jobflowproject.data;

import jobflowproject.model.Tag;
import jobflowproject.model.Website;
import jobflowproject.repository.JobDailyOfferRepository;
import jobflowproject.repository.TagRepository;
import jobflowproject.repository.WebsiteRepository;
import jobflowproject.service.JobDailyOfferService;
import jobflowproject.service.PracujPlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataCommandLineApp implements CommandLineRunner {

    private TagRepository tagRepository;
    private WebsiteRepository websiteRepository;
    private JobDailyOfferRepository jobDailyOfferRepository;
    private JobDailyOfferService jobDailyOfferService;

    public DataCommandLineApp(TagRepository tagRepository, WebsiteRepository websiteRepository, JobDailyOfferRepository jobDailyOfferRepository, JobDailyOfferService jobDailyOfferService) {
        this.tagRepository = tagRepository;
        this.websiteRepository = websiteRepository;
        this.jobDailyOfferRepository = jobDailyOfferRepository;
        this.jobDailyOfferService = jobDailyOfferService;
    }

    @Override
    public void run(String... args){

        jobDailyOfferRepository.deleteAll();

        Website website;
        Tag tag;
        if (!websiteRepository.findWebsite("pracuj").isPresent())
            website = websiteRepository.save(new Website("Pracuj", "http://pracuj.pl"));
        else website = websiteRepository.findWebsite("pracuj").get();

        String[] tags = {"java", "ruby", "project manager", "hr", "sprzątaczka", "programista"};
        String[] cities = {"warszawa", "kraków", "poznań", "gdańsk", "szczecin", "wrocław"};

        for (String city : cities) {
            for (String tagName : tags) {
                if (!tagRepository.findByName(tagName).isPresent())
                    tag = tagRepository.save(new Tag(tagName));
                else tag = tagRepository.findByName(tagName).get();
                jobDailyOfferService.addJobDailyOffer(tag, website, city);
            }
        }
    }
}

