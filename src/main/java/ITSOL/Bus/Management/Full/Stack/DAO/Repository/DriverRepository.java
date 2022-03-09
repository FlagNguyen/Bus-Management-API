package ITSOL.Bus.Management.Full.Stack.DAO.Repository;

import ITSOL.Bus.Management.Full.Stack.DAO.Entity.Drivers;
import ITSOL.Bus.Management.Full.Stack.DTO.Request.DriverRequest;

import java.util.List;
import java.util.Optional;

public interface DriverRepository {
    Optional<List<Drivers>> getAll();
    Optional<Drivers> getDriverById(int id);
    Optional<Drivers> addDrivers(Drivers driver);
    Optional<Drivers> updateDriver(Drivers driver, int id);
    Optional<Drivers> deleteDriver(int id);
}
