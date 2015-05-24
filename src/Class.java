import java.util.*;
public class Class {
    ArrayList<Student> list = new ArrayList<Student>();
    public Class () {
    }
    public void addStudent(Student student) {
      list.add(student);
    }
    
    public ArrayList<Student> get() {
    	return list;
    }
}

