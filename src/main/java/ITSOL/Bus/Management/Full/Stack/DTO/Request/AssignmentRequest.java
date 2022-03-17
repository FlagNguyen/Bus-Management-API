package ITSOL.Bus.Management.Full.Stack.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentRequest {
    @NotEmpty(message = "driver_ID is mandatory")
    private String driver_ID;
    @NotEmpty(message = "route_ID is mandatory")
    private String route_ID;
    @NotEmpty(message = "turn is mandatory")
    private String turn;
}
