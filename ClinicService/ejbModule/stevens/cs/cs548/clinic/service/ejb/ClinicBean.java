package stevens.cs.cs548.clinic.service.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.Stateless;

import stevens.cs.cs548.clinic.service.ClinicServiceExn;
import stevens.cs.cs548.clinic.service.dto.PatientDTO;
import edu.stevens.cs.cs548.clinic.domain.Clinic;
import edu.stevens.cs.cs548.clinic.domain.Patient;

/**
 * Session Bean implementation class ClinicEJB
 */
@Stateless(name="ClinicBean")
public class ClinicBean implements IClinicBeanRemote, 
								   IClinicBeanLocal,
								   IClinicLocal 
{
	// Defined in the META-INF/ejb-jar.xml
	@Resource(name="ClinicName")
	private String clinicName;
	
	@EJB(name="ClinicDAO")
	private IClinicDAOLocal clinicDAO;
	
	private Clinic clinic;
	
	public ClinicBean()
	{
		
	}
	
	@PostActivate
	public void initialize()
	{
		clinic = clinicDAO.getClinic(this.clinicName);
	}
	
	@Override
	public String getClinicName()
	{
		return this.clinicName;
	}

	@Override
	public List<Long> getPatients() 
	{
		Set<Patient> patients = clinic.getPatients();
		List<Long> pids = new ArrayList<Long>();
		for(Patient p : patients)
		{
			pids.add(p.getId());
		}
		return pids;
	}

	@Override
	public PatientDTO getPatient(long id) {
		Patient patient = clinic.getPatient(id);
		List<Long> tids = patient.getTreatmentIds();
		return new PatientDTO(patient.getId(), patient.getName(), patient.getBirthdate(), tids);
	}

	@Override
	public List<PatientDTO> getPatient(String name, Date birthdate) {
		List<Patient> patients = clinic.getPatients(name, birthdate);
		List<PatientDTO> dtos = new ArrayList<PatientDTO>();
		for(Patient p : patients)
		{
			PatientDTO dto = new PatientDTO(p.getId(),
										    p.getName(),
										    p.getBirthdate(),
										    p.getTreatmentIds());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public void addPatient(String name, Date birthdate, int age)
			throws ClinicServiceExn 
	{
		clinic.addPatient(name, birthdate, age);
	}

	@Override
	public Patient getPatientEntity(long id) {
		return clinic.getPatient(id);
	}
	
	
/*
	@EJB(beanName="PatientDAO")
	PatientDAO patientDAO;
	
	@EJB(beanName="ProviderDAO")
    ProviderDAO providerDAO;
	
	@EJB(beanName="TreatmentDAO")
	TreatmentDAO treatmentDAO;
	
    public ClinicBean() {
        // TODO Auto-generated constructor stub
    }
    
    public PatientDTO GetPatient(long id)
    {
    	Patient patient = patientDAO.GetPatient(id);
    	PatientDTO patientDTO = new PatientDTO( patient.getId(),
    											patient.getName(),
    											patient.GetTreatmentIds());
    	return patientDTO;
    }
    
    public ArrayList<PatientDTO> GetPatients(String name)
    {
    	ArrayList<Patient> patients = patientDAO.GetPatientList(name);
    	ArrayList<PatientDTO> patientDTOs = new ArrayList<PatientDTO>();
    	for(Patient patient : patients)
    	{
    		patientDTOs.add(new PatientDTO(patient.getId(),
    									   patient.getName(),
    									   patient.GetTreatmentIds()));
    	}
    	return patientDTOs;
    }
    
    public ProviderDTO GetProvider(String npi)
    {
    	Provider provider = providerDAO.GetProvider(npi);
    	ProviderDTO providerDTO = new ProviderDTO(provider.getName(),
    											  provider.getNpi(),
    											  null);
    	return providerDTO;
    }
    
    public ArrayList<ProviderDTO> GetProviderList(String name)
    {
    	ArrayList<Provider> providers = providerDAO.GetProviderList(name);
    	ArrayList<ProviderDTO> providerDTOs = new ArrayList<ProviderDTO>();
    	for(Provider provider : providers)
    	{
    		providerDTOs.add(new ProviderDTO(provider.getName(),
    										 provider.getNpi(),
    										 null));
    	}
    	return providerDTOs;
    }
    
    public Long AddPatient(String name, Date birthdate)
    {
    	Patient patient = patientDAO.CreatePatient(name, birthdate);
    	return patient.getId();
    }
    
    public Physician CreatePhysician (String name, String npi)
	{
    	Physician p = providerDAO.CreatePhysician(name, npi);
    	return p;
	}
	
	public Radiologist CreateRadiologist(String name, String npi)
	{
		Radiologist r = providerDAO.CreateRadiologist(name, npi);
		return r;
	}
	
	public Surgeon CreateSurgeon(String name, String npi)
	{
		Surgeon s = providerDAO.CreateSurgeon(name, npi);
		return s;
	}
	
	public TreatmentDTO GetTreatmentDTO(String id)
	{
		Treatment t = treatmentDAO.GetTreatment(id);
		TreatmentDTO treatmentDTO = new TreatmentDTO(t.getId());
		return treatmentDTO;
	}

	public void AddDrugTreatmentForPatient(DrugTreatment dt, Patient patient)
	{
		//...
		patientDAO.AddDrugTreatmentForPatient(dt, patient);
	}
	
	public void AddSurgeryTreatmentForPatient(SurgeryTreatment st, Patient patient)
	{
		//...
		patientDAO.AddSurgeryTreatmentForPatient(st, patient);
	}
	
	public void AddRadiologyTreatmentForPatient(RadiologyTreatment rt, Patient patient)
	{
		//...
		patientDAO.AddRadiologyTreatmentForPatient(rt, patient);
	}
*/
}
