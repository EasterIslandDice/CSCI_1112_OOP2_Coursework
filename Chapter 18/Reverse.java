import java.util.Scanner;

class Reverse {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter a string:");
		String value = input.nextLine();
		input.close();
		
		reverseDisplay(value);
	}
	public static void reverseDisplay(String value){
		if(value.length() == 1)
			System.out.print(value);
		else{
			System.out.print(value.charAt(value.length() - 1));
			reverseDisplay(value.substring(0, value.length() -1));
		}	
	}
	
}