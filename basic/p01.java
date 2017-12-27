
public class p01 {
	public static void main(String[] args) {
		for(int i=0;i<32;i++){
			int temp=Integer.valueOf(Integer.toBinaryString(i));
			System.out.printf("%05d\n",temp);
		}
	}
}
