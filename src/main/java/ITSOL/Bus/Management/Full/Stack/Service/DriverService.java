package ITSOL.Bus.Management.Full.Stack.Service;

import ITSOL.Bus.Management.Full.Stack.DAO.Entity.Drivers;
import ITSOL.Bus.Management.Full.Stack.DTO.Request.DriverRequest;
import ITSOL.Bus.Management.Full.Stack.DTO.Response.DriverResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface DriverService {
    Optional<List<DriverResponse>> getAll();
    Optional<DriverResponse> getDriverById(int id);
    Optional<DriverResponse> addDriver(DriverRequest driverRequest);
    Optional<DriverResponse> updateDriver(DriverRequest driverRequest, int id);
    Optional<DriverResponse> deleteDriver(int id);
}
