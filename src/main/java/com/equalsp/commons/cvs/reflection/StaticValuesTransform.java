package com.equalsp.commons.cvs.reflection;

import java.lang.reflect.Field;

import com.equalsp.commons.cvs.validator.TransformValue;

public class StaticValuesTransform implements TransformValue {

	private Field field;
	
	public StaticValuesTransform(Field field){
		this.field = field;
	}
	
	public Object transform(String value) throws Exception {
		
		Class<?> klass = field.getType();
		try{ 
			if( Boolean.class.isAssignableFrom( klass ) || Boolean.TYPE == klass ) return Boolean.valueOf( value );
		    if( Byte.class.isAssignableFrom( klass ) || Byte.TYPE == klass ) return Byte.valueOf( value );
		    if( Short.class.isAssignableFrom( klass ) || Short.TYPE == klass) return Short.valueOf( value );
		    if( Double.class.isAssignableFrom( klass ) || Double.TYPE == klass) return Double.valueOf( value );
		    if( Float.class.isAssignableFrom( klass ) || Float.TYPE == klass) return Float.valueOf( value );
		    if( Integer.class.isAssignableFrom( klass ) || Integer.TYPE == klass) return Integer.valueOf( value );
		    if( Long.class.isAssignableFrom( klass ) || Long.TYPE == klass ) return Long.valueOf( value );
		    if( Character.class.isAssignableFrom( klass ) || Character.TYPE == klass ) return Character.valueOf( value.charAt(0) );
		}catch(NumberFormatException e){
			throw new ValidateException("The value <"+value+"> can not be paser for type of "+field.getName());
		}
		return value;
	}
	
}
