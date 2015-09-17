package com.equalsp.commons.cvs.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.equalsp.commons.cvs.annotation.Column;
import com.equalsp.commons.cvs.annotation.Template;
import com.equalsp.commons.cvs.annotation.Transform;
import com.equalsp.commons.cvs.annotation.Validation;
import com.equalsp.commons.cvs.validator.StaticValidator;
import com.equalsp.commons.cvs.validator.TransformValue;
import com.equalsp.commons.cvs.validator.ValidationsEnum;
import com.equalsp.commons.cvs.validator.Validator;

public class TemplateField implements Comparable<TemplateField>{
	
	private Field field;
	
	private Map<String, String> validateErrors;
	
	private Column columnAnno;
	
	private Validation validationAnno;
	
	private Transform tranformAnno;

	public TemplateField(Field field){
		this.field = field;
		validateErrors = new HashMap<String, String>(); 
		for (Annotation a : field.getAnnotations()) {
			if(a instanceof Column ){
				this.columnAnno = (Column) a;
			}
			if(a instanceof Validation){
				this.validationAnno = (Validation) a;
			}
			if(a instanceof Template){
				this.tranformAnno = (Transform) a;
			}
		}
	}

	public Field getField() {
		return field;
	}
	
	public Column getColumnAnnotation(){
		return columnAnno;
	}
	
	public Validation getValidationAnnotation(){
		return validationAnno;
	}
	
	public int compareTo(TemplateField o) {
		boolean thisHasColumn = columnAnno != null;
		boolean oHasColumn =  o.columnAnno != null;
		
		if(thisHasColumn && oHasColumn){
			return new Integer(columnAnno.order()).compareTo(new Integer(o.columnAnno.order()));
		
		}else if( thisHasColumn && !oHasColumn){
			return -1;
		
		}else if(!thisHasColumn && !oHasColumn){
			return 0;
		
		}else if(!thisHasColumn &&  oHasColumn){
			return 1;
		}

		return 0;
	}
	
	public boolean isAnnotated(Field field){
		return columnAnno != null 
				|| validationAnno != null 
					|| tranformAnno != null;
	}
	
	public static boolean isFieldAnnotated(Field field){
		return field.isAnnotationPresent(Column.class) ||
				field.isAnnotationPresent(Validation.class) ||
				 field.isAnnotationPresent(Transform.class);
	}

	public Object value(String value) throws Exception {
		validateField(value);
		Object result = transformValue(value);
		return result;
	}

	private Object transformValue(String value) throws Exception {
		TransformValue transform = new StaticValuesTransform(this.field);
		if(tranformAnno != null){
			try {
				transform = (TransformValue)tranformAnno.klass().newInstance();
			}catch(ClassCastException e){
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return transform.transform(value);
	}

	private void validateField(String value) throws ValidateException {
		if(validationAnno != null){
			Validator validator;
			for (ValidationsEnum v : validationAnno.types()) {
	
				validator = new StaticValidator(v);
				
				if(v.equals(ValidationsEnum.CUSTOM)){
					Class<?> validateClass = validationAnno.validatorClass();
					if(validateClass == null || validateClass.equals(StaticValidator.class)){
						throw new ValidateException("Validation CUSTOM should define classValidation.");
					}
					try{
						validator = (Validator) validateClass.newInstance();
					} catch (InstantiationException e) {
							e.printStackTrace();
					} catch (IllegalAccessException e) {
							e.printStackTrace();
					}catch(ClassCastException e){
						throw new ValidateException("validateClass in @Validate deve implementar Validator.");
					}
				}
				
				if(validator.validate(value)){
					validateErrors.put(validator.name(), validator.message(this.field.getName()) );
				}
			}
		}
	}

	public Map<String, String> getValidateErrors() {
		return validateErrors;
	}

	public void setValidateErrors(Map<String, String> validateErrors) {
		this.validateErrors = validateErrors;
	}
	
}
