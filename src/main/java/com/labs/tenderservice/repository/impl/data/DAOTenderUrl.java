package com.labs.tenderservice.repository.impl.data;

import com.labs.tenderservice.entity.tender.TenderUrlConnector;
import com.labs.tenderservice.repository.TenderUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class DAOTenderUrl implements TenderUrlRepository {

    private static final String sqlCreate = "INSERT INTO TENDERURLCONNECTOR (tenderId, url) VALUES (?,?)";
    private static final String sqlRead = "SELECT tenderId, url FROM TENDERURLCONNECTOR WHERE tenderId = ?";
    private static final String sqlUpdate = "UPDATE TENDERURLCONNECTOR SET url = ?  WHERE tenderId = ?";
    private static final String sqlDelete = "DELETE FROM TENDERURLCONNECTOR WHERE tenderId = ?";
    private static final String sqlReadAll = "SELECT tenderId, url FROM TENDERURLCONNECTOR ";
    private static final String sqlReadByURL = "SELECT tenderId, url FROM TENDERURLCONNECTOR WHERE url = ?";
    JdbcTemplate jdbcTemplate;
    @Autowired
    public DAOTenderUrl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TenderUrlConnector create(TenderUrlConnector tenderURLConnector) {
        jdbcTemplate.update(
                sqlCreate,
                tenderURLConnector.getTenderId(),
                tenderURLConnector.getUrl()
        );
        return tenderURLConnector;
    }

    @Override
    public TenderUrlConnector read(long id) {
        return jdbcTemplate.queryForObject(sqlRead, DAOTenderUrl::connectorRowMapper, id);
    }

    @Override
    public TenderUrlConnector update(TenderUrlConnector tenderURLConnector) {
        jdbcTemplate.update(
                sqlUpdate,
                tenderURLConnector.getUrl(),
                tenderURLConnector.getTenderId()
        );
        return jdbcTemplate.queryForObject(sqlRead, DAOTenderUrl::connectorRowMapper, tenderURLConnector.getTenderId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(sqlDelete, id);
    }

    @Override
    public List<TenderUrlConnector> getAll() {
        return jdbcTemplate.query(sqlReadAll, DAOTenderUrl::connectorRowMapper);
    }

    @Override
    public long getTenderIdByURL(String URL) {
        return Objects.requireNonNull(jdbcTemplate.queryForObject(sqlReadByURL, DAOTenderUrl::connectorRowMapper, URL)).getTenderId();
    }


    private static TenderUrlConnector connectorRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new TenderUrlConnector(
                rs.getLong(1),
                rs.getString(2)
        );
    }
}
