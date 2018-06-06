/**************************************************************************************************************
 * Classe implémentant la couche de persistance du micro service du release 0.
 * 
 * @author  Francois Poulin
 * @date    6 juin 2018
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

/********************************************************************************************
 * Persistance layer for the release 0 service.
 ********************************************************************************************/
public class PersistanceLayer {

    /********************************************************************************************
     * Constructeur.
     ********************************************************************************************/
    public PersistanceLayer() {}

    /********************************************************************************************
     * Get a specific user.
     * @param p_student
     * @return Student
     ********************************************************************************************/
    public Student getUser(String CIP){
        String fileContent = readFile(filePath);
        JSONObject jsonFileContent = new JSONObject(fileContent);
        if (jsonFileContent.has(CIP)) {
            Student studentResult = new Student(jsonFileContent.getJSONObject(CIP).get("fname").toString(), 
            									CIP,
                                                jsonFileContent.getJSONObject(CIP).get("password").toString());
            return studentResult;
        }    
        return null;
    }

    /********************************************************************************************
     * Get all users.
     * @return ArrayList<Student>
     ********************************************************************************************/
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

    /********************************************************************************************
     * Create a user.
     * @param p_student
     * @return boolean
     ********************************************************************************************/
    public boolean postUser(Student p_student) {
        String fileContent = readFile(filePath);
        JSONObject jsonFileContent = new JSONObject(fileContent);

        if (!jsonFileContent.has(p_student.getCip())) {
            try {
                JSONObject postJsonObject = new JSONObject();
                postJsonObject.accumulate("fname", p_student.getName());
                postJsonObject.accumulate("lname", "");
                postJsonObject.accumulate("password", p_student.getPassword());
                jsonFileContent.put(p_student.getCip(), postJsonObject);
                dumpJsonFile(jsonFileContent);
                return true;    
            } catch(Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /********************************************************************************************
     * Update a user.
     * @param p_student
     * @return boolean
     ********************************************************************************************/
    public boolean putUser(Student p_student) {
        String fileContent = readFile(filePath);
        JSONObject jsonFileContent = new JSONObject(fileContent);

        if (jsonFileContent.has(p_student.getCip())) {
            JSONObject putJsonObject = new JSONObject();
            putJsonObject.accumulate("fname", p_student.getName());
            putJsonObject.accumulate("lname", "");
            putJsonObject.accumulate("password", p_student.getPassword());
            dumpJsonFile(jsonFileContent.put(p_student.getCip(), putJsonObject));
            return true;
        }
        return false;
    }

    /********************************************************************************************
     * Delete a user.
     * @param p_student
     * @return boolean
     ********************************************************************************************/
    public boolean delUser(String CIP) {
        String fileContent = readFile(filePath);
        JSONObject jsonFileContent = new JSONObject(fileContent);
        
        if (jsonFileContent.has(CIP)) {
            jsonFileContent.remove(CIP);
            dumpJsonFile(jsonFileContent);
            return true;
        }
        return false;
    }

    /********************************************************************************************
     * Read file to String.
     * @param p_filePath
     * @return String
     ********************************************************************************************/
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

    /********************************************************************************************
     * Dump JSONObject to file.
     * @param p_jsonObject
     * @return boolean
     ********************************************************************************************/
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

    private static String filePath = "C:\\Users\\K_Kal\\OneDrive\\Documents\\Génie Informatique\\S6\\PROJET\\ExtensionProject\\FTFs6\\release0.json";
}