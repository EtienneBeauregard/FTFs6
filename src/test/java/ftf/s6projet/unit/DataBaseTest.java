/**********************************************************************************************
 * Implémentation de la ressource "student" de l'API REST.
 * 
 * @author Éric Poirier
 * @date Juin 2018
 * 
 *********************************************************************************************/
package ftf.s6projet.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ftf.s6projet.DataBase;
import ftf.s6projet.StudentResource;

class DataBaseTest {

    /******************************************************************************************
     * Méthode d'initialisation de tests. Comme nous testons un singleton, on doit le
     * le réinitialiser à chaque test pour s'assurer que les tests ne s'affectent pas les
     * uns les autres.
     * 
     ******************************************************************************************/
    @BeforeEach
    void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        // On retrouve le membre qui contient l'instance du singleton:
        Field singletonInstance = DataBase.class.getDeclaredField("INSTANCE");

        // On s'assure que ce membre est accessible, même s'il est privé:
        singletonInstance.setAccessible(true);

        // On le déinitialise pour annuler les changements du dernier test:
        singletonInstance.set(null, null);
    }


    @Test
    void testGetStudent() {
        String cip = "poie2104";
        StudentResource student = new StudentResource("Éric", "poie2104");
        StudentResource foundStudent = DataBase.theDataBase().getStudent(cip);
        assertTrue(student.equals(foundStudent));
    }


    @Test
    void testGetStudents() {
        ArrayList<StudentResource> students = new ArrayList<StudentResource>();
        students.add(new StudentResource("Karan",    "kalk2701"));
        students.add(new StudentResource("Vincent",  "perv2807"));
        students.add(new StudentResource("Simon",    "vals2212"));
        students.add(new StudentResource("François", "pouf1903"));
        students.add(new StudentResource("Louis",    "schl3301"));
        students.add(new StudentResource("Étienne",  "beae3011"));
        students.add(new StudentResource("Éric",     "poie2104"));
        
        assertTrue(students.equals(DataBase.theDataBase().getStudents()));
    }


    @Test
    void testAddStudent() {
        // Je cré deux instances séparées pour m'assurer que ce sont bien les membres
        // des objets qui sont comparés, et non les références.
        StudentResource student1 = new StudentResource("Bob", "grat7777");
        StudentResource student2 = new StudentResource("Bob", "grat7777");
        DataBase.theDataBase().addStudent(student1);
        assertTrue(DataBase.theDataBase().getStudents().contains(student2));
    }


    @Test
    void testRemoveStudent() {
        StudentResource student = new StudentResource("Éric", "poie2104");
        DataBase.theDataBase().removeStudent(student.getCip());
        assertFalse(DataBase.theDataBase().getStudents().contains(student));
    }


    @Test
    void testModifyStudentName() {
        StudentResource studentToModify = new StudentResource("Éric", "poie2104");
        DataBase.theDataBase().modifyStudentName(studentToModify, "Bob");
        
        StudentResource modifiedStudent = new StudentResource("Bob", studentToModify.getCip());

        assertFalse(DataBase.theDataBase().getStudents().contains(studentToModify));
        assertTrue(DataBase.theDataBase().getStudents().contains(modifiedStudent));
    }

}
