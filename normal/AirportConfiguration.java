package algorithm_formal;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 输入文件有多组测试数据。
　　最后一组只有一个0。
　　每组测试数据的输入有两部分。先是客流数据，之后是机场布局。
　　数据开始时一个n(1<n<25)，表示城市数。接下来n行，
	每行表示一个城市的数据，第i行先是一个整数，表示起始城市，
	再一个1到n的整数k，表示目标城市数，k对整数，每对描述一个目标城市，
	第一个数是城市编号j，然后是乘客数目(最多500)从i到j的人数。
　　机场布局部分包括1到20个方案。用一个0结束。
　　一个方案包括3行。第一行一个数表示编号，第二行是1-n的一个排列，
	描述到达门对应的城市的排列，第三行用同样的方式描述出发大门。
 * @author weizhiwei
 *
 */
public class AirportConfiguration {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		LinkedList<R> result=new LinkedList<R>();//结果集
		int city;
		int count=0;
		while((city=sc.nextInt())!=0){
			//判断方案是否结束
			int[][] A=new int[25][25];
			//所有城市数输入
			for(int j=0;j<city;j++){
				int i=sc.nextInt();
				int num=sc.nextInt();
				for(int step=0;step<num;step++){
					A[i-1][sc.nextInt()-1]=sc.nextInt();
				}
			}
			//输入方案
			int method;
			while((method=sc.nextInt())!=0){
				int[][] B=new int[2][city];//方案数组
				for(int i=0;i<2;i++){
					for(int j=0;j<city;j++){
						B[i][j]=sc.nextInt();
					}
				}
				int[][] L=new int[city][city];
				for(int i=0;i<city;i++){
					for(int j=0;j<city;j++){
						L[B[0][i]-1][B[1][j]-1]=Math.abs(i-j)+1;
					}
				}
				int rs=0;
				for(int i=0;i<city;i++){
					for(int j=0;j<city;j++){
						rs+=A[i][j]*L[i][j];
					}
				}
				R temp=new R(count,method,rs);
				result.add(temp);
			}
			count++;
		}
		//完成所有输入
		sc.close();
		Collections.sort(result);
		int temp=-1;
		for(R i:result){
			if(i.m!=temp){
				temp++;
				System.out.println("Configuration Load");
			}
			System.out.printf("%d%9s%d\n",i.n,"",i.out);
		}
	}
}
class R implements Comparable<R>{
	int m;//第m组数据
	int n;//第n个方法
	int out;//客流量
	R(int m,int n,int out){
		this.m=m;
		this.n=n;
		this.out=out;
	}
	@Override
	public int compareTo(R o) {
		// TODO Auto-generated method stub
		//想要升序
		if(this.m>o.m) return 1;//先输入的一组数据在前
		else if(this.m<o.m) return -1;
		else if(this.out>o.out) return 1;
		else if(this.out<o.out) return -1;
		else if(this.n>o.n) return 1;//当客流量相同时，方案名小的在前
		else return -1;//方案不可能相同
	}
}
