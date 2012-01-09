package stevens.cs.cs548.clinic.service.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import edu.stevens.cs.cs548.clinic.domain.Clinic;
import edu.stevens.cs.cs548.clinic.domain.IPatientDAO;
import edu.stevens.cs.cs548.clinic.domain.IProviderDAO;
import edu.stevens.cs.cs548.clinic.domain.PatientDAO;
import edu.stevens.cs.cs548.clinic.domain.ProviderDAO;

/**
 * Session Bean implementation class ClinicDAOBean
 */
@Stateless(name="ClinicDAO")
@LocalBean
public class ClinicDAOBean implements IClinicDAOLocal {

    public ClinicDAOBean() {
        // TODO Auto-generated constructor stub
    }
    
    //
    // Copy source code below from ClinicDomainModel
    //
    
    @PersistenceContext(unitName="ClinicDomain")
	private EntityManager em;
    
	private IPatientDAO patientDAO;
	private IProviderDAO providerDAO;
	
	@PostActivate
	public void initialize()
	{
		this.patientDAO = new PatientDAO(em);
		this.providerDAO = new ProviderDAO(em);
	}
		
	/*@Override
	public void addClinic(Clinic clinic) throws ClinicExn
	{
		if (getClinics(clinic.getName()).size()==0)
		{
			em.getTransaction().begin();
			em.persist(clinic);
			em.getTransaction().commit();
			clinic.setPatientDAO(this.patientDAO);		//why??
			clinic.setProviderDAO(this.providerDAO);	//why??
		}
		else
		{
			throw new ClinicExn("Duplicate clinic: "+clinic.getName());
		}
	}*/
	
	private List<Clinic> getClinics(String name)
	{
		try
		{
			TypedQuery<Clinic> query = em.createNamedQuery("findClinicByName", Clinic.class)
				.setParameter("name", name)
				.setMaxResults(1);
			List<Clinic> clinics = query.getResultList();
			return clinics;
		}
		catch(RuntimeException e)
		{
			throw e;
		}
	}
	
	@Override
	public Clinic getClinic(String name) 
	{
		List<Clinic> clinics = getClinics(name);
		if ( clinics.size() == 1 )
		{
			Clinic clinic = clinics.get(0);
			clinic.setPatientDAO(patientDAO);	// ???
			clinic.setProviderDAO(providerDAO);	// ???
			return clinic;
		}
		else 
		{
			return null;
		}
	}

}
