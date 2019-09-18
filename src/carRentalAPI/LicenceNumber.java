package carRentalAPI;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public final class LicenceNumber {
	
	private final String initials;
	private final int yearOfIssue;
	private final int serialNumber;
	
	private static final List<LicenceNumber> LICENCE_NUMBERS = new ArrayList<LicenceNumber>();
	
	private LicenceNumber(Name name, int yearOfIssue) {
		// Get the initials out of the name
		initials = name.getFirstName().substring(0, 1).concat(name.getLastName().substring(0, 1));
		
		this.yearOfIssue = yearOfIssue;
		
		// Generate a random serial number from 0 to 100 exclusive
		Random r = new Random();
		this.serialNumber = r.nextInt(100);
	}
	
	public static final LicenceNumber makeLicenceNumber(Name name, int yearOfIssue) {
		LicenceNumber instance = new LicenceNumber(name, yearOfIssue);
		while (LICENCE_NUMBERS.contains(instance)) {
			instance = new LicenceNumber(name, yearOfIssue);
		}
		LICENCE_NUMBERS.add(instance);
		return instance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((initials == null) ? 0 : initials.hashCode());
		result = prime * result + yearOfIssue;
		result = prime * result + serialNumber;
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
		LicenceNumber other = (LicenceNumber) obj;
		if (initials == null) {
			if (other.initials != null)
				return false;
		} else if (!initials.equals(other.initials))
			return false;
		if (serialNumber != other.serialNumber)
			return false;
		if (yearOfIssue != other.yearOfIssue)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return initials + "-" + yearOfIssue + "-" + serialNumber;
	}
}
