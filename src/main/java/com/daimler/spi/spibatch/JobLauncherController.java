package com.daimler.spi.spibatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLauncherController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job myJob;
    
    @Autowired
    private JobExplorer jobExplorer;


    @RequestMapping("/launchjob/{id}")
    public ResponseEntity<String> launchJob(@PathVariable String id) throws Exception {
        JobParameters params = new JobParametersBuilder()
            .addString("JobID", id)
            .toJobParameters();
        jobLauncher.run(myJob, params);
        return ResponseEntity.ok("Job has been launched!");
    }
    
    @RequestMapping("/calljob/{jobId}")
    public String startJob(@PathVariable String jobId) throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("currentID", jobId)
                .toJobParameters();
        
        JobExecution execution = jobLauncher.run(myJob, params);
        return "Job started with execution id " + execution.getId() + " and current ID: " + jobId;
    }
    
    
    @GetMapping("/status/{jobExecutionId}")
    public ResponseEntity<String> getJobStatus(@PathVariable Long jobExecutionId) {
        JobExecution jobExecution = jobExplorer.getJobExecution(jobExecutionId);
        if (jobExecution == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(jobExecution.getStatus().toString());
    }
    
 
}