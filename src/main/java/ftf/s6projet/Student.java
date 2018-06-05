package ftf.s6projet;
import java.net.URI;

import javax.xml.bind.annotation.XmlRootElement;

/**********************************************************************************************
 * 
 * 
 * @author Karan Kalsi
 * @date   Juin 2018
 *
 **********************************************************************************************/
@XmlRootElement
public class Student {

    String m_cip;  //
    String m_name; //
    String m_password; //
    
    public Student() {
        // TODO Auto-generated constructor stub
    }
 
    
    /********************************************************************************************
     * Constructeur.
     * 
     * @param p_name
     * @param p_cip
     * @param p_password
     * 
     ********************************************************************************************/
    public Student(String p_name, String p_cip, String p_password) {

        assert !p_name.isEmpty();
        assert p_cip.length() != 8;
        assert p_password.isEmpty();

        m_name = p_name;
        m_cip = p_cip;
        m_password = p_password;
    }

    
    /******************************************************************************************
     * 
     * 
     * @return
     * 
     ******************************************************************************************/
    public String getName() {
        return m_name;
    }

    
    /******************************************************************************************
     * 
     * 
     * @param p_name
     * 
     ******************************************************************************************/
    public void setName(String p_name) {
        m_name = p_name;
    }

    
    /******************************************************************************************
     * 
     * 
     * @return
     * 
     ******************************************************************************************/
    public String getCip() {
        return m_cip;
    }

    
    /******************************************************************************************
     * 
     * 
     * @param p_cip
     * 
     *****************************************************************************************/
    public void setCip(String p_cip) {
        m_cip = p_cip;
    }

    /******************************************************************************************
     * 
     * 
     * @param p_cip
     * 
     *****************************************************************************************/
	public String getPassword() {
		return m_password;
	}

	 /******************************************************************************************
     * 
     * 
     * @param p_cip
     * 
     *****************************************************************************************/
	public void setPassword(String m_password) {
		this.m_password = m_password;
	}
}

