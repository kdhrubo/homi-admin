

package com.tryhomi.admin.domain;


import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@SuppressWarnings("serial")
public class PersonalName implements Serializable {

	private String firstName;
	private String lastName;

	public PersonalName() {}

	public PersonalName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return getLastName() + " " + getFirstName();
	}
}
