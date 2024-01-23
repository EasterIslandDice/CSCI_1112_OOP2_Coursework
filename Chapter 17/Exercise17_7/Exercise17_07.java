/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rmazorow
 */
import java.io.*;

public class Exercise17_07 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (
            ObjectInputStream input = new ObjectInputStream(new 
                BufferedInputStream(new FileInputStream("Exercise17_07.dat")))
        ) {
            while (true) {
                Loan loan = (Loan)input.readObject();
                System.out.println(loan);
                System.out.printf("Total loan amount: $%.2f\n", 
                    loan.getTotalPayment());
                System.out.println();
            }
        }
        catch (EOFException ex) {
        }
    }
}
