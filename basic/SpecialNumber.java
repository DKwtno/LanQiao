
public class SpecialNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=100;i<1000;i++){
			int temp=(int) (Math.pow(i/100, 3)+Math.pow((i%100)/10, 3)+Math.pow(i%10, 3));
			if(temp==i)
				System.out.println(i);
		}
	}

}
