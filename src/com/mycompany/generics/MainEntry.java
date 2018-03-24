/**
 * 
 */
package com.mycompany.generics;

import com.mycompany.expgenerics.generics.classes.MyGenericClass;
import com.mycompany.expgenerics.generics.staticmethods.ShowStaticGenericMethodsAndBounds;
import com.mycompany.expgenerics.generics.staticmethods.wildcards.ShowMoreStaticGenericMethodsAndBounds;

/**
 * @author ilker
 *
 */
public class MainEntry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyGenericClass.main(args);
		ShowStaticGenericMethodsAndBounds.main(args);	
		ShowMoreStaticGenericMethodsAndBounds.main(args);		
	}

}
