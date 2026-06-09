package dev;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ApiAplicationControllers {
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ApiAplicationControllers( JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
}
