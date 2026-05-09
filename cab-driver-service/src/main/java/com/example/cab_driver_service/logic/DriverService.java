	package com.example.cab_driver_service.logic;

	import java.io.IOException;
	import java.time.LocalDateTime;
	import java.util.List;

	import org.modelmapper.ModelMapper;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

	import com.example.cab_driver_service.DTO.DriverRequestDTO;
	import com.example.cab_driver_service.DTO.DriverResponseDTO;
	import com.example.cab_driver_service.Enum.DriverStatus;
	import com.example.cab_driver_service.model.Driver;
	import com.example.cab_driver_service.repository.DriverRepository;

	@Service
	public class DriverService{

		@Autowired
		private DriverRepository driverRepository;

		@Autowired
		private ModelMapper modelMapper;

		@Autowired
		private FileStorageService fileStorageService;

		public DriverResponseDTO registerDriver(DriverRequestDTO request) throws IOException{

			if(driverRepository.existsByEmail(request.getEmail())){
				 throw new RuntimeException("This email already exists");
			}
		    if(driverRepository.existsByVehicleNumber(request.getVehicleNumber())){
			        throw new RuntimeException("Vehicle already registered");
			    }
		    if(request.getLicenseFile().isEmpty()){
		        throw new RuntimeException("License file is required");
		    }

		    String fileName = fileStorageService.storeFile(request.getLicenseFile());

	//		Driver driver = new Driver();
	//		driver.setName(request.getName());
	//		driver.setEmail(request.getEmail());
	//		driver.setPhone(request.getPhone());
	//		driver.setVehicleNumber(request.getVehicleNumber());
	//		driver.setLicenseNumber(request.getLicenseNumber());
	//		driver.setVehicleModel(request.getVehicleModel());
	//		driver.setCreatedAt(LocalDateTime.now());
	//		driver.setStatus(DriverStatus.OFFLINE);
	//
	//		Driver savedDriver=driverRepository.save(driver);
	//
	//		DriverResponseDTO response=new DriverResponseDTO();
	//		response.setName(savedDriver.getName());
	//		response.setPhone(savedDriver.getPhone());
	//		response.setVehicleModel(savedDriver.getVehicleModel());
	//		response.setVehicleNumber(savedDriver.getVehicleNumber());
	//
	//		return response;

			 Driver driver=modelMapper.map(request, Driver.class);

			 driver.setCreatedAt(LocalDateTime.now());
			 driver.setStatus(DriverStatus.OFFLINE);
			 driver.setLicenseFilePath(fileName);

			 Driver savedDriver=driverRepository.save(driver);

			 return modelMapper.map(savedDriver, DriverResponseDTO.class);
		}

		public void updateDriverStatus(Long driverId,DriverStatus status){
			Driver driver=driverRepository.findById(driverId)
					.orElseThrow(()-> new RuntimeException("Driver Not Found"));

			driver.setStatus(status);
			driverRepository.save(driver);
		}

		public List<Driver>getAvailableDrivers(){
			return driverRepository.findByStatus(DriverStatus.AVAILABLE);
		}

	}
