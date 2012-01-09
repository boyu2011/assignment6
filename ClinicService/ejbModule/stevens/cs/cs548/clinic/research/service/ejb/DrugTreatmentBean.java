package stevens.cs.cs548.clinic.research.service.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import edu.stevens.cs.cs548.clinic.domain.DrugTreatment;

/**
 * Session Bean implementation class DrugTreatmentBean
 */
@Stateless(name="DrugTreatmentBean")
@LocalBean
public class DrugTreatmentBean implements IDrugTreatmentBeanRemote {

    /**
     * Default constructor. 
     */
    public DrugTreatmentBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<DrugTreatment> getTreatments(String drugName) {
		// TODO Auto-generated method stub
		return null;
	}

}
