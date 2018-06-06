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
import java.util.ArrayList;
import java.util.Iterator;

public class PersistanceLayer {

    public PersistanceLayer() {}

    public Student getUser(Student p_student){
        String fileContent = readFile(filePath);
        JSONObject jsonFileContent = new JSONObject(fileContent);

        if (jsonFileContent.has(p_student.m_cip)) {
            Student studentResult = new Student(jsonFileContent.getJSONObject(p_student.m_cip).get("fname").toString(), 
                                                p_student.m_cip,
                                                jsonFileContent.getJSONObject(p_student.m_cip).get("password").toString());
            return studentResult;
        }    
        return null;
    }

    public ArrayList<Student> getAllUsers() {
        ArrayList<Student> al = new ArrayList<Student>();
        
        String fileContent = readFile(filePath);
        JSONObject jsonFileObject = new JSONObject(fileContent);
        Iterator<String> keyIterator = jsonFileObject.keys();
        int arrayListIterator = 0;

        if (keyIterator != null) {
            while(keyIterator.hasNext()) {
                String key = keyIterator.next();
                Student student = new Student(jsonFileObject.getJSONObject(key).get("fname").toString(), 
                                              key, 
                                              jsonFileObject.getJSONObject(key).get("password").toString());
                
                al.add(arrayListIterator, student);
                arrayListIterator++;
            }
            return al;
        }
        return null;
    }

    public boolean postUser(Student p_student) {
        String fileContent = readFile(filePath);
        JSONObject jsonFileContent = new JSONObject(fileContent);

        if (!jsonFileContent.has(p_student.m_cip)) {
            try {
                JSONObject postJsonObject = new JSONObject();
                postJsonObject.accumulate("fname", p_student.m_name);
                postJsonObject.accumulate("lname", "");
                postJsonObject.accumulate("password", p_student.m_password);
                jsonFileContent.put(p_student.m_cip, postJsonObject);
                dumpJsonFile(jsonFileContent);
                return true;    
            } catch(Exception e) {
                e.printStackTrace();
                return false;
            }
            
        }
        return false;
    }

    public boolean putUser(Student p_student) {
        String fileContent = readFile(filePath);
        JSONObject jsonFileContent = new JSONObject(fileContent);

        if (jsonFileContent.has(p_student.m_cip)) {
            JSONObject putJsonObject = new JSONObject();
            putJsonObject.accumulate("fname", p_student.m_name);
            putJsonObject.accumulate("lname", "");
            putJsonObject.accumulate("password", p_student.m_password);
            dumpJsonFile(jsonFileContent.put(p_student.m_cip, putJsonObject));
            return true;
        }
        return false;
    }

    public boolean delUser(Student p_student) {
        String fileContent = readFile(filePath);
        JSONObject jsonFileContent = new JSONObject(fileContent);
        
        if (jsonFileContent.has(p_student.m_cip)) {
            jsonFileContent.remove(p_student.m_cip);
            dumpJsonFile(jsonFileContent);
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