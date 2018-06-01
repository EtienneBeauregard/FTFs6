/**************************************************************************************************************
 * Tests unitaires pour la classe SimpleHTTPServer. Pour l'instant, seuls les méthodes GET et POST sont
 * testées puisque seules ces deux méthodes ont été implémentées. D'autres tests devraient être ajouté
 * si le nombre de méthode HTTP supportées augmente.
 * 
 * @author  Éric Poirier
 * @date    26 mai 2018
 * @version 1.0
 * 
 *************************************************************************************************************/

// Packages:
package ftf.s6projet.server.http;

// Imports statiques:
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Imports généraux:
import ftf.s6projet.server.http.SimpleHTTPServer;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


/***********************************************************************************************************************
 * Classe de test permettant de tester les fonstionalités de la classe 'SimpleHTTPServer'.
 *
 * @see ftf.s6.server.http.SimpleHTTPServer
 *
 **********************************************************************************************************************/
class SimpleHTTPServerIT {
    
    /// =================================================================================================================
    /// -------------------------------------------------- DATA ---------------------------------------------------------
    /// =================================================================================================================
    SimpleHTTPServer          m_servlet;     // Le servlet de test.
    @Mock HttpServletRequest  m_request;     // Un mock d'une requête.
    @Mock HttpServletResponse m_response;    // Un mock d'une réponse.

/// ====================================================================================================================
/// ----------------------------------------------------- METHODS ------------------------------------------------------
/// ====================================================================================================================

    /*******************************************************************************************************************
     * Cette méthode est AUTOMATIQUEMENT appelée avant chaque test pour faire la mise sur pied du contexte de test.
     * Ne pas appeler directement.
     *
     ******************************************************************************************************************/
    @BeforeEach
    public void setUpClasseTestServlet() throws Exception {
        // On initialise tous les mocks (important!):
        MockitoAnnotations.initMocks(this);

        // On initialise également notre servlet:
        m_servlet = new SimpleHTTPServer();
    }


/// ====================================================================================================================
/// ------------------------------------------------------- TESTS ------------------------------------------------------
/// ====================================================================================================================


    /*******************************************************************************************************************
     * Valide le bon fonctionnement d'un appel à GET.
     *
     ******************************************************************************************************************/
    @Test
    void GETValidRequestImpliesValidResponse() throws IOException, ServletException {
        // Ici on prépare les réponses de notre mock de requête. On doit faire ceci étant donné que ce n'est
        // pas une véritable requête:
        when(m_request.getParameter("userStringGet")).thenReturn("Some string");
        when(m_request.getMethod()).thenReturn("GET");

        // On se prépare un stream de caractères pour récupérer la réponse du servlet:
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        when(m_response.getWriter()).thenReturn(pWriter);

        // On test notre fonctionalité via la méthode 'service' puisqu'elle est publique, et que c'est elle
        // qui est appelé en réalité:
        try {
            m_servlet.service(m_request, m_response);
        } catch (ServletException exception) {
            // Si ça passe ici: très louche...
            fail("Unexpected exception thrown during the test.");
        }

        // On vérifie qu'on a bien reçu la réponse souhaitée:
        assertTrue(sWriter.toString().contains("<p>From your favorite HTTP server...</p>" + 
                                               "<p>This is the string I got from you: "   +
                                               "Some string"                              +
                                               "<p>It comes from a GET method.</p>"));
    }


    /*******************************************************************************************************************
     * Valide le bon fonctionnement d'un appel à POST.
     *
     ******************************************************************************************************************/
    @Test
    void POSTValidRequestImpliesValidResponse() throws IOException, ServletException {
        // Ici on prépare les réponses de notre mock de requête. On doit faire ceci étant donné que ce n'est
        // pas une véritable requête:
        when(m_request.getParameter("userStringPost")).thenReturn("Some string");
        when(m_request.getMethod()).thenReturn("POST");

        // On se prépare un stream de caractères pour récupérer la réponse du servlet:
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        when(m_response.getWriter()).thenReturn(pWriter);

        // On test notre fonctionalité via la méthode 'service' puisqu'elle est publique, et que c'est elle
        // qui est appelé en réalité:
        try {
            m_servlet.service(m_request, m_response);
        } catch (ServletException exception) {
            // Si ça passe ici: très louche...
            fail("Unexpected exception thrown during the test.");
        }

        // On vérifie qu'on a bien reçu la réponse souhaitée:
        assertTrue(sWriter.toString().contains("<p>From your favorite HTTP server...</p>" + 
                                               "<p>This is the string I got from you: "   +
                                               "Some string"                              +
                                               "<p>It comes from a POST method.</p>"));
    }
}
