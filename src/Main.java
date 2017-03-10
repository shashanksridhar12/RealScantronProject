import java.util.ArrayList;

import processing.core.PImage;

public class Main {
	public static final String PDF_PATH = "/omrtest.pdf";
	public static OpticalMarkReader markReader = new OpticalMarkReader();

	public static void main(String[] args) {
		System.out.println("Welcome!  I will now auto-score your pdf!");
		System.out.println("Loading file..." + PDF_PATH);
		ArrayList<PImage> images = PDFHelper.getPImagesFromPdf(PDF_PATH);

		System.out.println("Scoring all pages...");
		scoreAllPages(images);

		System.out.println("Complete!");

		// Optional: add a saveResults() method to save answers to a csv file
	}

	/***
	 * Score all pages in list, using index 0 as the key.
	 * 
	 * NOTE: YOU MAY CHANGE THE RETURN TYPE SO YOU RETURN SOMETHING IF YOU'D
	 * LIKE
	 * 
	 * @param images
	 *            List of images corresponding to each page of original pdf
	 */
	private static void scoreAllPages(ArrayList<PImage> images) {
		ArrayList<AnswerSheet> scoredSheets = new ArrayList<AnswerSheet>();
		//CSVDATA data = new CSVDATA("/Users/myaccount/Documents/workspace/ScanTronReader2.csv",0 );
		// Score the first page as the key
		AnswerSheet key = markReader.ansProcess(images.get(0));
		// key.print();

		for (int i = 1; i < images.size(); i++) {
			PImage image = images.get(i);

			AnswerSheet answers = markReader.ansProcess(image);
			scoredSheets.add(answers);
		}

		for (int i = 0; i < scoredSheets.size(); i++) {
			AnswerSheet answers = scoredSheets.get(i);
			int score = 0;
			for (int q = 0; q < key.QuestionAmount(); q++)
				if (key.get(q) == answers.get(q))
					score++;
			int percent = (score * 100) / (key.QuestionAmount());
			
	}
}
}