package com.pratik.firstjobapp.job;

import java.util.List;

public interface JobService {
  List<Job> findAll();
    void createJob(Job job);

  Job getJobById(Long id);
}
