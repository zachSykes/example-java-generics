/**
 * 
 */
package com.mycompany.expgenerics.generics.staticmethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;



/**
 * Showing "static generic method"
 * "unbounded" generic method
 * "type bounded" generic method
 * "upper bounded" generics
 * "lower bounded" generics
 * "directly" specifying type by caller of static generic method, instead of relying on "inferring" it
 * 
 * @author ilker
 *
 */
public class ShowStaticGenericMethodsAndBounds {
	/**
	 * Static "unbounded" generic method example
	 * @param _e element to add to 
	 * @param _collection Collection to add _e into
	 * @return _e that has been just added to _collection
	 */
	public static<T> T add2collection(T _e, Collection<T> _collection) {
		_collection.add(_e);
		return _e;
	}

	/**
	 * Static "type bounded" generic method example (in this case bound by class Number and its children)
	 * @param _e element, that can be a Number or any of its children (like Integer, Double, Float, ...)to add to 
	 * @param _collection Collection of Number or any of its children to add _e into
	 * @return _e that has been just added to _collection
	 */
	public static<T extends Number> T addNumberOrItsChildren2collection(T _e, Collection<T> _collection) {
//	NOTE ilker this is NOT "upper bounding", just "type bounding" of static method
//         so, below line is WRONG
//	public static<T super Integer> T addIntegerOrItsParents2collection(T _e, Collection<T> _collection) {
		_collection.add(_e);
		return _e;
	}

	/**
	 * Static "type bounded" generic method example (in this case bound by class Number)
	 * @param _e element, that can be a Number or any of its children (like Integer, Double, Float, ...)to add to 
	 * @param _collection Collection of Number or any of its children to add _e into
	 * @return _e that has been just added to _collection
	 */
//	public static<T super Integer>  T addTOrItsParents2collection(T _e, Collection<T> _collection) {
	public static<T extends Number> T addTOrItsParents2collection(T _e, Collection<? super T> _collection) {
		_collection.add(_e);
		return _e;
	}

	/**
	 * To show why we need "upper bound" generics.
	 * Expects input of List of Number ONLY. Will not even accept children Number like Integer, Float, Double, ..
	 * @param list
	 * @return sum of numbers in list
	 */
	public static double  sumNumberONLY(List<Number> list) {
		double sum = 0d;
		for (Number number : list) {
			sum += number.doubleValue();
		}
		return sum;
	}
	
	/**
	 * "upper bound" generic example.
	 * Allows users to pass input List of Number or its children (like Integer, Double, Float, ...)
	 * NOTE ilker the "upper bound" is only for the type of list that can be passed. Not for elements that can be added to list. The list like every other bounded generics can have Number and its children.
	 * @param listReadOnly 
	 * @return sum of numbers in listReadOnly
	 */
	public static double  sumNumberAndItsChildren(List<? extends Number> listReadOnly) {
		double sum = 0d;
		for (Number number : listReadOnly) {
			sum += number.doubleValue();
		}
		// NOTE ilker read is OK (above), but not write (below)
		// NOTE ilker, due to "upper bounding" the input list, listReadOnly, it becomes a read only list, since due to "upper bounding" one can not be sure what the list is really pointing to (could be List<Integer>, could be List<Float> etc), so compiler won't let you add to it. Will only let you read it.
//		listReadOnly.add(new Integer(1));	// NOT allowed
		
		return sum;
	}
	
	/**
	 * "lower bound" generic example
	 * Allows users to pass input List of Integer or its parents (like Number)
	 * NOTE ilker the "lower bound" is only for the type of list that can be passed. Not for elements that can be added to list. The list like every other bounded generics can have Integer and its children (if it was possible for Integer to have children).
	 * @param listWriteOnly 
	 * @return 0. Since can not read elements of listWriteOnly, can not sum their values.
	 */
	public static double  sumIntegerAndItsParents(List<? super Integer> listWriteOnly) {
		double sum = 0d;
		// NOTE ilker, due to "lower bounding" the input list, listWriteOnly, it becomes a write only list, and not readable, since due to "lower bounding" one can not be sure what the list is really pointing to (could be List<Number>, could be List<Integer>), so compiler won't let you read it. Will only let you write to it.
//		for (Integer number : listWriteOnly) {
//			sum += number.doubleValue();
//		}
		// NOTE ilker write is OK (below), but not read (above)
		listWriteOnly.add(new Integer(1));
		System.out.println("AFTER adding 1, listWriteOnly:" + listWriteOnly);
		
		// NOTE ilker reading listWriteOnly as Object is NOT really reading. But that's all you can read
		for (Object o : listWriteOnly) {
			System.out.println("o:" + o);
		}
		
		return sum;
	}
	
	
	public static void test_add2collection() {
		System.out.println("BEF MyCollectionUtility::test_add2collection");
		String s1 = "Hello";
		String s2 = new String("ilker");
		List<String> lists = new ArrayList<String>();
		lists.add(s1);
		lists.add(s2);

		String s3 = new String("kiris");
		String returnedStr = ShowStaticGenericMethodsAndBounds.<String>add2collection(s3, lists);
		System.out.println("returnedStr:" + returnedStr);
		
//		ShowStaticGenericMethodsAndBounds.add2collection(new Integer(123), lists);	// NO intentionally
		
		System.out.println("EOF MyCollectionUtility::test_add2collection");		
	}

	public static void test_addNumberOrItsChildren2collection() {
		System.out.println("BEF MyCollectionUtility::test_addNumberOrItsChildren2collection");
		List<String> lists = new ArrayList<String>();
		lists.add("Hello");
		lists.add(new String("ilker"));
//		String returnedStr = MyCollectionUtility.addNumberOrItsChildren2collection(new String("kiris"), lists);

		
		// Object <-- Number |<-- Integer
		//                   |<-- Double
		//                   |<-- Long	
		//                   |<-- Float
		//                   |<-- Byte	
		List<Number> listOfNumber = new ArrayList<Number>();
		listOfNumber.add(1);	// Integer
		listOfNumber.add(2d);	// Double
		listOfNumber.add(3f);	// Float
		Number returnedNumber = ShowStaticGenericMethodsAndBounds.addNumberOrItsChildren2collection(new Long(4), listOfNumber);
		System.out.println("returnedNumber:" + returnedNumber);

		List<Integer> listOfInteger = new ArrayList<Integer>();
		listOfInteger.add(10);
		listOfInteger.add(20);
		listOfInteger.add(30);
//		listOfInteger.add(50d);	// WRONG - NOTE can not add Double, Float etc. Only Integer (or if it could have, its children. Integer can not have children because it is final class)
		Number returnedInteger = ShowStaticGenericMethodsAndBounds.addNumberOrItsChildren2collection(new Integer(40), listOfInteger);
		System.out.println("returnedInteger:" + returnedInteger);

		// NOTE ilker below is intentionally NO
//		List<String> listOfString = Arrays.asList("one", "two");
//		String returnedString = ShowStaticGenericMethodsAndBounds.<String>addNumberOrItsChildren2collection("ilker", listOfString);

		System.out.println("EOF MyCollectionUtility::test_addNumberOrItsChildren2collection");		
	}
	
	public static void test_addTOrItsParents2collection() {
		System.out.println("BEF MyCollectionUtility::test_addTOrItsParents2collection");
		List<Number> listOfNumber = new ArrayList<Number>();
		listOfNumber.add(1);	// Integer
		listOfNumber.add(2d);	// Double
		listOfNumber.add(3f);	// Double
		Number returnedNumber = ShowStaticGenericMethodsAndBounds.<Number>addTOrItsParents2collection(new Long(4), listOfNumber);
		// NOTE ilker in above caller directly specifying T to be Number and not relying on inferring from Type(s) of input
		System.out.println("returnedNumber:" + returnedNumber);

		List<Integer> listOfInteger = new ArrayList<Integer>();
		listOfInteger.add(10);
		listOfInteger.add(20);
		listOfInteger.add(30);
//		listOfInteger.add(50d);	// WRONG - NOTE can not add Double, Float etc. Only Integer (or if it could have, its children. Integer can not have children because it is final class)
		Number returnedInteger = ShowStaticGenericMethodsAndBounds.<Integer>addTOrItsParents2collection(new Integer(40), listOfInteger);
		System.out.println("returnedInteger:" + returnedInteger);
		
		System.out.println("EOF MyCollectionUtility::test_addTOrItsParents2collection");		
	}

	public static void test_sumNumberONLY() {
		System.out.println("BEF MyCollectionUtility::test_sumNumberONLY");
		List<Number> numberList = new ArrayList<Number>();
		numberList.add(new Integer(1));
		numberList.add(new Double(2d));
		numberList.add(new Float(3f));
		double sum1 = sumNumberONLY(numberList);
		System.out.println("sum1:" + sum1 + " of numberList:" + numberList);
		
		List<Integer> integerList = new ArrayList<Integer>();
		integerList.add(new Integer(1));
		// NOTE ilker below 2 can NOT be added because integerList takes Integer (and its children)
//		integerList.add(new Double(2d));
//		integerList.add(new Float(3f));
		List<Float> floatList = new ArrayList<Float>();
		floatList.add(new Float(1f));		
		// NOTE ilker sumNumberONLY allows ONLY Number. So can not pass List<Integer>, List<Float>, ..
//		double sum2 = sumNumberONLY(integerList);	// not allowed
//		double sum3 = sumNumberONLY(floatList);		// not allowed

		System.out.println("EOF MyCollectionUtility::test_sumNumberONLY");
	}
	
	public static void test_sumNumberAndItsChildren() {
		System.out.println("BEF MyCollectionUtility::test_sumNumberAndItsChildren");
		List<Number> numberList = new ArrayList<Number>();
		numberList.add(new Integer(1));
		numberList.add(new Double(2d));
		numberList.add(new Float(3f));
		double sum1 = sumNumberAndItsChildren(numberList);
		System.out.println("sum1:" + sum1 + " of numberList:" + numberList);
		
		List<Integer> integerList = new ArrayList<Integer>();
		integerList.add(new Integer(10));
		integerList.add(new Integer(20));
		integerList.add(new Integer(30));
		double sum2 = sumNumberAndItsChildren(integerList);	// children of Number is OK too
		System.out.println("sum2:" + sum2 + " of integerList:" + integerList);

		List<Float> floatList = new ArrayList<Float>();
		floatList.add(new Float(100f));		
		floatList.add(new Float(200f));		
		floatList.add(new Float(300f));		
		// NOTE ilker sumNumberONLY allows ONLY Number. So can not pass List<Integer>, List<Float>, ..
		double sum3 = sumNumberAndItsChildren(floatList);	// children of Number is OK too
		System.out.println("sum3:" + sum3 + " of floatList:" + floatList);

		System.out.println("EOF MyCollectionUtility::test_sumNumberAndItsChildren");
	}

	
	/**
	 * To show what lower bound and upper bound means to inputs to a method.
	 * A very good example of it is Collection.copy method with signature
	 * public static <T> void copy(List<? super T> dest,List<? extends T> src)
	 * 
	 *                    |<-- Integer
	 * Object<--Number <--|<-- Double
	 *                    |<-- Float
	 *                    |<-- Long
	 */
	public static void show_lowerBoundAndUpperBound() {
		System.out.println("EOF MyCollectionUtility::show_lowerBoundAndUpperBound");
		// since below upperBount ones can point to either of below 3, can read. But can not write to it.
		List<? extends Number> upperBound_readOnly1 = new ArrayList<Number>();
		List<? extends Number> upperBound_readOnly2 = new ArrayList<Integer>();
		List<? extends Number> upperBound_readOnly3 = new ArrayList<Double>();
		
		// since below lowerBound ones can point to either below 2, can not read. But can write
		List<? super Number> lowerBound_writeOnly1 = new ArrayList<Object>();
		List<? super Number> lowerBound_writeOnly2 = new ArrayList<Number>();		
//		List<? super Number> lowerBound_writeOnly3 = new ArrayList<Integer>();	// NO intentionally		
		
		upperBound_readOnly1 = Arrays.<Number>asList(1,2,3);
		upperBound_readOnly1 = Arrays.<Integer>asList(10,20,30);
		upperBound_readOnly1 = Arrays.<Double>asList(100d,200d,300d);
		int index = 0;
		
		// as you see reading is OK for upperBound variables, but adding is not
		for (Number number : upperBound_readOnly1) {
			System.out.println("upperBound_readOnly1 " + index++ + "th element is:" + number.floatValue());
		}
//		upperBound_readOnly1.add(1);	// adding is not ok

		// as you see reading is NOT OK for lowerBound variables, but adding is OK
//		index = 0;
//		for (Number number : lowerBound_writeOnly2) {
//			System.out.println("lowerBound_writeOnly2 " + index++ + "th element is:" + number.floatValue());
//		}
		System.out.println("EOF MyCollectionUtility::show_lowerBoundAndUpperBound");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test_add2collection();
		test_addNumberOrItsChildren2collection();
		test_addTOrItsParents2collection();
		test_sumNumberONLY();
		test_sumNumberAndItsChildren();
		show_lowerBoundAndUpperBound();
	}

}
