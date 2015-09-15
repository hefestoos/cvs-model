package com.equalsp.commons.cvs;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.csv.CSVFormat;

public class Configuration {
	
	private Collection<Class<?>> classes;
	
	private String packageName;

	private File file;

	private Class<?> template;
	
	private CSVFormat format;

	private Charset charset;
	
	public Configuration(){
		this.setClasses(new ArrayList<Class<?>>());
		this.setFormat(CSVFormat.RFC4180);
		this.charset = Charset.defaultCharset();
	}

	public Configuration addClass(Class<?> klass){
		this.getClasses().add(klass);
		return this;
	}
	
	public Configuration forFile(String filePath) throws ConfigurationException{
		try{
			URL url = Thread.currentThread().getContextClassLoader().getResource(filePath);
			if(url != null){
				setFile(new File(url.toURI()));
				return this;
			}
			throw new ConfigurationException("File Not Found. ("+filePath+")");
		}catch (Exception e) {
			throw new ConfigurationException("File Error. ("+filePath+")", e);
		}
	}
	
	public Configuration template(Class<?> templateClass){
		this.setTemplate(templateClass);
		return this;
	}
	
	public Configuration usePackage(String packegeName){
		this.packageName = packegeName;
		return this;
	}
	
	public Configuration setFormat(CSVFormat format){
		this.format = format;
		return this;	
	}
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Collection<Class<?>> getClasses() {
		return classes;
	}

	public void setClasses(Collection<Class<?>> classes) {
		this.classes = classes;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public static Configuration init(){
		return new Configuration();
	}

	public Class<?> getTemplate() {
		return template;
	}

	public void setTemplate(Class<?> template) {
		this.template = template;
	}

	public CSVFormat getFormat() {
		return format;
	}

	public Charset getCharset() {
		return charset;
	}

}
