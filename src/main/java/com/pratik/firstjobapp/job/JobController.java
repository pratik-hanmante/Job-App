package com.pratik.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    // Endpoint to create a new job
    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully",HttpStatus.OK);
    }

    // Endpoint to get job by ID, returning a dummy job if the ID doesn't exist
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {

        Job job = jobService.getJobById(id);

        // If the job is not found, return HttpStatus.NOT_FOUND
        if (job == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the job if found
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long  id) {

        boolean deleted = jobService.deleteJobById(id);


        if (deleted) {
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        boolean updated = jobService.updateJob(id, updatedJob);

        if (updated) {
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Job not found or update failed", HttpStatus.NOT_FOUND);
        }
    }

}
