package carRentalAPI;

public class LargeCar extends AbstractCar {
	
	private static final int FUEL_TANK_CAPACITY = 65;
	private static final int FUEL_CONSUMPTION_RATE1 = 15;
	private static final int FUEL_CONSUMPTION_RATE1_KILOMETRES = 50;
	private static final int FUEL_CONSUMPTION_RATE2 = 20;
	
	public int getCapacityOfFuelTank() {
		return FUEL_TANK_CAPACITY;
	}
	
	public int drive(int kilometres) {
		if (!meetDriveConditions())
			return 0;
		
		// Make sure kilometres is always a positive number
		kilometres = (kilometres < 0) ? -kilometres : kilometres;
		
		int fuelConsumed;
		if (kilometres <= FUEL_CONSUMPTION_RATE1_KILOMETRES) {
			fuelConsumed = kilometres / FUEL_CONSUMPTION_RATE1;
		} else {
			fuelConsumed = FUEL_CONSUMPTION_RATE1_KILOMETRES / FUEL_CONSUMPTION_RATE1;
			fuelConsumed += (kilometres - FUEL_CONSUMPTION_RATE1_KILOMETRES) / FUEL_CONSUMPTION_RATE2;
		}
		setFuelAmount(getFuelAmount() - fuelConsumed);
		return fuelConsumed;
	}
	
	@Override
	public String toString() {
		return "LargeCar [" + getRegistrationNumber() + ", Fuel=" + getFuelAmount() + ", isRented=" + isCarRented() + "]";
	}
}
