import java.util.Scanner;

public class zuidazuixiao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int sum=0;
		int max = -10000,min = 10000;
		int[] ar=new int[n];
		for(int i=0;i<n;i++){
			ar[i]=sc.nextInt();
			if(ar[i]>max)
				max=ar[i];
			if(ar[i]<min)
				min=ar[i];
			sum+=ar[i];
		}
		sc.close();
		System.out.println(max);
		System.out.println(min);
		System.out.println(sum);
	}

}
