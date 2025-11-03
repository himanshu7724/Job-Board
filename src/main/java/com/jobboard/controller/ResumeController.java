
package com.jobboard.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobboard.model.Resume;
import com.jobboard.repo.ResumeRepository;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeRepository resumeRepository;

    public ResumeController(ResumeRepository resumeRepository){
        this.resumeRepository = resumeRepository;
    }

    @GetMapping("/get")
    public List<Resume> all(){ return resumeRepository.findAll(); }

    @PostMapping("/add")
    public Resume create(@RequestBody Resume r){ return resumeRepository.save(r); }

    @GetMapping("/{id}")
    public ResponseEntity<Resume> get(@PathVariable Long id){
        return resumeRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        resumeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
