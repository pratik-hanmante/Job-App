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

    // Endpoint to get job by ID, returning a dummy job if the ID doesn't exist
    @GetMapping("/jobs/{id}")
    public Job getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);

        // If the job is not found, return a dummy job
        if (job == null) {
            return new Job(0L, "Dummy Job", "This is a dummy job description", "1000", "5000", "Unknown location");
        }

        return job;
    }
}
