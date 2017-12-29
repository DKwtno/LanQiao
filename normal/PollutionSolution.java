package algorithm_formal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 污染治理问题：
 * 输入文件包含仅包含一组测试数据。
　　每组测试数据第一行为两个整数n (3 <= n <= 100), r (1 <= r <= 1000)，n表示了多边形的顶点个数，r表示了污染区域的半径；
　　接下来n行，每行包含两个整数xi (-1500 <= xi <= 1500), yi (0 <= yi <=1500)，表示每个顶点的坐标，以逆时针顺序给出；
　　数据保证多边形不自交或触及自身，没有顶点会位于圆弧上。
 * @author weizhiwei
 *
 */
public class PollutionSolution {
	static double calculate(Node[] v){
		double area=0.0;
		for(int i=1;i<v.length-1;i++){
			area+=v[0].x*v[i].y+v[i+1].x*v[0].y+v[i].x*v[i+1].y-v[i+1].x*v[i].y-v[0].x*
					v[i+1].y-v[i].x*v[0].y;
		}
		return area/2.0;
	}
	static double calcircle(Node[] c,int r){
		double area=0.0;
		if(c.length==0)
			return area;
		for(int i=0;i<c.length-1;i+=2){
			double d=Math.sqrt((c[i].x-c[i+1].x)*(c[i].x-c[i+1].x)+(c[i].y-c[i+1].y)*(c[i].y-c[i+1].y));
			double a=Math.asin((d/2)/r);
			area+=a*r*r;
			area-=0.5*r*r*Math.sin(2*a);
		}
		return area;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();//点的个数
		int r=sc.nextInt();//半圆半径
		Node[] node=new Node[n];
		ArrayList<Node> circle=new ArrayList<>();
		ArrayList<Node> in=new ArrayList<>();
		for(int i=0;i<n;i++){
			Node temp=new Node(sc.nextInt(),sc.nextInt());
			if(temp.x*temp.x+temp.y*temp.y<r*r){
				//落在半圆内的点
				temp.inside=true;
			}
			node[i]=temp;
			//表示穿过圆
			if(i>0&&node[i].inside==!node[i-1].inside){
				if(node[i-1].x==node[i].x){
					double x=node[i].x;
					double y=Math.sqrt(r*r-x*x);
					Node on=new Node(x, y);
					circle.add(on);
					in.add(on);
					if(temp.inside)
						in.add(temp);
				}
				else{
					double k,b;
					k=(node[i-1].y-node[i].y)/(node[i-1].x-node[i].x);
					b=node[i-1].y-k*node[i-1].x;
					double y=(2*b+Math.sqrt(4*b*b-4*(k*k+1)*(b*b-k*k*r*r)))/(2*(k*k+1));
					double x=(y-b)/k;
					Node on=new Node(x, y);
					circle.add(on);
					in.add(on);
					if(temp.inside)
						in.add(temp);
				}
			}
			else if(temp.inside){
				in.add(temp);
			}
		}
		sc.close();
		//还要判断数组最后一位和第一位是否穿过圆
		if(node[n-1].inside!=node[0].inside){
			if(node[n-1].x==node[0].x){
				double x=node[0].x;
				double y=Math.sqrt(r*r-x*x);
				Node on=new Node(x, y);
				circle.add(on);
				in.add(on);
			}
			else{
				double k,b;
				k=(node[n-1].y-node[0].y)/(node[n-1].x-node[0].x);
				b=node[n-1].y-k*node[n-1].x;
				double y=(2*b+Math.sqrt(4*b*b-4*(k*k+1)*(b*b-k*k*r*r)))/(2*(k*k+1));
				double x=(y-b)/k;
				Node on=new Node(x, y);
				in.add(on);
				circle.add(on);
			}
		}
		in.trimToSize();
		circle.trimToSize();
		//按照横坐标从左到右排序
		Collections.sort(circle);
		double area=0.0;
		if(in.size()>0){
			Node[] fin=new Node[in.size()];
			area=calculate(in.toArray(fin));
		}
		if(circle.size()>0){
			Node[] cir=new Node[circle.size()];
			area+=calcircle(circle.toArray(cir),r);
		}
		System.out.println(area);
	}
	
}
class Node implements Comparable<Node>{
	boolean inside=false;
	double x,y;
	Node(double x,double y){
		this.x=x;
		this.y=y;
	}
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return (int) (x-o.x);
	}
}
