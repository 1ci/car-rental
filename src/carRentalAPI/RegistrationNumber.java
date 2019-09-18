package carRentalAPI;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public final class RegistrationNumber {

	private final char letter;
	private final int number;
	
	private static final List<RegistrationNumber> PLATES = new ArrayList<RegistrationNumber>();
	
	private RegistrationNumber() {
		Random r = new Random();
		
		// Random lowercase letter
		letter = (char) (97 + r.nextInt(27));
		// Random 4-digit number
		number = 1000 + r.nextInt(9000);
	}
	
	public static final RegistrationNumber generateRegistrationNumber() {
		RegistrationNumber plate = new RegistrationNumber();
		while (PLATES.contains(plate)) {
			// Keep generating until the plate doesn't match any existing ones
			plate = new RegistrationNumber();
		}
		PLATES.add(plate);
		return plate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) letter;
		result = prime * result + number;
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
		RegistrationNumber other = (RegistrationNumber) obj;
		if (letter != other.letter)
			return false;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%c%d", letter, number);
	}
}
