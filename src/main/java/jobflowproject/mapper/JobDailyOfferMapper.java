package jobflowproject.mapper;

import com.google.common.collect.FluentIterable;
import jobflowproject.dto.JobDailyOfferDto;
import jobflowproject.repository.JobDailyOfferRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobDailyOfferMapper {

    private JobDailyOfferRepository jobDailyOfferRepository;

    public JobDailyOfferMapper(JobDailyOfferRepository jobDailyOfferRepository) {
        this.jobDailyOfferRepository = jobDailyOfferRepository;
    }

    public List<JobDailyOfferDto> convertToListDailyOfferDto(){
        return FluentIterable.from(jobDailyOfferRepository.findAll()).toList()
                .stream()
                .map(jobDailyOffer -> new JobDailyOfferDto(
                        jobDailyOffer.getTag().getName(),
                        jobDailyOffer.getCity(),
                        jobDailyOffer.getNumber())).
                collect(Collectors.toList());
        //guava tworzy immutable list, ale mozna na niej dziala jakby byla Guavova lista
    }

}
