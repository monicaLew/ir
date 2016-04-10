package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CalendarDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private final String SQL_SELECT_COUNTER_VALUE = "SELECT value FROM counters WHERE id = :id";
    private final String SQL_UPDATE_COUNTER_VALUE = "UPDATE counters SET value = value + 1 WHERE id = :id";

    public int getCounterValue() {
        RowMapper<Integer> rowMapper = (resultSet, i) -> resultSet.getInt("value");
        Map<String, Integer> params = new HashMap<>();
        params.put("id", 1);
        return jdbcTemplate.queryForObject(SQL_SELECT_COUNTER_VALUE, params, rowMapper);
    }

    public void increaseCounterValue() {
        Map<String, Integer> params = new HashMap<>();
        params.put("id", 1);
        jdbcTemplate.update(SQL_UPDATE_COUNTER_VALUE, params);
    }

}
