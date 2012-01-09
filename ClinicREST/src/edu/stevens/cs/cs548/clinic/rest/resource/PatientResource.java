package edu.stevens.cs.cs548.clinic.rest.resource;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import stevens.cs.cs548.clinic.service.ClinicServiceExn;
import stevens.cs.cs548.clinic.service.dto.PatientDTO;
import stevens.cs.cs548.clinic.service.ejb.IClinicBeanRemote;

import edu.stevens.cs.cs548.clinic.reps.PatientRepresentation;

@Path("generic")
public class PatientResource {
	
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;
    
    @EJB(name="ClinicBean")
    IClinicBeanRemote clinic;

    /**
     * Default constructor. 
     */
    public PatientResource() {
        // TODO Auto-generated constructor stub
    }

    @POST
    @Consumes("application/xml")
    public Response addPatient(PatientRepresentation patient)
    {
    	try
    	{
    		clinic.addPatient(patient.getName(), patient.getDob(), patient.getAge());
    		return Response.created(null).build();
    	}
    	catch(ClinicServiceExn e)
    	{
    		throw new WebApplicationException();
    	}
    }
    
    @GET
    @Path("{patientId}")
    @Produces("application/xml")
    public PatientRepresentation getPatient(@PathParam("patientId") String patientId) 
    {
    	long pid = Long.parseLong(patientId);
    	PatientDTO patient = clinic.getPatient(pid);
    	PatientRepresentation patientRep = new PatientRepresentation(patient);
        return patientRep;
    }

    /**
     * PUT method for updating or creating an instance of PatientResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

}