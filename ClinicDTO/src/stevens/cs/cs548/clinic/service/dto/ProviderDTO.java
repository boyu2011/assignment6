package stevens.cs.cs548.clinic.service.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace="http://www.example.org/clinic/provider",
				name="provider")
public class ProviderDTO {
	
	@XmlElement
	public String name;
	
	@XmlElement
	public String npi;
	
	@XmlElement
	public List<Long> treatments;
	
	public ProviderDTO(String name, String npi, List<Long> treatments)
	{
		this.name = name;
		this.npi = npi;
		this.treatments = treatments;
	}
	
	public ProviderDTO()
	{
		
	}
}