package com.myjobapp.MyJobApp.job.impl;

import com.myjobapp.MyJobApp.job.Job;
import com.myjobapp.MyJobApp.job.JobService;
import com.myjobapp.MyJobApp.job.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    private JobRepository jobRepo;

    public JobServiceImpl(JobRepository jobServiceRepo) {
        this.jobRepo = jobServiceRepo;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepo.findAll();
    }

    @Override
    public Job saveJob(Job job) {
        //job.setCompany(company);
        return jobRepo.save(job);
    }

    @Override
    public boolean removeJob(Long id) {
        Optional<Job> job = jobRepo.findById(id);
        if(job.isPresent()){
            jobRepo.delete(job.get());
            return true;
        }
        return false;

    }

    @Override
    public Optional<Job> fetchJobById(Long id) {
        Optional<Job> job = jobRepo.findById(id);
        return job;
    }

    @Override
    public Job updateJob(Long id, Job job) {
        Optional<Job> oldJobOptional = jobRepo.findById(id);

        if(oldJobOptional.isPresent()){
            Job oldJob = oldJobOptional.get();

            oldJob.setTitle(job.getTitle());
            oldJob.setDescription(job.getDescription());
            oldJob.setLocation(job.getLocation());
            oldJob.setMinSalary(job.getMinSalary());
            oldJob.setMaxSalary(job.getMaxSalary());

            jobRepo.save(oldJob);

            return oldJob;
        }else {
            return null;
        }
    }
}
