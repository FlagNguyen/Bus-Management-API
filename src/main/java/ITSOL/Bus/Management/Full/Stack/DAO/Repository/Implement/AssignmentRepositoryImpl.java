package ITSOL.Bus.Management.Full.Stack.DAO.Repository.Implement;

import ITSOL.Bus.Management.Full.Stack.DAO.Entity.Assignment;
import ITSOL.Bus.Management.Full.Stack.DAO.Repository.AbstractRepository;
import ITSOL.Bus.Management.Full.Stack.DAO.Repository.AssignmentRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AssignmentRepositoryImpl extends AbstractRepository implements AssignmentRepository {
    @Override
    public Optional<List<Assignment>> getAllRoster() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM ASSIGNMENT ");
        sql.append("WHERE isdeleted = 0 ");
        List<Assignment> assignments = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Assignment.class));
        if (assignments.size() == 0) return Optional.empty();
        return Optional.ofNullable(assignments);
    }

    @Override
    public Optional<Assignment> getRosterByID(int assignment_id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM ASSIGNMENT ");
        sql.append("WHERE isdeleted = 0 AND assign_id = ?");
        List<Assignment> assignments = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Assignment.class), assignment_id);
        if (assignments.size() == 0) return Optional.empty();
        return Optional.ofNullable(assignments.get(0));
    }

    @Override
    public Optional<Assignment> assign(Assignment assignment) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ASSIGNMENT ");
        sql.append(" (assign_id, driver_id, route_id, turn, isdeleted) ");
        sql.append("VALUES (?,?,?,?,0)");
        jdbcTemplate.update(sql.toString(), assignment.getAssign_ID(), assignment.getDriver_ID(), assignment.getRoute_ID(), assignment.getTurn());
        return getRosterByID(assignment.getAssign_ID());
    }

    @Override
    public Optional<Assignment> updateAssign(Assignment assignment, int assignment_id) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ASSIGNMENT SET ");
        sql.append("driver_id = ? , route_id = ? , turn = ?");
        sql.append(" WHERE assign_id = ? ");
        jdbcTemplate.update(sql.toString(), assignment.getDriver_ID(), assignment.getRoute_ID(), assignment.getTurn(), assignment_id);
        return getRosterByID(assignment_id);
    }

    @Override
    public Optional<Assignment> deleteAssign(int assignment_id) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ASSIGNMENT ");
        sql.append(" SET isdeleted = 1 ");
        sql.append(" WHERE assign_id = ? ");
        jdbcTemplate.update(sql.toString(),assignment_id);
        return Optional.empty();
    }

    @Override
    public Optional<List<Assignment>> getRosterByDriverID(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM ASSIGNMENT ");
        sql.append("WHERE isdeleted = 0 AND driver_id = ?");
        List<Assignment> assignments = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Assignment.class), id);
        if (assignments.size() == 0) return Optional.empty();
        return Optional.ofNullable(assignments);
    }
}
