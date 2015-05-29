import java.util.*;
public class Class {
	
    ArrayList<Student> list = new ArrayList<Student>();
    static int classID = -1;
    String name;
    boolean is;
    
    public Class (String name) {
    	this.classID++;
    	this.name=name;
    	is = false;
    }
    
    public void addStudent(Student student) {
      list.add(student);
    }
    
    public ArrayList<Student> get() {
    	return list;
    }
    
    public Student getStudent(int i) {
    	return list.get(i);
    }
    
    public String getName() {
    	return this.name;
    }
    
    public int searchName(String student) {
    	for(int i=0; i<list.size(); i++) {
    		if(list.get(i).getName().equals(student)) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    public void change() {
    	is=!is;
    }
    
    public boolean is() {
    	return this.is;
    }
}

