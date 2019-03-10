/**
 * 
 */
package org.bawaweb;

/**
 * @author Navroz
 *
 */
class A {
	public static int getThis(int a) {
		return 5 + a;
	}

	public int getThat(int a) {
		return 0 + a;
	}
}

class B extends A {
	public static int getThis(int a) {
		return 5 * a;
	}

	public int getThat(int a) {
		return 1 + a;
	}
}

class C extends B {
	public static int getThis(int a) {
		return 5 - a;
	}

	public int getThat(int a) {
		return 2 + a;
	}
}

public class Test {
	public static void main(String[] args) {
		A a = new A();
		System.out.println(a.getThis(10));
		System.out.println(a.getThat(10));

		A b = new B();
		System.out.println(b.getThis(10));
		System.out.println(b.getThat(10));

		A c = new C();
		System.out.println(c.getThis(10));
		System.out.println(c.getThat(10));

		B bb = new B();
		System.out.println(bb.getThis(10));
		System.out.println(bb.getThat(10));

		B bc = new C();
		System.out.println(bc.getThis(10));
		System.out.println(bc.getThat(10));
	}
}



/****
OUTPUT
15
10
15
11
15
12
50
11
50
12

 
****/