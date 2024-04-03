package ITSOL.Bus.Management.Full.Stack.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteResponse {
    private int routeId;
    private float distance;
    private int stations;
}
