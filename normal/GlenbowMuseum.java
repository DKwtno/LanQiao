package algorithm_formal;
/**
 * 题目：
 * 新的建筑的尺寸和容量将不同于原始的建筑，
 * 但是所有楼层的设计都是直角多边形。一个直角多边形是内角均为90°或270°的多边形。
 * 所以只有满足以下要求的楼层设计能被接受：存在一个地点使得一个保安能监视到整个楼层。
 * 因此一个角序列能被接受，当且仅当它描述了一个能够被接受的多边形。
 * 
 * 解题思路：
 * 重点在于想到钝角O和直角R的数量关系是R=O+4
 * 以及最终问题能够简化成往所有R之中插入O，并且保证首尾两端不同时有O
 * 因为连续的O是不可能满足计划的
 */
import java.util.ArrayList;
import java.util.Scanner;

public class GlenbowMuseum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		ArrayList<Integer> arr=new ArrayList<Integer>();
		String str=sc.nextLine();
		while(!str.equals("0")){
			arr.add(Integer.valueOf(str));
			str=sc.nextLine();
		}
		sc.close();
		for(int i=0;i<arr.size();i++){
			System.out.print("Case "+(i+1)+": "+way(arr.get(i))+"\n");
		}
	}
	static long way(long n){
		if(n<4||(n-4)%2!=0)
			return 0;
		return C(4+(n-4)/2+1,5)-C(4+(n-4)/2-1,5);  
	}
	static long C(long m,int n){
		long sum = 1;
		int num=1;
		for(long i=m-n+1;i<=m;i++)
			sum*=i;
		for(int i=1;i<=n;i++)
			num*=i;
		return sum/num;
	}
}
