package ITSOL.Bus.Management.Full.Stack.DAO.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {
    private int assign_ID;
    private int driver_ID;
    private int route_ID;
    private int turn;
    private int isdeleted;
}
