import java.util.ArrayList;

/***
 * A class to represent a set of answers from a page
 */
public class AnswerSheet {

	private ArrayList<String> answerList;

	public AnswerSheet() {
		answerList = new ArrayList<String>();
	}
	public void add(int ans) {
		if (ans == 0)
			answerList.add("A");
		else if (ans == 1)
			answerList.add("B");
		else if (ans == 2)
			answerList.add("C");
		else if (ans == 3)
			answerList.add("D");
		else if (ans == 4)
			answerList.add("E");
	}

	public String get(int index) {
		return answerList.get(index);
	}
	public int QuestionAmount() {
		return answerList.size();
	}
}
