package com.example.test.Service;
import com.example.test.Dto.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class JobService {

    private static final String JOB_API_URL = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";

    @Autowired
    private RestTemplate restTemplate;

    public List<JobDto> getJobs() {
        ResponseEntity<JobDto[]> response = restTemplate.exchange(
                JOB_API_URL, HttpMethod.GET, null, JobDto[].class);

        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public JobDto getJobDetail(String id) {
        String jobDetailUrl = "http://dev3.dansmultipro.co.id/api/recruitment/positions/" + id + ".json";
        return restTemplate.getForObject(jobDetailUrl, JobDto.class);
    }
}
