/**********************************************************************************************
 * Tests unitaires de la ressource "student" de l'API REST.
 * 
 * @author Karan Kalsi
 * @date Juin 2018
 * 
 *********************************************************************************************/
package ftf.s6projet.services.release0;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class StudentTest {

    @Test
    void testGetName() {
        Student student = new Student("Bob", "bobj1234", "9876123");
        assertTrue(student.getName().equals("Bob"));
    }


    @Test
    void testSetName() {
    	Student student = new Student("George", "Geog1234", "6734788");
        student.setName("Bob");
        assertTrue(student.getName().equals("Bob"));
    }


    @Test
    void testGetCip() {
    	Student student = new Student("Bob" ,"bobj1234", "9876123");
        assertTrue(student.getCip().equals("bobj1234"));
    }


    @Test
    void testSetCip() {
    	Student student = new Student("George", "bobj5678", "6734788");
        student.setCip("Geog1234");
        assertTrue(student.getCip().equals("Geog1234"));
    }
    
    
    @Test
    void testGetPassword() {
    	Student student = new Student("Bob", "bobj1234", "9876123");
        assertTrue(student.getPassword().equals("9876123"));
    }


    @Test
    void testSetPassword() {
    	Student student = new Student("George", "bobj5678", "9876123");
        student.setPassword("6734788");
        assertTrue(student.getPassword().equals("6734788"));
    }
}
