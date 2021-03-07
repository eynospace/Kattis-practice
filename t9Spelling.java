import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;


// Zhu Enyao A0206188B
public class t9Spelling{
    public static void main(String[] args){
        Scanner sc = new Scanner (System.in);
        int caseNum = Integer.parseInt(sc.nextLine());
        //implementation to map letter into digits
        Dictionary keypress = new Hashtable();
        keypress.put("a", "2");
        keypress.put("b", "22");
        keypress.put("c", "222");
        keypress.put("d", "3");
        keypress.put("e","33");
        keypress.put("f", "333");
        keypress.put("g", "4");
        keypress.put("h", "44");
        keypress.put("i", "444");
        keypress.put("j", "5");
        keypress.put("k", "55");
        keypress.put("l", "555");
        keypress.put("m", "6");
        keypress.put("n", "66");
        keypress.put("o", "666");
        keypress.put("p", "7");
        keypress.put("q", "77");
        keypress.put("r", "777");
        keypress.put("s", "7777");
        keypress.put("t", "8");
        keypress.put("u", "88");
        keypress.put("v", "888");
        keypress.put("w", "9");
        keypress.put("x", "99");
        keypress.put("y", "999");
        keypress.put("z", "9999");
        keypress.put(" ", "0");
        for (int i=0; i<caseNum; i++){
            String word = sc.nextLine();
            String num = "";
            String lastNum = "0";
            for (int j=0; j<word.length(); j++){
                String letter = Character.toString(word.charAt(j));
                // some operations to obtain digits from char
                Object dig = keypress.get(letter);
                String digits = String.valueOf(dig);
                // add digits to num (if end of num is not equal to start of string of num else add space)
                if (lastNum.equals(Character.toString(digits.charAt(0)))){
                    num += " " + digits;
                    lastNum = Character.toString(num.charAt(num.length()-1));
                }else{num += digits;
                    lastNum = Character.toString(num.charAt(num.length()-1));}
                
            }
            System.out.println("Case #" +(i+1)+ ": " + num);
        }
        sc.close();
    }
}