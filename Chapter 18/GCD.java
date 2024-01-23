import java.util.Scanner;

class GCD {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter two digits separated by spaces:");
		int a = input.nextInt();
		int b = input.nextInt();
		input.close();
		
		System.out.println("The greatest common divisor is " + gcd(a, b));
	}
	
	static int gcd(int a, int b){
		if (a == b){
			return a;
		}
		
		if (a > b){
			return gcd(a - b, b);
		}
		
		return gcd(a, b-a);
	}
}