package com.equalsp.commons.csv;

import java.lang.reflect.Field;

import junit.framework.Assert;

import org.junit.Test;

import com.equalsp.commons.cvs.reflection.StaticValuesTransform;

public class StaticValuesTransformSpec {
	
	private static String fieldName;
	private static StaticValuesTransform transform; 

	public static void loadField() throws Exception{
		StaticClassWithStaticTypes model = new StaticClassWithStaticTypes();
		Field field = model.getClass().getDeclaredField(fieldName);
		transform = new StaticValuesTransform(field);
	}
	
	@Test
	public void check_intValue() throws Exception{
		String value = "2147483647"; 
		fieldName = "number";
		loadField();
		Object result = transform.transform(value);
		Assert.assertEquals("Incorrect parse.", 2147483647, result);
	}
	
	@Test
	public void check_IntegerValue() throws Exception{
		String value = "2147483647"; 
		fieldName = "number";
		loadField();
		Object result = transform.transform(value);
		Assert.assertEquals("Incorrect type.", Integer.class, result.getClass());
		Assert.assertEquals("Incorrect parse." ,2147483647, result);
	}
	
	@Test
	public void check_doubleValue() throws Exception{
		String value = "-4.232459234"; 
		fieldName = "realNumber";
		loadField();
		Object result = transform.transform(value);
		Assert.assertEquals("Incorrect parse.", -4.232459234, result);
	}
	
	@Test
	public void check_DoubleValue() throws Exception{
		String value = "1.7976931348623157"; 
		fieldName = "realNumberObj";
		loadField();
		Object result = transform.transform(value);
		Assert.assertEquals("Incorrect type.", Double.class, result.getClass());
		Assert.assertEquals("Incorrect parse." ,1.7976931348623157, result);
	}
	
	@Test
	public void check_booleanValue() throws Exception{
		String trueValue = "true";
		String falseValue = "false";
		fieldName = "trueOrFalse";
		loadField();
		Object resultTrue = transform.transform(trueValue);
		Object resultFalse = transform.transform(falseValue);
		Assert.assertEquals("Incorrect parse.", resultTrue, Boolean.TRUE);
		Assert.assertEquals("Incorrect parse.", resultFalse, Boolean.FALSE);
	}
	
	@Test
	public void check_BooleanValue() throws Exception{
		String trueValue = "true";
		String falseValue = "false";
		fieldName = "trueOrFalse";
		loadField();
		Object resultTrue = transform.transform(trueValue);
		Object resultFalse = transform.transform(falseValue);
		Assert.assertEquals("Incorrect type.", resultFalse.getClass(), Boolean.class);
		Assert.assertEquals("Incorrect parse.", resultTrue, Boolean.TRUE);
		Assert.assertEquals("Incorrect parse.", resultFalse, Boolean.FALSE);
	}
	
}
