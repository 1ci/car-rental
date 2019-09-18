package carRentalTest;

import carRentalAPI.*;

import java.util.Date;
import java.util.Calendar;

class CarRentalTest {
	
	public static void main(String args[]) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(1990, 5, 5);
		Date dateOfBirth = calendar.getTime();
		
		calendar.set(2010, 8, 1);
		Date dateOfIssue = calendar.getTime();
		
		DrivingLicence hristo = new DrivingLicence(Name.getInstance("Hristo", "Hristov"), dateOfBirth, dateOfIssue, true);
		System.out.println(hristo.toString());
		System.out.println();
		
		System.out.printf("Can Issue large car to %s [%s] = ", hristo.getDriverName(), hristo.getLicenceNumber());
		System.out.println(CarRentalAgency.INSTANCE.issueCar(hristo, Car.TypeOfCar.LARGE));
		System.out.println();
		
		System.out.println("Return a list of the rented cars:");
		System.out.println(CarRentalAgency.INSTANCE.getRentedCars());
		System.out.println();
		
		System.out.println("Available small cars: " + CarRentalAgency.INSTANCE.availableCars(Car.TypeOfCar.SMALL));
		System.out.println("Available large cars: " + CarRentalAgency.INSTANCE.availableCars(Car.TypeOfCar.LARGE));
		System.out.println();
		
		Car car = CarRentalAgency.INSTANCE.getCar(hristo);
		System.out.println("Drive Hristo's car for 100 KM, fuel consumed: " + car.drive(100));
		System.out.println("Current fuel in Hristo's car: " + car.getFuelAmount());
		System.out.println("Add 10 litres to Hristo's car, litres actually added: " + car.addFuel(10));
		System.out.println("Current fuel in Hristo's car: " + car.getFuelAmount());
		System.out.println();
		
		System.out.println("Drive Hristo's car for 200 KM, fuel consumed: " + car.drive(200));
		System.out.println("Terminate Hristo's contract, fuel to add to car: " + CarRentalAgency.INSTANCE.terminateRental(hristo));
		System.out.println();
		
		System.out.println("Return a list of the rented cars:");
		System.out.println(CarRentalAgency.INSTANCE.getRentedCars());
		System.out.println();
		
		System.out.println("Available small cars: " + CarRentalAgency.INSTANCE.availableCars(Car.TypeOfCar.SMALL));
		System.out.println("Available large cars: " + CarRentalAgency.INSTANCE.availableCars(Car.TypeOfCar.LARGE));
	}
}
