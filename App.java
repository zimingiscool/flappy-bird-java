public class App{
	public static void main(String [] args) 
	{
		String q1 = "What is the tallest mountain in the world?\n" +
					"A) Mount Everest\n B) Mount Killimanjaro C) Mount Olympus";
		String q2 = "What is the square root of 9? \n" + 
		"A) 3\n B) 6\n C) 12";
		
		Question [] questions = {
				new Question(q1, "a"),
				new Question(q2, "a")
		}
	}
	
	public static void takeTest(Question [] questions) {
		int score = 0
	}
}