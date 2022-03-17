package ITSOL.Bus.Management.Full.Stack.DAO.Repository;

import ITSOL.Bus.Management.Full.Stack.DAO.Entity.Assignment;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository {
    Optional<List<Assignment>> getAllRoster();
    Optional<Assignment> getRosterByID(int assignment_id);
    Optional<Assignment> assign(Assignment assignment);
    Optional<Assignment> updateAssign(Assignment assignment, int assignment_id);
    Optional<Assignment> deleteAssign(int assignment_id);
}
