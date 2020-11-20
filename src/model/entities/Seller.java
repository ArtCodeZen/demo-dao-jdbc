package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Seller implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idInteger;
	private String nameString;
	private String emailString;
	private Date birthDate;
	private Double baseSalaryDouble;
	
	private Department department;
	
	public Seller() {
		
	}
	
	public Seller(Integer idInteger, String nameString, String emailString, Date birthDate, Double baseSalaryDouble,
			Department department) {
		//super();
		this.idInteger = idInteger;
		this.nameString = nameString;
		this.emailString = emailString;
		this.birthDate = birthDate;
		this.baseSalaryDouble = baseSalaryDouble;
		this.department = department;
	}

	// getter e setters

	public Integer getIdInteger() {
		return idInteger;
	}

	public void setIdInteger(Integer idInteger) {
		this.idInteger = idInteger;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public String getEmailString() {
		return emailString;
	}

	public void setEmailString(String emailString) {
		this.emailString = emailString;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Double getBaseSalaryDouble() {
		return baseSalaryDouble;
	}

	public void setBaseSalaryDouble(Double baseSalaryDouble) {
		this.baseSalaryDouble = baseSalaryDouble;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idInteger == null) ? 0 : idInteger.hashCode());
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
		Seller other = (Seller) obj;
		if (idInteger == null) {
			if (other.idInteger != null)
				return false;
		} else if (!idInteger.equals(other.idInteger))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seller [idInteger=" + idInteger + ", nameString=" + nameString + ", emailString=" + emailString
				+ ", birthDate=" + birthDate + ", baseSalaryDouble=" + baseSalaryDouble + "]";
	}
	
	
}
