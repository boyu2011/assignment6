package stevens.cs.cs548.clinic.service.dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace="http://www.example.org/clinic/schema",
				name="patient")
public class PatientDTO {
	
	@XmlElement(name="patient_id")
	public long id;
	
	@XmlElement
	public String name;
	
	@XmlElement(name="birth-date")
	public Date birthdate;
	
	/*
	 * TODO: Should have a age field.
	 */
	
	@XmlElement
	public List<Long> treatments;

	public PatientDTO()
	{
		
	}
	
	public PatientDTO(long id, String name, Date birthdate, List<Long> treatments)
	{
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
		this.treatments = treatments;
	}
	
}