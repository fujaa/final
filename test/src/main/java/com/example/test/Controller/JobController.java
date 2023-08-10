package com.example.test.Controller;
import com.example.test.Dto.JobDto;
import com.example.test.Service.CsvService;
import com.example.test.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/api")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private CsvService csvService;

    @GetMapping("/jobs")
    public ResponseEntity<List<JobDto>> getJobList() {
        List<JobDto> jobs = jobService.getJobs();
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobDto> getJobDetail(@PathVariable String id) {
        JobDto job = jobService.getJobDetail(id);
        return ResponseEntity.ok(job);
    }

    @GetMapping("/jobs/csv")
    public ResponseEntity<ByteArrayResource> downloadJobListCSV() {
        List<JobDto> jobs = jobService.getJobs();

        try {
            byte[] csvBytes = csvService.convertToCsv(jobs, JobDto.class);

            ByteArrayResource csvResource = new ByteArrayResource(csvBytes);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=jobs.csv");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(csvResource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
