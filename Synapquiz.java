import java.util.ArrayList;
import java.util.Scanner;

public class Synapquiz {
	ArrayList <String>list = new ArrayList<String>();
	int start_Value = 0, step = 1; //풀이 출력을위한 변수.
	int current_Value = 0;
	
	public void init(int val) { // 초기화 및 계산.
		current_Value = val;
		start_Value = 0;
		step = 1;
		list.clear();
		calculate();
	}	
	/*----  리스트에 *,+ 값 추가 : 계산이 끝난후에 풀이를 출력하기 위함.  ----*/	
	// 값 나누기 2 후에 리스트에 "*" 추가. 
	public void divide() { 
		current_Value /= 2;
		list.add("*");
	}
	// 값 빼기 1 , 리스트에 "+" 추가.
	public void minus() {
		current_Value -= 1;
		list.add("+");
	}
	/* 최소의 단계수를 출하는 방법
	 * 1. 2로 나눌 수 있을때 나누기.
	 * 2. 2로 나눌 수 없을때 1 빼기. */
	public void calculate(){ 
		while(true){
			/* current_Value !=2 와 current_Value ==2 조건문을 없애면 2단계 풀이 -> 1 * 2 = 2 
			 * 해당 조건을 없애면 풀이 -> 1 + 1 =  로나오지만 정답에는 영향이 없음.
			 */
			if(current_Value != 0 && current_Value %2 == 0 && current_Value != 2) {     
				divide();
			}
			else if(current_Value != 1 && current_Value %2 == 1 && current_Value != 2)
			{
				minus();
			}
			else if(current_Value == 2) {
				minus();
			}
			/* current_Value가 1이면 마지막 연산하
			 * list에 저장된 부호(*,+값) 역순으로 불러와서 풀이출력하기.
			 */
			else if(current_Value == 1) {
				minus();	
				for(int i = list.size()-1 ; i>=0 ; i--) {
					if(list.get(i) == "+") {
						System.out.println(Integer.toString(step)+"단계 : "+ Integer.toString(start_Value) + " + 1 = " +Integer.toString(start_Value+1));
						start_Value += 1;
						step ++;		
					}
					if(list.get(i) == "*") {
						System.out.println(Integer.toString(step)+"단계 : "+ Integer.toString(start_Value) + " * 2 = " +Integer.toString(start_Value*2));
						start_Value *= 2;
						step ++;			
					}						
				}
				break;
			}
		}	
		//정답 출력하기.
		System.out.println("정답 = "+Integer.toString(list.size())+"단계");
	}
	
	
	
	public static void main(String[] args) {
	
		Synapquiz sq= new Synapquiz(); //인스턴스 생성
		Scanner sc = new Scanner(System.in); 
		String exit_check = "";
		System.out.println("x를 입력하면 종료됩니다.");
		while(true) {
			try {
				System.out.print("N 값을 입력하세요 : ");
				exit_check = sc.next();
				if(exit_check.equals("x")) {
					System.out.println("x를 입력해 종료합니다.");
					break;
				}
				else if(0 < Integer.parseInt(exit_check) && Integer.parseInt(exit_check) <= 1000)
					sq.init(Integer.parseInt(exit_check));
				else
					System.out.println("Error : 1 <= N <= 1000");
			}
			//입력받은 N 값이 x가 아닌 다른 문자인경우 예외처리.
			catch(Exception e) { 
				System.out.println("Error : N must be a number!!");
			}

		}
		sc.close();
	}

}
