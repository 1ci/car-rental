package carRentalAPI;

import java.util.List;
import java.util.ArrayList;

public final class Name {
	
	private final String firstName;
	private final String lastName;
	
	private static final List<Name> NAMES = new ArrayList<Name>();
	
	private Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public static final Name getInstance(String firstName, String lastName) {
		Name name = new Name(firstName, lastName);
		if (!NAMES.contains(name)) {
			NAMES.add(name);
		}
		return NAMES.get(NAMES.indexOf(name));
	}
	
	/** The caller gets a direct reference to the internal field. This is not 
	  * dangerous, since String is immutable and cannot be changed.
	  */
	public final String getFirstName() {
		return firstName;
	}
	
	public final String getLastName() {
		return lastName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		Name other = (Name) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
