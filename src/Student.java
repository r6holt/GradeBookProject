
public class Student {
	String name;
	double grade;
	int total = 0;
	int myGrade = 0;
	
	public Student(String n) {
		this.name=n;
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
		return this.name;
	}
	
	public int getTotal() {
		return this.total;
	}
	
	public int getMy() {
		return this.myGrade;
	}

}
