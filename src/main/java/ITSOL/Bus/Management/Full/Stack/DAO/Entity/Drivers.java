package ITSOL.Bus.Management.Full.Stack.DAO.Entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drivers {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String dLevel;
    private int isdeleted;
}
