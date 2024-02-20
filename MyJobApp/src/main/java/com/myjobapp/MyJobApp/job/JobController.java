package com.myjobapp.MyJobApp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job")
public class JobController {

    private JobService jobService ;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    private ResponseEntity<List<Job>> getAllJobs(){
        List<Job> allJobs = jobService.getAllJobs();
        return new ResponseEntity<List<Job>>(allJobs, HttpStatus.OK);
    }
    @PostMapping
    private ResponseEntity<String> addJob(@RequestBody Job job){
        jobService.saveJob(job);
        return new ResponseEntity<String>("Job added successfully",HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<String> removeJob(@PathVariable Long id){
        if(jobService.removeJob(id)){
            return new ResponseEntity<String>("Job removed successfully",HttpStatus.OK);
        }
        return new ResponseEntity<String>("Job not found",HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    private ResponseEntity<Job> getJobById(@PathVariable Long id){
        Optional<Job> optionalJob = jobService.fetchJobById(id);

        if (optionalJob.isPresent()) {
            return new ResponseEntity<>(optionalJob.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<Job> updateJob(@PathVariable Long id,@RequestBody Job updatedJob){

        Job job = jobService.updateJob(id,updatedJob);
        if(job!=null){
            return new ResponseEntity<>(job,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
