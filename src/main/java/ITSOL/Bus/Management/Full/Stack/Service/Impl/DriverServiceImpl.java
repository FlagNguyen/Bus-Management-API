package ITSOL.Bus.Management.Full.Stack.Service.Impl;

import ITSOL.Bus.Management.Full.Stack.DAO.Entity.Drivers;
import ITSOL.Bus.Management.Full.Stack.DAO.Repository.DriverRepository;
import ITSOL.Bus.Management.Full.Stack.DTO.Request.DriverRequest;
import ITSOL.Bus.Management.Full.Stack.DTO.Response.DriverResponse;
import ITSOL.Bus.Management.Full.Stack.Exception.ResourceNotFoundException;
import ITSOL.Bus.Management.Full.Stack.Service.AbstractService;
import ITSOL.Bus.Management.Full.Stack.Service.DriverService;
import ITSOL.Bus.Management.Full.Stack.Utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl extends AbstractService implements DriverService {

    @Autowired
    DriverRepository driverRepository;

    StringUtil stringUtil;

    @Override
    public Optional<List<DriverResponse>> getAll() {
        List<Drivers> drivers = driverRepository.getAll().orElseThrow(() -> {
            throw new ResourceNotFoundException("Resource not found");
        });
        return Optional.of(drivers.stream().map(driver -> new DriverResponse(driver.getId(), driver.getName(),
                driver.getAddress(), driver.getPhone(), driver.getDLevel())).collect(Collectors.toList()));
    }

    @Override
    public Optional<DriverResponse> getDriverById(int id) {
        Drivers drivers = driverRepository.getDriverById(id).orElseThrow(()->{
            throw new ResourceNotFoundException("Resource not found");
        });
        return Optional.of(new DriverResponse(drivers.getId(), drivers.getName(), drivers.getAddress(),drivers.getPhone()
        ,drivers.getDLevel()));
    }

    @Override
    public Optional<DriverResponse> addDriver(DriverRequest driverRequest) {
        List<DriverResponse> driverResponses = getAll().orElseThrow(() -> {
            throw new ResourceNotFoundException("resource not found");
        });
        int curId = driverResponses.size() + 10000;
        Drivers drivers = new Drivers(curId, stringUtil.standardlizeString(driverRequest.getName()),stringUtil.standardlizeString(driverRequest.getAddress()),driverRequest.getPhone(),driverRequest.getDLevel(),0);
        driverRepository.addDrivers(drivers);
        return Optional.of(objectMapper.convertValue(driverRequest, DriverResponse.class));
    }

    @Override
    public Optional<DriverResponse> updateDriver(DriverRequest driverRequest, int id) {
        Drivers drivers = driverRepository.getDriverById(id).orElseThrow(()->{
            throw new ResourceNotFoundException("Can't found resource");
        });
        driverRepository.updateDriver(objectMapper.convertValue(driverRequest, Drivers.class),id);
        return getDriverById(id);
    }

    @Override
    public Optional<DriverResponse> deleteDriver(int id) {
        Drivers drivers = driverRepository.getDriverById(id).orElseThrow(()->{
            throw new ResourceNotFoundException("Delete Successfully");
        });
        driverRepository.deleteDriver(id);
        return Optional.of(objectMapper.convertValue(drivers, DriverResponse.class));
    }
}
