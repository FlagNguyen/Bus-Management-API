package ITSOL.Bus.Management.Full.Stack.DAO.Repository.Implement;

import ITSOL.Bus.Management.Full.Stack.DAO.Entity.Drivers;
import ITSOL.Bus.Management.Full.Stack.DAO.Repository.AbstractRepository;
import ITSOL.Bus.Management.Full.Stack.DAO.Repository.DriverRepository;
import ITSOL.Bus.Management.Full.Stack.DTO.Request.DriverRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DriverRepositoryImpl extends AbstractRepository implements DriverRepository {

    @Override
    public Optional<List<Drivers>> getAll() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM DRIVERS ");
        sql.append("WHERE isdeleted = 0 ");
        List<Drivers> drives = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Drivers.class));
        return Optional.ofNullable(drives);
    }

    @Override
    public Optional<Drivers> getDriverById(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM DRIVERS ");
        sql.append("WHERE isdeleted = 0 AND id = ?");
        Drivers driver = jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(Drivers.class), id);
        return Optional.ofNullable(driver);
    }

    @Override
    public Optional<Drivers> addDrivers(Drivers driver) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO DRIVERS");
        sql.append("(id,name,address,phone,d_level,isdeleted) " +
                "VALUES (?,?,?,?,?,0)");
        jdbcTemplate.update(sql.toString(),driver.getId(),driver.getName(),driver.getAddress(),driver.getPhone(),
                driver.getDLevel());
        return getDriverById(driver.getId());
    }

    @Override
    public Optional<Drivers> updateDriver(Drivers drivers, int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE DRIVERS ");
        sql.append(" SET name = ?, address = ?, phone = ?, d_level = ? ");
        sql.append(" WHERE id = ?");
        jdbcTemplate.update(sql.toString(), drivers.getName(), drivers.getAddress(), drivers.getPhone(), drivers.getDLevel(), id);
        return getDriverById(id);
    }

    @Override
    public Optional<Drivers> deleteDriver(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE DRIVERS ");
        sql.append(" SET isdeleted = 1 ");
        sql.append(" WHERE id = ?");
        jdbcTemplate.update(sql.toString(), id);
        return Optional.empty();
    }
}
