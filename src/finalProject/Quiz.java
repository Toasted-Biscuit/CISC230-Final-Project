package finalProject;

/*
 * This stores the quiz questions for the sustainability trivia part of the game.
 * Each question is matched with a list of questions and a correct answer.
 * The correctAnswer is an index for the questions array of the correct answer to the question
 * 0 = A, 1 = B, 2 = C, 3 = D
 */
public enum Quiz {
	// Quiz questions
	QUESTION1("Which of the following is NOT a way to conserve energy?", 3, // D
			new String[] {"A. Unplugging devices when not in use",
					  	  "B. Turning off lights when leaving the room",
					  	  "C. Keeping windows closed in winter",
					  	  "D. Washing laundry in small loads"},
			"Washing full loads of laundry saves energy since each load of laundry takes energy to wash."),
	
	QUESTION2("What is the name of the series of gardens around campus that attract pollinators?", 1, // B
			new String[] {"A. Bee Garden",
						  "B. Pollinator Path",
						  "C. Butterfly Garden",
						  "D. Bee Path"},
			""),
	
	QUESTION3("Where is there a bike repair station located on campus?", 3, // D
			new String[] {"A. South side of O'Shaughnessy Stadium",
		            	  "B. Bike storage in Frey Residence Hall",
		            	  "C. Bike storage in Schoenecker Hall North",
		            	  "D. All of the above"},
			""),
	
	QUESTION4("Which of the following is NOT a helpful tip for reducing food waste?", 3, // D
			new String[] {"A. Storing produce properly",
		            	  "B. Planning meals at the beginning of the week",
		            	  "C. Only purchasing what you need",
		            	  "D. Storing all items in the refrigerator"},
			""),
	
	QUESTION5("Which of the following is NOT a way to conserve water?", 2, // C
			new String[] {"A. Eating more plant-based meals",
		            	  "B. Taking shorter showers",
		            	  "C. Leaving the sink on while brushing teeth",
		            	  "D. Only washing full loads of laundry"},
			""),
	
	QUESTION6("The Sustainability minor is available to students in which fields?", 3, // D
			new String[] {"A. Arts and Sciences",
						  "B. Engineering",
						  "C. Business",
						  "D. All of the above and more"},
			"The Sustainability minor is available to all majors except Environmental Studies majors."),
	
	QUESTION7("Which of the following are clubs at St. Thomas?", 3, // D
			new String[] {"A. Sustainability Club",
		            	  "B. Earth, Environment, and Society Club",
		            	  "C. Tommie Outdoors",
		            	  "D. All of the above"},
			"All of the above are clubs at St. Thomas."),
	
	QUESTION8("Dining Services reduces waste by...", 3, // D
			new String[] {"A. Recovering leftover food",
		            	  "B. Offering reusable to-go containers",
		            	  "C. Offering reusable cup discounts",
		            	  "D. All of the above"},
			""),
	
	QUESTION9("At St. Thomas, which item is accepted in the blue recycling bins?", 0, // A
			new String[] {"A. Glass",
		            	  "B. Lightbulbs",
		            	  "C. Plastic bags",
		            	  "D. Batteries"},
			"Glass is accepted for recycling in the blue recycling bins around campus.\nThe other items can be disposed of through the specialized recycling bins on campus."),
	
	QUESTION10("All of the following are accepted for organics recycling EXCEPT...", 2,  // C
			new String[] {"A. All food scraps",
		            	  "B. Napkins",
		            	  "C. All paper cups",
		            	  "D. Flower trimmings"},
			"Paper cups must have a BPI logo to be accepted for organics recycling."),
	
	QUESTION11("Where is there a specialized recycling bin located on campus", 3, // D
			new String[] {"A. Outside the Campus Store in Murray-Herric",
						  "B. In the entrance to the Facilities & Design Center",
						  "C. In the create[space]",
						  "All of the above"},
			"Specialized recycling bins can be found at all of those locations.");
	
	// Attributes
	private String question;
	private String[] answers;
	private int correctAnswer;
	private String explanation;
	
	private static Quiz lastQuestion;
	
	// Constructor
	private Quiz (String question, int correctAnswer, String[] answers, String explanation) {
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.answers = answers;
		this.explanation = explanation;
	}
	
	// Returns the question
	public String getQuestion() {
		return question;
	}
	
	// Returns an array of answers
	public String[] getAnswers() {
		return answers;
	}
	
	// Returns the correctAnswer index for answers
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	
	public String getExplanation() {
		return explanation;
	}
	
	// Returns a random question
	public static Quiz getRandomQuestion() {
		java.util.Random rand = new java.util.Random();
		Quiz[] questions;
		
		if (lastQuestion != null) {
			// Creates an array of Quiz questions excluding the last chosen question
			questions = new Quiz[Quiz.values().length - 1];
			int index = 0;
			for (Quiz q : Quiz.values()) {
				if (!q.equals(lastQuestion)) {
					questions[index] = q;
					index++;
				}
			}
		} else {
			// Happens if this is the first question chosen
			questions = Quiz.values();			
		}
		
		Quiz chosen = questions[rand.nextInt(0, questions.length)];
		lastQuestion = chosen;
		return chosen;
	}
}


