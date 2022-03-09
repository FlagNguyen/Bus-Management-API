package ITSOL.Bus.Management.Full.Stack.DAO.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    private int route_ID;
    private float distance;
    private int stations;
    private int isdeleted;
}
