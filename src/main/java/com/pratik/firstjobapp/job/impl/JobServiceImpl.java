package com.pratik.firstjobapp.job.impl;

import com.pratik.firstjobapp.job.Job;
import com.pratik.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return new ArrayList<>(jobs); // Return a copy to prevent external modifications
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobs.stream()
                   .filter(job -> job.getId().equals(id))
                   .findFirst()
                   .orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        return jobs.removeIf(job -> job.getId().equals(id));
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobToUpdate = jobs.stream()
                                        .filter(job -> job.getId().equals(id))
                                        .findFirst();

        if (jobToUpdate.isPresent()) {
            Job job = jobToUpdate.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            return true;
        }
        return false;
    }
}
