/**********************************************************************************************
 * Tests d'intégration du handler de la ressource "student" de l'API REST.
 * 
 * @author Éric Poirier
 * @date Juin 2018
 * 
 *********************************************************************************************/
package ftf.s6projet.services.release0;
import static org.junit.jupiter.api.Assertions.*;
import ftf.s6projet.services.release0.Student;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**********************************************************************************************
 * Stratégie pour ces tests: il y a une librairie permettant de tester des méthodes implantées
 * pour Jersey, mais je ne l'utilise pas ici. Je me borne à tester les effets de ces méthodes
 * sur la base de données en RAM ainsi que les sorties, lorsque nécessaire. Toutefois, pour la
 * prochaine itération, les tests devront être plus large.
 * 
 *
 **********************************************************************************************/
class ResourceStudentTest {

    /******************************************************************************************
     * Méthode d'initialisation de tests. Comme nous testons un singleton, on doit le
     * le réinitialiser à chaque test pour s'assurer que les tests ne s'affectent pas les
     * uns les autres.
     * 
     ******************************************************************************************/
    @BeforeEach
    void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        // On retrouve le membre qui contient l'instance du singleton:
        Field singletonInstance = ServiceAuthentification.class.getDeclaredField("INSTANCE");

        // On s'assure que ce membre est accessible, même s'il est privé:
        singletonInstance.setAccessible(true);

        // On le déinitialise pour annuler les changements du dernier test:
        singletonInstance.set(null, null);
    }


    @Test
    void testOnGetAllStudents() {
        ArrayList<Student> studentList = new ArrayList<Student>();
        studentList = ServiceAuthentification.theService().getStudentsInfo();
        
        ResourceStudent resource = new ResourceStudent();
        assertTrue(studentList.equals(resource.getStudents()));
    }


    @Test
    void testOnGetOneStudent() {
    	Student student = new Student("Karan", "kalk1234", "1234");

    	ResourceStudent resource = new ResourceStudent();
        assertTrue(student.equals(resource.getStudent(student.getCip())));
    }


    @Test
    void testOnNewStudent() {
    	Student student = new Student("Bob", "bobj1234", "9876123");

        // On s'assure d'abord que le nouvel étudiant n'est pas déjà dans la base de données:
        assertFalse(ServiceAuthentification.theService().getStudentsInfo().contains(student));

        // Puis on l'ajoute:
        ResourceStudent resource = new ResourceStudent();
        resource.postStudent(student);

        // Ici je crée un nouvel objet identique au premier pour m'assurer que ce ne sont
        // pas les références qui sont comparés mais bien les membres:
        Student student2 = new Student("Bob", "bobj1234", "9876123");

        // Puis on conclue le test:
        assertTrue(ServiceAuthentification.theService().getStudentsInfo().contains(student2));
    }


    @Test
    void testOnModifyExistingStudent() {
    	Student oldStudent = new Student("Bob", "bobj1234", "9876123");
    	Student newStudent = new Student("Jerry","bobj1234", "2345678");
        assertTrue(ServiceAuthentification.theService().getStudentsInfo().contains(oldStudent));
        assertFalse(ServiceAuthentification.theService().getStudentsInfo().contains(newStudent));

        ResourceStudent resource = new ResourceStudent();
        resource.putStudent(newStudent);
        assertTrue(ServiceAuthentification.theService().getStudentsInfo().contains(newStudent));
        assertFalse(ServiceAuthentification.theService().getStudentsInfo().contains(oldStudent));
    }


    @Test
    void testOnRemoveStudent() {
    	Student studentToRemove = new Student("Jerry","bobj1234", "2345678");
        assertTrue(ServiceAuthentification.theService().getStudentsInfo().contains(studentToRemove));

        ResourceStudent resource = new ResourceStudent();
        resource.deleteStudent(studentToRemove.getCip());
        assertFalse(ServiceAuthentification.theService().getStudentsInfo().contains(studentToRemove));
    }
}