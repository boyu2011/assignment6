package stevens.cs.cs548.clinic.service.ejb;

import java.util.Date;
import java.util.List;

import stevens.cs.cs548.clinic.service.ClinicServiceExn;
import stevens.cs.cs548.clinic.service.dto.PatientDTO;

public interface IClinicBean {

	public List<Long> getPatients();
	
	public PatientDTO getPatient(long id);
	
	public List<PatientDTO> getPatient(String name, Date birthdate);
	
	public void addPatient(String name, Date birthdate, int age)
		throws ClinicServiceExn;
	
	public String getClinicName();
	
	/*
	public PatientDTO GetPatient(long id);
	
	public ArrayList<PatientDTO> GetPatients(String name);
	
	public ProviderDTO GetProvider(String npi);
    
    public ArrayList<ProviderDTO> GetProviderList(String name);
    
    public Long AddPatient(String name, Date birthdate);
    
    public Physician CreatePhysician (String name, String npi);
	
	public Radiologist CreateRadiologist(String name, String npi);
	
	public Surgeon CreateSurgeon(String name, String npi);
	
	public TreatmentDTO GetTreatmentDTO(String id);
	
	public void AddDrugTreatmentForPatient(DrugTreatment dt, Patient patient);
	
	public void AddSurgeryTreatmentForPatient(SurgeryTreatment st, Patient patient);
	
	public void AddRadiologyTreatmentForPatient(RadiologyTreatment rt, Patient patient);
	*/
}
