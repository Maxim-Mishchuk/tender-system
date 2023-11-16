package com.labs.tenderservice.repository.impl.Data;

import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.repository.TenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class DAOTender implements TenderRepository {
    private static final String sqlCreate = "INSERT INTO TENDERS (UserID, Name, Description, Status) VALUES (?, ?, ?, ?)";
    private static final String sqlRead = "SELECT ID, UserID, Name, Description, Status   FROM TENDERS WHERE ID = ?";
    private static final String sqlUpdate = "UPDATE tenders SET Name=?, Description=?, Status=? WHERE ID = ?";
    private static final String sqlDelete = "DELETE FROM TENDERS WHERE ID = ?";
    private static final String sqlReadAll = "SELECT ID, UserID, Name, Description, Status  FROM TENDERS ";
    private static final String sqlReadByKeywords = "SELECT ID, UserID, Name, Description, Status  FROM TENDERS WHERE Name OR Description LIKE ? ";
    private static final String sqlUpdateStatus = "UPDATE TENDERS SET Status=? WHERE ID = ?";
    private static final String sqlReadActive = "SELECT ID, UserID, Name, Description, Status  FROM TENDERS WHERE STATUS = ?";
    private static final String sqlReadByUserId = "SELECT ID, UserID, Name, Description, Status  FROM TENDERS WHERE UserID = ?";
    JdbcTemplate jdbcTemplate;

    @Autowired
    public DAOTender(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tender create(Tender tender) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sqlCreate, new String[]{"ID"});
            preparedStatement.setLong(1, tender.getUserId());
            preparedStatement.setString(2, tender.getName());
            preparedStatement.setString(3, tender.getDescription());
            preparedStatement.setString(4, tender.getStatus().name());
            return preparedStatement;
        }, keyHolder);
        return new Tender(
                Objects.requireNonNull(keyHolder.getKey()).longValue(),
                tender.getUserId(),
                tender.getName(),
                tender.getDescription(),
                tender.getStatus()
        );
    }

    @Override
    public Tender read(long id) {
        return jdbcTemplate.queryForObject(sqlRead, DAOTender::tenderRowMapper, id);
    }

    @Override
    public Tender update(Tender tender) {
        jdbcTemplate.update(
                sqlUpdate,
                tender.getName(),
                tender.getDescription(),
                tender.getStatus(),
                tender.getId()
        );
        return jdbcTemplate.queryForObject(sqlReadActive, DAOTender::tenderRowMapper, tender.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(sqlDelete, id);
    }

    @Override
    public List<Tender> getAll() {
        return jdbcTemplate.query(sqlReadAll, DAOTender::tenderRowMapper);
    }

    @Override
    public List<Tender> getActiveTenders() {
        return jdbcTemplate.query(sqlReadActive, DAOTender::tenderRowMapper, Tender.Status.ACTIVE.name());
    }

    @Override
    public List<Tender> getUserTenders(long userId) {
        return jdbcTemplate.query(sqlReadByUserId, DAOTender::tenderRowMapper, userId);
    }

    @Override
    public List<Tender> getTendersByKeywords(String keywords) {
        return jdbcTemplate.query(sqlReadByKeywords, DAOTender::tenderRowMapper, keywords);
    }

    @Override
    public Tender updateTenderStatus(long id, Tender.Status status) {
        jdbcTemplate.update(
                sqlUpdateStatus,
                status.name(),
                id
        );
        return jdbcTemplate.queryForObject(sqlRead, DAOTender::tenderRowMapper, id);
    }

    private static Tender tenderRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Tender(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getString(4),
                Tender.Status.valueOf(rs.getString(5))
        );
    }
}
