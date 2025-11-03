
package com.jobboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobboard.model.Job;
import com.jobboard.repo.JobRepository;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Job save(Job job) {
        return jobRepository.save(job);
    }

    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        jobRepository.deleteById(id);
    }

    public List<Job> searchByTitle(String q) {
        return jobRepository.findByTitleContainingIgnoreCase(q);
    }
}
