import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author 24jayashankar
 * @version 12/02/22
 * takes values from a data file and then converts them into a new  base
 */
public class BaseConverter {
    private final String DIGITS = "0123456789ABCDEF";
    /**
     * Convert a String num in fromBase to base-10 int.
     * @param num the original number
     * @param fromBase the original from base
     * @return a base-10 int of num base fromBase
     */
    public int strToInt(String num, String fromBase)    {
        int value = 0, exp = 0;
        for(int i = num.length()-1; i>=0; i--) {
            value += DIGITS.indexOf(num.charAt(i)) * Math.pow(Integer.parseInt(fromBase), exp);
            exp++;
        }

        /*
        start at the right most digit of num
        run a loop for x or i digits of num-- backwards
        `   pull out the character at index i
            find the index of that character in DIGITS
            value += indexOf character * Math.pow(fromBase, exp)
            exp ++ or exp += 1
         */

        return value;
    }

    /**
     * Convert an int num in base-10 to a String that is in a new base
     * @param num is the original base-10 number
     * @param toBase the new base that the number will be returned in
     * @return the base-10 number converted to a new base
     */
    public String intToStr(int num, int toBase) {
        String toNum = new String();
        int index = -1;
        while(num > 0)   {
            index = num % toBase;
            toNum = DIGITS.charAt(index) + toNum;
            num /= toBase;

        }
        return (toNum.equals("")) ? "0" : toNum;

        // ALGORITHM
        // String toNum = ""
        // while base-10 num > 0
        // toNum = find the indexOf(num % toBase) in DIGITS, add to the front of toNum
        // wny add the % result to the front of toNum? we will discuss
        // "decrease" num by toBase

        // if num == 0, return "0"
        // else return toNum

    }

    /**
     * Take values from a data file
     * Convert the values to a new base and write it to converted.dat
     */
    public void inputConvertPrintWrite()    {
        Scanner in = null;
        PrintWriter out = null;
        try {
            in = new Scanner(new File("datafiles/values30.dat"));
            out = new PrintWriter(new File("datafiles/converted.dat"));
            String[] line;
            String output;
            while(in.hasNext()) {
              line = in.nextLine().split("\t");
              //TO WRITE THE FILE:
              // write the original number - STRING
              // tab
              // write the original base - STRING
              // TAB
              // write the converted number
              // tab
              // write the toBase - STRING
              if(Integer.parseInt(line[1]) < 2 || Integer.parseInt(line[1]) > 16)
                  System.out.println("Invalid output base " + line[1]);
              else if(Integer.parseInt(line[2]) < 2 || Integer.parseInt(line[2]) > 16)
                  System.out.println("Invalid input base " + line[2]);
              else {
                  output = intToStr(strToInt(line[0], line[1]), Integer.parseInt(line[2]));
                  out.println(line[0] + "\t" + line[1] + "\t" + output + "\t" +line[2]);
                  System.out.println(line[0] + " base " + line[1] + " = " + output + " base " + line[2] );

              }


              //System.out.println(line[0]);    // String num
              //System.out.println(line[1]);    // String fromBase
              //System.out.println(line[2]);    // String toBase
            }
            if(out != null)
                out.close();
            if(in != null)
                in.close();
            //System.out.println("The revolution will not be televised");
        }
        catch(Exception e)  {
            System.out.println("Something bad happened. Details here: " + e.toString());
        }
    }

    /**
     * Main method for class BaseConverter
     * @param args Command line arguments, if needed
     */
    public static void main(String[] args) {
        BaseConverter bc = new BaseConverter();
        bc.inputConvertPrintWrite();
    }
}
