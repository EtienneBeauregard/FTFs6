package ftf.s6projet.services.release0;

import java.util.ArrayList;
/**********************************************************************************************
 * 
 * 
 * @author Vincent Labbé
 * @date   Juin 2018
 *
 **********************************************************************************************/
public class ServiceAuthentification {
	
    /********************************************************************************************
     * Constructeur.
     * 
     * 
     ********************************************************************************************/
	
    public ServiceAuthentification() {
        // TODO Auto-generated constructor stub
    }
    
    /********************************************************************************************
     * 
     * 
     * @return ArrayList<Student>
     * 
     ********************************************************************************************/
public ArrayList<Student> getStudentsInfo() {
        
	   /* Student s1 = new Student("karan", "kalk2701", "password");
	    Student s2 = new Student("bob", "kalk2501", "password");
	    
	    ArrayList<Student> list = new ArrayList<Student>();
	    
	    list.add(s1);
	    list.add(s2);
	    return list; */
		PersistanceLayer persistance = new PersistanceLayer();
		
		ArrayList<Student> listStudent = persistance.getAllUsers(); //Francois me retorune un array list de student
		
		if(listStudent.size()==0) {
			Student error = new Student("Error", "Error", "Error");
			listStudent.add(error);
		}
		return listStudent;
    }

/********************************************************************************************
 * 
 * @param String CIP
 * @return Student student
 * 
 ********************************************************************************************/
	public Student getStudentInfo(String CIP) {
		PersistanceLayer persistance = new PersistanceLayer();
		
		Student student = persistance.getUser(CIP);
		
		return student;
		/*if(studentArray[0] != null) {
		    Student s1 = new Student(CIP, studentArray[1], studentArray[3]);
			return s1;
		}
		else {
			Student error = new Student("Error", "Error", "Error");
			return error;
		}*/
    }
	
	/********************************************************************************************
	 * 
	 * @param Student student
	 * @return Student
	 * 
	 ********************************************************************************************/
	public Student updateUser(Student student) {
		
		PersistanceLayer persistance = new PersistanceLayer();
		boolean message_retour = persistance.putUser(student);
		
		if(message_retour == true){
			return student;
		}
	    else{
			Student error = new Student("Error", "Error", "Error");
			return error;
	    }
    }
	
	/********************************************************************************************
	 * 
	 * @param Student student
	 * @return Student
	 * 
	 ********************************************************************************************/
	public Student createNewUser(Student student) {
		
		PersistanceLayer persistance = new PersistanceLayer();
		boolean message_retour = persistance.postUser(student);
		
		Student newStudent = getStudentInfo(student.getCip());
		
		if(message_retour == true && newStudent.getCip() != "Error"){
			return newStudent;
		}
	    else{
			Student error = new Student("Error", "Error", "Error");
			return error;
	    }
    }
	
	/********************************************************************************************
	 * 
	 * @param String CIP
	 * @return String
	 * 
	 ********************************************************************************************/
	public String removeStudentInfo(String CIP) {
		
		PersistanceLayer persistance = new PersistanceLayer();
		
		boolean message_retour = persistance.delUser(CIP);
		if(message_retour == true) {
			return "Delete student info "+ CIP;
		}
		else {
			return CIP + " Not Found in the BD, Can't delete it";
		}
    }
	
}
