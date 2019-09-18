package carRentalAPI;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Date;
import java.util.Calendar;

import carRentalAPI.Car.TypeOfCar;

public class CarRentalAgency implements CarRental {

	private static final int NUMBER_OF_SMALL_CARS = 20;
	private static final int NUMBER_OF_LARGE_CARS = 10;

	private static final int SMALL_CAR_RENT_MIN_AGE = 21;
	private static final int SMALL_CAR_RENT_MIN_DRIVING_LICENCE_DUR = 1;
	private static final int LARGE_CAR_RENT_MIN_AGE = 25;
	private static final int LARGE_CAR_RENT_MIN_DRIVING_LICENCE_DUR = 5;

	private final Map<DrivingLicence, Car> CONTRACTS = new HashMap<DrivingLicence, Car>();
	private final Car[] SMALLCARS = new SmallCar[NUMBER_OF_SMALL_CARS];
	private final Car[] LARGECARS = new LargeCar[NUMBER_OF_LARGE_CARS];
	
	public static final CarRental INSTANCE = new CarRentalAgency();
	
	private CarRentalAgency() {
		for (int i = 0; i < NUMBER_OF_SMALL_CARS; i++)
			SMALLCARS[i] = new SmallCar();
		for (int i = 0; i < NUMBER_OF_LARGE_CARS; i++)
			LARGECARS[i] = new LargeCar();
	}

	public int availableCars(TypeOfCar type) {
		int rentedCars = 0;
		int availableCars = 0;

		switch (type) {
		case SMALL:
			for (Car car : SMALLCARS)
				if (car.isCarRented())
					rentedCars++;
			availableCars = NUMBER_OF_SMALL_CARS - rentedCars;
			break;
		case LARGE:
			for (Car car : LARGECARS)
				if (car.isCarRented())
					rentedCars++;
			availableCars = NUMBER_OF_LARGE_CARS - rentedCars;
			break;
		}
		return availableCars;
	}

	public Collection<Car> getRentedCars() {
		return CONTRACTS.values();
	}

	public Car getCar(DrivingLicence drivingLicence) {
		return CONTRACTS.get(drivingLicence);
	}

	public boolean issueCar(DrivingLicence drivingLicence, TypeOfCar type) {
		if (drivingLicence == null)
			throw new NullPointerException("Driving licence not found");

		// Is it a full driving licence?
		if (!drivingLicence.isFullLicence())
			return false;

		// Has the person got a car already?
		if (CONTRACTS.containsKey(drivingLicence))
			return false;

		// Is there any available car of the specified type?
		if (availableCars(type) == 0)
			return false;

		Calendar calendar = Calendar.getInstance();
		
		// Calculate person's age
		long todayInMillis = new Date().getTime();
		long dateOfBirthInMillis = drivingLicence.getDriverDateOfBirth().getTime();
		long ageInMillis = todayInMillis - dateOfBirthInMillis;
		calendar.setTimeInMillis(ageInMillis);
		int age = calendar.get(Calendar.YEAR) - 1970;
		
		// Calculate how long the person has held his/her driving licence for
		long dateOfIssueInMillis = drivingLicence.getDateOfIssue().getTime();
		long durationInMillis = todayInMillis - dateOfIssueInMillis;
		calendar.setTimeInMillis(durationInMillis);
		int duration = calendar.get(Calendar.YEAR) - 1970;
		
		switch (type) {
		case SMALL:
			if (age < SMALL_CAR_RENT_MIN_AGE)
				return false;
			if (duration < SMALL_CAR_RENT_MIN_DRIVING_LICENCE_DUR)
				return false;
			for (Car car : SMALLCARS) {
				if (!car.isCarRented()) {
					// Associate driver's licence with the car
					car.setCarRentedState(true);
					CONTRACTS.put(drivingLicence, car);
					return true;
				}
			}
			break;
		case LARGE:
			if (age < LARGE_CAR_RENT_MIN_AGE)
				return false;
			if (duration < LARGE_CAR_RENT_MIN_DRIVING_LICENCE_DUR)
				return false;
			for (Car car : LARGECARS) {
				if (!car.isCarRented()) {
					car.setCarRentedState(true);
					CONTRACTS.put(drivingLicence, car);
					return true;
				}
			}
			break;
		}
		return false;
	}

	public int terminateRental(DrivingLicence drivingLicence) {
		if (drivingLicence == null)
			throw new NullPointerException("Driving licence not found");

		// Get the car associated with the driving licence
		// and remove it from the list of rented cars
		Car car = CONTRACTS.remove(drivingLicence);
		if (car == null)
			throw new NullPointerException("Car not found");

		car.setCarRentedState(false);

		int fuelToAdd = car.getCapacityOfFuelTank() - car.getFuelAmount();
		return fuelToAdd;
	}
}
