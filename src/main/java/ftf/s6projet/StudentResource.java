/**********************************************************************************************
 * Implémentation de la ressource "student" de l'API REST.
 * 
 * @author Éric Poirier
 * @date Juin 2018
 * 
 *********************************************************************************************/

package ftf.s6projet;

import javax.xml.bind.annotation.XmlRootElement;

/**********************************************************************************************
 * Classe implémentant la ressource "student" de l'API REST. Un étudiant, dans ce contexte,
 * a un prénom et un CIP uniquement. Cette ressource est gérée par la classe 
 * StudentRessourceHandler.
 * 
 * Note: cette classe doit absolument être sérialisable et avoir un constructeur par
 * défaut afin que Jersey arrive à la traiter. Pour être sérialisable, elle doit avoir,
 * pour chaque attribut privé, une getter et un setter.
 * 
 * @see StudentRessourceHandler
 *
 **********************************************************************************************/
@XmlRootElement
public class StudentResource {

    private String m_name; // Le prénom de l'étudiant.
    private String m_cip;  // Le CIP de l'étudiant.


    /********************************************************************************************
     * Constructeur par défaut.
     * 
     * Note: NE PAS ENLEVER, JERSEY L'UTILISE À L'INTERNE!!!!
     * 
     ********************************************************************************************/
    public StudentResource() {
        // ...
    }


    /********************************************************************************************
     * Constructeur.
     * 
     * @param p_name Le prénom de l'étudiant à créer.
     * @param p_cip  Le CIP de l'étudiant à créer (8 caractères).
     * 
     ********************************************************************************************/
    public StudentResource(String p_name, String p_cip) {

        assert !p_name.isEmpty();
        assert p_cip.length() == 8;

        m_name = p_name;
        m_cip = p_cip;
    }


    /******************************************************************************************
     * Accesseur pour le prénom.
     * 
     * @return Le prénom de l'étudiant.
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
        assert !p_name.isEmpty();

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
        assert p_cip.length() == 8;

        m_cip = p_cip;
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

        if (!(p_rhs instanceof StudentResource)) {
            return false;
        }

        // On a le bon type, on convertit au bon type pour continuer:
        StudentResource student = (StudentResource)p_rhs;
        
        // On compare les propriétés:
        if(m_name == student.getName() && m_cip == student.getCip()) {
            return true;
        }

        return false;
    }
}
