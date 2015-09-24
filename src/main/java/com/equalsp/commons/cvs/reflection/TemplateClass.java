package com.equalsp.commons.cvs.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import com.equalsp.commons.cvs.annotation.Column;

public class TemplateClass {

	private List<TemplateField> templateFields;
	
	private Class<?> klass;

	public TemplateClass(Class<?> klass){
		
		this.klass = klass;
		
		ArrayList<TemplateField> annotedField = new ArrayList<TemplateField>();
		ArrayList<TemplateField> allField = new ArrayList<TemplateField>();
		for (Field field : klass.getDeclaredFields()) {
			if(field.isAnnotationPresent(Column.class)){
				annotedField.add(new TemplateField(field));
			}
			allField.add(new TemplateField(field));
		}
		
		templateFields = annotedField.isEmpty() ? allField : annotedField;
		
		Collections.sort(templateFields);
		
	}

	public List<TemplateField> getFields() {
		return templateFields;
	}

	public void setFields(List<TemplateField> fields) {
		this.templateFields = fields;
	}

	public Class<?> getKlass() {
		return klass;
	}

	public void setKlass(Class<?> klass) {
		this.klass = klass;
	}

	public Object process(CSVRecord csvRecord) throws Exception {
		try {
			Object newInstance = klass.newInstance();
			Object value = null;
			
			TemplateField currentField;
			for (int i = 0 ; i < csvRecord.size() ; i++) {
				currentField = templateFields.get(i);
				value = currentField.value(csvRecord.get(i));
				currentField.getField().setAccessible(true);
				currentField.getField().set(newInstance, value);
			}
			return newInstance;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) { 
			e.printStackTrace();
		}
		
		return null;
	}
}
