package com.labs.tenderservice.repository.impl.Data;


import com.labs.tenderservice.entity.user.User;
import com.labs.tenderservice.repository.UserRepository;
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

public class DAOUser implements UserRepository {

    private static final String sqlCreate = "INSERT INTO USERS (USERNAME) VALUES (?)";
    private static final String sqlRead = "SELECT ID, USERNAME FROM USERS WHERE ID = ?";
    private static final String sqlUpdate = "UPDATE USERS SET USERNAME = ?  WHERE ID = ?";
    private static final String sqlDelete = "DELETE FROM USERS WHERE ID = ?";
    private static final String sqlReadAll = "SELECT ID, USERNAME   FROM USERS ";


    JdbcTemplate jdbcTemplate;
    @Autowired
    public DAOUser(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User create(User user) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sqlCreate, new String[]{"ID"});
            preparedStatement.setString(1, user.getUsername());
            return preparedStatement;
        }, keyHolder);
        return new User(
                Objects.requireNonNull(keyHolder.getKey()).longValue(),
                user.getUsername()
        );
    }

    @Override
    public User read(long id) {
        return jdbcTemplate.queryForObject(sqlRead, DAOUser::userRowMapper, id);
    }

    @Override
    public User update(User user) {
        jdbcTemplate.update(
                sqlUpdate,
                user.getUsername()
        );
        return jdbcTemplate.queryForObject(sqlRead, DAOUser::userRowMapper, user.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(sqlDelete, id);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(sqlReadAll, DAOUser::userRowMapper);
    }

    private static User userRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getLong(1),
                rs.getString(2)
        );
    }
}
