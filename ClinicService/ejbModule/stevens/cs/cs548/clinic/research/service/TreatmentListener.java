package stevens.cs.cs548.clinic.research.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import stevens.cs.cs548.clinic.service.dto.Treatment;
import edu.stevens.cs.cs548.clinic.research.domain.DrugResearchDAO;

/**
 * Message-Driven Bean implementation class for: TreatmentListener
 *
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Topic"
		) },
		mappedName = "jms/clinic/Treatment")
public class TreatmentListener implements MessageListener {

    /**
     * Default constructor. 
     */
    public TreatmentListener() {
        // TODO Auto-generated constructor stub
    }
	
    @PersistenceContext(unitName="ClinicDomain")
    private EntityManager em;
    
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	ObjectMessage objMessage = (ObjectMessage)message;
        try {
			Treatment treatment = (Treatment)objMessage.getObject();
			DrugResearchDAO trd = new DrugResearchDAO(em);
			
			trd.addResearchInfo(treatment.getTid(), treatment.getDrug().getDrugName());
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
