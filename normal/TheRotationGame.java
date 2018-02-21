package algorithm_formal;

import java.util.Scanner;

public class TheRotationGame {
	static final int MAXN=24,LEN=8;
	static int[][] board={{0, 2, 6, 11, 15, 20, 22}, {1, 3, 8, 12, 17, 21, 23},
			{10, 9, 8, 7, 6, 5, 4}, {19, 18, 17, 16, 15, 14, 13},
			{23, 21, 17, 12, 8, 3, 1}, {22, 20, 15, 11, 6, 2, 0},
			{13, 14, 15, 16, 17, 18, 19}, {4, 5, 6, 7, 8, 9, 10}};//A-H八个数组
	static int[] qst=new int[MAXN],ans=new int[30],symbol=new int[30],
			center={6, 7, 8, 11, 12, 15, 16, 17};
	static char[][] order=new char[30][100];
	static int cnt=0,maxd;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int temp;
		while((temp=sc.nextInt())!=0){
			qst[0]=temp;
			for(int i=1;i<MAXN;i++){
				qst[i]=sc.nextInt();
			}
			if(unfit()==0){
				symbol[cnt]=qst[6];
				ans[cnt++]=0;
				continue;
			}
			for(maxd=1;;maxd++) if(dfs(0)) break;
			ans[cnt]=maxd;
			symbol[cnt]=qst[6];
			cnt++;
		}
		for(int i=0;i<cnt;i++){
			//打印答案
			String a="";
			for(int j=0;j<ans[i];j++)
				a+=order[i][j];
			if(ans[i]==0)
				a="No moves needed";
			System.out.println(a);
			System.out.println(symbol[i]);
		}
		sc.close();
	}
	private static int unfit(){//返回八个格子里不同数量的数目
		int n1=0,n2=0,n3=0;
		for(int i=0;i<LEN;i++){
			if(qst[center[i]]==1)
				n1++;
			else if(qst[center[i]]==2)
				n2++;
			else n3++;
		}
		return LEN-max3(n1,n2,n3);
	}
	private static void rotate(int di){
		int temp=qst[board[di][0]];
		for(int i=1;i<LEN-1;i++){
			qst[board[di][i-1]]=qst[board[di][i]];
		}
		qst[board[di][LEN-2]]=temp;
	}
	private static boolean dfs(int d) {
		// TODO Auto-generated method stub
		int c=unfit();
		if(c==0) return true;
		if(c+d>maxd) return false;
		int[] temp=qst.clone();
		for(int i=0;i<LEN;i++){
			rotate(i);
			order[cnt][d]=(char) (i+'A');
			if(dfs(d+1)) return true;
			qst=temp.clone();
		}
		return false;
	}
	private static int max3(int n1, int n2, int n3) {
		// TODO Auto-generated method stub
		if(n1>n2)
			if(n1>n3) return n1;
			else return n3;
		if(n3>n2) return n3;
		return n2;
	}
	
}
