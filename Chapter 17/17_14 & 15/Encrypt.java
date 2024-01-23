import java.util.Scanner;
import java.io.*;

class Untitled {
	public static void main(String[] args) throws IOException{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter target file name:");
		String inputFile = input.next();
		System.out.println("Please enter output file name:");
		String outputFile = input.next();
		
		input.close();
		
		try(
			RandomAccessFile sourceFile = new RandomAccessFile(inputFile, "r");
			RandomAccessFile targetFile = new RandomAccessFile(outputFile, "rw");
		){
			int i = 0;
			sourceFile.seek(0);
			while((i = sourceFile.read()) != -1){
				targetFile.write(((byte)i) +5);
			}
		}
	}
}