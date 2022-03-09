package ITSOL.Bus.Management.Full.Stack.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverResponse {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String dLevel;
}
