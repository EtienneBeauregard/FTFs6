package ftf.s6projet.services.release0;


import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//import ftf.s6projet.Service.ServiceAuthentification;

/**********************************************************************************************
 * 
 * 
 * @author Karan Kalsi
 * @date Juin 2018
 * 
 **********************************************************************************************/

@Path("students")
public class ResourceStudent {

	ServiceAuthentification service = new ServiceAuthentification();

	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public ArrayList<Student> getStudents() {
		return service.getStudentsInfo(); 
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Student postStudent(Student p_Student) {
		return service.createNewUser(p_Student);
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Student putStudent(Student p_Student) {
		return service.updateUser(p_Student);
	}
	
	@GET
	@Path("/{studentID}")
	@Produces(MediaType.APPLICATION_JSON) 
	public Student getStudent(@PathParam("studentID") String CIP) {
		return service.getStudentInfo(CIP); 
	}
	
	@DELETE
	@Path("/{studentID}")
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteStudent(@PathParam("studentID") String CIP) {
		return service.removeStudentInfo(CIP); 
	}
}

