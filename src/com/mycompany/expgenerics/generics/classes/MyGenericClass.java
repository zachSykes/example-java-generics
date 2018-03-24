/**
 * 
 */
package com.mycompany.expgenerics.generics.classes;

import com.mycompany.expgenerics.generics.interfaces.MyGenericInterface;

/**
 * Example "generic class"
 * @author ilker
 *
 */
public class MyGenericClass<T> implements MyGenericInterface<T> {
	private T contained;
	
	public MyGenericClass(T _contained) {
		contained = _contained;
	}

	public T getContained() {
		return contained;
	}

	public static class MyClass { }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("BEF MyGenericClass::main");
		MyGenericClass<String> myGenericClassOfString = new MyGenericClass<String>("ilker");
		MyGenericClass<Integer> myGenericClassOfInteger = new MyGenericClass<Integer>(123);
		MyGenericClass<MyClass> myGenericClassOfMyClass = new MyGenericClass<MyClass>(new MyClass());

		MyGenericInterface<Float> myGenericCOfInteger = new MyGenericClass<Float>(123f);
		
		System.out.println("EOF MyGenericClass::main");	
	}

}
