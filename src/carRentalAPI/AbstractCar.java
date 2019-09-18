package carRentalAPI;

public abstract class AbstractCar implements Car {
	
	private RegistrationNumber registrationNumber;
	private int fuel;
	private boolean isRented;
	
	public AbstractCar() {
		registrationNumber = RegistrationNumber.generateRegistrationNumber();
		setFuelAmount(getCapacityOfFuelTank());
	}
	
	public final RegistrationNumber getRegistrationNumber() {
		return registrationNumber; // This is safe.
	}
	
	public final int getFuelAmount() {
		return fuel;
	}
	
	public final void setFuelAmount(int fuel) {
		this.fuel = fuel;
	}
	
	public final boolean isCarRented() {
		return isRented;
	}
	
	public final void setCarRentedState(boolean state) {
		isRented = state;
	}
	
	public final boolean isFuelTankFull() {
		return (getFuelAmount() == getCapacityOfFuelTank());
	}

	public final int addFuel(int litres) {
		// If the tank is already full
		if (isFuelTankFull())
			return 0;
		
		// If the car is not rented
		if (!isCarRented())
			return 0;
		
		int fuel = getFuelAmount();
		fuel += litres;
		
		if (fuel > getCapacityOfFuelTank()) {
			this.fuel = getCapacityOfFuelTank();
			// Calculate how many litres were added
			return -(fuel - getCapacityOfFuelTank() - litres);
		}
		setFuelAmount(fuel);
		return litres;
	}
	
	public final boolean meetDriveConditions() {
		// Is the car rented?
		if (!isCarRented())
			return false;
		
		// Does it have enough fuel?
		if (getFuelAmount() <= 0)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((registrationNumber == null) ? 0 : registrationNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractCar other = (AbstractCar) obj;
		if (registrationNumber == null) {
			if (other.registrationNumber != null)
				return false;
		} else if (!registrationNumber.equals(other.registrationNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass() + " [" + registrationNumber + ", Fuel=" + fuel + ", isRented=" + isRented + "]";
	}
}
