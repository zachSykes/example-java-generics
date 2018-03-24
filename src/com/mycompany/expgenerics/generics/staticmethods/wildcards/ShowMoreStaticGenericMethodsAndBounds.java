/**
 * 
 */
package com.mycompany.expgenerics.generics.staticmethods.wildcards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Showing 
 * @author ilker
 *
 */
public class ShowMoreStaticGenericMethodsAndBounds {

/**
 * 
 * 	public static class A implements AI {  }
	public static class X extends A { } 
	public static class Y extends A { }
	
	public class B extends A { }
	public class C extends B { }
	public class D extends C { }
*/
	public static void whyGenericsWildcardsAndLowerOrUpperBounds() {
		System.out.println("BEF ShowMoreStaticGenericMethodsAndBounds::whyGenericsWildcardsAndLowerOrUpperBounds");
		A a = new A();
		X x = new X();
		Y y = new Y();
		
		A aa= new A();  A a3= new A();  
		B b = new B();  B b2 = new B();  B b3 = new B();
		C c = new C();  C c2 = new C();  C c3 = new C();
		D d = new D();  D d2 = new D();  D d3 = new D();

		List<A> listA = new ArrayList<A>();
		List<B> listB = new ArrayList<B>();
		List<C> listC = new ArrayList<C>();
		List<D> listD = new ArrayList<D>();
		List<X> listX = new ArrayList<X>();
		List<Y> listY = new ArrayList<Y>();
		
		// NOTE below 3 are OK, since listA is to contain instances of type A and x and y are also A
		listA.add(a);	
		listA.add(x);	
		listA.add(y);		
		
		A a0 = listA.get(0); System.out.println("a0 name:" + a0.getName());
		A a1 = listA.get(1); System.out.println("a1 name:" + a1.getName());
		A a2 = listA.get(2); System.out.println("a2 name:" + a2.getName());
		
		
//		listX.add(a);	// WRONG - compilation error, a of A is NOT X 
		listX.add(x);
		
//		listY.add(a);	// WRONG - compilation error, a of A is NOT Y. Y isA A, but A isNot Y.
		listY.add(y);

		
		
		// can we do below 5 lines ?
		List<A> listA2; 
		listA2 = listA; 
//		listA2 = listB;	// NO
//		listA2 = listC;	// NO
//		listA2 = listD;	// NO
//		listA2 = listX;	// NO
//		listA2 = listY; // NO
		
		// But below 5 is OK
		List<? extends A> listExtendsA;
		listExtendsA = listA;
		listExtendsA = listB;
		listExtendsA = listC;
		listExtendsA = listD;
		listExtendsA = listX;
		listExtendsA = listY;
	
		// can we do below 5 lines ?		
		List<C> listC2;
//		listC2  = listA; // NO
//		listC2  = listB; // NO
		listC2  = listC;
//		listC2  = listD; // NO
//		listC2  = listX; // NO
//		listC2  = listY; // NO

		// But below 3 is OK
		List<? super C> listSuperC;
		listSuperC  = listA;
		listSuperC  = listB;
		listSuperC  = listC;
//		listSuperC  = listD; // intentionally NO
//		listSuperC  = listX; // intentionally NO
//		listSuperC  = listY; // intentionally NO
		
		// A<--B<--C<--D
		// can we do below 1 line ?
//		List<? extends A> listExtendsA2 = new ArrayList<? extends A>();		// WRONG - compilation error
//		List<? extends A> listExtendsA3 = new ArrayList<A>();	// OK
//		List<? extends A> listExtendsA4 = new ArrayList<B>();	// OK
		List<? extends A> listExtendsA5 = Arrays.<A>asList(aa, b, c, d);	// NOTE ilker - caller "directly" specifying the Type T, instead of relying on "inferring" it from inputs
		List<? extends A> listExtendsA6 = Arrays.asList(aa, b, c, d);
		int index = 0;
		for (A aaa : listExtendsA) {
			System.out.println("listExtendsA " + index++ + "th element is:" + aaa.getName());
		}
		// NOTE ilker can not add 
//		listExtendsA.add(aa);
//		listExtendsA.add(b);
		
		List<? extends B> listExtendsB = Arrays.asList(b, c, d); 	 // B and its children are OK
//		List<? extends B> listExtendsB2 = Arrays.asList(aa,b, c, d); // aa of A is NOT allowed

		List<? super C> listSuperC2 = Arrays.asList(aa,b, c, d);
		List<? super C> listSuperC3 = new ArrayList<>();
		listSuperC3.add(d2);
		
		
		
		List<A> listOfA = Arrays.asList(a, aa, a2);
		List<B> listOfB = Arrays.asList(b, b2, b3);
		List<C> listOfC = Arrays.asList(c, c2, c3);
		List<D> listOfD = Arrays.asList(d, d2, d3);

//		passListExtendsC(listOfA);	// intentionally NO
//		passListExtendsC(listOfB);	// intentionally NO
		passListExtendsC(listOfC);
		passListExtendsC(listOfD);
		
		passListSuperC(listOfA);
		passListSuperC(listOfB);
		passListSuperC(listOfC);
//		passListSuperC(listOfD);	// intentionally NO
		
		System.out.println("EOF ShowMoreStaticGenericMethodsAndBounds::whyGenericsWildcardsAndLowerOrUpperBounds");
	}
	
	public static void usingLowerOrUpperBoundInVariableType() {
		System.out.println("BEF ShowMoreStaticGenericMethodsAndBounds::usingLowerOrUpperBoundInVariableType");
		List<A> listA = new ArrayList<A>();
		List<B> listB = new ArrayList<B>();
		List<C> listC = new ArrayList<C>();
		List<D> listD = new ArrayList<D>();
		List<X> listX = new ArrayList<X>();
		List<Y> listY = new ArrayList<Y>();
		
		// A<--B<--C<--D
		// A<--X<--Y

		// can we do below 5 lines ?
		List<A> listA2; 
		listA2 = listA; 
//		listA2 = listB;	// NO
//		listA2 = listC;	// NO
//		listA2 = listD;	// NO
//		listA2 = listX;	// NO
//		listA2 = listY; // NO
		
		// But below 5 is OK with "upper bound"
		List<? extends A> listExtendsA;
		listExtendsA = listA;
		listExtendsA = listB;
		listExtendsA = listC;
		listExtendsA = listD;
		listExtendsA = listX;
		listExtendsA = listY;
	
		
		// can we do below 5 lines ?		
		List<C> listC2;
//		listC2  = listA; // NO
//		listC2  = listB; // NO
		listC2  = listC;
//		listC2  = listD; // NO
//		listC2  = listX; // NO
//		listC2  = listY; // NO

		// But below 3 is OK with "lower bound"
		List<? super C> listSuperC;
		listSuperC  = listA;
		listSuperC  = listB;
		listSuperC  = listC;
//		listSuperC  = listD; // intentionally NO
//		listSuperC  = listX; // intentionally NO
//		listSuperC  = listY; // intentionally NO

		System.out.println("EOF ShowMoreStaticGenericMethodsAndBounds::usingLowerOrUpperBoundInVariableType");
	}

	public static void usingLowerOrUpperBoundTypeMakingVariableReadOrWriteOnly() {
		System.out.println("BEF ShowMoreStaticGenericMethodsAndBounds::usingLowerOrUpperBoundTypeMakingVariableReadOrWriteOnly");
		A a = new A();  A a2 = new A();  A a3 = new A();  
		B b = new B();  B b2 = new B();  B b3 = new B();
		C c = new C();  C c2 = new C();  C c3 = new C();
		D d = new D();  D d2 = new D();  D d3 = new D();

		// A<--B<--C<--D
		// can we do below line
//		List<? extends B> listExtendsB = Arrays.asList(a, b, c, d);	// NO since a of A is not B, intententionally can not add a
		List<? extends B> listExtendsB = Arrays.asList(b, c, d);	// since B<--C<--D b,c,d are IS-A B so they are OK to add

		
		// can we do below 1 line ?
//		List<? extends A> listExtendsA2 = new ArrayList<? extends A>();				// WRONG - compilation error
//		List<? extends A> listExtendsA3 = new ArrayList<A>();	// OK
//		List<? extends A> listExtendsA4 = new ArrayList<B>();	// OK
		List<? extends A> listExtendsA5 = Arrays.<A>asList(a, b, c, d);	// NOTE ilker - caller "directly" specifying the Type T, instead of relying on "inferring" it from inputs
		
		// NOTE "upper bound" type of a variable makes it "read only", can not add to it. But assigning is already created list is OK
		List<? extends A> listExtendsA = Arrays.asList(a, b, c, d);
		int index = 0;
		for (A aaa : listExtendsA) {
			System.out.println("listExtendsA " + index++ + "th element is:" + aaa.getName());
		}
		// NOTE ilker can not add to listExtendsA
//		listExtendsA.add(a);
//		listExtendsA.add(b);

		// NOTE "lower bound" type of a variable makes it "write only", can not read(unless as Object) it.
		List<? super C> listSuperC = Arrays.asList(a, b, c, d);
		List<? super C> listSuperC2 = new ArrayList<>();
		// NOTE can add to listSuperC or listSuperC2
		listSuperC2.add(d2);
		index = 0;
		// NOTE ilker can not read listSuperC (other than as Object)
//		for (A aa : listSuperC) {
//			System.out.println("listSuperC " + index++ + "th element is:" + aa.getName());
//		}
		for (Object o : listSuperC) {
			System.out.println("listSuperC " + index++ + "th element is:" + o.toString());
		}
		// Also NOTE ilker toString() of collection goes back to using toString() of Object
		System.out.println("listSuperC:" + listSuperC);

		System.out.println("EOF ShowMoreStaticGenericMethodsAndBounds::usingLowerOrUpperBoundTypeMakingVariableReadOrWriteOnly");
	}

	/**
	 * Showing effect of using "lower bound" or "upper bound" restricted methods
	 */
	public static void usingLowerOrUpperBoundInMethodInputs() {
		System.out.println("BEF ShowMoreStaticGenericMethodsAndBounds::usingLowerOrUpperBoundInMethodInputs");
		// A<--B<--C<--D
		A a = new A();  A a2 = new A();  A a3 = new A();  
		B b = new B();  B b2 = new B();  B b3 = new B();
		C c = new C();  C c2 = new C();  C c3 = new C();
		D d = new D();  D d2 = new D();  D d3 = new D();
		
		List<A> listOfA = Arrays.asList(a, a2, a3);
		List<B> listOfB = Arrays.asList(b, b2, b3);
		List<C> listOfC = Arrays.asList(c, c2, c3);
		List<D> listOfD = Arrays.<D>asList(d, d2, d3);

		// use of "upper bound" restricting method
//		passListExtendsC(listOfA);	// intentionally NO
//		passListExtendsC(listOfB);	// intentionally NO
		passListExtendsC(listOfC);
		passListExtendsC(listOfD);
		
		// use of "lower bound" restricting method
		passListSuperC(listOfA);
		passListSuperC(listOfB);
		passListSuperC(listOfC);
//		passListSuperC(listOfD);	// intentionally NO
		
		System.out.println("EOF ShowMoreStaticGenericMethodsAndBounds::usingLowerOrUpperBoundInMethodInputs");
	}
	
	/**
	 * Showing effect of using "wild char" ? in methods
	 */
	public static void usingWildCharInMethodInputs() {
		System.out.println("BEF ShowMoreStaticGenericMethodsAndBounds::usingWildCharInMethodInputs");
		// A<--B<--C<--D
		A a = new A();  A a2 = new A();  A a3 = new A();  
		B b = new B();  B b2 = new B();  B b3 = new B();
		C c = new C();  C c2 = new C();  C c3 = new C();
		D d = new D();  D d2 = new D();  D d3 = new D();
		
		List<A> listOfA = Arrays.asList(a, a2, a3);
		List<B> listOfB = Arrays.asList(b, b2, b3);
		List<C> listOfC = Arrays.asList(c, c2, c3);
		List<D> listOfD = Arrays.<D>asList(d, d2, d3);

		// use of "wild char" ? in method
		passAnyUsingWildChar(listOfA);
		passAnyUsingWildChar(listOfB);
		passAnyUsingWildChar(listOfC);
		passAnyUsingWildChar(listOfD);
		
		System.out.println("EOF ShowMoreStaticGenericMethodsAndBounds::usingWildCharInMethodInputs");
	}
	
	/**
	 * Showing effect of using "bounded type" with interface in methods
	 */
	public static void usingBoundedToInterface() {
		System.out.println("BEF ShowMoreStaticGenericMethodsAndBounds::usingBoundedToInterface");
		// A implements AI <--B <--C <--D
		// Kobra implements KobraI
		// Ciyan implements CiyanI
		// CiyanKobra implements CiyanI, KobraI
		A a = new A();  A a2 = new A();  A a3 = new A();  
		B b = new B();  B b2 = new B();  B b3 = new B();
		C c = new C();  C c2 = new C();  C c3 = new C();
		D d = new D();  D d2 = new D();  D d3 = new D();
		KobraI kobraI = new Kobra(); KobraI kobraI2 = new Kobra();
		Kobra kobra   = new Kobra(); Kobra kobra2   = new Kobra();
		
		List<A> listOfA = Arrays.asList(a, a2, a3);
		List<B> listOfB = Arrays.asList(b, b2, b3);
		List<C> listOfC = Arrays.asList(c, c2, c3);
		List<D> listOfD = Arrays.<D>asList(d, d2, d3);
		
		List<? extends KobraI> listOfKobraI = Arrays.asList(kobraI, kobraI2);
		List<? extends KobraI> listOfKobraI2 = Arrays.asList(kobra, kobra2);
		List<? extends Kobra> listOfKobra2 = Arrays.asList(kobra, kobra2);
		List<Kobra> listOfKobra = Arrays.asList(kobra, kobra2);

		// use of "bounded Type" to interface AI in method
		boundedToInterfaceAI(listOfA);
		boundedToInterfaceAI(listOfB);
		boundedToInterfaceAI(listOfC);
		boundedToInterfaceAI(listOfD);
		
//		boundedToInterfaceAI(listOfKobra);	// NO intentionally
		
		System.out.println("EOF ShowMoreStaticGenericMethodsAndBounds::usingBoundedToInterface");
	}
	
	/**
	 * Showing effect of using "bounded type" with interface in methods
	 */
	public static void usingBoundedToMultipleInterfaces() {
		System.out.println("BEF ShowMoreStaticGenericMethodsAndBounds::usingBoundedToMultipleInterfaces");
		// A implements AI <--B <--C <--D
		// Kobra implements KobraI
		// Ciyan implements CiyanI
		// CiyanKobra implements CiyanI, KobraI
		A a = new A();  A a2 = new A();  A a3 = new A();  
		B b = new B();  B b2 = new B();  B b3 = new B();
		C c = new C();  C c2 = new C();  C c3 = new C();
		D d = new D();  D d2 = new D();  D d3 = new D();
		KobraI kobraI = new Kobra(); KobraI kobraI2 = new Kobra();
		Kobra kobra   = new Kobra(); Kobra kobra2   = new Kobra();
		Ciyan ciyan = new Ciyan();   Ciyan ciyan2 = new Ciyan();
		CiyanI ciyanIKobra = new CiyanKobra();
		KobraI kobraICiyan = new CiyanKobra(); 
		CiyanKobra ciyanKobra = new CiyanKobra(); CiyanKobra ciyanKobra2 = new CiyanKobra();
		
		List<A> listOfA = Arrays.asList(a, a2, a3);
		List<B> listOfB = Arrays.asList(b, b2, b3);
		List<C> listOfC = Arrays.asList(c, c2, c3);
		List<D> listOfD = Arrays.<D>asList(d, d2, d3);
		
		List<? extends KobraI> listOfKobraI = Arrays.asList(kobraI, kobraI2);
		List<? extends KobraI> listOfKobraI2 = Arrays.asList(kobra, kobra2);
		List<Kobra> listOfKobra = Arrays.asList(kobra, kobra2);

		List<Ciyan> listOfCiyan = Arrays.asList(ciyan, ciyan2);

		List<CiyanKobra> listOfCiyanKobra = Arrays.asList(ciyanKobra, ciyanKobra2);
		
		// use of "bounded Type" to interface AI in method
//		boundedToInterfaceKobraIandCiyanI(listOfA); // NO intentionally
//		boundedToInterfaceKobraIandCiyanI(listOfB); // NO intentionally
//		boundedToInterfaceKobraIandCiyanI(listOfC); // NO intentionally
//		boundedToInterfaceKobraIandCiyanI(listOfD); // NO intentionally
		
//		boundedToInterfaceKobraIandCiyanI(listOfKobra);	// NO intentionally

//		boundedToInterfaceKobraIandCiyanI(listOfCiyan);	// NO intentionally

		boundedToInterfaceKobraIandCiyanI(listOfCiyanKobra);
		
		System.out.println("EOF ShowMoreStaticGenericMethodsAndBounds::usingBoundedToMultipleInterfaces");
	}
	
	/**
	 * Showing effect of using "bounded type" to Class in methods
	 */
	public static void usingBoundedToClassC() {
		System.out.println("BEF ShowMoreStaticGenericMethodsAndBounds::usingBoundedToClassC");
		// A implements AI <--B <--C <--D
		A a = new A();  A a2 = new A();  A a3 = new A();  
		B b = new B();  B b2 = new B();  B b3 = new B();
		C c = new C();  C c2 = new C();  C c3 = new C();
		D d = new D();  D d2 = new D();  D d3 = new D();
		
		List<A> listOfA = Arrays.asList(a, a2, a3);
		List<B> listOfB = Arrays.asList(b, b2, b3);
		List<C> listOfC = Arrays.asList(c, c2, c3);
		List<D> listOfD = Arrays.<D>asList(d, d2, d3);
		
		// use of "bounded Type" to Class C in method
//		boundedToExtendingClassC(listOfA);	// NO intentionally
//		boundedToExtendingClassC(listOfB);	// NO intentionally
		boundedToExtendingClassC(listOfC);
		boundedToExtendingClassC(listOfD);
		
		System.out.println("EOF ShowMoreStaticGenericMethodsAndBounds::usingBoundedToClassC");
	}
	
	/**
	 * Example using "upper bound" generics in inputs
	 *  A<--B<--C<--D
	 * @param listExtendsC "read only" list variable
	 * @return size of listExtendsC
	 */
	private static int passListExtendsC(List<? extends C> listExtendsC){
		return listExtendsC.size();
	}

	/**
	 * Example using "lower bound" generics in inputs
	 *  A<--B<--C<--D
	 * @param listSuperC "write only" list variable
	 * @return size of listSuperC
	 */
	private static int passListSuperC(List<? super C> listSuperC){
		return listSuperC.size();
	}
	
	/**
	 * Example using "wild char" ? in inputs
	 * @param listAny List of anything
	 * @return size of listSuperC
	 */
	private static int passAnyUsingWildChar(List<?> listAny) {
		return listAny.size();
		
	}
	
	/**
	 * Example using "bounded interface"
	 * @param listOfInterfaceAI List of things implementing AI interface
	 * @return size of listOfInterfaceAI
	 */
	private static<T extends AI> int boundedToInterfaceAI(List<T> listOfInterfaceAI) {
		return listOfInterfaceAI.size();
		
	}

	/**
	 * Example using "bounded multiple(2) interfaces"
	 * @param listOfInterfaceKobraIandCiyanI List of things implementing KobraI and CiyanI interfaces
	 * @return size of listOfInterfaceKobraIandCiyanI
	 */
	private static<T extends KobraI & CiyanI> int boundedToInterfaceKobraIandCiyanI(List<T> listOfInterfaceKobraIandCiyanI) {
		return listOfInterfaceKobraIandCiyanI.size();
		
	}

	/**
	 * Example using "bounded to class"
	 * @param listOfClassExtendingC List of things implementing AI interface
	 * @return size of listOfClassExtendingC
	 */
	private static<T extends C> int boundedToExtendingClassC(List<T> listOfClassExtendingC) {
		return listOfClassExtendingC.size();
		
	}

	public static void main(String[] args){
		whyGenericsWildcardsAndLowerOrUpperBounds();
		usingLowerOrUpperBoundInVariableType();
		usingLowerOrUpperBoundTypeMakingVariableReadOrWriteOnly();
		usingLowerOrUpperBoundInMethodInputs();
		usingWildCharInMethodInputs();
		usingBoundedToInterface();
		usingBoundedToMultipleInterfaces();
		usingBoundedToClassC();
	}
	
}