package ftf.s6projet.services.release0;


import ftf.s6projet.services.release0.PersistanceLayer;
import ftf.s6projet.services.release0.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersistanceLayerIT {
    @BeforeEach
    void setUpTests() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        Student testStudent = new Student("test", "test1234", "1234");
        if (persistanceLayer.getUser(testStudent) != null) {
            persistanceLayer.delUser(testStudent);
        }
    }

    @AfterAll
    static void cleanUpTest() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        Student testStudent = new Student("test", "test1234", "1234");
        if (persistanceLayer.getUser(testStudent) != null) {
            persistanceLayer.delUser(testStudent);
        }
    }

    @Test
    void getUserFromJsonFile() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        Student testStudent = new Student("test", "test1234", "1234");
        persistanceLayer.postUser(testStudent);
        assertEquals(persistanceLayer.getUser(testStudent).m_cip, testStudent.m_cip);
        assertEquals(persistanceLayer.getUser(testStudent).m_name, testStudent.m_name);
        assertEquals(persistanceLayer.getUser(testStudent).m_password, testStudent.m_password);
    }
    
    @Test
    void getAllUsersFromJsonFile() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        ArrayList<Student> studentsList = persistanceLayer.getAllUsers();
        assertEquals(8, studentsList.size());
    }

    @Test
    void postUserInJsonFile() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        Student testStudent = new Student("test", "test1234", "1234");

        persistanceLayer.postUser(testStudent);
        Student user = persistanceLayer.getUser(testStudent);
        assertEquals(user.m_cip, "test1234");
        assertEquals(user.m_name, "test");
        assertEquals(user.m_password, "1234");
    }
    
    @Test
    void putUserFromJsonFile() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        Student testStudent = new Student("test", "test1234", "1234");

        persistanceLayer.postUser(testStudent);

        testStudent.m_password = "4321";

        persistanceLayer.putUser(testStudent);
        Student user = persistanceLayer.getUser(testStudent);
        assertEquals(user.m_cip, "test1234");
        assertEquals(user.m_name, "test");
        assertEquals(user.m_password, "4321");
    }

    @Test
    void delUserFromJsonFile() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        Student testStudent = new Student("test", "test1234", "1234");

        persistanceLayer.postUser(testStudent);
        persistanceLayer.delUser(testStudent);
        assertNull(persistanceLayer.getUser(testStudent)); 
    }
}