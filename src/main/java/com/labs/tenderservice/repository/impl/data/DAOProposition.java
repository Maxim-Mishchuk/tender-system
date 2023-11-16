package com.labs.tenderservice.repository.impl.data;

import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.repository.PropositionRepository;
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
public class DAOProposition implements PropositionRepository {

    private static final String sqlCreate = "INSERT INTO PROPOSITIONS (TenderID, Name, Description, Price, Currency, Status) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String sqlRead = "SELECT ID, TenderID, Name, Description, Price, Currency, Status   FROM PROPOSITIONS WHERE ID = ?";
    private static final String sqlUpdate = "UPDATE PROPOSITIONS SET Name=?, Description=?, Price=?, Currency=?, Status=? WHERE ID = ?";
    private static final String sqlDelete = "DELETE FROM Propositions WHERE ID = ?";
    private static final String sqlReadAll = "SELECT ID, TenderID, Name, Description, Price, Currency, Status   FROM PROPOSITIONS ";
    private static final String sqlReadByTenderId = "SELECT ID, TenderID, Name, Description, Price, Currency, Status   FROM PROPOSITIONS WHERE TenderID = ?";

    private static final String sqlUpdateStatus = "UPDATE PROPOSITIONS SET Status=? WHERE ID = ?";

    JdbcTemplate jdbcTemplate;

    @Autowired
    public DAOProposition(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Proposition create(Proposition proposition) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sqlCreate, new String[]{"ID"});
            preparedStatement.setLong(1, proposition.getTenderId());
            preparedStatement.setString(2, proposition.getName());
            preparedStatement.setString(3, proposition.getDescription());
            preparedStatement.setDouble(4, proposition.getPrice());
            preparedStatement.setString(5, proposition.getCurrency().name());
            preparedStatement.setString(6, proposition.getStatus().name());
            return preparedStatement;
        }, keyHolder);
        return new Proposition(
                Objects.requireNonNull(keyHolder.getKey()).longValue(),
                proposition.getTenderId(),
                proposition.getName(),
                proposition.getDescription(),
                proposition.getPrice(),
                proposition.getCurrency(),
                proposition.getStatus()
        );
    }

    @Override
    public Proposition read(long id) {
        return jdbcTemplate.queryForObject(sqlRead, DAOProposition::propositionRowMapper, id);
    }

    @Override
    public Proposition update(Proposition proposition) {
        jdbcTemplate.update(
                sqlUpdate,
                proposition.getName(),
                proposition.getDescription(),
                proposition.getPrice(),
                proposition.getCurrency().name(),
                proposition.getStatus().name(),
                proposition.getId()
        );
        return read(proposition.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(sqlDelete, id);
    }

    @Override
    public List<Proposition> getAll() {
        return jdbcTemplate.query(sqlReadAll, DAOProposition::propositionRowMapper);
    }

    @Override
    public List<Proposition> getPropositionsByTenderId(long id) {
        return jdbcTemplate.query(sqlReadByTenderId, DAOProposition::propositionRowMapper, id);
    }

    @Override
    public Proposition updatePropositionStatus(long id, Proposition.Status status) {

        jdbcTemplate.update(
                sqlUpdateStatus,
                status.name(),
                id
        );
        return read(id);
    }

    private static Proposition propositionRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Proposition(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getString(4),
                rs.getDouble(5),
                Proposition.Currency.valueOf(rs.getString(6)),
                Proposition.Status.valueOf(rs.getString(7))
        );
    }
}
