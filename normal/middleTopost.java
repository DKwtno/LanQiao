package algorithm_formal;

import java.util.Scanner;
import java.util.Stack;

public class middleTopost {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String form=sc.nextLine();
		String result="";
		Stack<Character> symbol=new Stack<>();
		for(int i=0;i<form.length();i++){
			char c=form.charAt(i);
			String temp="";
			boolean f=false;
			for(;i<form.length()&&Character.isDigit(form.charAt(i));i++){
				temp+=form.charAt(i);
				f=true;
			}
			if(f)
			{
				result+=temp;
				result+=" ";
				i--;
			}
			else{
				if(c==')'){
					while(symbol.peek()!='('){
						result+=symbol.pop();
					}
					symbol.pop();
				}
				else if(c=='*'||c=='/'){
					while(!symbol.isEmpty()&&(symbol.peek()!='('&&symbol.peek()!='+'&&symbol.peek()!='-')){
						result+=symbol.pop();
					}
					symbol.push(c);
				}
				else{
					if(c=='('){
						symbol.push(c);
						continue;
					}
					while(!symbol.isEmpty()){
						if(symbol.peek()=='(')
							break;
						result+=symbol.pop();
					}
					symbol.push(c);
				}
			}
		}
		while(!symbol.isEmpty()){
			result+=symbol.pop();
		}
		Stack<Integer> operand=new Stack<>();
		for(int i=0;i<result.length();i++){
			if(Character.isDigit(result.charAt(i))){
				String temp="";
				while(Character.isDigit(result.charAt(i))){
					temp+=result.charAt(i++);
				}
				operand.push(Integer.valueOf(temp.trim()));
			}
			else{
				char c=result.charAt(i);
				if(c=='+'){
					int a=operand.pop();
					int b=operand.pop();
					operand.push(a+b);
				}
				else if(c=='-'){
					int a=operand.pop();
					int b=operand.pop();
					operand.push(b-a);
				}
				else if(c=='*'){
					int a=operand.pop();
					int b=operand.pop();
					operand.push(a*b);
				}
				else if(c=='/'){
					int a=operand.pop();
					int b=operand.pop();
					operand.push(b/a);
				}
			}
		}
		System.out.println(operand.peek());
	}
}
