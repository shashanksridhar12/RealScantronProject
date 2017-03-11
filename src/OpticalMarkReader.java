import java.text.Format;
import java.util.ArrayList;



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
		image.loadPixels();
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
		answersheet.SaveToCSV();
		return answersheet;
	}

	private double averageOf(int[] results) {
		int count = 0;
		for (int i = 0; i < results.length; i++) {
			count += results[i];
		}
		return ((double) count) / results.length;
	}

	public ArrayList<Integer> boxCountsToAnswer(int[] pixelCounts, int COUNT_THRESHOLD) {
		ArrayList<Integer> results = new ArrayList<Integer>();

		for (int i = 0; i < pixelCounts.length; i++) {
			if (pixelCounts[i] > COUNT_THRESHOLD) {
				results.add(i);
			}
		}
		return results;
	}

	public static int[] checkRowofBoxes(int startRow, int startCol, int boxWidth, int boxHeight, int boxHSpacing,
			int numBoxes, PImage image, int THRESHOLD) {
		int[] results = new int[numBoxes];

		for (int i = 0; i < numBoxes; i++) {
			results[i] = countBlackPixels(startRow, startCol + i * boxHSpacing, boxWidth, boxHeight, image, THRESHOLD);
		}
		return results;
	}

	public static int countBlackPixels(int startRow, int startCol, int width, int height, PImage image, int THRESHOLD) {
		int count = 0;
		for (int row = startRow; row < startRow + height; row++) {
			for (int col = startCol; col < startCol + width; col++) {
				if (getPixelAt(row, col, image) < THRESHOLD) {
					count++;
				}
			}
		}
		return count;
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

	public static int getPixelAt(int r, int c, PImage image) {
		image.loadPixels();
		int index = r * image.width + c;
		return image.pixels[index] & 255;
	}
}
