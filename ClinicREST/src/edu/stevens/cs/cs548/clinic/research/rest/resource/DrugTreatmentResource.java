package edu.stevens.cs.cs548.clinic.research.rest.resource;

import java.util.List;

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
import edu.stevens.cs.cs548.clinic.domain.DrugTreatment;

import stevens.cs.cs548.clinic.research.service.ejb.IDrugTreatmentBeanRemote;

@Path("generic")
public class DrugTreatmentResource {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;
    
    @EJB(name="DrugTreatmentBean")
    IDrugTreatmentBeanRemote drugTreatment;

    /**
     * Default constructor. 
     */
    public DrugTreatmentResource() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of DrugTreatmentResource
     * @return an instance of String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        // TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of DrugTreatmentResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
    
    @GET
    @Path("{drugName}")
    @Produces("application/xml")
    public List<DrugTreatment> getPatient(@PathParam("drugName") String drugName) 
    {   
        List<DrugTreatment> treatments = drugTreatment.getTreatments(drugName);
        return treatments;
    }
    

}