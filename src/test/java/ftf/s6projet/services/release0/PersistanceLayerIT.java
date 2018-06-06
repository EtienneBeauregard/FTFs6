/**************************************************************************************************************
 * Classe implémentant la couche de persistance du micro service du release 0.
 * 
 * @author  Francois Poulin
 * @date    6 juin 2018
 * @version 1.0
 * 
 *************************************************************************************************************/

// Packages:
package ftf.s6projet.services.release0;

// Imports generaux
import ftf.s6projet.services.release0.PersistanceLayer;
import ftf.s6projet.services.release0.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersistanceLayerIT {
    @BeforeEach
    void setUpTests() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        Student testStudent = new Student("test", "test1234", "1234");
        if (persistanceLayer.getUser("test1234") != null) {
            persistanceLayer.delUser("test1234");
        }
    }

    @AfterAll
    static void cleanUpTest() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        Student testStudent = new Student("test", "test1234", "1234");
        if (persistanceLayer.getUser("test1234") != null) {
            persistanceLayer.delUser("test1234");
        }
    }

    @Test
    void getUserFromJsonFile() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        Student testStudent = new Student("test", "test1234", "1234");
        persistanceLayer.postUser(testStudent);
        assertEquals(persistanceLayer.getUser("test1234").getCip(), testStudent.getCip());
        assertEquals(persistanceLayer.getUser("test1234").getName(), testStudent.getName());
        assertEquals(persistanceLayer.getUser("test1234").getPassword(), testStudent.getPassword());
    }
    
    @Test
    void getAllUsersFromJsonFile() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        ArrayList<Student> studentsList = persistanceLayer.getAllUsers();
        assertTrue(3 < studentsList.size());
    }

    @Test
    void postUserInJsonFile() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        Student testStudent = new Student("test", "test1234", "1234");

        persistanceLayer.postUser(testStudent);
        Student user = persistanceLayer.getUser("test1234");
        assertEquals(user.getCip(), testStudent.getCip());
        assertEquals(user.getName(), testStudent.getName());
        assertEquals(user.getPassword(), testStudent.getPassword());
    }
    
    @Test
    void putUserFromJsonFile() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        Student testStudent = new Student("test", "test1234", "1234");

        persistanceLayer.postUser(testStudent);

        testStudent.setPassword("4321");

        persistanceLayer.putUser(testStudent);
        Student user = persistanceLayer.getUser("test1234");
        assertEquals(user.getCip(), testStudent.getCip());
        assertEquals(user.getName(), testStudent.getName());
        assertEquals(user.getPassword(), testStudent.getPassword());
    }

    @Test
    void delUserFromJsonFile() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        Student testStudent = new Student("test", "test1234", "1234");

        persistanceLayer.postUser(testStudent);
        persistanceLayer.delUser("test1234");
        assertNull(persistanceLayer.getUser("test1234")); 
    }
}