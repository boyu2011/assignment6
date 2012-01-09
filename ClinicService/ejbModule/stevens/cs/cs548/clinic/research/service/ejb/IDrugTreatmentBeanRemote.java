package stevens.cs.cs548.clinic.research.service.ejb;
import java.util.List;

import javax.ejb.Remote;

import edu.stevens.cs.cs548.clinic.domain.DrugTreatment;

@Remote
public interface IDrugTreatmentBeanRemote {

	List<DrugTreatment> getTreatments(String drugName); 
}
