import java.util.ArrayList;
public class Box {
	ArrayList<Class> all = new ArrayList<Class>();
	
	public Box() {
		
	}
	
	public ArrayList<Class> get() {
		return all;
	}
	
	public void add(Class cla) {
		all.add(cla);
	}
	
	public void remove(int i) {
		all.remove(i);
	}
	
}
