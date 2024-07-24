package Nuthan.SeleniumFramework;

public class TestTwo {

	public static void main(String[] args) {
		TestTwo testTwo = new TestTwo();
		testTwo.add(1);
		testTwo.sub('a');
	}
	
	public void add(int a)
	{
		System.out.println("integer");
	}
	
	public void sub(char a)
	{
		System.out.println("character");
	}
}
