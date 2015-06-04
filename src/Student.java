
public class Student {
	String first;
	String last;
	double grade;
	int total = 0;
	int myGrade = 0;
	int studID;
	
	public Student(String f, String l) {
		this.first = f;
		this.last = l;
		grade=0.0;
	}
	
	public void addScore(int tot, int my) {
		total+=tot;
		myGrade+=my;
		double g = total;
		double m = myGrade;
		grade = 100.0*(m/g);
		grade = Math.round(grade * 100);
		grade = grade/100;
	}
	
	public double getGrade() {
		return this.grade;
	}
	
	public String getName() {
		return this.first +" "+this.last;
	}
	
	public int getTotal() {
		return this.total;
	}
	
	public int getMy() {
		return this.myGrade;
	}
	
	public String getLast() {
		return this.last;
	}
	
	public String getFirst() {
		return this.first;
	}

}
