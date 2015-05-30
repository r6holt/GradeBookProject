import java.util.*;
public class Assignment{
  int outOf;
  String name;
  int id;
  int taking = 0;
  double average = 0;
  ArrayList<Integer> scores = new ArrayList<Integer>();
  
  public Assignment(String name, int outOf, int ID){
	  this.name=name;
	  this.outOf=outOf;
	  this.id=ID;
  }
  
  public int getID() {
	  return this.id;
  }
  
  public String getName() {
	  return this.name;
  }
  
  public int getOutOf() {
	  return this.outOf;
  }
  
  public void update(int n) {
	  double nd = n;
	  double outa = this.outOf;
	  double one = (nd/outa);
	  double all = this.average*this.taking;
	  all+=one;
	  taking++;
	  this.average=(all/taking);
  }
  
  public double getAverage() {
	  return this.average;
  }
}

