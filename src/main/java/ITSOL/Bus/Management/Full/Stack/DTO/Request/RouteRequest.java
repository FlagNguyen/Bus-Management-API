package ITSOL.Bus.Management.Full.Stack.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteRequest {
    @NotEmpty(message = "Distance is mandatory")
    private String distance;
    @NotEmpty(message = "Stations is mandatory")
    private String stations;
}
