import java.util.ArrayList;

import processing.core.PImage;

/***
 * A class to represent a set of answers from a page
 */
public class AnswerSheet {

	private ArrayList<String> answerList;
	private int score;
	public static OpticalMarkReader markReader = new OpticalMarkReader();
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
	/**public void setScore() {
		ArrayList<AnswerSheet> scoredSheets = new ArrayList<AnswerSheet>();
		CSVDATA data = new CSVDATA("/Users/myaccount/Documents/workspace/ScanTronReader2.csv",0 );
		// Score the first page as the key
		AnswerSheet key = markReader.ansProcess(images.get(0));
		// key.print();

		for (int i = 1; i < images.size(); i++) {
			PImage image = images.get(i);

			AnswerSheet answers = markReader.ansProcess(image);
			answers.setScore();
			scoredSheets.add(answers);
			
		}**/

	public void SaveToCSV(){
		CSVData a = new CSVData("/Users/myaccount/Documents/workspace/ScanTronReader2/lib", new String[]{"percent correct", "percent incorrect", "number correct", "number incorrect"}, 0);	
	}
	
	public String get(int index) {
		return answerList.get(index);
	}
	public int QuestionAmount() {
		return answerList.size();
	}
}
