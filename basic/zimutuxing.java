import java.util.Scanner;

public class zimutuxing {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int m=sc.nextInt();
		int n=sc.nextInt();
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				int alpha='A'+j-i;
				if(alpha<'A')
					alpha+=2*(i-j);
				System.out.print((char)alpha);
			}
			System.out.println("");
		}
	}
}
