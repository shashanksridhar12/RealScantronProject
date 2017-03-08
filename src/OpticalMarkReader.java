import processing.core.PImage;

/***
 * Class to perform image processing for optical mark reading
 * 
 */
public class OpticalMarkReader {
	// hard coded values
	private int X = 126, Y = 472;
	private int height = 18, width = 18;
	private int horizontalSpace = 38;
	private int verticalSpace = 37;
	private int totalNumAnswers = 5; 
	private int totalNumQuestions = 100;
	private int numQuestionsPerCol = 25;
	/***
	 * Method to do optical mark reading on page image. Return an AnswerSheet
	 * object representing the page answers.
	 * 
	 * @param image
	 * @return
	 */
	public AnswerSheet ansProcess(PImage image) {
		image.filter(PImage.GRAY);
		AnswerSheet answersheet = new AnswerSheet();
		for (int i = 0; i < totalNumQuestions; i++) {
			int dVal = Integer.MAX_VALUE, darkestIndex = 0;
			int r = i % numQuestionsPerCol;
			for (int j = 0; j < totalNumAnswers; j++) {
				int val = getSum(Y + r * (verticalSpace), X + j * (horizontalSpace), image);
				if (val < dVal) {
					dVal = val;
					darkestIndex = j;
				}
			}
			answersheet.add(darkestIndex);
		}
		return answersheet;
	}
	
	private int getSum(int r, int c, PImage image) {
		int sum = 0;
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				sum += getPixelAt(r + x, c + y, image);
			}
		}
		return sum;
	}

	public int getPixelAt(int row, int col, PImage image) {
		image.loadPixels();
		int index = row * image.width + col;
		return image.pixels[index] & 255;
	}
}
