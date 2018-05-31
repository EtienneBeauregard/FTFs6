package ftf.s6projet;

public class ConcreteResource extends AbstractResource {

    int m_id;
    
    public ConcreteResource(String p_description, int p_id) {
        super(p_description);
        m_id = p_id;
    }

    public int getId() {
        return m_id;
    }

    public void setId(int p_id) {
        m_id = p_id;
    }
}
