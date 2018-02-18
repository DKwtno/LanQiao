package algorithm_formal;
/**
 * 通过！
 */
import java.util.LinkedList;
import java.util.Scanner;

public class TheMorningAfterHalloween_Final {
	static short[][] G;//每个空格可以走的格子
	static short[] deg;
	static byte[][] hous=new byte[16][16];
	static byte[][][] d;
	static int count=0,w,h,n,i,j,k;
	static Nodem fst,sec=new Nodem(3);
	static int[] ghst=new int[3],aim=new int[3],dx={0,1,-1,0,0},dy={0,0,0,1,-1},ans=new int[10];//中下上右左
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int temp;
		while((temp=sc.nextInt())!=0){
			w=temp;//列
			h=sc.nextInt();//行
			n=sc.nextInt();//鬼的数量
			sc.nextLine();//读取回车
			for(i=0;i<h;i++){
				String line=sc.nextLine();
				char[] ch=line.toCharArray();//第i行的一列
				for(j=0;j<w;j++){
					Character c=ch[j];
					if(c=='#')
						hous[i][j]=-1;//-1表示不可走
					else{
						hous[i][j]=0;//空格表示为0
						if (Character.isLowerCase(c))
							ghst[c - 'a'] = i * w + j;//用一维数组来存储鬼在hous中的位置
						else if(Character.isUpperCase(c)){
							aim[c - 'A'] = i * w + j;
							//整个hous数组只有0或-1，表示可以走或不可以走
						}
					}
				}
			}
			int c=w*h;
			int c1=(w-1)*(h-1);
			deg=new short[c];
			G=new short[c][5];
			d=new byte[c1][c1][c1];
			pregraph();
			bfs();
			System.gc();
		}
		sc.close();
		for(i=0;i<count;i++){
			System.out.println(ans[i]);
		}
	}
	private static void bfs() {
		// TODO Auto-generated method stub
		LinkedList<Nodem> q=new LinkedList<Nodem>();
		Nodem f=new Nodem(aim);
		Nodem now=new Nodem(ghst);
		now.dist=0;
		q.push(now);
		if(n==1)
			d[now.stat[0]-w-1][0][0]=1;
		else if(n==2)
			d[now.stat[0]-w-1][now.stat[1]-w-1][0]=1;
		else if(n==3)
			d[now.stat[0]-w-1][now.stat[1]-w-1][now.stat[2]-w-1]=1;
		while(!q.isEmpty()){
			fst=q.pollLast();	
			if(fst.equals(f)){
				break;
			}
			//接下来要把鬼的不同状态压入队列中
			int a=fst.stat[0],b=fst.stat[1],c=fst.stat[2];
			int a2=0,b2=0,c2=0;
			for(i=0;i<deg[a];i++){
				a2=G[a][i];
				if(n==1){
					sec.stat[0]=a2;
					if(d[a2-w-1][0][0]!=0) continue;
					d[a2-w-1][0][0] = 1;
					Nodem p=new Nodem(sec.stat);
					p.dist=fst.dist+1;
					q.push(p);
				}
				if(n>=2){
					for(j=0;j<deg[b];j++){
						b2 = G[b][j];
						if(conflict(a,b,a2,b2)) continue;
						if(n==2){
							sec.stat[0]=a2;sec.stat[1]=b2;
							if(d[a2-w-1][b2-w-1][0]!=0) continue;
							d[a2-w-1][b2-w-1][0] = 1;
							Nodem p=new Nodem(sec.stat);
							p.dist=fst.dist+1;
							q.push(p);
						}
						if(n==3){
							for(k=0;k<deg[c];k++){
								c2=G[c][k];
								if(conflict(a, c, a2, c2)) continue;
						        if(conflict(b, c, b2, c2)) continue;
								sec.stat[0]=a2;sec.stat[1]=b2;sec.stat[2]=c2;
								if(d[a2-w-1][b2-w-1][c2-w-1]!=0) continue;
								d[a2-w-1][b2-w-1][c2-w-1] = 1;
								Nodem p=new Nodem(sec.stat);
								p.dist=fst.dist+1;
								q.push(p);
							}
						}
					}
				}
			}
		}
		ans[count]=fst.dist;
		count++;
	}
	private static boolean conflict(int a,int b,int c,int d) {//fst是之前的状态，sec是当前的状态
		// TODO Auto-generated method stub
		return c==d||(b==c&&a==d);
	}
	private static void pregraph() {
		// TODO Auto-generated method stub
		int x,y,pos;
		for(i=0;i<h;i++){
			for(j=0;j<w;j++){
				if(hous[i][j]!=0)
					continue;
				for(k=0;k<5;k++){
					//以i,j为前两维，以k为方向，看hous[i][j]的四个方向是否都能走
					pos=i*w+j;
					x=i+dx[k];
					y=j+dy[k];
					if(inbox(x,y)&&hous[x][y]==0){
						G[pos][deg[pos]++]=(short) (x*w+y);//记录下位置的值
					}
				}
			}
		}
	}
	private static boolean inbox(int x,int y){
		return x>=0&&x<h&&y>=0&&y<w;
	}
}
class Nodem{
	int[] stat;
	int d;
	int dist;
	Nodem(int d){
		this.d=d;
		stat=new int[d];
	}
	Nodem(int[] stat){
		d=stat.length;
		this.stat=new int[d];
		for(int i=0;i<d;i++){
			this.stat[i]=stat[i];
		}
	}
	public boolean equals(Nodem nd){
		for(int i=0;i<nd.d;i++){
			if(stat[i]!=nd.stat[i])
				return false;
		}
		return true;
	}
}
