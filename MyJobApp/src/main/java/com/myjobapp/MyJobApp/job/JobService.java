package com.myjobapp.MyJobApp.job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    public List<Job> getAllJobs();

    Job saveJob(Job job);

    boolean removeJob(Long id);

    Optional<Job> fetchJobById(Long id);

    Job updateJob(Long id,Job job);
}
