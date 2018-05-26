/**************************************************************************************************************
 * Classe impl�mentant un petit serveur HTTP de base.
 * 
 * @author  �ric Poirier
 * @date    25 mai 2018
 * @version 1.0
 * 
 *************************************************************************************************************/

package ftf.s6.server.http;                    // Package custom pour ftf.

import java.io.IOException;                    // Permet de g�rer des exceptions de I/O.

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;  // Permet de g�rer une requ�te client (ce qui est re�u par le serveur).
import javax.servlet.http.HttpServletResponse; // Permet de g�rer une r�ponse serveur (ce qui sort du serveur).


/**************************************************************************************************************
 * Servelet HTTP FTF.
 * 
 * Cette classe permet de mettre sur pied un petit serveur HTTP permettant � un utilisateur d'envoyer une
 * cha�ne de caract�res � un serveur et de recevoir une r�ponse personalis�e de ce dernier.
 * 
 *************************************************************************************************************/
public class SimpleHTTPServer extends HttpServlet {

/// ====================================================================================================================
/// ----------------------------------------------------- METHODS ------------------------------------------------------
/// ====================================================================================================================
    
    /***********************************************************************************************************
     * G�re une requ�te 'GET' de la part du client.
     * 
     * @param req   La requ�te 'GET' du client.
     * @param resp  La r�ponse du serveur.
     * 
     * @see SimpleHTTPServer#manageClientRequest
     * 
     **********************************************************************************************************/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ManageClientRequest(req, resp, HTTPMethod.GET);
    }
    
    
    /***********************************************************************************************************
     * G�re une requ�te 'POST' de la part du client.
     * 
     * @param req   La requ�te 'POST' du client.
     * @param resp  La r�ponse du serveur.
     * 
     * @see SimpleHTTPServer#manageClientRequest
     * 
     **********************************************************************************************************/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ManageClientRequest(req, resp, HTTPMethod.POST);
    }


    /***********************************************************************************************************
     * G�re une requ�te 'POST' de la part du client.
     * 
     * Dans sa forme actuelle, la m�thode r�cup�re la cha�ne de caract�res envoy�e par l'utilisateur (lorsque
     * ce dernier clique sur 'Submit your string...' et retourne une page avec quelques commentaires et la
     * cha�ne de caract�res envoy�e par l'utilisateur. De plus, il indique la m�thode HTTP utilis�e.
     * 
     * @param req   La requ�te HTTP du client.
     * @param resp  La r�ponse du serveur.
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
                req.getParameter("userStringGet"); // C'est ici qu'on r�cup�re la cha�ne de
                                                   // caract�res envoy�e par le client.
                break;
            case POST:
                        serverResponse +=
                        req.getParameter("userStringPost"); // C'est ici qu'on r�cup�re la cha�ne de
                                                            // caract�res envoy�e par le client.
                break;
            default:
                assert false : "HTTP method not supported.";
                break;
        }
        
        serverResponse         +=
        "<p>It comes from a "  +
        meth.toString()        + // On affiche la m�thode HTTP utilis�e...
        " method.</p>";
        
        resp.getWriter().println(serverResponse); // C'est ici qu'on envoie la r�ponse du serveur.
    }


/// ====================================================================================================================
/// ------------------------------------------------------- DATA -------------------------------------------------------
/// ====================================================================================================================

    /************************************************************************************************************
     * M�thodes HTTP.
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
                                                     // s�rialisons rien. Je l'ai ajout� parce que Eclipse
                                                     // me gossait avec un warning... Donc, ne pas s'en occuper.
}
