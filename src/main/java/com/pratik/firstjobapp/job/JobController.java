package com.pratik.firstjobapp.job;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // Endpoint to get all jobs
    @GetMapping("/jobs")
    public List<Job> findAll() {
        return jobService.findAll();
    }

    // Endpoint to create a new job
    @PostMapping("/jobs")
    public String createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return "Job added successfully";
    }

    // Endpoint to get job by ID
    @GetMapping("/jobs/{id}")
    public Job getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        return job;
    }
}
