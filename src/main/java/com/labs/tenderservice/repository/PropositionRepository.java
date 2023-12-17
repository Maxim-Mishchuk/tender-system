package com.labs.tenderservice.repository;

import com.labs.tenderservice.entity.proposition.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PropositionRepository extends JpaRepository<Proposition, Long> {
    List<Proposition> getPropositionsByTenderId(long id);
    List<Proposition> deletePropositionsByTenderId(long id);
    Proposition getPropositionById(long id);


}
