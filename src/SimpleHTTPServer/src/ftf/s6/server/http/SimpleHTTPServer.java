/**************************************************************************************************************
 * Classe implémentant un petit serveur HTTP de base.
 * 
 * @author  Éric Poirier
 * @date    25 mai 2018
 * @version 1.0
 * 
 *************************************************************************************************************/

package ftf.s6.server.http;                    // Package custom pour ftf.

import java.io.IOException;                    // Permet de gérer des exceptions de I/O.

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;  // Permet de gérer une requête client (ce qui est reçu par le serveur).
import javax.servlet.http.HttpServletResponse; // Permet de gérer une réponse serveur (ce qui sort du serveur).


/**************************************************************************************************************
 * Servelet HTTP FTF.
 * 
 * Cette classe permet de mettre sur pied un petit serveur HTTP permettant à un utilisateur d'envoyer une
 * chaîne de caractères à un serveur et de recevoir une réponse personalisée de ce dernier.
 * 
 *************************************************************************************************************/
public class SimpleHTTPServer extends HttpServlet {

/// ====================================================================================================================
/// ----------------------------------------------------- METHODS ------------------------------------------------------
/// ====================================================================================================================
    
    /***********************************************************************************************************
     * Gère une requête 'GET' de la part du client.
     * 
     * @param req   La requête 'GET' du client.
     * @param resp  La réponse du serveur.
     * 
     * @see SimpleHTTPServer#manageClientRequest
     * 
     **********************************************************************************************************/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ManageClientRequest(req, resp, HTTPMethod.GET);
    }
    
    
    /***********************************************************************************************************
     * Gère une requête 'POST' de la part du client.
     * 
     * @param req   La requête 'POST' du client.
     * @param resp  La réponse du serveur.
     * 
     * @see SimpleHTTPServer#manageClientRequest
     * 
     **********************************************************************************************************/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ManageClientRequest(req, resp, HTTPMethod.POST);
    }


    /***********************************************************************************************************
     * Gère une requête 'POST' de la part du client.
     * 
     * Dans sa forme actuelle, la méthode récupère la chaîne de caractères envoyée par l'utilisateur (lorsque
     * ce dernier clique sur 'Submit your string...' et retourne une page avec quelques commentaires et la
     * chaîne de caractères envoyée par l'utilisateur. De plus, il indique la méthode HTTP utilisée.
     * 
     * @param req   La requête HTTP du client.
     * @param resp  La réponse du serveur.
     * 
     * @see HTTPMethod
     * 
     **********************************************************************************************************/
    private void ManageClientRequest(HttpServletRequest req, HttpServletResponse resp, HTTPMethod meth) throws ServletException, IOException {
        
        String serverResponse = "<p>From your favorite HTTP server...</p>" +
                                "<p>This is the string I got from you: ";
        
        switch (meth) {
            case GET:
                serverResponse +=
                req.getParameter("userStringGet"); // C'est ici qu'on récupère la chaîne de
                                                   // caractères envoyée par le client.
                break;
            case POST:
                        serverResponse +=
                        req.getParameter("userStringPost"); // C'est ici qu'on récupère la chaîne de
                                                            // caractères envoyée par le client.
                break;
            default:
                assert false : "HTTP method not supported.";
                break;
        }
        
        serverResponse         +=
        "<p>It comes from a "  +
        meth.toString()        + // On affiche la méthode HTTP utilisée...
        " method.</p>";
        
        resp.getWriter().println(serverResponse); // C'est ici qu'on envoie la réponse du serveur.
    }


/// ====================================================================================================================
/// ------------------------------------------------------- DATA -------------------------------------------------------
/// ====================================================================================================================

    /************************************************************************************************************
     * Méthodes HTTP.
     *
     * @see SimpleHTTPServer#manageClientRequest
     * 
     ************************************************************************************************************/
    public enum HTTPMethod {
        GET,
        HEAD,
        POST,
        PUT,
        DELETE,
        CONNECT,
        OPTIONS,
        TRACE,
        PATCH;
      }
    
    private static final long serialVersionUID = 1L; // Cet attribut n'est pas utile pour nous car nous ne
                                                     // sérialisons rien. Je l'ai ajouté parce que Eclipse
                                                     // me gossait avec un warning... Donc, ne pas s'en occuper.
}
