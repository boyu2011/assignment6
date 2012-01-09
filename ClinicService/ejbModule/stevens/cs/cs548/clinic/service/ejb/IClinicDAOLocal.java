package stevens.cs.cs548.clinic.service.ejb;
import javax.ejb.Local;

import edu.stevens.cs.cs548.clinic.domain.Clinic;

@Local
public interface IClinicDAOLocal {
	
	public Clinic getClinic(String name);
}
