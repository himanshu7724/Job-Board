
package com.jobboard.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobboard.model.Job;
import com.jobboard.service.JobService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/jobs")

public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @GetMapping("/get")
    public List<Job> all() {
        return jobService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> get(@PathVariable Long id){
        Job j = jobService.findById(id);
        if(j==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(j);
    }

    @PostMapping("/add")
    public Job create(@RequestBody Job job){
        return jobService.save(job);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> update(@PathVariable Long id, @RequestBody Job job){
        Job existing = jobService.findById(id);
        if(existing==null) return ResponseEntity.notFound().build();
        existing.setTitle(job.getTitle());
        existing.setCompany(job.getCompany());
        existing.setLocation(job.getLocation());
        existing.setType(job.getType());
        existing.setDescription(job.getDescription());
        existing.setSalary(job.getSalary());
        return ResponseEntity.ok(jobService.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        jobService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Job> search(@RequestParam("q") String q){
        return jobService.searchByTitle(q);
    }
}
