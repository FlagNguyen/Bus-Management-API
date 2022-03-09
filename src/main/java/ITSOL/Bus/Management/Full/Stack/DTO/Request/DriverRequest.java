package ITSOL.Bus.Management.Full.Stack.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverRequest {

    @NotEmpty(message = "Driver's name is mandatory")
    private String name;
    @NotEmpty(message = "Address is mandatory")
    private String address;
    @NotEmpty(message = "Phone is mandatory")
    private String phone;
    @NotEmpty(message = "Driver's level is mandatory")
    private String dLevel;
}
