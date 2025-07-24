package com.example.Crud.Operation.repository;

import com.example.Crud.Operation.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    @Query("select votes from Candidate where candidate = :candidate")
    public int getNumOfVotes(@Param("candidate") String candidate);

    @Query("select c from Candidate c where c.candidate = :candidate")
    public Candidate getCandidateByCandidate(@Param("candidate") String candidate);

}
