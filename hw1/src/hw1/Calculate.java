package hw1;
import java.util.*;

public class Calculate {
	//字符串转数组
	public static ArrayList<String> getStringList(String str){
		ArrayList<String> equation = new ArrayList<String>();
		String digit = "";
		for (int i = 0; i < str.length(); i++){
			if (Character.isDigit(str.charAt(i))){
				digit += str.charAt(i);
			} else {
				if (digit != ""){
					equation.add(digit);
				}
				equation.add(String.valueOf(str.charAt(i)));
				digit = "";
			}
		}
		if (digit != ""){
			equation.add(digit);
		}
		return equation;
	}
	
	//中缀转后缀
	
	public static ArrayList<String> InOrderToPostOrder(ArrayList<String> inOrderList){
		ArrayList<String> postOrderList = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < inOrderList.size();i++){
			if (Character.isDigit(inOrderList.get(i).charAt(0))){
				postOrderList.add(inOrderList.get(i));
			} else {
				switch (inOrderList.get(i).charAt(0)){
				case '(':
					stack.push(inOrderList.get(i));
					break;
				case ')':
					while (!stack.peek().equals("(")){
						postOrderList.add(stack.pop());
					}
					stack.pop();
					break;
				default:
					while (!stack.isEmpty() && compare(stack.peek(),inOrderList.get(i))){
						postOrderList.add(stack.pop());
					}
					stack.push(inOrderList.get(i));
					break;
				}
			}
		}
		while (!stack.isEmpty()){
			postOrderList.add(stack.pop());
		}
		return postOrderList;
	}
	//比较
	public static boolean compare(String peek,String now){
		if("*".equals(peek) && ("/".equals(now) || "*".equals(now) ||"+".equals(now) ||"-".equals(now))){  
            return true;  
        }else if("/".equals(peek) && ("/".equals(now) || "*".equals(now) ||"+".equals(now) ||"-".equals(now))){  
            return true;  
        }else if("+".equals(peek) && ("+".equals(now) || "-".equals(now))){  
            return true;  
        }else if("-".equals(peek) && ("+".equals(now) || "-".equals(now))){  
            return true;  
        }  
        return false;  
	}
	//计算后缀表达式
	public static Integer calc(ArrayList<String> postOrderList){
		Stack stack = new Stack();
		for (int i = 0; i < postOrderList.size(); i++){
			if(Character.isDigit(postOrderList.get(i).charAt(0))){  
                stack.push(Integer.parseInt(postOrderList.get(i)));  
            }else{  
                Integer b = (Integer)stack.pop();  
                Integer a = (Integer)stack.pop();  
                Integer res = 0;  
                switch (postOrderList.get(i).charAt(0)) {  
                case '+':  
                    res = a + b;  
                    break;  
                case '-':  
                    res = a - b;  
                    break;  
                case '*':  
                    res = a * b;  
                    break;  
                case '/':  
                    res = a / b;
                    break;  
                }  
                stack.push(res);
            }  
        }  
        return (Integer)stack.pop();
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String tmp= scan.nextLine();
		String s = new String();
		while (!tmp.isEmpty()) {
			s = "";
			for (int i = 0; i < tmp.length(); i++){
				if(tmp.charAt(i)!=' '){
					s += tmp.charAt(i);
				}
			}
			ArrayList result = getStringList(s); 
			result = InOrderToPostOrder(result);   
			int answer = calc(result);   
			System.out.println(answer);
			tmp= scan.nextLine();
		}
	}
}
