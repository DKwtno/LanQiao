	import java.util.Scanner;
	
	public class a10to16 {
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			Scanner sc=new Scanner(System.in);
			while(sc.hasNext()){
				System.out.println(Long.toHexString(Long.parseLong(sc.nextLine())).toUpperCase());
			}
		}
	}
