package com.sysco.sampleService.Stas.dao;

import com.sysco.sampleService.Stas.exception.ConnectionFailedException;
import com.sysco.sampleService.Stas.exception.RowMapperException;
import com.sysco.sampleService.Stas.model.HomeModelOnCalories;
import com.sysco.sampleService.Stas.model.HomeModelOnQuantity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public class HomeDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public HomeDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<HomeModelOnCalories> getProductOnCalories(String calories) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("CALORIES", Integer.parseInt(calories));

        try {
            return namedParameterJdbcTemplate.query("""
                    SELECT
                        d.product_id,
                        d.value ->>'name' as product_name,
                        n.value ->>'calories' as calories
                    FROM
                        products.details d
                    INNER JOIN
                        products.nutrition n
                    ON
                        d.seller_id = n.seller_id AND d.product_id = n.product_id
                    WHERE
                        (n.value->> 'calories')::INT > :CALORIES""", mapSqlParameterSource, rowMapperCalories);
        } catch (EmptyResultDataAccessException er) {
            return List.of();
        } catch (Exception e) {
            throw new ConnectionFailedException("Connection failed" + e.getMessage(), e);
        }
    }


    private final RowMapper<HomeModelOnCalories> rowMapperCalories = (rs, rowNum) -> {
        try {
            String productId = rs.getString("product_id");
            String productName = rs.getString("product_name");
            return new HomeModelOnCalories(productId, productName);
        } catch (Exception e) {
            throw new RowMapperException("Exception" + e, e);
        }
    };

    public List<HomeModelOnQuantity> getProductOnQuantity(String quantity) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("QUANTITY", Integer.parseInt(quantity));

        try {
            return namedParameterJdbcTemplate.query("""
                    SELECT
                        d.product_id,
                        d.value ->> 'name' as product_name,
                        i.value ->> 'quantity_on_hand' as quantity_on_hand
                    FROM
                        products.details d
                    INNER JOIN
                        products.inventory i
                    ON
                        d.seller_id = i.seller_id
                    AND
                        d.product_id = i.product_id
                    WHERE
                        (i.value ->> 'quantity_on_hand')::INT >= :QUANTITY""", mapSqlParameterSource, rowMapperQuantity);
        } catch (EmptyResultDataAccessException er) {
            return List.of();
        } catch (Exception e) {
            throw new ConnectionFailedException("Connection failed" + e.getMessage(), e);
        }
    }

    private final RowMapper<HomeModelOnQuantity> rowMapperQuantity = (rs, rowNum) -> {
        try {
            String productId = rs.getString("product_id");
            String productName = rs.getString("product_name");
            String quantity = rs.getString("quantity_on_hand");
            return new HomeModelOnQuantity(productId, productName, quantity);
        } catch (Exception e) {
            throw new RowMapperException("Exception" + e.getMessage(), e);
        }
    };
}

