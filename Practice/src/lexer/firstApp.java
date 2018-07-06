package lexer;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.html.HTMLDocument.Iterator;

public class firstApp {
    static Map < String, Integer > hash = new HashMap < String, Integer > ();
    static Stack < Integer > goSub = new Stack < Integer > ();
    static Scanner sc = new Scanner(System.in);
    static int count_line;
    static ArrayList tokens = new ArrayList();
    static ArrayList lineTokenization = new ArrayList();
    static ArrayList tokens_exp = new ArrayList();
    static ArrayList tokens_exp_left = new ArrayList();
    static ArrayList tokens_exp_right = new ArrayList();
    static ArrayList tokens_exp_print = new ArrayList();
    static ArrayList tokens_exp_println = new ArrayList();
    static LinkedHashMap < Integer, String > lineNoAndData = new LinkedHashMap < Integer, String > ();
    static LinkedHashMap < Integer, Integer > lineNoAndData2 = new LinkedHashMap < Integer, Integer > ();
    static BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
    static String currentToken;
    static String currToken;
    static int goto_no;
    static java.util.Iterator token;

    static java.util.Iterator line_Token;
    static java.util.Iterator line_Token2;
    static int value = 0;
    static int goto_i = 0;
    static boolean flag = false;
    static int i;
    public static void main(String[] args) throws IOException {
            int lineNo = 0;
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("inputfile.txt")); //reading the code from input file
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            String line = ""; //declared a string line with value null
            while ((line = br.readLine()) != null) // reading the code from input line-by-line
            {
                lineNo++;
                // An ArrayList is created with tokens.
                String lexer = "lexing";
                tokenizeInput(line, lexer);
                tokens.add("$");
            } //while
            token = tokens.iterator();

            /* while(token.hasNext()){
              		
              			System.out.println(token.next());
              		}*/
            int abc = 0;
            while (token.hasNext()) { // search if there is any other token left.
                //System.out.println(token.next());
                currentToken = (String) token.next();
                // System.out.println(currentToken);
                abc++;
                StringBuilder lineTokens = new StringBuilder(30);
                if (currentToken.matches("[0-9]*")) {

                    int lineNumber = Integer.parseInt(currentToken);
                    currentToken = token.next().toString();
                    //System.out.println(currentToken);
                    while (true) {
                        if (currentToken.equals("$")) {
                            //System.out.println(currentToken);
                            lineTokens.append(currentToken);
                            break;
                        } else {
                            //System.out.println(currentToken);
                            lineTokens.append(currentToken + " ");
                            currentToken = token.next().toString();
                        }
                    }

                    /*  String check=lineTokens.toString();
                   if(check.contains("PRINT") || check.contains("PRINTLN")|| check.contains("print") || check.contains("println")){
                	  
                	 // System.out.println(check);
                	  tokenizeInput(check,"parser");
                	  line_Token2=lineTokenization.iterator();
                	  while(line_Token2.hasNext()){
                		  //System.out.println(line_Token2.next());
                	  }
                	  
                	  lineTokenization.clear();
                   }*/

                    lineNoAndData.put(lineNumber, lineTokens.toString());

                    lineNoAndData2.put(value++, lineNumber);
                    //System.out.println(lineNumber+" "+lineNoAndData2.get(lineNumber));
                } else if (currentToken.equals("$")) {

                } else {
                    System.err.println("Error at line  " + abc + ": Please enter line number only in digits");
                    System.exit(0);
                }
            }

            for (i = 0; i <= lineNoAndData2.size(); i++) {
                //System.out.println("i :"+ i);
                //System.out.println(lineNoAndData.get(lineNoAndData2.get(i)));
                //System.out.println(x);
                tokenizeInput(lineNoAndData.get(lineNoAndData2.get(i)), "parser");
                count_line = lineNoAndData2.get(i);
                //System.out.println(count_line);
                line_Token = lineTokenization.iterator();

                while (line_Token.hasNext()) {


                    currToken = line_Token.next().toString();
                    //System.out.println(currToken);

                    if (currToken.equalsIgnoreCase("PRINTLN")) {
                        print_method(true);

                    } else if (currToken.equalsIgnoreCase("PRINT")) {

                        print_method(false);
                    } else if (currToken.equalsIgnoreCase("IF")) {
                        // int if_val=if_method();
                        if (if_method()) {
                            if (flag) {
                                i = goto_i;
                                flag = false;
                                break;
                            } else
                                continue;
                        } else {
                            break;
                        }
                    } else if (currToken.equalsIgnoreCase("GOTO")) {
                        currToken = (String) line_Token.next();
                        //System.out.println(currToken);
                        if (currToken.matches("([0-9]*)")) {
                            int val = Integer.parseInt(currToken);
                            //System.out.println(val);
                            if (lineNoAndData.containsKey(val)) {
                                //System.out.println(val);
                                i = goto_st(val);
                                break;
                            } else {
                                System.err.println("Error at line  at " + count_line + ": Line Number " + val + " does not exists");
                                System.exit(1);
                            }
                        }
                    } else if (currToken.equalsIgnoreCase("INTEGER")) {
                        //System.out.println("yes");
                        integer_method();
                    } else if (currToken.equalsIgnoreCase("GOSUB")) {
                        i = goSub_method(i);
                        break;
                    } else if (currToken.equalsIgnoreCase("LET")) {
                        //System.out.println("1");
                        let_method();
                    } else if (currToken.equalsIgnoreCase("RET")) {
                        //System.out.println("1111");
                        i = ret_method();
                        break;
                    } else if (currToken.equalsIgnoreCase("INPUT")) {
                        input_method();
                    } else if (currToken.equalsIgnoreCase("end")) {
                        if (!goSub.empty()) {
                            System.err.println("Error at line  " + count_line + ": Did not find RET for GOSUB");
                        }
                        System.exit(1);
                    } else {
                        System.err.println("Error at line  " + count_line + ": Found " + currToken + ". Expected println,print,integer,let, input, if, goto, gosub, end.");
                        System.exit(1);
                    }
                    //}//while string tokenizer
                }
                lineTokenization.clear();




            } //for loop

        } //main method



    private static int ret_method() {
        try {
            int goSub_ret = goSub.pop();
            return goSub_ret;
            //System.out.println(goSub.empty());}
        } catch (EmptyStackException qw) {
            System.err.println("Error at line  " + count_line + ": No GOSUB for ret");
            System.exit(1);
            return 0;
        }

    }



    private static int goSub_method(int goSubLineNumber) {
        currToken = line_Token.next().toString();
        int lineNumber = 0;
        int val1 = 0;
        boolean flag = false;
        if (currToken.matches("([0-9]*)")) {
            val1 = Integer.parseInt(currToken);

            for (Map.Entry < Integer, Integer > entry: lineNoAndData2.entrySet()) {
                if (Objects.equals(val1, entry.getValue())) {
                    lineNumber = entry.getKey();
                    // System.out.println(lineNumber);
                    goSub.push(goSubLineNumber);
                    --lineNumber;
                    flag = true;
                }

            }
            if (!flag) {
                System.err.println("Error at line  " + count_line + " : Line Number " + val1 + " does not exists");
                System.exit(1);
            }
        }
        return lineNumber;
    }



    private static int goto_st(int val) throws IOException {
        //lineNoAndData.clear();
        line_Token.remove();
        int lineNumber = 0;
        //if(tokens.hasNext())
        //System.out.println("Hello");
        //token= tokens.iterator();
        /*while(token.hasNext()){
        currentToken = (String) token.next();
        //System.out.println(currentToken);
			
        if(currentToken.matches("[0-9]*")){
        	lineNumber = Integer.parseInt(currentToken);
        	StringBuilder lineTokens = new StringBuilder(30);
        	currentToken=token.next().toString();
        	//System.out.println(currentToken);
        	while(true){
        		if(currentToken.equals("$"))
        		{
        			//System.out.println(currentToken);
        			lineTokens.append(currentToken);
        			break;
        		}
        		else
        		{
        			//System.out.println(currentToken);
        			lineTokens.append(currentToken+" ");
        			currentToken=token.next().toString();	
        		}
        	}*/
        /*if(lineNumber >= val){
        	lineNoAndData.put(lineNumber,lineTokens.toString());
        	//System.out.println(lineNumber+" "+lineNoAndData.get(lineNumber));
        	
        }*/

        //}
        //	}
        //System.out.println(val);

        for (Map.Entry < Integer, Integer > entry: lineNoAndData2.entrySet()) {
            if (Objects.equals(val, entry.getValue())) {
                lineNumber = entry.getKey();
                --lineNumber;
            }

        }
        //lineNoAndData.containsKey(val);
        return lineNumber;

    }

    private static boolean if_method() throws IOException {
        currToken = line_Token.next().toString();
        String rel_op = null;
        //System.out.println(currToken);
        while ((!currToken.equals("<")) && (!currToken.equals("=")) && (!currToken.equals(">"))) {
            //System.out.println(currToken);
            tokens_exp_left.add(currToken);
            try {
                currToken = line_Token.next().toString();
            } catch (NoSuchElementException e) {
                System.err.println("Error at line  " + count_line + ": Incorrect or Missing Operator. Expecting >, = or <");
                System.exit(1);
            }
        }

        rel_op = currToken;
        currToken = line_Token.next().toString();

        while (!currToken.equals("then")) {
            //System.out.println(currToken);
            tokens_exp_right.add(currToken);
            try {
                currToken = line_Token.next().toString();
            } catch (NoSuchElementException e) {
                System.err.println("Error at line  " + count_line + ": Missing then");
                System.exit(1);
            }
            //System.out.println(currToken);
        }
        String exp_left = String.valueOf(expression(tokens_exp_left));
        String exp_right = String.valueOf(expression(tokens_exp_right));
        tokens_exp.add(exp_left);
        tokens_exp.add(rel_op);
        tokens_exp.add(exp_right);




        //boolean flag= if_expression();
        if (if_expression()) {
            if (currToken.equalsIgnoreCase("then")) {
                currToken = line_Token.next().toString();
                tokens_exp.clear();
                tokens_exp_left.clear();
                tokens_exp_right.clear();
                call_Statements();
                return true;
            }
        }
        tokens_exp.clear();
        tokens_exp_left.clear();
        tokens_exp_right.clear();
        return false;
    }

    private static boolean if_expression() {
        // TODO Auto-generated method stub
        int size = tokens_exp.size();
        //System.out.println(size);
        if (size < 3 || size > 3) {
            System.err.println("Error at line  " + count_line + ": Missing expression or operator in if else");
            System.exit(1);
        }
        //System.out.println(size);
        int count = 0;
        String[] token = new String[size];
        java.util.Iterator a = tokens_exp.iterator();
        for (int i = 0; i < size; i++) {
            token[i] = (String) a.next();
            //System.out.println(token[i]);
        }
        Stack < Integer > operands = new Stack < Integer > ();

        Stack < String > operators = new Stack < String > ();
        for (int i = 0; i < size; i++) {
            //System.out.println(token[i]);
            /*if (token[i].matches("([a-z|A-Z][a-z|A-Z|0-9|_]*)")) {
            	if (hash.containsKey(token[i])) {
            		//sSystem.out.println("TRUE 1");
            		int tokenValue = hash.get(token[i]);
            		//System.out.println(token[i]+ ": " +tokenValue);
            		operands.push(tokenValue);
            		
            		//System.out.println(operands.peek());

            	}
            	else {
            		System.err.println("Error at line : Variable " + token[i] + " not declared");
            		System.exit(1);
            	}
            }*/
            if (token[i].matches("-?([0-9]*)")) {
                int val1 = Integer.parseInt(token[i]);
                operands.push(val1);

            } else if (token[i].equals("<") || token[i].equals(">") || token[i].equals("=")) {

                operators.push(token[i]);
                //System.out.println(operators.peek());
            }

        }

        return evaluate_if_expression(operators.pop(), operands.pop(), operands.pop());




    }

    private static boolean evaluate_if_expression(String op, Integer op2, Integer op1) {
        // TODO Auto-generated method stub

        //System.out.println(op1+ " "+ op+" "+op2);

        switch (op) {
            case "<":
                if (op1 < op2) {
                    //System.out.println("True for <");
                    return true;
                }
                break;
            case "=":
                if (op1 == op2) {
                    //System.out.println("True for  =");
                    return true;
                }
                break;
            case ">":
                if (op1 > op2) {
                    //System.out.println("True for >");
                    return true;
                }
                break;
            default:
                System.out.println("Error");
                break;
        }

        //System.out.println(flag);
        return false;
    }

    private static void call_Statements() throws IOException {
        // TODO Auto-generated method stub

        if (currToken.equalsIgnoreCase("PRINTLN")) {
            print_method(true);

        } else if (currToken.equalsIgnoreCase("PRINT")) {
            print_method(false);
        } else if (currToken.equalsIgnoreCase("IF")) {
            if_method();
        } else if (currToken.equalsIgnoreCase("INTEGER")) {
            integer_method();
        } else if (currToken.equalsIgnoreCase("LET")) {
            //System.out.println("1");
            let_method();
        } else if (currToken.equalsIgnoreCase("INPUT")) {
            input_method();
        } else if (currToken.equalsIgnoreCase("GOTO")) {
            goto_method();
        } else if (currToken.equalsIgnoreCase("end")) {
            System.exit(1);
        }
    }


    private static void goto_method() throws IOException {
        // TODO Auto-generated method stub
        currToken = (String) line_Token.next();
        if (currToken.matches("([0-9]*)")) {
            int val1 = Integer.parseInt(currToken);
            if (lineNoAndData.containsKey(val1)) {
                goto_i = goto_st(val1);
                flag = true;
            } else {
                System.err.println("Error at line  " + count_line + ": Line Number " + val1 + " does not exists");
                System.exit(1);
            }
            //System.out.println(goto_i);

        }

    }

    private static void input_method() throws IOException {
        // TODO Auto-generated method stub
        ArrayList < String > inp_var = new ArrayList < String > ();
        String currToken_name = (String) line_Token.next();

        if (currToken_name.matches("([a-zA-Z])([a-zA-Z0-9_]*)")) {
            if (hash.containsKey(currToken_name)) {
                inp_var.add(currToken_name);
                String currToken_comma = (String) line_Token.next();
                while (currToken_comma.equals(",")) {
                    String inp = (String) line_Token.next();
                    if (hash.containsKey(inp)) {
                        inp_var.add((String) inp);
                        //System.out.println(val);			
                    } else {
                        System.err.println("Error at line  " + count_line + ": Variable " + inp + " not declared.");
                        System.exit(0);
                    }
                    currToken_comma = (String) line_Token.next();
                }
                String[] inps = null;
                String inp_line = buf.readLine();
                if (inp_line != null) inps = inp_line.trim().split(" ");
                if (inps.length == inp_var.size()) {
                    for (int i = 0; i < inps.length; i++) {
                        try {
                            hash.put(inp_var.get(i), Integer.parseInt(inps[i]));
                        } catch (NumberFormatException ne) {
                            System.err.println("Error at line  " + count_line + ": Please enter integers only for input");
                            System.exit(1);
                        }
                        //System.out.println(inp_var.get(i));
                    }
                } else {
                    System.err.println("Error at line  " + count_line + ": Provide proper number of inputs and seperated by space");
                    System.exit(1);
                }

            } else {
                System.err.println("Error at line  " + count_line + ": Varibale " + currToken_name + " not defined.");
                System.exit(1);
            }
        }
    }

    private static void let_method() {
        // TODO Auto-generated method stub
        String currToken_name = (String) line_Token.next();
        if (currToken_name.matches("([a-zA-Z][a-zA-Z0-9_]*)")) {
            //System.out.println("2");
            if (hash.containsKey(currToken_name)) {
                //System.out.println("3");
                String currToken_equals = (String) line_Token.next();
                if (currToken_equals.equals("=")) {
                    String next_token = (String) line_Token.next();
                    while (!next_token.equals("$")) {
                        //System.out.println(next_token);
                        tokens_exp.add(next_token);
                        //System.out.println(tokens[i]);
                        next_token = (String) line_Token.next();

                    }

                    int a = expression(tokens_exp);
                    hash.put(currToken_name, a);
                    tokens_exp.clear();
                    //for(int a=0;a<tokens.length;a++)
                    //System.out.println(tokens[a]);

                } else {
                    System.err.println("Error at line  " + count_line + ": Expected = ");
                    System.exit(1);
                }
            } else {
                System.err.println("Error at line  " + count_line + ": Variable " + currToken_name + " not defined");
                System.exit(1);
            }

        } else {
            System.err.println("Error at line  " + count_line + ": Expected a varibale after let");
            System.exit(1);
        }
    }

    private static void integer_method() {

        currToken = (String) line_Token.next();
        if (currToken.matches("[0-9]*")) {
            System.err.println("Error at line  " + count_line + ": Varible cannot start with integer");
            System.exit(1);
        }
        if (currToken.matches("([a-zA-Z][a-zA-Z0-9_]*)")) {
            if (!hash.containsKey(currToken)) {
                hash.put(currToken, 0);
                //System.out.println(currToken);
                currToken = (String) line_Token.next();
                if (!currToken.equals(",")) {
                    System.err.println("Error at line  " + count_line + ": Expected , between variables");
                    System.exit(1);
                }
                while (currToken.equals(",")) {

                    currToken = (String) line_Token.next();
                    if (currToken.matches("[0-9]*")) {
                        System.err.println("Error at line  " + count_line + ": Varible cannot start with integer");
                        System.exit(1);
                    }
                    //System.out.println(currToken);
                    if (currToken.matches("([a-zA-Z][a-zA-Z0-9_]*)")) {
                        addToHashMap(currToken);
                        currToken = (String) line_Token.next();
                        //System.out.println();

                        if (!currToken.equals(",")) {
                            if (currToken.equals("$")) {
                                break;
                            }
                            System.err.println("Error at line  " + count_line + ": Expected , between variables");
                            System.exit(1);
                        }
                    }
                }
            } else {
                System.err.println("Error at line  " + count_line + ": Variable " + currToken + " already defined.");
                System.exit(1);
            }
        } else {
            System.err.println("Error at line : " + count_line + ": Please enter variable in proper format");
            System.exit(1);
        }
    }

    private static void print_method(boolean println) {

        while (!currToken.equals("$")) { //System.out.println(currToken);

            currToken = (String) line_Token.next();
            // System.out.println(currToken);
            if (currToken.startsWith("\"")) {
                //if (!currToken.contains(",")) 
                int length = currToken.toCharArray().length;
                //System.out.println(length);
                if (length == 1) {
                    System.err.println("Error at line " + count_line + ": Please enter appropriate println statement");
                    System.exit(1);
                }
                System.out.print(currToken.substring(1, currToken.length() - 1));
                /*else {
                    System.err.println("Error at line : Incorrect Quotes");
                    System.exit(1);
                }*/
            } else if (currToken.equals(",")) {


            } else if (currToken.matches("([a-zA-Z][a-zA-Z0-9_]*)") || currToken.startsWith("(") || currToken.matches("[0-9]*")) {
                while (!currToken.equals("$") || !currToken.equals(",")) {
                    //System.out.println(currToken);
                    //if(!currToken.equals())
                    tokens_exp_print.add(currToken);
                    currToken = line_Token.next().toString();
                    if (currToken.equals(",") || currToken.equals("$")) {
                        break;
                    }
                }

                int a = expression(tokens_exp_print);
                System.out.print(a);
                tokens_exp_print.clear();

            }
            /*else if(!currToken.equals("$")||!currToken.equals(',')){
                        	System.err.println("Error at line "+count_line+": Expected either \" at the start or end , a comma',' , defined identifier, or expression");
                        	System.exit(1);
                        }*/

        }
        if (println)
            System.out.println();

    }

    private static void tokenizeInput(String line, String lexer) {

        StringBuilder sb = new StringBuilder();
        String[] p = new String[7];
        p[0] = "(?<Reserved>\\bPRINTLN\\b|\\bPRINT\\b|\\bINTEGER\\b|\\bINPUT\\b|\\bEND\\b|\\bLET\\b)"; //RESERVED WORDS eg: println print
        p[1] = "(?<Comments> \\/\\/.*)";
        p[2] = "(?<Identifiers>[a-zA-Z][a-zA-Z0-9_]*)";
        p[3] = "(?<Literals>\"[^\"]*\")";
        //Constants eg: 21 54 14215
        p[4] = "(?<Special>[\\[\\]/.$*\\-+=>\"<#()%,@!|&{}])"; //SPECIAL CHAR Eg:-+*/ etc
        //Literals eg: "Hello World"
        p[5] = "(?<Constants>[0-9]+)"; //Identifiers eg: circum radius
        p[6] = "(?<Whitespaces>[\\t\\n\\r\\f ])";

        for (String s: p) {
            sb.append(String.format("|(%s)", s)); //append the token one after another with pipe'|'
        } //for ends here

        Pattern tp = Pattern.compile(new String(sb.substring(1))); // adding the patterns one after another separated by |

        Matcher m = tp.matcher(line);

        while (m.find()) { //searches if a token is matched.

            if ((m.group("Constants") != null)) { //if a constants is found
                if (lexer.equals("lexing"))
                    tokens.add(m.group("Constants")); //add the Constant token to the arraylist
                else
                    lineTokenization.add(m.group("Constants"));
                continue;
            }
            if ((m.group("Whitespaces") != null)) { //if a constants is found

                continue;
            }
            if ((m.group("Comments") != null)) {
                continue;
            } else if ((m.group("Reserved") != null)) { //if a Reserved token(Println/print) is found
                if (lexer.equals("lexing"))
                    tokens.add(m.group("Reserved")); //add the Constant token to the arraylist
                else
                    lineTokenization.add(m.group("Reserved"));
                continue;
            } else if ((m.group("Literals") != null)) { //if a Literals token is found
                if (lexer.equals("lexing"))
                    tokens.add(m.group("Literals")); //add the Constant token to the arraylist
                else
                    lineTokenization.add(m.group("Literals"));
                continue;
            } else if ((m.group("Identifiers") != null)) { //if an Identifier is found
                if (m.group("Identifiers").length() > 32) { //if Identifier has more than 32 char, then print err string
                    System.out.println(String.format("IDENTIFIER %s has more than 32 characters", m.group("Identifiers")));
                    continue;
                } else {
                    if (lexer.equals("lexing"))
                        tokens.add(m.group("Identifiers")); //add the Constant token to the arraylist
                    else
                        lineTokenization.add(m.group("Identifiers"));
                    continue;
                }
            } else if ((m.group("Special") != null)) { //if a special char is found
                if (lexer.equals("lexing"))
                    tokens.add(m.group("Special")); //add the Constant token to the arraylist
                else
                    lineTokenization.add(m.group("Special"));
                continue;
            }
        } // while ends here
    }

    private static int expression(ArrayList tokens_exp) {
        try {

            int size = tokens_exp.size();
            int count = 0;
            String[] token = new String[size];
            java.util.Iterator a = tokens_exp.iterator();
            for (int i = 0; i < size; i++) {
                token[i] = (String) a.next();
                //System.out.println(token[i]);
            }

            Stack < Integer > operands = new Stack < Integer > ();

            Stack < String > operators = new Stack < String > ();

            for (int i = 0; i < size; i++) {
                //System.out.println(currToken);
                if (token[i].matches("([a-z|A-Z][a-z|A-Z|0-9|_]*)")) {
                    if (hash.containsKey(token[i])) {
                        //sSystem.out.println("TRUE 1");
                        int tokenValue = hash.get(token[i]);
                        // System.out.println(token[i]+ ": " +tokenValue);
                        operands.push(tokenValue);
                        count++;
                        //System.out.println(operands.peek());

                    } else {
                        System.err.println("Error at line  " + count_line + ": Variable " + token[i] + " not declared");
                        System.exit(1);
                    }
                } else if (token[i].matches("-?([0-9]*)")) {
                    int val1 = Integer.parseInt(token[i]);
                    operands.push(val1);
                    count++;
                } else if (token[i].equals("(")) {

                    operators.push(token[i]);
                    count++;
                } else if (token[i].equals(")")) {
                    while (!operators.peek().equals("(")) {

                        operands.push(evalulateExp(operators.pop(), operands.pop(), operands.pop()));
                        //System.out.println(operands.peek());
                        count++;
                    }
                    operators.pop();
                } else if (token[i].equals("+") || token[i].equals("-") || token[i].equals("*") || token[i].equals("/")) {
                    while (!operators.empty() && checkForPrec(token[i], operators.peek())) {
                        operands.push(evalulateExp(operators.pop(), operands.pop(), operands.pop()));
                        count++;

                    }
                    operators.push(token[i]);
                    //System.out.println(operators.peek());
                } else {
                    System.err.println("Error at line  " + count_line + ": Incorrect operator. Expected either of + - * / ( )");
                    System.exit(1);
                }

            }

            while (!operators.empty()) {
                operands.push(evalulateExp(operators.pop(), operands.pop(), operands.pop()));
                //System.out.println(operands.peek());
            }
            //System.out.println("Count "+ count);
            return operands.pop();

        } catch (java.util.EmptyStackException e) {

            System.err.println("Error at line  " + count_line + ": Missing Operator or Operand");
            System.exit(1);
            return 0;
        }
    }
    public static boolean checkForPrec(String currToken, String string) {
        if (string.equals("(") || string.equals(")")) return false;
        if ((currToken.equals("*") || currToken.equals("/")) && (string.equals("+") || string.equals("-"))) return false;
        else return true;
    }
    public static int evalulateExp(String string, int b, int a) {
        switch (string) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }

    private static void addToHashMap(Object name) {

        String a = (String) name;
        if (!hash.containsKey(a)) {
            //  System.out.println(a);
            hash.put(a, 0);
        } else {
            System.err.println("Error at line  " + count_line + ": Integer " + a + " already defined.");
            System.exit(1);
        }
    }
}