/**********************************************************************************************
 * 
 **********************************************************************************************/

package ftf.s6projet;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
        return DataBase.theDataBase().getStudents();
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
        return DataBase.theDataBase().getStudent(p_cip);
    }


    /******************************************************************************************
     * 
     * 
     * @return
     * 
     ******************************************************************************************/
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StudentResource onNewStudent(StudentResource p_newStudent) throws URISyntaxException {
        DataBase.theDataBase().addStudent(p_newStudent);
        return DataBase.theDataBase().getStudent(p_newStudent.getCip());
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
    public StudentResource onModifyExistingStudent(StudentResource p_updatedStudent) {
        // Find the actual student thats needs to be updated:
        String cip = p_updatedStudent.getCip();
        StudentResource oldStudent = DataBase.theDataBase().getStudent(cip);
        
        // Update his name:
        DataBase.theDataBase().modifyStudentName(oldStudent, p_updatedStudent.getName());;
        
        // Return the appropriate response:
        return DataBase.theDataBase().getStudent(cip);
    }
    
    
    /******************************************************************************************
     * 
     * 
     * @return
     * 
     ******************************************************************************************/
    @DELETE
    @Path("/{p_cip}")
    @Produces(MediaType.APPLICATION_JSON)
    public void onRemoveStudent(@PathParam("p_cip") String p_cip) {
        DataBase.theDataBase().removeStudent(p_cip);
    }
}
