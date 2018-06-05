/**************************************************************************************************************
 * Classe implémentant la couche de persistance du micro service du release 0.
 * 
 * @author  Francois Poulin
 * @date    31 mai 2018
 * @version 1.0
 * 
 *************************************************************************************************************/

// Packages:
package ftf.s6projet.services.release0;

// Imports généraux:
import org.json.JSONObject;
import java.io.*;

public class PersistanceLayer {
    public String getPassword(String cip){
        String fileContent = readFile("D:\\ProjetS6\\FTFS6\\release0.json");
        JSONObject jsonFileObject = new JSONObject(fileContent);
        return jsonFileObject.getJSONObject(cip).get("password").toString();
    }

    public static String readFile(String filename) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while(line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}