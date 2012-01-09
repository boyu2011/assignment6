package stevens.cs.cs548.clinic.billing.service.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import edu.stevens.cs.cs548.clinic.billing.domain.TreatmentBilling;

/**
 * Session Bean implementation class TreatmentBillingBean
 */
@Stateless(name="TreatmentBillingBean")
@LocalBean
public class TreatmentBillingBean implements ITreatmentBillingBeanRemote {

    /**
     * Default constructor. 
     */
    public TreatmentBillingBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public TreatmentBilling getTreatmentBilling(long treatmentId) {
		// TODO Auto-generated method stub
		return null;
	}
}
