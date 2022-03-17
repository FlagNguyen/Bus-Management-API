package ITSOL.Bus.Management.Full.Stack.Service.Impl;

import ITSOL.Bus.Management.Full.Stack.DAO.Entity.Assignment;
import ITSOL.Bus.Management.Full.Stack.DAO.Repository.AssignmentRepository;
import ITSOL.Bus.Management.Full.Stack.DTO.Request.AssignmentRequest;
import ITSOL.Bus.Management.Full.Stack.DTO.Response.AssignmentResponse;
import ITSOL.Bus.Management.Full.Stack.Exception.ResourceNotFoundException;
import ITSOL.Bus.Management.Full.Stack.Service.AbstractService;
import ITSOL.Bus.Management.Full.Stack.Service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentServiceImpl extends AbstractService implements AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public Optional<List<AssignmentResponse>> getAllRoster() {
        List<Assignment> assignments = assignmentRepository.getAllRoster().orElseThrow(()->{
            throw new ResourceNotFoundException("Resource not found");
        });
        return Optional.of(assignments.stream().map(assignment -> new AssignmentResponse(assignment.getAssign_ID(), assignment.getDriver_ID(), assignment.getRoute_ID(), assignment.getTurn())).collect(Collectors.toList()));
    }

    @Override
    public Optional<AssignmentResponse> getRosterByID(int assign_id) {
        Assignment assignments = assignmentRepository.getRosterByID(assign_id).orElseThrow(()->{
            throw new ResourceNotFoundException("Resource not found");
        });
        return Optional.of(new AssignmentResponse(assignments.getAssign_ID(), assignments.getDriver_ID(), assignments.getRoute_ID(),
                assignments.getTurn()));
    }

    @Override
    public Optional<AssignmentResponse> assign(AssignmentRequest assignmentRequest) {
        List<AssignmentResponse> assignmentResponses = getAllRoster().orElseThrow(()->{
            throw new ResourceNotFoundException("Resource not found");
        });
        int curId = assignmentResponses.size();
        Assignment assignment = new Assignment(curId,Integer.parseInt(assignmentRequest.getDriver_ID()),
                Integer.parseInt(assignmentRequest.getRoute_ID()),Integer.parseInt(assignmentRequest.getTurn()),0);
        assignmentRepository.assign(assignment);
        return Optional.of(objectMapper.convertValue(assignmentRequest, AssignmentResponse.class));
    }

    @Override
    public Optional<AssignmentResponse> updateAssign(AssignmentRequest assignmentRequest, int assign_id) {
        Assignment assignment = assignmentRepository.getRosterByID(assign_id).orElseThrow(()->{
            throw new ResourceNotFoundException("Can't found resource");
        });
        assignmentRepository.updateAssign(objectMapper.convertValue(assignmentRequest,Assignment.class),assign_id);
        return getRosterByID(assign_id);
    }

    @Override
    public Optional<AssignmentResponse> deleteAssign(int assign_id) {
        Assignment assignment = assignmentRepository.getRosterByID(assign_id).orElseThrow(()->{
            throw new ResourceNotFoundException("Already Deleted");
        });
        assignmentRepository.deleteAssign(assign_id);
        return Optional.empty();
    }
}
