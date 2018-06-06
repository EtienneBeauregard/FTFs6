/**********************************************************************************************
 * Fichier pour handler d'appel à l'API REST concernant la ressource "Students".
 * 
 * @author Karan Kalsi
 * @date   Juin 2018.
 * 
 **********************************************************************************************/

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

/**********************************************************************************************
 * Classe permettant la gestion des ressources "student" lors d'appels à l'API REST du
 * microservice de test. Les requêtes Http GET, POST, PUT et DELETE sont supportées.
 * 
 **********************************************************************************************/
@Path("students")
public class ResourceStudent {

	ServiceAuthentification service = new ServiceAuthentification();

    /******************************************************************************************
     * Prépare la liste complète des étudiants enregistrés.
     * 
     * @return La liste des étudiants (JSON).
     * 
     ******************************************************************************************/
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public ArrayList<Student> getStudents() {
		return service.getStudentsInfo(); 
	}
	
	/******************************************************************************************
     * Ajoute un étudiant à la base de données.
     * 
     * @param   p_Student L'étudiant à ajouter.
     * @return  L'étudiant ajouté (JSON).
     * 
     ******************************************************************************************/
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Student postStudent(Student p_Student) {
		return service.createNewUser(p_Student);
	}
	
	/******************************************************************************************
     * Mets à jour le nom d'un étudiant dans la base de données. On utilise les CIP afin
     * de trouver le bon étudiant dans la base de données.
     * 
     * @param  p_Student L'étudiant à jour (même CIP que l'ancien, mais un 
     *                   nom différent).
     * @return L'étudiant mis à jour (JSON).
     * 
     ******************************************************************************************/
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Student putStudent(Student p_Student) {
		return service.updateUser(p_Student);
	}
	
    /******************************************************************************************
     * Prépare un étudiant enregistré.
     * 
     * @param   p_cip Le CIP de l'étudiant voulu.
     * @return  L'étudiant voulu (JSON).
     * 
     ******************************************************************************************/
	@GET
	@Path("/{studentID}")
	@Produces(MediaType.APPLICATION_JSON) 
	public Student getStudent(@PathParam("studentID") String p_cip) {
		return service.getStudentInfo(p_cip); 
	}
	
    /******************************************************************************************
     * Retire un étudiant de la base de données. Si l'étudiant n'existe pas, un message de retour
     * est retourné.
     * 
     * @param p_cip Le CIP de l'étudiant à retirer de la base de données.
     * @return  Message de succès indiquant si l'étudiant à été supprimé ou non (String).
     ******************************************************************************************/
	@DELETE
	@Path("/{studentID}")
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteStudent(@PathParam("studentID") String CIP) {
		return service.removeStudentInfo(CIP); 
	}
}

