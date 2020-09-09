package jobflowproject.api;

import io.swagger.annotations.ApiOperation;
import jobflowproject.dto.JobDailyOfferDto;
import jobflowproject.mapper.JobDailyOfferMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/jobs")
@RestController
public class JobController {

    private JobDailyOfferMapper jobDailyOfferMapper;

    public JobController(JobDailyOfferMapper jobDailyOfferMapper) {
        this.jobDailyOfferMapper = jobDailyOfferMapper;
    }

    @ApiOperation(value = "Show data for Front-End", notes="Show data for Front-End")
    @GetMapping
    public List<JobDailyOfferDto>  getList(){
        return jobDailyOfferMapper.convertToListDailyOfferDto();
    }

}
