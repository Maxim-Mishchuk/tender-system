package com.labs.tenderservice.repository;

import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.entity.tender.TenderUrlConnector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TenderUrlRepository extends JpaRepository<TenderUrlConnector, Long> {
    @Query("select t.tender from TenderUrlConnector t where t.url=?1")
    Tender getTenderByUrl(String URL);
    TenderUrlConnector getTenderUrlConnectorByTenderId(long id);
    TenderUrlConnector getTenderUrlConnectorsByUrl(String url);
}
