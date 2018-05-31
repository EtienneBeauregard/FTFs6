/**********************************************************************************************
 * Fichier contenant une classe abstraite de ressource REST assurant un interface commun
 * pour tous les microservices du projet.
 * 
 * @author  Éric Poirier
 * @version Mai 2018
 * 
 *********************************************************************************************/
package ftf.s6projet;

import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**********************************************************************************************
 * Classe-interface commune pour tout service REST à implémenter. Il s'agit, dans un système à
 * microservices, de l'interface de la couche de présentation, c'est-à-dire la couche
 * permettant de communique avec le monde extérieur. C'est à travers cet interface que passeront
 * les requêtes Http provenant du serveur d'application.
 * 
 * Implémenter cette classe-interface, c'est s'assurer que:
 * <ul>
 *   <li>les méthodes GET, POST, PUT et DELETE seront implémentées;</li>
 *   <li>le microservice communiquera avec le serveur d'application en JSON.</li>
 * </ul>
 * 
 * Pour fonctionner, la ressource traitée ici doit implémenter la classe interface
 * AbstractResource. Au besoin, voir les tests pour comprendre comment utiliser cette
 * classe.
 * 
 * @see AbstractResource
 * 
 *********************************************************************************************/
public abstract class AbstractResourceHandler {

    /******************************************************************************************
     * Gère une requête GET du serveur d'application.
     * 
     * @return Une ressource (traduite en JSON).
     * 
     *****************************************************************************************/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AbstractResource onGet() {
        assert false :   "this method has not been implemented in your "
                       + "concrete ressource class...";
        return null;
    }


    /******************************************************************************************
     * Gère une requête POST du serveur d'application.
     * 
     * @return Une ressource (traduite en JSON).
     * 
     *****************************************************************************************/
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public AbstractResource onPost() {
        assert false :   "this method has not been implemented in your "
                       + "concrete ressource class...";
        return null;
    }

    /******************************************************************************************
     * Gère une requête PUT du serveur d'application.
     * 
     * @return Une ressource (traduite en JSON).
     * 
     *****************************************************************************************/
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public AbstractResource onPut() {
        assert false :   "this method has not been implemented in your "
                       + "concrete ressource class...";
        return null;
    }


    /******************************************************************************************
     * Gère une requête DELETE du serveur d'application.
     * 
     * @return Une ressource (traduite en JSON).
     * 
     *****************************************************************************************/
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public AbstractResource onDelete() {
        assert false :   "this method has not been implemented in your "
                       + "concrete ressource class...";
        return null;
    }
}
