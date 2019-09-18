package carRentalAPI;

import java.util.Date;
import java.util.Calendar;

public final class DrivingLicence {

	private final Name driverName;
	private final Date driverDateOfBirth;
	private final Date dateOfIssue;
	private final boolean fullLicence;
	private final LicenceNumber licenceNumber;

	public DrivingLicence(Name driverName, Date driverDateOfBirth, Date dateOfIssue, boolean fullLicence) {

		this.driverName = driverName;
		this.driverDateOfBirth = driverDateOfBirth;
		this.dateOfIssue = dateOfIssue;
		this.fullLicence = fullLicence;

		// Avoid using Date's deprecated methods
		// Use the Calendar to get the year as an integer
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateOfIssue);
		this.licenceNumber = LicenceNumber.makeLicenceNumber(driverName, calendar.get(Calendar.YEAR));
	}
	
	public final Name getDriverName() {
		// Return a direct reference to the name object.
		// This is not dangerous because the Name class is a factory
		// and the fields are Strings which are immutable.
		return driverName;
	}
	
	public final Date getDriverDateOfBirth() {
		return new Date(driverDateOfBirth.getTime());
	}
	
	public final Date getDateOfIssue() {
		return new Date(dateOfIssue.getTime());
	}
	
	public final boolean isFullLicence() {
		return fullLicence;
	}
	
	public final LicenceNumber getLicenceNumber() {
		// The state of this object can be changed by its class only
		// so it's not a problem to return the reference to it
		return licenceNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfIssue == null) ? 0 : dateOfIssue.hashCode());
		result = prime * result + ((driverDateOfBirth == null) ? 0 : driverDateOfBirth.hashCode());
		result = prime * result + ((driverName == null) ? 0 : driverName.hashCode());
		result = prime * result + ((licenceNumber == null) ? 0 : licenceNumber.hashCode());
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
		DrivingLicence other = (DrivingLicence) obj;
		if (dateOfIssue == null) {
			if (other.dateOfIssue != null)
				return false;
		} else if (!dateOfIssue.equals(other.dateOfIssue))
			return false;
		if (driverDateOfBirth == null) {
			if (other.driverDateOfBirth != null)
				return false;
		} else if (!driverDateOfBirth.equals(other.driverDateOfBirth))
			return false;
		if (driverName == null) {
			if (other.driverName != null)
				return false;
		} else if (!driverName.equals(other.driverName))
			return false;
		if (licenceNumber == null) {
			if (other.licenceNumber != null)
				return false;
		} else if (!licenceNumber.equals(other.licenceNumber))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "DrivingLicence [driverName=" + driverName
				+ ", driverDateOfBirth=" + driverDateOfBirth + ", dateOfIssue="
				+ dateOfIssue + ", fullLicence=" + fullLicence
				+ ", licenceNumber=" + licenceNumber + "]";
	}
}
