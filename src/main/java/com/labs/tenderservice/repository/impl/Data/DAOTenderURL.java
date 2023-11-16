package com.labs.tenderservice.repository.impl.Data;

import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.entity.tender.TenderURLConnector;
import com.labs.tenderservice.entity.user.User;
import com.labs.tenderservice.repository.IRepository;
import com.labs.tenderservice.repository.TenderURLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class DAOTenderURL implements TenderURLRepository {

    private static final String sqlCreate = "INSERT INTO TENDERURLCONNECTOR (tenderId, url) VALUES (?,?)";
    private static final String sqlRead = "SELECT tenderId, url FROM TENDERURLCONNECTOR WHERE tenderId = ?";
    private static final String sqlUpdate = "UPDATE TENDERURLCONNECTOR SET url = ?  WHERE tenderId = ?";
    private static final String sqlDelete = "DELETE FROM TENDERURLCONNECTOR WHERE tenderId = ?";
    private static final String sqlReadAll = "SELECT tenderId, url FROM TENDERURLCONNECTOR ";
    private static final String sqlReadByURL = "SELECT tenderId, url FROM TENDERURLCONNECTOR WHERE url = ?";
    JdbcTemplate jdbcTemplate;
    @Autowired
    public DAOTenderURL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TenderURLConnector create(TenderURLConnector tenderURLConnector) {
        jdbcTemplate.update(
                sqlCreate,
                tenderURLConnector.getTenderID(),
                tenderURLConnector.getUrl()
        );
        return tenderURLConnector;
    }

    @Override
    public TenderURLConnector read(long id) {
        return jdbcTemplate.queryForObject(sqlRead, DAOTenderURL::connectorRowMapper, id);
    }

    @Override
    public TenderURLConnector update(TenderURLConnector tenderURLConnector) {
        jdbcTemplate.update(
                sqlUpdate,
                tenderURLConnector.getUrl(),
                tenderURLConnector.getTenderID()
        );
        return jdbcTemplate.queryForObject(sqlRead, DAOTenderURL::connectorRowMapper, tenderURLConnector.getTenderID());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(sqlDelete, id);
    }

    @Override
    public List<TenderURLConnector> getAll() {
        return jdbcTemplate.query(sqlReadAll, DAOTenderURL::connectorRowMapper);
    }

    @Override
    public long getTenderIdByURL(String URL) {
        return Objects.requireNonNull(jdbcTemplate.queryForObject(sqlReadByURL, DAOTenderURL::connectorRowMapper, URL)).getTenderID();
    }


    private static TenderURLConnector connectorRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new TenderURLConnector(
                rs.getLong(1),
                rs.getString(2)
        );
    }
}
