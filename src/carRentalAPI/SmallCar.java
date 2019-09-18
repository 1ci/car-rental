package carRentalAPI;

public class SmallCar extends AbstractCar {
	
	private static final int FUEL_TANK_CAPACITY = 45;
	private static final int FUEL_CONSUMPTION_RATE = 25;
	
	public int getCapacityOfFuelTank() {
		return FUEL_TANK_CAPACITY;
	}

	public int drive(int kilometres) {
		if (!meetDriveConditions())
			return 0;
		
		// Make sure kilometres is always a positive number
		kilometres = (kilometres < 0) ? -kilometres : kilometres;
		
		// Return fuel consumed
		int fuelConsumed = kilometres / FUEL_CONSUMPTION_RATE;
		setFuelAmount(getFuelAmount() - fuelConsumed);
		return fuelConsumed;
	}
	
	@Override
	public String toString() {
		return "SmallCar [" + getRegistrationNumber() + ", Fuel=" + getFuelAmount() + ", isRented=" + isCarRented() + "]";
	}
}
