/**********************************************************************************************
 * 
 **********************************************************************************************/
package ftf.s6projet;

import java.util.ArrayList;

/**********************************************************************************************
 * 
 * @author Éric Poirier
 * @Date   Juin 2018
 *
 **********************************************************************************************/
public class DataBase {

    private ArrayList<StudentResource> m_students = new ArrayList<StudentResource>(); //

    
    /******************************************************************************************
     * Constructeur. C'est ce dernier qui s'assure que la base de données de test est
     * initialisée en RAM.
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
    
    private static DataBase INSTANCE = new DataBase();
    
    /******************************************************************************************
     * 
     * 
     * @return
     * 
     ******************************************************************************************/
    public static DataBase theDataBase() {
        return INSTANCE;
    }

    
    /******************************************************************************************
     * 
     * 
     * @param p_cip
     * @return
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
     * 
     * 
     * @return
     * 
     ******************************************************************************************/
    public ArrayList<StudentResource> getStudents() {
        return m_students;
    }


    /******************************************************************************************
     * 
     * 
     * @param p_name
     * @param p_cip
     *
     ******************************************************************************************/
    public void addStudent(StudentResource p_newStudent) {
        m_students.add(p_newStudent);
    }


    /******************************************************************************************
     * 
     * 
     * @param p_cip
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
     * 
     * 
     * @param p_name
     * @param p_cip
     * @param p_newName
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
