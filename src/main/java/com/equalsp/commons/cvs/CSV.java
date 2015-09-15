package com.equalsp.commons.cvs;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.equalsp.commons.cvs.annotation.Template;
import com.equalsp.commons.cvs.reflection.TemplateClass;

public class CSV<T> {
	
	
	private static final Class<Annotation> Collumn = null;
	private Configuration configuration;

	public CSV(Configuration config){
		this.configuration = config;
	}
	
	public List<T> load() throws Exception{
		Configuration conf = this.configuration;
		
		Class<?> klass = conf.getTemplate();
		if(klass  == null){
			throw new Exception("Template Class Not Found.");
		}

		TemplateClass template = new TemplateClass(klass);
		
		CSVParser result = CSVParser.parse(conf.getFile(), conf.getCharset(), conf.getFormat());
		
		List<T> objects = new ArrayList<T>();
		Object object = null;
		for (CSVRecord csvRecord : result) {
			object = template.process(csvRecord);
			objects.add((T)object);
		}
		
		return objects;
	}
	
	public Class<?> getTemplateClass(String templateName){
		Class<?> klass;
		Vector<Class<?>> classes = getClassClassLoader();
		Iterator<Class<?>> iterator = classes.iterator();
		while(iterator.hasNext()){
			klass = iterator.next();
			Annotation annotation = klass.getAnnotation(Template.class);
			if( annotation != null){
				Template template = (Template) annotation;
				if(template.name().equals(templateName)){ 
					return klass; 
				}
			}
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	private Vector<Class<?>> getClassClassLoader() {
		Vector<Class<?>> classes = new Vector<Class<?>>();
		try {
			Field f;
			f = ClassLoader.class.getDeclaredField("classes");
			f.setAccessible(true);
			classes = (Vector<Class<?>>) f.get(Thread.currentThread().getContextClassLoader());
			classes = new Vector<Class<?>>(classes);
		} catch (NoSuchFieldException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return classes;
	}

}
