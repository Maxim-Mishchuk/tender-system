package com.labs.tenderservice.repository;

import com.labs.tenderservice.entity.tender.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface TenderRepository extends JpaRepository<Tender, Long> {
    @Query("select t from Tender t where t.status='ACTIVE'")
    List<Tender> getActiveTenders();
    @Query("select t from Tender t where t.name=?1 or t.description=?1")
    List<Tender> getTendersByKeywords(String keywords);

    Tender getTenderById(long id);

}