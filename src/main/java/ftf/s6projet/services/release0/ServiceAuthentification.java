package ftf.s6projet.services.release0;

import java.util.ArrayList;

public class ServiceAuthentification {

public ArrayList<Student> getStudentsInfo() {
        
	    //Student s1 = new Student("karan", "kalk2701", "password");
	    //Student s2 = new Student("bob", "kalk2501", "password");
	    
	    ///ArrayList<Student> list = new ArrayList<Student>();
	    
	    //list.add(s1);
	    //list.add(s2);
		ArrayList<Student> listStudent = getAllUser(); //Francois me retorune un array list de student
		
		if(listStudent.size()==0) {
			Student error = new Student("Error", "Error", "Error");
			listStudent.add(error);
		}
		return listStudent;
    }
	
	public Student getStudentInfo(String CIP) {
        
		String[] studentArray = getUser(CIP);
		
		if(studentArray[0] != null) {
		    Student s1 = new Student(CIP, studentArray[1], studentArray[3]);
			return s1;
		}
		else {
			Student error = new Student("Error", "Error", "Error");
			return error;
		}
    }

	public Student updateUser(Student student) {
		
		boolean message_retour = putUser(student.getCip(), student.getName(),"LASTNAME", student.getPassword());
		
		if(message_retour == true){
			return student;
		}
	    else{
			Student error = new Student("Error", "Error", "Error");
			return error;
	    }
    }
	
	public Student createNewUser(Student student) {
		
		boolean message_retour = postUser(student.getCip(), student.getName(),"LASTNAME", student.getPassword());
		
		Student newStudent = getStudentInfo(student.getCip());
		
		if(message_retour == true && newStudent.getCip() != "Error"){
			return newStudent;
		}
	    else{
			Student error = new Student("Error", "Error", "Error");
			return error;
	    }
    }
	
	public String removeStudentInfo(String CIP) {
        
		boolean message_retour = deleteUser(CIP);
		if(message_retour = true) {
			return "Delete student info "+ CIP;
		}
		else {
			return CIP + " Not Found in the BD, Can't delete it";
		}
    }
	
}
