package hw2;
import java.util.*;
import java.io.*;

class main {
	public ArrayList<String> qus = new ArrayList<String>();
    public ArrayList<String> ans = new ArrayList<String>();
    
	public void readData(String filename) {
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(filename),"UTF-8");
            BufferedReader br = new BufferedReader(read);
            String row;
            while ((row = br.readLine()) != null) {
            	String[] ab = row.split("\\|");
            	this.qus.add(ab[0]);
            	this.ans.add(ab[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args){
		main now = new main();
		String filename = "data.txt";
		now.readData(filename);
		
		Scanner scan = new Scanner(System.in);
		String question = scan.nextLine();
		while (!question.isEmpty()){
			boolean flag = false;
			for (int i = 0; i < now.qus.size(); i++){
				String nowqus = now.qus.get(i);
				if (question.indexOf(nowqus)!=-1){
					System.out.println(now.ans.get(i));
					flag = true;
				}
			}
			if (!flag){
				System.out.println("字典中没有相应解答");
			}
			question = scan.nextLine();
		}
	}
}
