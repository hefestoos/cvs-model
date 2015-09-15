package com.equalsp.commons.cvs.reflection;

import java.lang.reflect.Field;

import com.equalsp.commons.cvs.validator.TransformValue;

public class StaticValuesTransform implements TransformValue {

	private Field field;
	
	public StaticValuesTransform(Field field){
		this.field = field;
	}
	
	public Object transform(String value) {
		
		Class<?> klass = field.getType();
		
		if( Boolean.class.isAssignableFrom( klass ) || Boolean.TYPE == klass ) return Boolean.parseBoolean( value );
	    if( Byte.class.isAssignableFrom( klass ) || Byte.TYPE == klass ) return Byte.parseByte( value );
	    if( Short.class.isAssignableFrom( klass ) || Short.TYPE == klass) return Short.parseShort( value );
	    if( Integer.class.isAssignableFrom( klass ) || Integer.TYPE == klass) return Integer.getInteger( value );
	    if( Long.class.isAssignableFrom( klass ) || Long.TYPE == klass ) return Long.parseLong( value );
	    if( Float.class.isAssignableFrom( klass ) || Float.TYPE == klass) return Float.parseFloat( value );
	    if( Double.class.isAssignableFrom( klass ) || Double.TYPE == klass) return Double.parseDouble( value );
	    
	    return value;
	}
	
}
