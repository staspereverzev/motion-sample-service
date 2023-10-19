package com.sysco.sampleService.Stas.dao;

import com.sysco.sampleService.Stas.model.HomeModelOnCalories;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HomeDaoTest {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate = mock(NamedParameterJdbcTemplate.class);

    HomeDao homeDao = new HomeDao(namedParameterJdbcTemplate);

    @Test
    public void test_dao_success(){

        HomeModelOnCalories homeModelOnCalories = new HomeModelOnCalories("100", "Peanut Butter");

        when(namedParameterJdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class))).thenReturn(List.of(homeModelOnCalories));

        List<HomeModelOnCalories> response = homeDao.getProductOnCalories("9");

        assertNotNull(response);

        assertEquals(response.get(0).getProductId(), "100");
        assertEquals(response.get(0).getProductName(), "Peanut Butter");
    }

    @Test
    public void test_dao_fail(){
        when(namedParameterJdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class)))
                .thenThrow(EmptyResultDataAccessException.class);

        List<HomeModelOnCalories> response = homeDao.getProductOnCalories("4");

        assertNotNull(response);
        assertTrue(response.isEmpty());
        assertEquals(0, response.size());
    }
}

// 1. 10 - 15 - common stuff - 2 years

// 2. DBA - DataBase Admin - never tell anyone. They will fuck you

// 3. Creating Controllers, Services, Dao, exception handling, logging, Unit testing, Mockito, Maven, Gradle, Spring Boot, Java core, Hibernate, JDBC
