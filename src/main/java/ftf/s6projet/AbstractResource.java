/**********************************************************************************************
 * Classe-interface permettant d'implémenter une ressource.
 * 
 * @author  Éric Poirier
 * @version Mai 2018
 * 
 *********************************************************************************************/

package ftf.s6projet;

/**********************************************************************************************
 * Implémenter cette classe-interface, c'est rendre notre ressource compatible avec notre
 * AbstractRessourceHandler permettant de gérer les requêtes Http du serveur d'application
 * uniformément. En théorie, chacune de nos ressources devrait dériver de cette classe-
 * interface.
 * 
 * IMPORTANT: toute ressource doit absolument avoir des getter et des setter pour chacun
 *            de ses attributs, autrement la classe n'est pas séialisable et le fichier
 *            JSON ne peut être créé (un fichier JSON vide est créé à la place).
 * 
 * @see AbstractRessourceHandler
 * 
 *********************************************************************************************/
public abstract class AbstractResource {

    protected String m_description; // Courte description de la ressource (512 caractères max).

    /******************************************************************************************
     * Constructeur.
     * 
     * @param p_description Une courte description (moins de 512 caractères) de la ressource.
     *                      À noter que la description ne peut pas être vide.
     * 
     *****************************************************************************************/
    public AbstractResource(String p_description) {
        
        assert !p_description.isEmpty()     : "You need a description for your ressource.";
        assert p_description.length() < 512 : "Your description is too long.";

        m_description = p_description;
    }

    /********************************************************************************************
     * Accès à la description de la ressource.
     * 
     * @return Une chaîne de caractères contenant la description.
     * 
     *******************************************************************************************/
    public String getDescription() {
        return m_description;
    }

    /********************************************************************************************
     * Permet de modifier la description. Les contraintes mentionnées dans le constructeur
     * s'appliquent également.
     * 
     * @param p_description La nouvelle description.
     * 
     *******************************************************************************************/
    public void setDescription(String p_description) {
        assert !p_description.isEmpty()     : "You need a description for your ressource.";
        assert p_description.length() < 512 : "Your description is too long.";
        
        m_description = p_description;
    }
}
