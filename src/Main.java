import java.io.FileNotFoundException;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new GUI();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
