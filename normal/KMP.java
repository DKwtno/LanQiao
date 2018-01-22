package Test;
/**
 * KMP算法，输入两个字符串，输出模式串在原字符串中的位置
 * 如果不存在模式串，则输出"不存在"
 * @author weizhiwei
 *
 */
public class KMP {
	public static void main(String[] args) {
		kmp("ababababca","ab");
	}
	private static int[] getNextval(char[] tc){
		int[] k=new int[tc.length];
		k[0]=-1;
		int i=0,j=-1;
		while(i<tc.length-1){
			if(j==-1||tc[i]==tc[j]){//j指向-1表示某个位的next值为-1，即为开头
				i++;j++;
				if(tc[i]!=tc[j]){//如果tj!=ti，则next[i]指向tj
					k[i]=j;
				}
				else{//如果tj==ti，则next[i]指向tnext[j]
					//这是改进过的KMP算法
					k[i]=k[j];
				}
			}
			else{
				j=k[j];//j指向next[j]
			}
		}
		return k;
	}
	public static void kmp(String s,String t){
		System.out.println("（初始位置记为1）");
		char[] sc=s.toCharArray();
		char[] tc=t.toCharArray();
		System.out.println("字符串为："+s);
		System.out.println("模式串为："+t);
		int i=0,j=0;
		int nextval[]=getNextval(tc);
		while(i<sc.length&&j<tc.length){
			if(j==-1||sc[i]==tc[j]){
				//对应相等或者指向开头处
				i++;j++;
			}
			else{
				//i不变，变j
				j=nextval[j];
			}
		}
		if(j>=tc.length){
			System.out.println("匹配成功！位置为："+(i-tc.length+1));
		}
		else
			System.out.println("匹配不成功！");
	}
}
