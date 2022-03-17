package ITSOL.Bus.Management.Full.Stack.Service;

import ITSOL.Bus.Management.Full.Stack.DTO.Request.AssignmentRequest;
import ITSOL.Bus.Management.Full.Stack.DTO.Response.AssignmentResponse;

import java.util.List;
import java.util.Optional;

public interface AssignmentService {
    Optional<List<AssignmentResponse>> getAllRoster();
    Optional<AssignmentResponse> getRosterByID(int assign_id);
    Optional<AssignmentResponse> assign(AssignmentRequest assignmentRequest);
    Optional<AssignmentResponse> updateAssign(AssignmentRequest assignmentRequest, int assign_id);
    Optional<AssignmentResponse> deleteAssign(int assign_id);
}
