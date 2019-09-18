package carRentalAPI;

public interface Car {
	
	public enum TypeOfCar {
		SMALL, LARGE
	}
	
	public RegistrationNumber getRegistrationNumber();
	public int getCapacityOfFuelTank();
	public int getFuelAmount();
	public boolean isFuelTankFull();
	public boolean isCarRented();
	public int addFuel(int litres);
	public int drive(int kilometres);
	public void setCarRentedState(boolean state);
}
