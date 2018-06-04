/**********************************************************************************************
 * Tests d'intégration du handler de la ressource "student" de l'API REST.
 * 
 * @author Éric Poirier
 * @date Juin 2018
 * 
 *********************************************************************************************/
package ftf.s6projet.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ftf.s6projet.DataBase;
import ftf.s6projet.StudentResource;
import ftf.s6projet.StudentResourceHandler;

/**********************************************************************************************
 * Stratégie pour ces tests: il y a une librairie permettant de tester des méthodes implantées
 * pour Jersey, mais je ne l'utilise pas ici. Je me borne à tester les effets de ces méthodes
 * sur la base de données en RAM ainsi que les sorties, lorsque nécessaire. Toutefois, pour la
 * prochaine itération, les tests devront être plus large.
 * 
 *
 **********************************************************************************************/
class StudentResourceHandlerTest {

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
    void testOnGetAllStudents() {
        ArrayList<StudentResource> studentList = new ArrayList<StudentResource>();
        studentList = DataBase.theDataBase().getStudents();
        
        StudentResourceHandler handler = new StudentResourceHandler();
        assertTrue(studentList.equals(handler.onGetAllStudents()));
    }


    @Test
    void testOnGetOneStudent() {
        StudentResource student = new StudentResource("Éric", "poie2104");

        StudentResourceHandler handler = new StudentResourceHandler();
        assertTrue(student.equals(handler.onGetOneStudent(student.getCip())));
    }


    @Test
    void testOnNewStudent() {
        StudentResource newStudent = new StudentResource("Bob", "grat9999");

        // On s'assure d'abord que le nouvel étudiant n'est pas déjà dans la base de données:
        assertFalse(DataBase.theDataBase().getStudents().contains(newStudent));

        // Puis on l'ajoute:
        StudentResourceHandler handler = new StudentResourceHandler();
        handler.onNewStudent(newStudent);

        // Ici je crée un nouvel objet identique au premier pour m'assurer que ce ne sont
        // pas les références qui sont comparés mais bien les membres:
        StudentResource newStudent2 = new StudentResource("Bob", "grat9999");

        // Puis on conclue le test:
        assertTrue(DataBase.theDataBase().getStudents().contains(newStudent2));
    }


    @Test
    void testOnModifyExistingStudent() {
        StudentResource oldStudent = new StudentResource("Éric", "poie2104");
        StudentResource newStudent = new StudentResource("Bob", "poie2104");
        assertTrue(DataBase.theDataBase().getStudents().contains(oldStudent));
        assertFalse(DataBase.theDataBase().getStudents().contains(newStudent));

        StudentResourceHandler handler = new StudentResourceHandler();
        handler.onModifyExistingStudent(newStudent);
        assertTrue(DataBase.theDataBase().getStudents().contains(newStudent));
    }


    @Test
    void testOnRemoveStudent() {
        StudentResource studentToRemove = new StudentResource("Éric", "poie2104");
        assertTrue(DataBase.theDataBase().getStudents().contains(studentToRemove));

        StudentResourceHandler handler = new StudentResourceHandler();
        handler.onRemoveStudent(studentToRemove.getCip());
        assertFalse(DataBase.theDataBase().getStudents().contains(studentToRemove));
    }
}
