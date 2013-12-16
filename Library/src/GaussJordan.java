/////////////////////////////////////////////////////////////////////////////////////////////
//
//   GaussJordan.java
//   Runs Gauss Jordan Elimination on a matrix
//
//   1. Save this file as GaussJordan.java on any system with the javac compiler, available
//      from Sun Microsystems: http://java.sun.com/javase/downloads/index.jsp
//   2. If you're on a unix system also save the file "Makefile" in the same directory, then
//   3.      Compile by typing "make" or "gmake" at the command line (leave out the quotes).
//   4.      Run the executable by doing "GaussJordan infile outfile", where "infile" is
//           a properly formated (described below) input file.
//   5. Else if you're on any other system, then
//   6.      Compile by typing "javac GaussJordan.java" at the command line (leave out quotes).
//   7.      Run the executable by doing "java GaussJordan infile outfile, where "infile" is
//           a properly formated (described below) input file.
//
//   Note: if "outfile" already exists in your working directory, then the above commands will
//         overwrite it.
//
//   Input file format.
//   The first line of an input file consists of two integers n and m, separated by a space,
//   giving the number of rows and columns, respectively.  The next n lines of the input file
//   specify the n rows of the matrix.  Each line is a space separated list of m real numbers.
//   Note that the number of spaces separating the numbers in each row is irrelevant.
//
//   Example 1  (Save the following lines as a text file called in1.)
//   3 5
//   2 0 4 2   9
//   3 4 0 0  -7
//   0 1 0 5   0
//
//   Example 2  (Save as text file in2.)
//   4 7
//    3.1  4.2  7.9  0.0  0.0  1.1   8.2
//   -6.7  1.2  3.4  5.6  7.8  0.0   0.0
//    9.8  7.6  5.4  3.2  2.1  2.3   4.5
//    6.7  8.8 -9.1  2.5  3.6  4.7  -5.5
//
/////////////////////////////////////////////////////////////////////////////////////////////

import java.io.*;
import java.util.StringTokenizer;

class GaussJordan{

   // swap()
   // swap row i with row k
   // pre: A[i][q]==A[k][q]==0 for 1<=q<j
   static void swap(double[][] A, int i, int k, int j){
      int m = A[0].length - 1;
      double temp;
      for(int q=j; q<=m; q++){
         temp = A[i][q];
         A[i][q] = A[k][q];
         A[k][q] = temp;
      }
   }

   // divide()
   // divide row i by A[i][j]
   // pre: A[i][j]!=0, A[i][q]==0 for 1<=q<j
   // post: A[i][j]==1;
   static void divide(double[][] A, int i, int j){
      int m = A[0].length - 1;
      for(int q=j+1; q<=m; q++) A[i][q] /= A[i][j];
      A[i][j] = 1;
   }

   // eliminate()
   // subtract an appropriate multiple of row i from every other row
   // pre: A[i][j]==1, A[i][q]==0 for 1<=q<j
   // post: A[p][j]==0 for p!=i
   static void eliminate(double[][] A, int i, int j){
      int n = A.length - 1;
      int m = A[0].length - 1;
      for(int p=1; p<=n; p++){
         if( p!=i && A[p][j]!=0 ){
            for(int q=j+1; q<=m; q++){
               A[p][q] -= A[p][j]*A[i][q];
            }
            A[p][j] = 0;
         }
      }
   }

   // printMatrix()
   // print the present state of Matrix A to file out
   static void printMatrix(PrintStream out, double[][] A){
     int n = A.length - 1;
     int m = A[0].length - 1;
      for(int i=1; i<=n; i++){
         for(int j=1; j<=m; j++) out.print(A[i][j] + "  ");
         out.println();
      }
      out.println();
      out.println();
   }

   // main()
   // read input file, initialize matrix, perform Gauss-Jordan Elimination,
   // and write resulting matrices to output file
   public static void main(String[] args) throws IOException {
      int n, m, i, j, k;
      String line;
      StringTokenizer st;

 
      BufferedReader in = new BufferedReader(new FileReader("in.txt"));
      PrintStream out = System.out;

      // read first line of input file
      line = in.readLine();
      st = new StringTokenizer(line);
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());

      // declare A to be of size (n+1)x(m+1) and do not use index 0
      double[][] A = new double[n+1][m+1];

      // read next n lines of input file and initialize array A
      for(i=1; i<=n; i++){
         line = in.readLine();
         st = new StringTokenizer(line);
         for(j=1; j<=m; j++){
            A[i][j] = Double.parseDouble(st.nextToken());
         }
      }

      // close input file
      in.close();

      // print array A to output file
      printMatrix(out, A);

      // perform Gauss-Jordan Elimination algorithm
      i = 1;
      j = 1;
      while( i<=n && j<=m ){

         //look for a non-zero entry in col j at or below row i
         k = i;
         while( k<=n && A[k][j]==0 ) k++;

         // if such an entry is found at row k
         if( k<=n ){

            //  if k is not i, then swap row i with row k
            if( k!=i ) {
               swap(A, i, k, j);
               printMatrix(out, A);
            }

            // if A[i][j] is not 1, then divide row i by A[i][j]
            if( A[i][j]!=1 ){
               divide(A, i, j);
               printMatrix(out, A);
            }

            // eliminate all other non-zero entries from col j by subtracting from each
            // row (other than i) an appropriate multiple of row i
            eliminate(A, i, j);
            printMatrix(out, A);
            i++;
         }
         j++;
      }

      // print rank to output file
      out.println("rank = " + (i-1));

      // close output file
      out.close();
   }
}