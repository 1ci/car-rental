package carRentalAPI;

import java.util.Collection;
import carRentalAPI.Car.TypeOfCar;

public interface CarRental {
	
	public int availableCars(TypeOfCar type);
	public Collection<Car> getRentedCars();
	public Car getCar(DrivingLicence drivingLicence);
	public boolean issueCar(DrivingLicence drivingLicence, TypeOfCar type);
	public int terminateRental(DrivingLicence drivingLicence);
}
