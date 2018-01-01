package algorithm_formal;

import java.util.Scanner;

/**
 * 给定一条标有整点(1, 2, 3, ...)的射线. 定义两个点之间的距离为其下标之差的绝对值.
　　Laharl, Etna, Flonne一开始在这条射线上不同的三个点, 他们希望其中某个人能够到达下标最大的点.
　　每个角色只能进行下面的3种操作, 且每种操作不能每人不能进行超过一次.
　　1.移动一定的距离
　　2.把另一个角色高举过头
　　3.将举在头上的角色扔出一段距离
　　每个角色有一个movement range参数, 他们只能移动到没有人的位置, 并且起点和终点的距离不超过movement range.
　　如果角色A和另一个角色B距离为1, 并且角色B没有被别的角色举起, 那么A就能举起B. 同时, B会移动到A的位置,B原来所占的位置变为没有人的位置. 
        被举起的角色不能进行任何操作, 举起别人的角色不能移动.同时, 每个角色还有一个throwing range参数, 即他能把举起的角色扔出的最远的距离. 
        注意, 一个角色只能被扔到没有别的角色占据的位置. 我们认为一个角色举起另一个同样举起一个角色的角色是允许的. 这种情况下会出现3个人在同一个位置的情况.
        根据前面的描述, 这种情况下上面的两个角色不能进行任何操作, 而最下面的角色可以同时扔出上面的两个角色. 你的任务是计算这些角色能够到达的位置的最大下标, 
        即最大的数字x, 使得存在一个角色能够到达x.
 * @author weizhiwei
 *
 */

public class LiftandThrow {
	static int ans=0;
	//空出0号位方便计算
	static int[] pos=new int[4];//表示位置
	static int[] thr=new int[4];//表示扔出距离
	static int[] mvr=new int[4];//表示最大移动距离
	static int[] able=new int[4];//表示能否被操作
	static int[] lift=new int[4];//表示第i个人举起的人
	static int[] stat={3,3,3,3};//3表示011，三个状态分别对应扔，举起，移动。0表示不可以扔（必须先举起），1表示可以举起别人，1表示可以移动
	private static int max(int a,int b){
		return a>b?a:b;
	} 
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		for(int i=1;i<4;i++){
			pos[i]=sc.nextInt();
			mvr[i]=sc.nextInt();
			thr[i]=sc.nextInt();
		}
		sc.close();
		dfs(1);
		System.out.println(ans);
	}
	private static void dfs(int d){
		int i=1,j=1,e=0;
		//更新答案
		for(;i<=3;i++){
			ans=max(ans,pos[i]);
		}
		for(i=1;i<4;i++){
			if(able[i]==1){
				continue;
			}
			//没移动且不可抛出
			if(stat[i]==3||stat[i]==1){
				//对应的移动距离
				for(j=1;j<=mvr[i];j++){
					pos[i]+=j;
					stat[i]^=1;
					//移动后附近有人或者达到移动上限
					if(near(pos[i])||j==mvr[i]){
						dfs(d+1);
					}
					pos[i]-=j;//向后移动j个单位
					pos[i]-=j;
					if(near(pos[i])||j==mvr[i]){
						dfs(d+1);
					}
					pos[i]+=j;//归位
					stat[i]^=1;//归位
					//每次探索完一种可能都要对已有的数据进行归位
				}
			}
			//没有举起
			if((stat[i]&2)==2){
				for(j=1;j<4;j++){
					if(i!=j&&able[j]==0&&thr[i]>0){
						//i可以举起j
						if(pos[i]==pos[j]+1||pos[j]==pos[i]+1){
							able[j]=1;//1代表不可操作
							stat[i]^=2;//举起
							stat[i]^=4;//可抛出
							lift[i]=j;//记录i举起了j
							e=pos[j];//记录j被举起之前的位置
							pos[j]=-1;//下一轮求最大值时防止该值被选中（为什么？）
							dfs(d+1);
							//归位
							pos[j]=e;
							able[j]=0;
							stat[i]^=2;
							stat[i]^=4;
						}
					}
				}
			}
			//可以抛出
			if(stat[i]==4||stat[i]==5){
				for(j=1;j<=thr[i];j++){
					able[lift[i]]=0;//设置为可操作
					stat[i]^=4;//不可抛出
					e=pos[lift[i]];//记录被抛出前的位置
					pos[lift[i]]=pos[i]+j;//扔出去，更新j的位置
					//如果被扔出去的j周围有人
					if(near(pos[lift[i]])||j==thr[i]){
						dfs(d+1);
					}
					pos[lift[i]]-=2*j;//归位并向后抛
					if(near(pos[lift[i]])||j==thr[i]){
						dfs(d+1);
					}
					pos[lift[i]]=e;//还原位置
					stat[i]^=4;//还原为可抛出
					able[lift[i]]=1;//设置为不可操作
				}
			}
		}
	}
	private static boolean near(int l) {
		// TODO Auto-generated method stub
		for(int i=1;i<4;i++){
			if(pos[i]==l+1||pos[i]==l-1)
				return true;
		}
		return false;
	}
}
