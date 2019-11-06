/**
 * 
 */
package org.bawaweb;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Optional.of;
import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Navroz
 *https://www.rosettacode.org/wiki/Cartesian_product_of_two_or_more_lists#Java
 */
public class CartesianProduct {

	public List<?> product(List<?>... a) {
        if (a.length >= 2) {
            List<?> product = a[0];
            for (int i = 1; i < a.length; i++) {
                product = product(product, a[i]);
            }
            return product;
        }
 
        return emptyList();
    }
 
    private <A, B> List<?> product(List<A> a, List<B> b) {
        return of(a.stream()
                .map(e1 -> of(b.stream().map(e2 -> asList(e1, e2)).collect(toList())).orElse(emptyList()))
                .flatMap(List::stream)
                .collect(toList())).orElse(emptyList());
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> ops1 = asList("Plus", "Minus", "Multiply", "Divide", "Power");
		List<String> ops2 = asList("Plus", "Minus", "Multiply", "Divide", "Power");
		List<String> ops3 = asList("none", "absolute", "absoluteSquare","reciprocal", "reciprocalSquare", "square", "cube","root", "exponent", "log(10)", "log(e)", "sine", "cosine", "tangent", "arcsine", "arccosine", "arctangent");

		CartesianProduct cp = new CartesianProduct();
		List<?> com = cp.product(ops1, ops2,ops3);
		System.out.println(com.size());
		for(int i = 0; i < com.size(); i++)
			System.out.println(""+i+" = "+com.get(i));

	}

}
