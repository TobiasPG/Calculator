import java.util.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Main {
    public static ArrayList<String> letters = new ArrayList<>();
    public static void main(String[] args)
    {
        System.out.println("Kenneth change");
        boolean running = true;
        String current;

        JOptionPane.showMessageDialog(null, "How to use: \nInput you calculation without spaces\nAlways Include *, fx no (2)2, use instead (2)*2\nwrite 'exit' to end program", "Calculator.exe", JOptionPane.PLAIN_MESSAGE);

//tissemand fra Mikkel J
        letters.add("a");
        letters.add("b");
        letters.add("c");
        letters.add("d");
        letters.add("e");
        letters.add("f");
        letters.add("g");
        letters.add("h");
        letters.add("i");
        letters.add("j");
        letters.add("k");
        letters.add("l");
        letters.add("m");
        letters.add("n");
        letters.add("o");
        letters.add("p");
        letters.add("q");
        letters.add("r");
        letters.add("s");
        letters.add("t");
        letters.add("u");
        letters.add("v");
        letters.add("x");
        letters.add("y");
        letters.add("z");

        while(running){

            current = JOptionPane.showInputDialog(null, "Enter Calculation:", "Calculator.exe", JOptionPane.PLAIN_MESSAGE);

            if(current.equals("exit"))   //Input for exit Program
            {
                JOptionPane.showMessageDialog(null, "Thank you for using this calculator:", "Calculator.exe", JOptionPane.PLAIN_MESSAGE);

                running = false;
            }
            else
            {
                ArrayList<String> line = new ArrayList<>(Arrays.asList(current.split("")));



                //Collect Numbers
                for(int i = 0; i < line.size(); i++){
                    for(int n = 0; n < 9; n++) {
                        try{ //Causes an error because I change the length of the arraylist within the for loop at the end.
                            if (line.get(i).charAt(0) == (("" + n).charAt(0)) || line.get(i).charAt(0) == ((".").charAt(0))){
                                if(i > 0){
                                    for(int m = 0; m < 9; m++) {
                                        if (line.get(i-1).charAt(0) == (("" + m).charAt(0)) || line.get(i-1).charAt(0) == ((".").charAt(0))) {
                                            line.set(i, line.get(i-1) + line.get(i));
                                            line.remove(i-1);
                                            i--;
                                        }
                                    }
                                }
                            }
                        }
                        catch(Exception e){
                            break;
                        }
                    }
                }


                //Collect Letters
                for(int j = 0; j < line.size(); j++){
                    try{ //Causes an error because I change the length of the arraylist within the for loop at the end.
                            if (isLetter(line.get(j).charAt(0), letters)){
                                try{
                                        if (isLetter(line.get(j-1).charAt(0), letters)) {
                                            line.set(j, line.get(j-1) + line.get(j));
                                            line.remove(j-1);
                                            j--;
                                        }

                                }
                                catch(Exception e){
                                    System.out.print(" ");
                                }
                            }
                        }
                        catch(Exception e){
                            break;
                        }

                }





                System.out.println(line + " =");

                //Calculate from PEMDAS

                while(parentheses(line)){
                    parentheses(line);
                }
                while(textOperators(line)){
                    textOperators(line);
                }
                while(power(line)){
                    power(line);
                }
                while(multiply(line)){
                    multiply(line);
                }
                while(division(line)){
                    division(line);
                }
                while(addition(line)){
                    addition(line);
                }
                while(subtraction(line)){
                    subtraction(line);
                }


                JOptionPane.showMessageDialog(null, current + "\n=  " + line.get(0), "Calculator.exe", JOptionPane.PLAIN_MESSAGE);


            }
        }
    }
    public static boolean isLetter(char c, ArrayList<String> letters){
        for(int alp = 0; alp < letters.size(); alp++){
            if(letters.get(alp).charAt(0) == c){
                return true;
            }
        }
        return false;
    }
    public static boolean textOperators(ArrayList<String> line){
        for(int i = 0; i < line.size(); i++) {
            if(isLetter(line.get(i).charAt(0), letters)) {
                if(!line.get(i+1).equals("(")){
                    switch (line.get(i))
                    {
                        case "pi":
                            String pi = "3.1415926535";
                            line.set(i, pi);
                            break;
                        case "cos":
                            line.set(i+1, (Math.cos(Double.parseDouble(line.get(i+1))))+"");
                            line.remove(i);
                            break;
                        case "invcos":
                        case "arccos":
                        case "acos":
                            line.set(i+1, (Math.acos(Double.parseDouble(line.get(i+1))))+"");
                            line.remove(i);
                            break;
                        case "sin":
                            line.set(i+1, (Math.sin(Double.parseDouble(line.get(i+1))))+"");
                            line.remove(i);
                            break;
                        case "invsin":
                        case "arcsin":
                        case "asin":
                            line.set(i+1, (Math.asin(Double.parseDouble(line.get(i+1))))+"");
                            line.remove(i);
                            break;
                        case "tan":
                            line.set(i+1, (Math.tan(Double.parseDouble(line.get(i+1))))+"");
                            line.remove(i);
                            break;
                        case "invtan":
                        case "arctan":
                        case "atan":
                            line.set(i+1, (Math.atan(Double.parseDouble(line.get(i+1))))+"");
                            line.remove(i);
                            break;
                        case "sqrt":
                            line.set(i+1, (Math.sqrt(Double.parseDouble(line.get(i+1))))+"");
                            line.remove(i);
                            break;
                        case "abs":
                            line.set(i+1, (Math.abs(Double.parseDouble(line.get(i+1))))+"");
                            line.remove(i);
                            break;
                        case "log":
                            line.set(i+1, (Math.log(Double.parseDouble(line.get(i+1))))+"");
                            line.remove(i);
                            break;
                    }

                    //line.set(i, ""+(Math.ceil(Double.parseDouble(line.get(i))*1000))/1000);
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean parentheses(ArrayList<String> line){
        ArrayList<String> par = new ArrayList<>(line.size());
        for(int i = 0; i < line.size(); i++)
        {
            if(line.get(i).equals(")") ){
                for(int n = i-1; n >= 0; n--){
                    if(line.get(n).equals("(")){
                        while(textOperators(par)){
                            textOperators(par);
                        }
                        while(power(par)){
                            power(par);
                        }
                        while(multiply(par)){
                            multiply(par);
                        }
                        while(division(par)){
                            division(par);
                        }
                        while(addition(par)){
                            addition(par);
                        }
                        while(subtraction(par)){
                            subtraction(par);
                        }
                        line.set(n, par.get(0));
                        for(int del = i; del > n; del--){
                            line.remove(del);
                        }
                        return true;
                    }
                    else
                    {
                        par.add(0, line.get(n));
                    }
                }
            }
        }
        return false;
    }
    public static boolean power(ArrayList<String> line){
        for(int i = 0; i < line.size(); i++){
            if(line.get(i).equals("^"))
            {
                if(line.get(i-1).equals("(") || line.get(i-1).equals(")")){
                    System.out.println("Tries multiplication before ( ) are removed");
                }
                else{
                    if(isNumber(line.get(i-1)))
                    {
                        if(line.get(i+1).equals("(") || line.get(i+1).equals(")")){
                            System.out.println("Tries multiplication before ( ) are removed");
                        }
                        else
                        {
                            if(isNumber(line.get(i+1))){
                                double ex1 = Double.parseDouble(line.get(i-1));
                                double ex2 = Double.parseDouble(line.get(i+1));
                                line.set(i, (Math.pow(ex1, ex2))+"");
                                line.remove(i+1);
                                line.remove(i-1);
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
    public static boolean multiply(ArrayList<String> line){
        for(int i = 0; i < line.size(); i++){
            if(line.get(i).equals("*"))
            {
                if(line.get(i-1).equals("(") || line.get(i-1).equals(")")){
                    System.out.println("Tries multiplication before ( ) are removed");
                }
                else{
                    if(isNumber(line.get(i-1)))
                    {
                        if(line.get(i+1).equals("(") || line.get(i+1).equals(")")){
                            System.out.println("Tries multiplication before ( ) are removed");
                        }
                        else
                        {
                            if(isNumber(line.get(i+1))){
                                double ex1 = Double.parseDouble(line.get(i-1));
                                double ex2 = Double.parseDouble(line.get(i+1));
                                line.set(i, (ex1*ex2)+"");
                                line.remove(i+1);
                                line.remove(i-1);
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
    public static boolean division(ArrayList<String> line){
        for(int i = 0; i < line.size(); i++){
            if(line.get(i).equals("/"))
            {
                if(line.get(i-1).equals("(") || line.get(i-1).equals(")")){
                    System.out.println("Tries multiplication before ( ) are removed");
                }
                else{
                    if(isNumber(line.get(i-1)))
                    {
                        if(line.get(i+1).equals("(") || line.get(i+1).equals(")")){
                            System.out.println("Tries multiplication before ( ) are removed");
                        }
                        else
                        {
                            if(isNumber(line.get(i+1))){
                                double ex1 = Double.parseDouble(line.get(i-1));
                                double ex2 = Double.parseDouble(line.get(i+1));
                                line.set(i, (ex1/ex2)+"");
                                line.remove(i+1);
                                line.remove(i-1);
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
    public static boolean addition(ArrayList<String> line){
        for(int i = 0; i < line.size(); i++){
            if(line.get(i).equals("+"))
            {
                if(line.get(i-1).equals("(") || line.get(i-1).equals(")")){
                    System.out.println("Tries multiplication before ( ) are removed");
                }
                else{
                    if(isNumber(line.get(i-1)))
                    {
                        if(line.get(i+1).equals("(") || line.get(i+1).equals(")")){
                            System.out.println("Tries multiplication before ( ) are removed");
                        }
                        else
                        {
                            if(isNumber(line.get(i+1))){
                                double ex1 = Double.parseDouble(line.get(i-1));
                                double ex2 = Double.parseDouble(line.get(i+1));
                                line.set(i, (ex1+ex2)+"");
                                line.remove(i+1);
                                line.remove(i-1);
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
    public static boolean subtraction(ArrayList<String> line){
        for(int i = 0; i < line.size(); i++){
            if(line.get(i).equals("-"))
            {
                if(line.get(i-1).equals("(") || line.get(i-1).equals(")")){
                    System.out.println("Tries multiplication before ( ) are removed");
                }
                else{
                    if(isNumber(line.get(i-1)))
                    {
                        if(line.get(i+1).equals("(") || line.get(i+1).equals(")")){
                            System.out.println("Tries multiplication before ( ) are removed");
                        }
                        else
                        {
                            if(isNumber(line.get(i+1))){
                                double ex1 = Double.parseDouble(line.get(i-1));
                                double ex2 = Double.parseDouble(line.get(i+1));
                                line.set(i, (ex1-ex2)+"");
                                line.remove(i+1);
                                line.remove(i-1);
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
    public static boolean isNumber(String str){
            for(int n = 0; n < 9; n++) {
                if ((str.charAt(0) == (("" + n).charAt(0)))) {
                    return true;
                }
                if(str.charAt(0) == "-".charAt(0)){
                    return true;
                }
            }
            return false;
    }
}