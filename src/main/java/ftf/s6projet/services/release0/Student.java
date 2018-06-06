/**********************************************************************************************
 * Implémentation de la ressource "Student" de l'API REST.
 * 
 * @author Karan Kalsi
 * @date Juin 2018
 * 
 *********************************************************************************************/

package ftf.s6projet.services.release0;
import java.net.URI;

import javax.xml.bind.annotation.XmlRootElement;

/**********************************************************************************************
 * Classe implémentant la ressource "student" de l'API REST. Un étudiant, dans ce contexte,
 * a un CIP, un nom et un mot de passe. Cette ressource est gérée par la classe 
 * ResourceStudent.
 * 
 * Note: cette classe doit absolument être sérialisable et avoir un constructeur par
 * défaut afin que Jersey arrive à la traiter. Pour être sérialisable, elle doit avoir,
 * pour chaque attribut privé, une getter et un setter.
 * 
 * @see RessourceStudent
 *
 **********************************************************************************************/
@XmlRootElement
public class Student {

    private String m_cip;  // Le CIP de l'étudiant
    private String m_name; // Le nom de l'étudiant 
    private String m_password; // Le mot de passe de l'étudiant
    
    /********************************************************************************************
     * Constructeur par défaut.
     * 
     * Note: NE PAS ENLEVER, JERSEY L'UTILISE À L'INTERNE!!!!
     * 
     ********************************************************************************************/
    public Student() {
        // ...
    }
 
    
    /********************************************************************************************
     * Constructeur.
     * 
     * @param p_cip  Le CIP de l'étudiant à créer (8 caractères).
     * @param p_name Le nom de l'étudiant à créer.
     * @param p_password Le mot de passe de l'étudiant à créer.
     * 
     ********************************************************************************************/
    public Student(String p_name, String p_cip, String p_password) {

    	assert (p_cip.length() == 8);
        assert !p_name.isEmpty();
        assert !p_password.isEmpty();

        m_cip = p_cip;
        m_name = p_name;
        m_password = p_password;
    }

    
    /******************************************************************************************
     * Accesseur pour le nom.
     * 
     * @return Le nom de l'étudiant.
     * 
     ******************************************************************************************/
    public String getName() {
        return m_name;
    }

    
    /******************************************************************************************
     * Mutateur pour le nom.
     * 
     * @param p_name Le nouveau nom à assigner à l'étudiant.
     * 
     ******************************************************************************************/
    public void setName(String p_name) {
        m_name = p_name;
    }

    
    /******************************************************************************************
     * Accesseur pour le CIP.
     * 
     * @return Le CIP de l'étudiant.
     * 
     ******************************************************************************************/
    public String getCip() {
        return m_cip;
    }

    
    /******************************************************************************************
     * Mutateur pour le CIP.
     * 
     * @param p_cip Le nouveau CIP à assigner à l'étudiant.
     * 
     *****************************************************************************************/
    public void setCip(String p_cip) {
        m_cip = p_cip;
    }

    
    /******************************************************************************************
     * Accesseur pour le mot de passe.
     * 
     * @return Le mot de passe de l'étudiant.
     * 
     ******************************************************************************************/
	public String getPassword() {
		return m_password;
	}

    /******************************************************************************************
     * Mutateur pour le mot de passe.
     * 
     * @param p_password Le nouveau mot de passe à assigner à l'étudiant.
     * 
     *****************************************************************************************/
	public void setPassword(String m_password) {
		this.m_password = m_password;
	}
	
    @Override
    public boolean equals(Object p_rhs) {
        // Quelques vérifications de base:
        if (p_rhs == null) {
            return false;
        }

        if (p_rhs == this) {
            return true;
        }

        if (!(p_rhs instanceof Student)) {
            return false;
        }

        // On a le bon type, on convertit au bon type pour continuer:
        Student student = (Student)p_rhs;
        
        // On compare les propriétés:
        if(m_name.equals(student.getName()) && m_cip.equals(student.getCip()) && (m_password.equals(student.getPassword()))) {
            return true;
        }

        return false;
    }
}

