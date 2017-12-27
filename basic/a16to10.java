import java.util.Scanner;

public class a16to10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		while(sc.hasNext()){
			System.out.println(Long.parseLong(sc.nextLine(),16));
		}
	}
}
