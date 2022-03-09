package ITSOL.Bus.Management.Full.Stack.DAO.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

public class AbstractRepository {
//    @Autowired
//    @Qualifier("dataSource")
//    protected DataSource dataSource;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

//    @Autowired
//    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
}
