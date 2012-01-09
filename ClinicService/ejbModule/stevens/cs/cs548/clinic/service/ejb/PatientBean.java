package stevens.cs.cs548.clinic.service.ejb;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

import com.sun.istack.logging.Logger;

import stevens.cs.cs548.clinic.service.dto.DateAdapter;
import stevens.cs.cs548.clinic.service.dto.DrugTreatmentType;
import stevens.cs.cs548.clinic.service.dto.ObjectFactory;
import stevens.cs.cs548.clinic.service.dto.RadiologyType;
import stevens.cs.cs548.clinic.service.dto.SurgeryType;
import stevens.cs.cs548.clinic.service.dto.Treatment;
import edu.stevens.cs.cs548.clinic.domain.ITreatmentVisitor;
import edu.stevens.cs.cs548.clinic.domain.Patient;
import edu.stevens.cs.cs548.clinic.domain.Patient.TreatmentExn;

/**
 * Session Bean implementation class PatientBean
 */
@Stateless
public class PatientBean implements IPatientRemote {

	@EJB(beanName="ClinicBean")
	private IClinicLocal clinic;
	
    public PatientBean() 
    {
    	
    }

	@Override
	public Treatment getTreatment(long pid, long tid)
	{
		Patient patient = clinic.getPatientEntity(pid);
		Treatment_PDO_to_DTO v = new Treatment_PDO_to_DTO();
		try
		{
			patient.visitTreatment(tid, v);
			return v.getTreatment();
		}
		catch(TreatmentExn e)
		{
			return null;
		}
	}
	
	private static class Treatment_PDO_to_DTO implements ITreatmentVisitor
	{

		private Treatment treatmentDTO;
		
		public Treatment getTreatment()
		{
			return this.treatmentDTO;
		}
		
		private ObjectFactory factory = new ObjectFactory();
		
		@Override
		public void visitDrugTreatment(String drug, int dosage) {
			DrugTreatmentType drugTreatment = factory.createDrugTreatmentType();
			drugTreatment.setDrugName(drug);
			drugTreatment.setDosage(dosage);
			treatmentDTO = factory.createTreatment();
			treatmentDTO.setDrug(drugTreatment);
		}

		@Override
		public void visitSurgery(Date date) {
			SurgeryType surgery = factory.createSurgeryType();
			surgery.setDate(date);
			treatmentDTO = factory.createTreatment();
			treatmentDTO.setSurgery(surgery);
		}

		@Override
		public void visitRadiology(List<Date> dates) {
			RadiologyType radiology = factory.createRadiologyType();
			List<String> radDates = radiology.getDates();
			for(Date d :dates)
			{
				radDates.add(DateAdapter.printDate(d));
			}
			treatmentDTO = factory.createTreatment();
			treatmentDTO.setRadiology(radiology);
		}
	}
	
	@Resource(mappedName="jms/clinic/TreatmentPool")
	private ConnectionFactory treatmentConnFactory;
	
	@Resource(mappedName="jms/clinic/Treatment")
	private Topic treatmentTopic;
	
	Logger logger = Logger.getLogger("edu.stevens.cs.cs548.clinic.service.ejb.PatientBean", null);

	@Override
	public void addDrugTreatment(long pid, long npi, String drug, int dosage) 
	{
		Patient p = clinic.getPatientEntity(pid);
		long tid = p.addDrugTreatment(drug, dosage);
		
		//
		// Publish a notification of treatment on the queue.
		//
		
		Connection treatmentConn = null;
		try {
			treatmentConn = treatmentConnFactory.createConnection();
			Session session = treatmentConn.createSession(true, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(treatmentTopic);
			
			Treatment treatment = new Treatment();
			DrugTreatmentType drugTreatment = new DrugTreatmentType();
			drugTreatment.setDosage(dosage);
			drugTreatment.setDrugName(drug);
			treatment.setDrug(drugTreatment);
			treatment.setTid((int)tid);
			
			ObjectMessage message = session.createObjectMessage();
			message.setObject(treatment);
			producer.send(message);
			
		} catch (JMSException e) {
			logger.severe("JMS Error: " + e);
		} finally {
			try
			{
			if(treatmentConn != null)
				treatmentConn.close();
			}
			catch(JMSException e)
			{
				logger.severe("Error closing JMS connection: " + e);
			}
		}
	}

	@Override
	public void addSurgery(long pid, long npi, Date date) {
		Patient p = clinic.getPatientEntity(pid);
		//...
	}

	@Override
	public void addRadiology(long pid, long npi, List<Date> dates) {
		//...
	}
}
