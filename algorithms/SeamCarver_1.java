import java.awt.Color;

public class SeamCarver_1 {
	private Picture pic;
	private static final int MAX_COLOR = 255;
	private static final double BONDER_ENG = Math.pow(MAX_COLOR, 2) * 3;
	private double [][] twodEnergy;
	
   public SeamCarver_1 (Picture picture) {
	   this.pic = new Picture(picture);
   }
   public Picture picture() {                      // current picture
	   return this.pic;
   }
   public     int width() {                        // width  of current picture
	   return pic.width();
   }
   public     int height() {                        // height of current picture
	   return pic.height();
   }
   public  double energy(int x, int y) {           // energy of pixel at column x and row y in current picture
	   if(x < 0 || x > width() - 1 || y < 0 || y > height() - 1) {
		   throw new java.lang.IndexOutOfBoundsException();
	   } else if( x== 0 || x == width() -1 || y == 0 || y == height() - 1) {
		   return BONDER_ENG;
	   } else {
		   Color c1 = pic.get(x - 1, y);
		   Color c2 = pic.get(x + 1, y);
		   double detaX = Math.pow(c1.getRed() - c2.getRed(), 2) + Math.pow(c1.getGreen() - c2.getGreen(), 2) + Math.pow(c1.getBlue() - c2.getBlue(), 2);
		   
		   c1 = pic.get(x, y -1);
		   c2 = pic.get(x, y +1);
		   double detaY = Math.pow(c1.getRed() - c2.getRed(), 2) + Math.pow(c1.getGreen() - c2.getGreen(), 2) + Math.pow(c1.getBlue() - c2.getBlue(), 2);
		   
		   return detaX + detaY;
	   }
   }
   
   private void transposePic() {
	   Picture tmpPic = new Picture(height(), width());
	   for (int i = 0; i < tmpPic.width(); i++) {
		   for (int j = 0; j < tmpPic.height(); j++) {
			   tmpPic.set(i, j, pic.get(j, i));
		   }
	   }	   
	   pic = tmpPic;
   }

   public   int[] findHorizontalSeam()  {          // sequence of indices for horizontal seam in current picture
	   transposePic();
	   int[] horizontalSeam = findVerticalSeam();
	   transposePic();	   
	   return horizontalSeam;
   }
   public   int[] findVerticalSeam() {             // sequence of indices for vertical   seam in current picture
	   //initla the twodEnergy 2d array energy
	   twodEnergy = new double [width()][height()];
	   for (int i = 0; i < width(); i++) {
		   for (int j = 0; j < height(); j++) {
			   twodEnergy[i][j] = energy(i, j);
		   }
	   }

	   //TODO: consider while width and height of picture is less than 2
	   double [][] distArray = new double [width()][height()];
	   int[][] distPath = new int[width()][height()];
	   for (int i = 0; i < width(); i++) {
		   distArray[i][1] = twodEnergy[i][1];
	   }

	   for (int j = 2; j < height() - 1; j++) {
		   for (int i = 1; i < width() - 1; i++) {
			   if (distArray[i-1][j-1] <= distArray[i][j-1] && distArray[i-1][j-1] <= distArray[i+1][j-1]) {
				   distArray[i][j] = distArray[i-1][j-1] + twodEnergy[i][j];
				   distPath[i][j] = i - 1;
			   } else if (distArray[i][j-1] <= distArray[i+1][j-1] ){
				   distArray[i][j] = distArray[i][j-1] + twodEnergy[i][j];
				   distPath[i][j] = i;
			   } else {
				   distArray[i][j] = distArray[i+1][j-1] + twodEnergy[i][j];
				   distPath[i][j] = i + 1;
			   }			   
		   }
	   }
	   
	   double shortDist = distArray[1][height() - 2];
	   int shortIndex = 0;
	   for (int i = 2; i < width() - 1; i++) {
		   if (distArray[i][height() - 2] < shortDist) {
			   shortDist = distArray[i][height() - 2];
			   shortIndex = i;
		   }
	   }
	   
	   //Now we got the shortIndex in the line above bottom
	   int[] verticalSeam = new int[height()];
	   //hanlde the bottom line
	   verticalSeam[height() - 1] = shortIndex - 1;
	   verticalSeam[height() - 2] = shortIndex;
	   for (int j = height() - 3 ; j > 0; j++) {
		   int xIndex = verticalSeam[j + 1];
		   //distPath[i][j+1] contains the shortpath of i in line pic[?][j]
		   verticalSeam[j] = distPath[xIndex][j+1];
	   }
	   verticalSeam[0] = verticalSeam[1] - 1;

	   return verticalSeam; 
   }
   public    void removeHorizontalSeam(int[] a) {  // remove horizontal seam from current picture
	   transposePic();
	   removeVerticalSeam(a);
	   transposePic();	   
   }
   public    void removeVerticalSeam(int[] a) {    // remove vertical   seam from current picture
	   Picture tmpPic = new Picture(width() - 1, height());
	   
	   for (int i = 0; i < tmpPic.width(); i++) {
		   for (int j = 0; j < tmpPic.height(); j++) {
			   if(i < a[j]) {
				   tmpPic.set(i, j, pic.get(i, j));
			   } else {
				   tmpPic.set(i, j, pic.get(i + 1, j));
			   }
		   }
	   }	
	   
	   pic = tmpPic;
   }

}

