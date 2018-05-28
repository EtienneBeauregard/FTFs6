/**************************************************************************************************************
 * Tests unitaires pour la classe SimpleHTTPServer. La version de Mockito utilisée ici peut être téléchargée 
 * via ce lien:
 * 
 *            http://www.java2s.com/Code/Jar/m/Downloadmockitoall19120110911sourcesjar.htm
 * 
 * en attandant que notre processus de tests soit complètement intégré à Maven.
 * 
 * @author  Éric Poirier
 * @date    26 mai 2018
 * @version 1.0
 * 
 *************************************************************************************************************/

// Packages:
package ftf.s6.server.http;

// Imports statiques:
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Imports généraux:
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ftf.s6.server.http.SimpleHTTPServer;


/***********************************************************************************************************************
 * Classe de test permettant de tester les fonstionalités de la classe 'SimpleHTTPServer'.
 *
 * @see ftf.s6.server.http.SimpleHTTPServer
 *
 **********************************************************************************************************************/
class SimpleHTTPServerTest {
	
	/// ====================================================================================================================
	/// ------------------------------------------------------- DATA -------------------------------------------------------
	/// ====================================================================================================================
	@Mock
	SimpleHTTPServer    m_servlet;     // Le servlet de test.
	@Mock
	HttpServletRequest  m_request;     // Un mock d'une requête.
	@Mock
	HttpServletResponse m_response;    // Un mock d'une réponse.

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
    	MockitoAnnotations.initMocks(this);
    	/*
        // Mock du servlet:
        m_servlet  = new SimpleHTTPServer();

        // Mock d'une requête et d'une réponse:
        m_request  = mock(HttpServletRequest.class);
        m_response = mock(HttpServletResponse.class);*/
    }


/// ====================================================================================================================
/// ----------------------------------------------------- TESTS --------------------------------------------------------
/// ====================================================================================================================


    /*******************************************************************************************************************
     * Valide que si une requête valide (ex. vers une ressource qui existe) au servlet, une réponse valide est
     * retournée.
     *
     ******************************************************************************************************************/
    @Test
    void ServiceValidRequestImpliesValidResponse() throws IOException, ServletException {
        // Ici, on stub une requête et une réponse. Quand on fait la requête pour la
        // ressource "ressource", on devrait recevoir la réponse "htmlPage".
        when(m_request.getParameter("ressource")).thenReturn("htmlPage");
        when(m_request.getProtocol()).thenReturn("HTTP/1.1");

        // On se prépare un stream de caractères pour récupérer la réponse du servlet:
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        when(m_response.getWriter()).thenReturn(pWriter);

        try {
            m_servlet.service(m_request, m_response);
        } catch (ServletException exception) {
            fail("Unexpected exception thrown during the test.");
        }

        // On vérifie qu'on a bien reçu la réponse souhaitée:
        assertTrue(sWriter.toString().contains("htmlPage"));
    }

    /*
    /*******************************************************************************************************************
     * Valide que si une requête invalide est envoyées au servlet (ex. vers une ressource inexistante), une réponse
     * invalide est renvoyée.
     *
     ******************************************************************************************************************/
    @Test
    void ServiceInvalidRequestImpliesInvalidResponse() {
        fail("Not yet implemented");
    }

    
    /*******************************************************************************************************************
     * Valide que si une méthode autre que les méthodes implémentées ('GET' et 'POST' pour l'instant) sont invoquées,
     * une exception est lancée.
     *
     ******************************************************************************************************************/
    @Test
    void UnavailableHTTPMethodThrowsException() {
        fail("Not yet implemented");
    }


    
}
