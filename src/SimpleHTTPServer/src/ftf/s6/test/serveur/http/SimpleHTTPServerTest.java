/**************************************************************************************************************
 * Tests unitaires pour la classe SimpleHTTPServer. La version de Mockito utilis�e ici peut �tre t�l�charg�e 
 * via ce lien:
 * 
 *            http://www.java2s.com/Code/Jar/m/Downloadmockitoall19120110911sourcesjar.htm
 * 
 * en attandant que notre processus de tests soit compl�tement int�gr� � Maven.
 * 
 * @author  �ric Poirier
 * @date    26 mai 2018
 * @version 1.0
 * 
 *************************************************************************************************************/

// Packages:
package ftf.s6.test.serveur.http;

// Imports statiques:
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Imports g�n�raux:
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import ftf.s6.server.http.SimpleHTTPServer;


/***********************************************************************************************************************
 * Classe de test permettant de tester les fonstionalit�s de la classe 'SimpleHTTPServer'.
 *
 * @see ftf.s6.server.http.SimpleHTTPServer
 *
 **********************************************************************************************************************/
class SimpleHTTPServerTest {

/// ====================================================================================================================
/// ----------------------------------------------------- METHODS ------------------------------------------------------
/// ====================================================================================================================

    /*******************************************************************************************************************
     * Cette m�thode est AUTOMATIQUEMENT appel�e avant chaque test pour faire la mise sur pied du contexte de test.
     * Ne pas appeler directement.
     *
     ******************************************************************************************************************/
    @Before
    public void setUp() {
        // Mock du servlet:
        m_servlet  = new SimpleHTTPServer();

        // Mock d'une requ�te et d'une r�ponse:
        m_request  = mock(HttpServletRequest.class);
        m_response = mock(HttpServletResponse.class);
    }


/// ====================================================================================================================
/// ----------------------------------------------------- TESTS --------------------------------------------------------
/// ====================================================================================================================


    /*******************************************************************************************************************
     * Valide que si une requ�te valide (ex. vers une ressource qui existe) au servlet, une r�ponse valide est
     * retourn�e.
     *
     ******************************************************************************************************************/
    @Test
    void ServiceValidRequestImpliesValidResponse() throws IOException, ServletException {
        // Ici, on stub une requ�te et une r�ponse. Quand on fait la requ�te pour la
        // ressource "ressource", on devrait recevoir la r�ponse "htmlPage".
        when(m_request.getParameter("ressource")).thenReturn("htmlPage");
        when(m_request.getProtocol()).thenReturn("HTTP/1.1");

        // On se pr�pare un stream de caract�res pour r�cup�rer la r�ponse du servlet:
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        when(m_response.getWriter()).thenReturn(pWriter);

        try {
            m_servlet.service(m_request, m_response);
        } catch (ServletException exception) {
            fail("Unexpected exception thrown during the test.");
        }

        // On v�rifie qu'on a bien re�u la r�ponse souhait�e:
        assertTrue(sWriter.toString().contains("htmlPage"));
    }

    /*
    /*******************************************************************************************************************
     * Valide que si une requ�te invalide est envoy�es au servlet (ex. vers une ressource inexistante), une r�ponse
     * invalide est renvoy�e.
     *
     ******************************************************************************************************************/
    @Test
    void ServiceInvalidRequestImpliesInvalidResponse() {
        fail("Not yet implemented");
    }

    
    /*******************************************************************************************************************
     * Valide que si une m�thode autre que les m�thodes impl�ment�es ('GET' et 'POST' pour l'instant) sont invoqu�es,
     * une exception est lanc�e.
     *
     ******************************************************************************************************************/
    @Test
    void UnavailableHTTPMethodThrowsException() {
        fail("Not yet implemented");
    }

/// ====================================================================================================================
/// ------------------------------------------------------- DATA -------------------------------------------------------
/// ====================================================================================================================

    private SimpleHTTPServer    m_servlet;     // Le servlet de test.
    private HttpServletRequest  m_request;     // Un mock d'une requ�te.
    private HttpServletResponse m_response;    // Un mock d'une r�ponse.
    
}
