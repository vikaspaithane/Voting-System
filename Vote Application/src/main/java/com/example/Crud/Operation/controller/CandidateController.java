package com.example.Crud.Operation.controller;

import com.example.Crud.Operation.entity.Candidate;
import com.example.Crud.Operation.entity.User;
import com.example.Crud.Operation.service.CandidateService;
import com.example.Crud.Operation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Validated
@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private UserService userService;

    @PostMapping("/vote")
    public ResponseEntity<String> vote(@RequestParam String candidateName, @RequestParam String email) {
        // 1. Get user by email
        User user = userService.getUserByEmail(email);

        // 2. Check if user has already voted
        if (user.getStatus() != null && user.getStatus().equalsIgnoreCase("Voted")) {
            return ResponseEntity.badRequest().body("You have already voted!");
        }

        // 3. Get candidate by name
        Candidate candidate = candidateService.getCandidateByCandidate(candidateName);

        // 4. Increment vote count
        candidate.setVotes(candidate.getVotes() + 1);
        candidateService.addCandidate(candidate);  // update candidate

        // 5. Update user status
        user.setStatus("Voted");
        userService.addUser(user); // update user

        return ResponseEntity.ok("Vote submitted successfully!");
    }

    // post
    @PostMapping
    public ResponseEntity<Candidate> addCandidate(@Validated @RequestBody Candidate candidate){
        Candidate add = candidateService.addCandidate(candidate);
    return ResponseEntity.ok(add);
    }

    // getAll
    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidate(){
        List<Candidate> getAll = candidateService.getAllCandidate();
        return ResponseEntity.ok(getAll);
    }

    // getById
    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getById(@PathVariable Long id){
        Candidate getById = candidateService.getCandidateById(id);
        return ResponseEntity.ok(getById);
    }


    // Update candidate by ID
    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate) {
        Candidate updatedCandidate = candidateService.updateCandidate(id, candidate);
        return ResponseEntity.ok(updatedCandidate);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    // Endpoint to get full candidate details
    @GetMapping("/name/{name}")
    public ResponseEntity<Candidate> getCandidateByName(@PathVariable String name) {
        Candidate candidate = candidateService.getCandidateByCandidate(name);
        return ResponseEntity.ok(candidate);
    }

    // Endpoint to get only number of votes
    @GetMapping("/{name}/votes")
    public ResponseEntity<Integer> getVotes(@PathVariable String name) {
        int votes = candidateService.getNumOfVotes(name);
        return ResponseEntity.ok(votes);
    }

}
