/**
 * 
 */
package org.bawaweb;

/**
 * @author Navroz
 * https://www.wikipedia.org/wiki/Cartoid%E2%80%93Kundalini_function
 * 
 * https://en.wikipedia.org/wiki/Carotid%E2%80%93Kundalini_function
 * 
 * https://sprott.physics.wisc.edu/pickover/CAROTID.HTM
 * 
 Consider the union of the infinite set of curves 
produced by Carotid-Kundalini functions defined by:
	y = cos(n*x*acos(x))
where (-1 < x < 1, n = 1, 2, 3, ...), 
and "acos" designates the arccosine function.

You can compute the union of the first 25 curves 
using the following logic: 

for (n=1; n < =25; n=n+1) {
    for (x= -1; x < = 1;  x=x+.01) {
        y = cos((float)n*x*acos(x));
        if (x == -1) MovePenTo(x,y);
            else  DrawTo(x,y);
    }
}
==
If you have the ability to display these curves, make a plot from -1 < x < 1 and -1 < y < 1. You could spend a lifetime exploring the infinite intricacies of the resulting superimposed patterns. 

C program
/ Compute Carotid-Kundalini Curves /
#include <math.h>
#include <stdio.h>

main()
{
float x,y;
int n;

/\ Superimpose 25 curves \/
for (n=1; n < =25; n=n+1) {
    for (x = -1; x < = 1;  x = x+.01) {
        y=cos( (float)n * x * acos(x));
        /\ Write out x,y points for plotting \/
        printf("%f %f\n",x,y);
    }
}
}



	T  (x) = cos(n�arccos(x)), n = 0, ..., 50. 
                                                                              

============	C source    paulborke
"Carotid-Kundalini" curves of degree i are defined as
f(x) = cos(i x arccos(x))
where i ranges from 1 upwards, and -1 <= x <= 1


#include "stdio.h" 
#include "stdio.h" 
#include "math.h" 
 
int main(int argc,char **argv) 
{ 
   int i,n; 
   double x,y,dx; 
 
   if (argc < 3) { 
      fprintf(stderr,"Usage: %s n dx\n",argv[0]); 
      exit(0); 
   } 
   n = atoi(argv[1]); 
   dx = atof(argv[2]);

   for (x=-1;x<=1;x+=dx) { 
      printf("%g ",x); 
      for (i=1;i<n;i++) { 
         y = cos(i * x * arccos(x));
         printf("%g ",y); 
      } 
      printf("\n"); 
   } 
} 

================
Extension to 3D
The Carodit functions can be extended into 3D in a number of ways, 
the one used here is as follows where a and
b are scaling factors.
f(x,y,z) = (a x, b cos(i x arccos(x)), b sin(i x arccos(x)))


0.2*x
0.2*cos(t * x * arccos(x))		0.2*cos(t * x * arccos(x))	//	0.3*cos(t*x*arccos(x))
0.2*sin(t * x * arccos(x))									//	0.3*sin(t*x*arccos(x))
 */
public class CartoidKundalini {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n;
		double x; double y;
		for (n = 1; n <= 25; n = n + 1) {
			System.out.println("n==" + n);
			for (x = -1; x <= 1; x = x + .01) {
				y = Math.cos((float) n * x * Math.acos(x));
				/* Write out x,y points for plotting */
				System.out.printf("%f %f\n", x, y);
			}
			System.out.println("------------------");
		}
		
		/*****************************************
		int loopXCnt = 0, loopYCnt = 0;
		// TODO Auto-generated method stub
		for (double x = -1; x <= 1; x += 0.05) { // dx=0.2
			System.out.println(x); 
			loopXCnt++;
			System.out.println("xloopCnt = "+loopXCnt);
//			System.out.println("x==="+x+", and loopX==="+loopXCnt+", and loopY=="+loopYCnt);			
			loopYCnt=0;
			for (int i = 1; i < 30; i++) { // n=30
				loopYCnt++;
				double y = Math.cos(i * x * Math.acos(x));
				System.out.println("\t"+y); 
//				System.out.println("x = " + x /\*+ ", i= " + i*\/ + ", and y= " + y);
			}
		}
		***********************************************/
	}

}
