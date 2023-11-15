package com.labs.tenderservice.repository.impl.Data;

import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.repository.impl.PropositionRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public class DAOProposition implements PropositionRepository {
    private static final String sqlRead="SELECT ID, TenderID, Name, Description, Price, Currency, Status   FROM PROPOSITIONS WHERE id = ?";

    JdbcTemplate jdbcTemplate;

    public DAOProposition(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Proposition create(Proposition proposition) {

        return null;
    }

    @Override
    public Proposition read(long id) {

        return jdbcTemplate.queryForObject(sqlRead, (rs, rowNum) -> new Proposition(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getString(4),
                rs.getDouble(5),
                Proposition.Currency.valueOf(rs.getString(6)),
                Proposition.Status.valueOf(rs.getString(7))
        ), id);
    }

    @Override
    public Proposition update(Proposition proposition) {
        return null;
    }

    @Override
    public Proposition delete(Proposition proposition) {

        return null;
    }

    @Override
    public List<Proposition> getAll() {
        return null;
    }

    @Override
    public List<Proposition> getPropositionByTenderId(long id, Proposition.Status status) {
        return null;
    }

    @Override
    public Proposition updatePropositionStatus(long id, Proposition.Status status) {
        return null;
    }
}
