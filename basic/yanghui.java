import java.util.Scanner;

public class yanghui {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		yanghui(sc.nextInt());
	}
	public static void yanghui(int n){
		int[][] tr=new int[n][n];
		for(int i=0;i<n;i++)
			tr[i][0]=1;
		for(int i=1;i<n;i++){
			for(int j=1;j<=i;j++){
				tr[i][j]=tr[i-1][j-1]+tr[i-1][j];
			}
		}
		for(int i=0;i<n;i++){
			for(int j=0;j<=i;j++){
				System.out.print(tr[i][j]+" ");
			}
			System.out.println("");
		}
	}
}
