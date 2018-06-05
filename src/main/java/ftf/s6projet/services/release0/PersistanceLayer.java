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
import org.json.JSONArray;
import java.io.*;
import java.util.ArrayList;

public class PersistanceLayer {

    public String[] getUser(String p_cip){
        String fileContent = readFile(filePath);
        JSONObject jsonFileContent = new JSONObject(fileContent);
        

        if (jsonFileContent.has(p_cip)) {
            String[] userInfo  = {p_cip,
                                  jsonFileContent.get("fname").toString(), 
                                  jsonFileContent.get("lname").toString(), 
                                  jsonFileContent.get("password").toString()};
            return userInfo;
        }    
        return null;
    }

    public ArrayList<String[]> getAllUser() {
        ArrayList<String[]> al = new ArrayList<String[]>();
        
        String fileContent = readFile(filePath);
        JSONObject jsonFileObject = new JSONObject(fileContent);
        Iterator<String> keyIterator = jsonFileObject.keys();

        for () {
            JSONObject currentJsonObject = new JSONObject();
            al.add(0, [
                jsonBDArray.getJSONObject(i).keys().,
                ,
                ,
                ,
            ]);
        }
        return null;
    }

    public boolean postUser(String p_cip, String p_fname, String p_lname, String p_password) {
        String fileContent = readFile(filePath);
        JSONObject jsonFileContent = new JSONObject(fileContent);

        if (!jsonFileContent.has(p_cip)) {
            JSONObject postJsonObject = new JSONObject();
            postJsonObject.accumulate("fname", p_fname);
            postJsonObject.accumulate("lname", p_lname);
            postJsonObject.accumulate("password", p_password);
            jsonFileContent.append(p_cip, postJsonObject);
            dumpJsonFile(jsonFileContent);
            return true;
        }
        return false;
    }

    public boolean putUser(String p_cip, String p_fname, String p_lname, String p_password) {
        String fileContent = readFile(filePath);
        JSONObject jsonFileContent = new JSONObject(fileContent);

        if (jsonFileContent.has(p_cip)) {
            JSONObject putJsonObject = jsonFileContent.getJSONObject(p_cip);
            putJsonObject.append("fname", p_fname);
            putJsonObject.append("lname", p_lname);
            putJsonObject.append("password", p_password);
            dumpJsonFile(jsonFileContent.append(p_cip, putJsonObject));
            return true;
        }
        return false;
    }

    public boolean delUser(String p_cip) {
        String fileContent = readFile(filePath);
        JSONObject jsonFileContent = new JSONObject(fileContent);
        
        if (jsonFileContent.has(p_cip)) {
            jsonFileContent.remove(p_cip);
            return true;
        }
        return false;
    }

    public static String readFile(String p_filePath) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(p_filePath));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while(line != null) {
                sb.append(line);
                line = br.readLine();
            }
            br.close();
            result = sb.toString();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean dumpJsonFile(JSONObject p_jsonObject) {
        try {
            FileWriter fw = new FileWriter(filePath);
            p_jsonObject.write(fw, 0, 4);
            fw.close();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String filePath = "D:\\ProjetS6\\FTFS6\\release0.json";
}