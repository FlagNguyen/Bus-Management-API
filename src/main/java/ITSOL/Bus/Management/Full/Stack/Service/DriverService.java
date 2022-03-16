package ITSOL.Bus.Management.Full.Stack.Service;

import ITSOL.Bus.Management.Full.Stack.DTO.Request.DriverRequest;
import ITSOL.Bus.Management.Full.Stack.DTO.Response.DriverResponse;

import java.util.List;
import java.util.Optional;


public interface DriverService {
    Optional<List<DriverResponse>> getAll();

    Optional<DriverResponse> getDriverById(int id);

    Optional<List<DriverResponse>> getTotalDriver();

    Optional<DriverResponse> addDriver(DriverRequest driverRequest);

    Optional<DriverResponse> updateDriver(DriverRequest driverRequest, int id);

    Optional<DriverResponse> deleteDriver(int id);
}
