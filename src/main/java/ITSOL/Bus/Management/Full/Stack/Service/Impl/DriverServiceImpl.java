package ITSOL.Bus.Management.Full.Stack.Service.Impl;

import ITSOL.Bus.Management.Full.Stack.DAO.Entity.Drivers;
import ITSOL.Bus.Management.Full.Stack.DAO.Repository.DriverRepository;
import ITSOL.Bus.Management.Full.Stack.DTO.Request.DriverRequest;
import ITSOL.Bus.Management.Full.Stack.DTO.Response.DriverResponse;
import ITSOL.Bus.Management.Full.Stack.Exception.InvalidLevelFormatException;
import ITSOL.Bus.Management.Full.Stack.Exception.InvalidPhoneNumberFormatException;
import ITSOL.Bus.Management.Full.Stack.Exception.InvalidRequestException;
import ITSOL.Bus.Management.Full.Stack.Exception.ResourceNotFoundException;
import ITSOL.Bus.Management.Full.Stack.Service.AbstractService;
import ITSOL.Bus.Management.Full.Stack.Service.DriverService;
import ITSOL.Bus.Management.Full.Stack.Utility.StringUtil;
import ITSOL.Bus.Management.Full.Stack.Utility.ValidateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl extends AbstractService implements DriverService {

    @Autowired
    private DriverRepository driverRepository;


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
        Drivers drivers = driverRepository.getDriverById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Can't found resource");
        });
        return Optional.of(new DriverResponse(drivers.getId(), drivers.getName(), drivers.getAddress(), drivers.getPhone()
                , drivers.getDLevel()));
    }

    @Override
    public Optional<List<DriverResponse>> getTotalDriver() {
        List<Drivers> drivers = driverRepository.getTotalDriver().orElseThrow(() -> {
            throw new ResourceNotFoundException("Resource not found");
        });
        return Optional.of(drivers.stream().map(driver -> new DriverResponse(driver.getId(), driver.getName(),
                driver.getAddress(), driver.getPhone(), driver.getDLevel())).collect(Collectors.toList()));
    }

    @Override
    public Optional<DriverResponse> addDriver(DriverRequest driverRequest) {
        validateDriverRequestAndReturnMessage(driverRequest);
        List<DriverResponse> driverResponses = getTotalDriver().orElseThrow(() -> {
            throw new ResourceNotFoundException("Resource not found");
        });
        int curId = driverResponses.size() + 10000;
        Drivers drivers = new Drivers(curId, driverRequest.getName(),
                driverRequest.getAddress(),
                driverRequest.getPhone(),
                driverRequest.getDLevel(), 0);
        driverRepository.addDrivers(drivers);
        return Optional.of(objectMapper.convertValue(driverRequest, DriverResponse.class));
    }

    @Override
    public Optional<DriverResponse> updateDriver(DriverRequest driverRequest, int id) {
        Drivers drivers = driverRepository.getDriverById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Can't found resource");
        });
        validateDriverRequestAndReturnMessage(driverRequest);
        driverRepository.updateDriver(objectMapper.convertValue(driverRequest, Drivers.class), id);
        return getDriverById(id);
    }

    @Override
    public Optional<DriverResponse> deleteDriver(int id) {
        Drivers drivers = driverRepository.getDriverById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Delete Successfully");
        });
        driverRepository.deleteDriver(id);
        return Optional.of(objectMapper.convertValue(drivers, DriverResponse.class));
    }

    private void validateDriverRequestAndReturnMessage(DriverRequest driverRequest) {
        String mess = objectValidator.validateRequestandReturnMessage(driverRequest);
        if (!ObjectUtils.isEmpty(mess)) {
            throw new InvalidRequestException(mess);
        }
        if (ObjectUtils.isEmpty(ValidateRequest.validPhone(driverRequest.getPhone()))) {
            throw new InvalidPhoneNumberFormatException(mess);
        }
        if (ObjectUtils.isEmpty(ValidateRequest.validLevel(driverRequest.getDLevel()))) {
            throw new InvalidLevelFormatException(mess);
        }
    }
}
