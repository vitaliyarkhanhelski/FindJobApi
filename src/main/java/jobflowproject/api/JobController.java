package jobflowproject.api;

import jobflowproject.dto.JobDailyOfferDto;
import jobflowproject.mapper.JobDailyOfferMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/jobs")
@RestController
public class JobController {


    //zamienic to co dostaniemy na Dto

    private JobDailyOfferMapper jobDailyOfferMapper;

    public JobController(JobDailyOfferMapper jobDailyOfferMapper) {
        this.jobDailyOfferMapper = jobDailyOfferMapper;
    }

    @GetMapping("")
    public List<JobDailyOfferDto>  getList(){
        return jobDailyOfferMapper.convertToListDailyOfferDto();
    }

}
