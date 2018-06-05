package ftf.s6projet.services.release0;


import ftf.s6projet.services.release0.PersistanceLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersistanceLayerIT {
    @BeforeEach
    void setUpTests() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        if (persistanceLayer.getUser("test1234") != null) {
            persistanceLayer.delUser("test1234");
        }
    }

    @Test
    void getUserFromJsonFile() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        assertEquals(persistanceLayer.getUser("pouf1234")[2], "1234");
    }

    @Test
    void postUserFromJsonFile() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        persistanceLayer.postUser("test1234", "t_fname", "t_lname", "t_password");
        String[] user = persistanceLayer.getUser("test1234");
        assertEquals(user[0], "t_fname");
        assertEquals(user[1], "t_lname");
        assertEquals(user[2], "t_password");
    }

    @Test
    void putUserFromJsonFile() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        persistanceLayer.postUser("test1234", "t_fname", "t_lname", "t_password");
        persistanceLayer.putUser("test1234", "put_fname", "put_lname", "put_password");
        String[] user = persistanceLayer.getUser("test1234");
        assertEquals(user[0], "put_fname");
        assertEquals(user[1], "put_lname");
        assertEquals(user[2], "put_password");
    }

    @Test
    void delUserFromJsonFile() {
        PersistanceLayer persistanceLayer = new PersistanceLayer();
        persistanceLayer.postUser("test1234", "p_fname", "p_lname", "p_password");
        persistanceLayer.delUser("test1234");
        assertNull(persistanceLayer.getUser("test1234")); 

    }
}