package ftf.s6projet;

import java.util.ArrayList;

public class ServiceAuthentification {

public ArrayList<Student> getStudentsInfo() {
        
	    Student s1 = new Student("karan", "kalk2701", "password");
	    Student s2 = new Student("bob", "kalk2501", "password");
	    
	    ArrayList<Student> list = new ArrayList<Student>();
	    
	    list.add(s1);
	    list.add(s2);
	    
		return list;
    }
	
	public Student getStudentInfo(String CIP) {
        
		
	    Student s1 = new Student("karan", "kalk2701", "password");
		
		return s1;
    }

	public Student updateUser(Student student) {
		
		return student;
    }
	
	public Student createNewUser(Student student) {
		
		return student;
    }
	
	public String removeStudentInfo(String CIP) {
        
		return "delete student info "+ CIP;
    }
	
}
