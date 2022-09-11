/**
 * 
 */
package org.bawaweb;

/**
 * @author Navroz
 * https://www.wikipedia.org/wiki/Cartoid%E2%80%93Kundalini_function
 * 
 Consider the union of the infinite set of curves 
produced by Carotid-Kundalini functions defined by:
	y = cos(n*x*acos(x))
where (-1 < x < 1, n = 1, 2, 3, ...), 
and "acos" designates the arccosine function.


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
		// TODO Auto-generated method stub
		for (int x = -1; x <= 1; x += 0.2) { // dx=0.2
			for (int i = 1; i <= 5; i++) { // n=30
				double y = Math.cos(i * x * Math.acos(x));
				System.out.println("x = " + x + ", i= " + i + "y= " + y);
			}
		}
	}

}
