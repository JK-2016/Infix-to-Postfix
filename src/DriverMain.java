import java.util.Scanner;
import java.util.Stack;

public class DriverMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Infix Expression");
        String input = sc.next();
        System.out.println(input);
        String postfix = infixToPostFix(input);
        System.out.println("PostFix Expression:"+postfix);
    }
public static String infixToPostFix(String inp){
        String postfix = new String();
        int precedenceCurr, precedenceTop;
        char [] charArr = new char[inp.length()];
        Stack<Character> stack = new Stack<Character>();
        charArr=inp.toCharArray();
        for(int i =0; i<charArr.length;i++){
            if(charArr[i] != ')') {
                precedenceCurr = precedence(charArr[i]);
                if(precedenceCurr == 0){
                    postfix = postfix + charArr[i];
                    continue;
                }
                if (stack.isEmpty()) {
                    stack.push(charArr[i]);
                } else {
                    precedenceTop = precedence(stack.peek());
                    if(precedenceTop>precedenceCurr){
                        postfix = postfix + stack.pop();
                        stack.push(charArr[i]);
                    }
                    else{
                        stack.push(charArr[i]);
                    }

                }
            }
            else{
                while(stack.peek()!='('){
                    if(!stack.isEmpty()) {
                        postfix = postfix + stack.pop();
                    }
                    else{
                        System.out.println("Invalid Input, braces donot match");
                        return("INVALID INPUT");
                    }

                }
                stack.pop();
            }
        }
        while(!stack.isEmpty()){
            postfix = postfix + stack.pop();
        }
        return postfix;
     }
    public static int precedence(char ch){
        if(ch == '('){
            return 4;
        }
        else if(ch == '^'){
            return 3;
        }
        else if(ch == '/' || ch == '*'){
            return 2;
        }
        else if (ch == '-' || ch == '+') {
            return 1;
        }
        else{
            return 0;
        }

    }


}
