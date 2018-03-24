/**
 * 
 */
package com.mycompany.expgenerics.generics.upper_lower_bounds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Showing "upper bound" and "lower bound"
 * <pre>
 * "upper bound" makes it; assignable to lists of boundType or its children. And becomes "read only"
 * "lower bound" makes it; assignable to lists of boundType or its parents. And becomes "write only". Can be read as Object only
 * </pre>
 * @author ilker
 *
 */
public class ShowUpperAndLowerBounds {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		show_upperBoundReadOnly_lowerBoundWriteOnly();
	}

	public static void show_upperBoundReadOnly_lowerBoundWriteOnly(){
		// 0 prepare the basic lists
		List<X> xs = new ArrayList<X>();
		xs.add(new X("xOne"));

		List<Y> ys = new ArrayList<Y>();
		ys.add(new Y("yOne")); ys.add(new Y("yTwo"));

		List<Z> zs = new ArrayList<Z>();
		zs.addAll(Arrays.asList(new Z("zOne"), new Z("zTwo"), new Z("zThree")));

		List<Z2> z2s = new ArrayList<Z2>();
		z2s.addAll(Arrays.asList(new Z2("z2One"), new Z2("z2Two"), new Z2("z2Three"), new Z2("z2Four")));

		// 1 "upper bound" makes it; assignable to lists of boundType or its children. And becomes "read only"
		// 1a "upper bound" makes it; assignable to lists of boundType or its children
		List<? extends Y> upperBound2Y_readOnly;
//		upperBound2Y_readOnly = xs;	// compile error - "upper bound" list can be assigned to lists of boundType or its children
		upperBound2Y_readOnly = ys;	// OK
		upperBound2Y_readOnly = zs;	// OK
		upperBound2Y_readOnly = z2s;// OK
		
		// 1b "upper bound" makes it; "read only". Can not write to it
//		upperBound2Y_readOnly.add(new Y("yCompileError")); // compile error
		
		// 1c read is OK; for loop and stream's forEach
		for (Y y : upperBound2Y_readOnly) { }	// OK
		upperBound2Y_readOnly.forEach(y -> {
			System.out.println(y.getName());
			System.out.println(y.getX());
			System.out.println(y.getY()); // NOTE y is of type Y
		});	
	
		
		// 2 "lower bound" makes it; assignable to lists of boundType or its parents. And becomes "write only"
		// 2a "lower bound" makes it; assignable to lists of boundType or its parents
		List<? super Y> lowerBound2Y_writeOnly;
		lowerBound2Y_writeOnly = xs;	// OK
		lowerBound2Y_writeOnly = ys;	// OK
//		lowerBound2Y_writeOnly = zs;	// compile error
//		lowerBound2Y_writeOnly = z2s;	// compile error
		
		// 2b "lower bound" is writable (in fact "write only"). But normal element addition rule applies
//		lowerBound2Y_writeOnly.add(new X("xNotOK")); // compile error - Instances of Y or its children can be added as usual
		lowerBound2Y_writeOnly.add(new Y("yOK"));	 // OK
		lowerBound2Y_writeOnly.add(new Z("zOK"));	 // OK
		lowerBound2Y_writeOnly.add(new Z2("z2OK"));	 // OK
		
		// 2c "lower bound" is "write only"; can not read (other than as Object)
//		for (Y y : lowerBound2Y_writeOnly) { }		// compile error - can only read as Object
		for (Object o : lowerBound2Y_writeOnly) { }	// OK, can only read as Object
		lowerBound2Y_writeOnly.forEach(o -> {
			System.out.println(o.toString());	// OK, o is Object
//			System.out.println(o.getX());		// compile error
//			System.out.println(o.getY());		// compile error
//			System.out.println(o.getZ());		// compile error
		});	
	}
	
	// BEF class hierarchy
	public static class X {
		protected String name;
		public X(String _name) { this.name = _name; }
		protected String getName() { return "X-" + name; }
		protected String getX() { return "x-" + name; }
	}
	public static class Y extends X {
		public Y(String _name) { super(_name); }
		protected String getName() { return "Y-" + name; }
		protected String getY() { return "y-" + name; }
	}
	public static class Z extends Y {
		public Z(String _name) { super(_name); }
		protected String getName() { return "Z-" + name; }
		protected String getZ() { return "z-" + name; }
	}
	public static class Z2 extends Y {
		public Z2(String _name) { super(_name); }
		protected String getName() { return "Z2-" + name; }
		protected String getZ2() { return "z2-" + name; }
	}
	// EOF class hierarchy
	
}
