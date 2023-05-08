package portefeuille.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class CategorieLePlusVenduRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
     * public ArrayList<CategorieLePlusVendu> SelectAllCategorie() {
     * String sql = "SELECT*from TOP5_CATEGORIEPRODUIT ORDER BY nbr DESC";
     * return (ArrayList<CategorieLePlusVendu>) jdbcTemplate.query(sql,
     * new BeanPropertyRowMapper<CategorieLePlusVendu>(CategorieLePlusVendu.class));
     * }
     */
}
