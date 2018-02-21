package algorithm_formal;

import java.util.Scanner;

/**
 * 输入：n组数据
 * 每组数据：房间宽度r，砝码个数s，各个砝码的质量wi
 * 0<r<10,1<=s<=6,1<=wi<=1000
 * 在不超过房间宽度的情况下求出最长的天平长度
 * @author weizhiwei
 *
 */
public class MobileComputing {
	static double[] ans;//n个database的解集
	static double r;//房间宽度
	static int s,cnt=0,twi;//石头个数
	static double maxlen;
	static double[] wi;
	static int[] vis;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		ans=new double[n];
		while(n-->0){
			twi=0;
			maxlen=-1;
			r=sc.nextDouble();
			s=sc.nextInt();
			wi=new double[s];
			for(int i=0;i<s;i++){
				wi[i]=sc.nextInt();
				twi+=wi[i];//总质量
			}
			//输入完毕
			vis=new int[s];//访问数组
			Tnode[] tnd=new Tnode[s];
			for(int i=0;i<s;i++){
				tnd[i]=new Tnode(wi[i]);
			}
			solve(tnd,s);
			ans[cnt++]=maxlen;
			maxlen=0;
		}
		for(int i=0;i<cnt;i++){
			if(ans[i]==-1.0)
				System.out.println(-1);
			else
				System.out.println(ans[i]);
		}
		sc.close();
	}
	private static void solve(Tnode[] tnd,int d) {
		// TODO Auto-generated method stub
		//每两个结点合并，就少一个结点
		Tnode[] temp=tnd.clone();
		if(d==1){
			//只剩一个根节点的时候
			for(int i=0;i<s;i++){
				if(tnd[i].weight==twi){//根节点的质量等于总质量
					double len=calculate(tnd,i);
					if(len>r) break;
					maxlen=Math.max(maxlen, len);
				}
			}
		}
		for(int i=0;i<s;i++){
			for(int j=0;j<s;j++){
				if(i==j) continue;
				if(vis[i]==0&&vis[j]==0){
					Tnode l=tnd[i];
					Tnode r=tnd[j];
					Tnode root=new Tnode(0);
					root.lft=l;root.rgt=r;
					root.weight=l.weight+r.weight;
					tnd[i]=root;
					vis[j]=-1;
					solve(tnd,d-1);
					tnd=temp;
					temp=tnd.clone();
					vis[j]=0;
				}
			}
		}
	}
	private static double calculate(Tnode[] tnd, int n) {
		// TODO Auto-generated method stub
		Tnode root=tnd[n];
		double[] a=new double[2],b=new double[2];
		if(root.lft.lft!=null)
			a=len(root.lft);
		if(root.rgt.lft!=null)
			b=len(root.rgt);	
		double t1=(double)(root.rgt.weight/(root.rgt.weight+root.lft.weight));
		double t2=1-t1;
		double len=Math.max(a[0]+t1, b[0]-t2)+Math.max(b[1]+t2, a[1]-t1);
		return len;
	}
	private static double[] len(Tnode root) {
		// TODO Auto-generated method stub
		if(root.lft.lft==null){//表示是最底层的天平
			double[] temp=new double[2];
			temp[0]=(double)(root.rgt.weight/(root.rgt.weight+root.lft.weight));
			temp[1]=1-temp[0];
			return temp;
		}
		double t1=(double)(root.rgt.weight/(root.rgt.weight+root.lft.weight));
		double t2=1-t1;
		double[] a=new double[2],b=new double[2];
		if(root.lft.lft!=null)
			a=len(root.lft);
		if(root.rgt.lft!=null)
			b=len(root.rgt);
		double[] tt=new double[2];
		tt[0]=Math.max(a[0]+t1, b[0]-t2);
		tt[1]=Math.max(b[1]+t2, a[1]-t1);
		return tt;
	}
	
}
class Tnode{
	Tnode lft=null;
	Tnode rgt=null;
	double weight;
	Tnode(double wi){
		this.weight=wi;
	}
}
