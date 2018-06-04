/**********************************************************************************************
 * Fichier pour handler d'appel à l'API REST concernant la ressource "students".
 * 
 * @author Éric Poirier
 * @date   Juin 2018.
 * 
 **********************************************************************************************/

package ftf.s6projet;

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

/**********************************************************************************************
 * Classe permettant la gestion des ressources "student" lors d'appels à l'API REST du
 * microservice de test. Les requêtes Http GET, POST, PUT et DELETE sont supportées.
 * 
 **********************************************************************************************/
@Path("students")
public class StudentResourceHandler {

    /******************************************************************************************
     * Prépare la liste complète des étudiants enregistrés dans la base de donnée.
     * 
     * @return La liste des étudiants (JSON).
     * 
     ******************************************************************************************/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<StudentResource> onGetAllStudents() {
        return DataBase.theDataBase().getStudents();
    }


    /******************************************************************************************
     * Prépare un étudiant enregistré dans la base de donnée. L'absence de l'étudiant demandé
     * n'est pas gérée pour le moment.
     * 
     * @param   p_cip Le CIP de l'étudiant voulu.
     * @return  L'étudiant voulu (JSON).
     * 
     ******************************************************************************************/
    @GET
    @Path("/{p_cip}")
    @Produces(MediaType.APPLICATION_JSON)
    public StudentResource onGetOneStudent(@PathParam("p_cip") String p_cip) {
        return DataBase.theDataBase().getStudent(p_cip);
    }


    /******************************************************************************************
     * Ajoute un étudiant à la base de données. Les étudiants dupliqués ne sont pas gérés pour
     * le moment.
     * 
     * @param   p_newStudent L'étudiant à ajouter.
     * @return  L'étudiant ajouté (JSON).
     * 
     ******************************************************************************************/
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StudentResource onNewStudent(StudentResource p_newStudent) {
        DataBase.theDataBase().addStudent(p_newStudent);
        return DataBase.theDataBase().getStudent(p_newStudent.getCip());
    }


    /******************************************************************************************
     * Mets à jour le nom d'un étudiant dans la base de données. On utilise les CIP afin
     * de trouver le bon étudiant dans la base de données. Le cas où l'étudiant n'exite pas
     * dans la base de données n'est pas traité pour le moment.
     * 
     * @param  p_updatedStudent L'étudiant à jour (même CIP que l'ancien, mais un 
     *                          nom différent).
     * @return L'étudiant mis à jour (JSON).
     * 
     ******************************************************************************************/
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StudentResource onModifyExistingStudent(StudentResource p_updatedStudent) {
        // Trouver l'étudiant qu'on veut mettre à jour:
        String cip = p_updatedStudent.getCip();
        StudentResource oldStudent = DataBase.theDataBase().getStudent(cip);

        // Mettre à jour son nom:
        DataBase.theDataBase().modifyStudentName(oldStudent, p_updatedStudent.getName());;

        return DataBase.theDataBase().getStudent(cip);
    }


    /******************************************************************************************
     * Retire un étudiant de la base de données. Si l'étudiant n'existe pas, rien ne se passe.
     * 
     * @param p_cip Le CIP de l'étudiant à retirer de la base de données.
     * 
     ******************************************************************************************/
    @DELETE
    @Path("/{p_cip}")
    @Produces(MediaType.APPLICATION_JSON)
    public void onRemoveStudent(@PathParam("p_cip") String p_cip) {
        DataBase.theDataBase().removeStudent(p_cip);
    }
}
