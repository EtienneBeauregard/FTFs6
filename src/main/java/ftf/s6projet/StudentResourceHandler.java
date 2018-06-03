/**********************************************************************************************
 * 
 **********************************************************************************************/

package ftf.s6projet;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**********************************************************************************************
 * 
 * 
 * @author Éric Poirier
 * @date Juin 2018
 * 
 **********************************************************************************************/
@Path("students")
public class StudentResourceHandler {

    /******************************************************************************************
     * 
     * 
     * @return
     * 
     ******************************************************************************************/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<StudentResource> onGetAllStudents() {
        return DataBase.theDataBase().getSudents();
    }

    
    /******************************************************************************************
     * 
     * 
     * @return
     * 
     ******************************************************************************************/
    @GET
    @Path("/{p_cip}")
    @Produces(MediaType.APPLICATION_JSON)
    public StudentResource onGetOneStudent(@PathParam("p_cip") String p_cip) {
        return DataBase.theDataBase().getSudent(p_cip);
    }


    /******************************************************************************************
     * 
     * 
     * @return
     * @throws URISyntaxException 
     * 
     ******************************************************************************************/
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response onNewStudent(StudentResource p_newStudent) throws URISyntaxException {
        DataBase.theDataBase().addStudent(p_newStudent);
        return Response.created(new URI("/webapi/student/" + p_newStudent.getCip())).build();
    }
    
    
    /******************************************************************************************
     * 
     * 
     * @return
     * 
     ******************************************************************************************/
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response onModifyExistingStudent(StudentResource p_updatedStudent) {
        // Find the actual student thats needs to be updated:
        String cip = p_updatedStudent.getCip();
        StudentResource oldStudent = DataBase.theDataBase().getSudent(cip);
        
        // Update his name:
        DataBase.theDataBase().modifyStudentName(oldStudent, p_updatedStudent.getName());;
        
        // Return the appropriate response:
        return Response.ok().entity(DataBase.theDataBase().getSudent(cip)).build();
    }
    
    
    /******************************************************************************************
     * 
     * 
     * @return
     * 
     ******************************************************************************************/
    public StudentResource onRemoveStudent() {
        return null;
    }
}
