/**********************************************************************************************
 * Implémentation d'une petite base de données contenant des instances de ressources "student"
 * définies dans l'API REST. Cette base de données vit en RAM.
 * 
 * @author Éric Poirier
 * @Date   Juin 2018
 * 
 **********************************************************************************************/
package ftf.s6projet;

import java.util.ArrayList;

/**********************************************************************************************
 * Base de données vivant dans la RAM permettant de stocker des instances de StudentRessources
 * pour la durée de l'exécution du programme. Pour plus de crédibilité, cette classe est 
 * implantée comme un singleton (donc n'est pas thread safe).
 * 
 * À noter que l'unicité des CIP est supposée, mais pas validée.
 * 
 * @see StudentRessources
 *
 **********************************************************************************************/
public class DataBase {

    // Conteneur de ressources en RAM. Une seule instance à travers tout le programme.
    private ArrayList<StudentResource> m_students = new ArrayList<StudentResource>();


    /******************************************************************************************
     * Constructeur. C'est ce dernier qui s'assure que la base de données de test est
     * initialisée en RAM. Voir à l'intérieur du constructeur pour connaître l'état initial
     * de la base de données.
     * 
     ******************************************************************************************/
    private DataBase() {
        m_students.add(new StudentResource("Karan",    "kalk2701"));
        m_students.add(new StudentResource("Vincent",  "perv2807"));
        m_students.add(new StudentResource("Simon",    "vals2212"));
        m_students.add(new StudentResource("François", "pouf1903"));
        m_students.add(new StudentResource("Louis",    "schl3301"));
        m_students.add(new StudentResource("Étienne",  "beae3011"));
        m_students.add(new StudentResource("Éric",     "poie2104"));
    }


    private static DataBase INSTANCE = new DataBase(); // instance de la base de données.

    /******************************************************************************************
     * Accès au singleton.
     * 
     * @return L'instance du singleton.
     * 
     ******************************************************************************************/
    public static DataBase theDataBase() {
        if(INSTANCE == null) {
            INSTANCE = new DataBase(); // Parfois nécessaire lors de tests, lorsqu'on
                                       // réinitialise le singleton.
        }

        return INSTANCE;
    }


    /******************************************************************************************
     * Retrouve une ressource en particulier.
     * 
     * @param p_cip Le CIP de l'étudiant à retrouver.
     * 
     * @return Le premier étudiant dont le CIP correspond à p_cip.
     * 
     ******************************************************************************************/
    public StudentResource getStudent(String p_cip) {

        StudentResource resource = null;

        for(StudentResource student : m_students) {
            if(student.getCip().equals(p_cip)) {
                resource = student;
            }
        }

        return resource;
    }


    /******************************************************************************************
     * Donne accès à la liste de toutes les ressources dans la base de donnée.
     * 
     * @return Une liste de toutes les ressources dans la base de données.
     * 
     ******************************************************************************************/
    public ArrayList<StudentResource> getStudents() {
        return m_students;
    }


    /******************************************************************************************
     * Ajoute une ressource à la base de données. Ne gère pas l'unicité des CIP.
     * 
     * @param p_newStudent L'étudiant à ajouter à la base de données.
     *
     ******************************************************************************************/
    public void addStudent(StudentResource p_newStudent) {
        m_students.add(p_newStudent);
    }


    /******************************************************************************************
     * Enlève un étudiant de la base de données. Si l'étudiant n'est pas dans la base de
     * données à priori, cette action n'a aucun effet.
     * 
     * @param p_cip Le CIP de l'étudiant qu'on veut enlever de la base de données.
     * 
     ******************************************************************************************/
    public void removeStudent(String p_cip) {
        int index = 0;
        StudentResource resource = getStudent(p_cip);

        for(StudentResource student : m_students) {
            if(student.equals(resource)) {
                m_students.remove(index);
                break;
            }

            index++;
        }
    }


    /******************************************************************************************
     * Modifie le nom d'un étudiant. Si l'étudiant en question n'exite pas, cette action n'a
     * aucun effet sur la base de données.
     * 
     * @param p_student  L'étudiant dont on veut modifier le nom.
     * @param p_newName  Le nouveau nom à donner à l'étudiant.
     * 
     ******************************************************************************************/
    public void modifyStudentName(StudentResource p_student, String p_newName) {
        for(StudentResource student : m_students) {
            if(student.equals(p_student)) {
                student.setName(p_newName);
                break;
            }
        }
    }
}
