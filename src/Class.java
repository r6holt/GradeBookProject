import java.util.*;
public class Class {
	
    ArrayList<Student> list = new ArrayList<Student>();
    ArrayList<Assignment> sign = new ArrayList<Assignment>();
    static int classID = -1;
    String name;
    
    public Class (String name) {
    	this.classID++;
    	this.name=name;
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
    
    public int searchName(String first, String last) {
    	for(int i=0; i<list.size(); i++) {
    		if(list.get(i).getFirst().equals(first) && list.get(i).getLast().equals(last)) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    public int getID() {
    	return this.classID;
    }
    
    public void addA(Assignment a) {
    	sign.add(a);
    }
    
    public ArrayList<Assignment> getA() {
    	return sign;
    }
    
    public void arrange(ArrayList<String> lasts) {
    	ArrayList<Student> nw = new ArrayList<Student>();
    	for(String l : lasts) {
    		nw.add(findByLast(l));
    	}
    	list.clear();
    	this.list=nw;
    }
    
    public void clear() {
    	list.clear();
    }
    
    public Student findByLast(String n) {
    	for(Student s : list) {
    		if(s.getLast().equals(n)) {
    			return s;
    		}
    	}
    	return null;
    }
}

