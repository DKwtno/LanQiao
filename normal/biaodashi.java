package algorithm_formal;
/**
 * 输入一个只包含加减乖除和括号的合法表达式，求表达式的值。其中除表示整除。
 * 表达式长度不超过100，表达式运算合法且运算过程都在int内进行。
 * 2017-12-27 09:37:53 
 * 完成无括号版本，但加减法仍然有问题，应该在读入的时候完成，方法类似（费时半小时）
 * 以上方法无法实行
 */
import java.util.Scanner;
import java.util.Stack;

public class biaodashi {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String form=sc.nextLine();
		sc.close();
		Stack<Character> symbol=new Stack<>();
		Stack<Integer> number=new Stack<>();
		for(int i=0;i<form.length();i++){
			if(isSymbol(form.charAt(i))){
				//乘除优先
				if(form.charAt(i)=='*'){
					String temp="";
					for(i=i+1;i<form.length()&&!isSymbol(form.charAt(i));i++){
						temp+=form.charAt(i);
					}
					i--;
					int n=Integer.valueOf(temp);
					int tn=number.pop()*n;
					number.push(tn);
					continue;
				}
				if(form.charAt(i)=='/'){
					String temp="";
					for(i=i+1;i<form.length()&&!isSymbol(form.charAt(i));i++){
						temp+=form.charAt(i);
					}
					i--;
					int n=Integer.valueOf(temp);
					int tn=number.pop()/n;
					number.push(tn);
					continue;
				}
				symbol.push(form.charAt(i));
				continue;
			}
			String num="";
			for(;i<form.length()&&!isSymbol(form.charAt(i));i++){
				num+=form.charAt(i);
			}
			i--;
			number.push(Integer.valueOf(num));
		}
		//符号读完
		while(!symbol.isEmpty()){
			char temp=symbol.pop();
			if(temp=='+'){
				int temp1=number.pop()+number.pop();
				number.push(temp1);
			}
			else if(temp=='-'){
				int temp1=number.pop()-number.pop();
				temp1*=-1;
				number.push(temp1);
			}
		}
		System.out.println(number.peek());
	}
	static boolean isSymbol(char c){
		return c=='+'||c=='-'||c=='*'||c=='/';
	}
}
