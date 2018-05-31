package ftf.s6projet;

import javax.ws.rs.Path;

@Path("test")
public class ConcreteResourceHandler extends AbstractResourceHandler {
    
    @Override
    public AbstractResource onGet() {
        return new ConcreteResource("Une ressource", 1);
    }
}
