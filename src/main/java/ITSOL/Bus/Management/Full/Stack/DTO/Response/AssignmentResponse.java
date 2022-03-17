package ITSOL.Bus.Management.Full.Stack.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentResponse {
    private int assign_ID;
    private int driver_ID;
    private int route_ID;
    private int turn;
}
