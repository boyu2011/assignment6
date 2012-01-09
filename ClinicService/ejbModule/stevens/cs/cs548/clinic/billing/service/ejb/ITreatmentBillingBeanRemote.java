package stevens.cs.cs548.clinic.billing.service.ejb;
import javax.ejb.Remote;

import edu.stevens.cs.cs548.clinic.billing.domain.TreatmentBilling;

@Remote
public interface ITreatmentBillingBeanRemote {

	TreatmentBilling getTreatmentBilling(long treatmentId);
}
