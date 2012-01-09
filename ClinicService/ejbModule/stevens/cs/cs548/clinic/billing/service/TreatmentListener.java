package stevens.cs.cs548.clinic.billing.service;

import java.util.Random;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.stevens.cs.cs548.clinic.billing.domain.TreatmentBillingDAO;

import stevens.cs.cs548.clinic.service.dto.Treatment;

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
     * When a new treatment is added to the treatments for a patient,
     * the notification is received. 
     */
    
    public void onMessage(Message message) {
        ObjectMessage objMessage = (ObjectMessage)message;
        try {
			Treatment treatment = (Treatment)objMessage.getObject();
			TreatmentBillingDAO tbd = new TreatmentBillingDAO(em);
			
			Random generator = new Random();
			float amount = generator.nextFloat()*500;
			tbd.addBillingInfo(treatment.getTid(), amount);
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
