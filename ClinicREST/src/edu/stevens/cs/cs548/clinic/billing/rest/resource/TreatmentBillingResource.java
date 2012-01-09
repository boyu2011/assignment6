package edu.stevens.cs.cs548.clinic.billing.rest.resource;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import edu.stevens.cs.cs548.clinic.billing.domain.TreatmentBilling;
import edu.stevens.cs.cs548.clinic.reps.PatientRepresentation;

import stevens.cs.cs548.clinic.billing.service.ejb.ITreatmentBillingBeanRemote;
import stevens.cs.cs548.clinic.service.dto.PatientDTO;
import stevens.cs.cs548.clinic.service.ejb.IClinicBeanRemote;

@Path("generic")
public class TreatmentBillingResource {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;
    
    @EJB(name="TreatmentBillingBean")
    ITreatmentBillingBeanRemote treatmentBilling;

    /**
     * Default constructor. 
     */
    public TreatmentBillingResource() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of TreatmentBillingResource
     * @return an instance of String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        // TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of TreatmentBillingResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
    
    @GET
    @Path("{treatmentId}")
    @Produces("application/xml")
    public TreatmentBilling getPatient(@PathParam("treatmentId") String treatmentId) 
    {   
        long tid = Long.parseLong(treatmentId);
        TreatmentBilling tb = treatmentBilling.getTreatmentBilling(tid);
        return tb;
    }
}