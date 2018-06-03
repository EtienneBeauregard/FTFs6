/**********************************************************************************************
 * Tests unitaires de la ressource "student" de l'API REST.
 * 
 * @author Éric Poirier
 * @date Juin 2018
 * 
 *********************************************************************************************/
package ftf.s6projet.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ftf.s6projet.StudentResource;

class StudentResourceTest {

    @Test
    void testGetName() {
        StudentResource student = new StudentResource("Bob", "bobj1234");
        assertTrue(student.getName().equals("Bob"));
    }


    @Test
    void testSetName() {
        StudentResource student = new StudentResource("Gratton", "bobj1234");
        student.setName("Bob");
        assertTrue(student.getName().equals("Bob"));
    }


    @Test
    void testGetCip() {
        StudentResource student = new StudentResource("Bob", "bobj1234");
        assertTrue(student.getCip().equals("bobj1234"));
    }


    @Test
    void testSetCip() {
        StudentResource student = new StudentResource("Gratton", "bobj1234");
        student.setCip("bobj5678");
        assertTrue(student.getCip().equals("bobj5678"));
    }
}
