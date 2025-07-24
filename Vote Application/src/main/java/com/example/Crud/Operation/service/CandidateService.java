package com.example.Crud.Operation.service;

import com.example.Crud.Operation.entity.Candidate;
import com.example.Crud.Operation.exception.CandidateNotFoundException;
import com.example.Crud.Operation.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    // add candidate
    public Candidate addCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    // get All Candidate
    public List<Candidate> getAllCandidate() {
        return candidateRepository.findAll();
    }

    // get Candidate By id
    public Candidate getCandidateById(Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        return candidate.orElseThrow(
                () -> new CandidateNotFoundException("candidate not found with id : " + id));
    }

    //update candidate
    public Candidate updateCandidate(Long id, Candidate candidate) {

        Candidate existing = getCandidateById(id);

        existing.setCandidate(candidate.getCandidate());
        existing.setVotes(candidate.getVotes());

        return candidateRepository.save(existing);
    }

    // delete candidate
    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);

    }

    // Get full Candidate object by name
    public Candidate getCandidateByCandidate(String name) {
        return candidateRepository.getCandidateByCandidate(name);
    }

    // Get only number of votes for a candidate
    public int getNumOfVotes(String name) {
        return candidateRepository.getNumOfVotes(name);
    }
}





