package com.equalsp.commons.csv

import java.lang.reflect.Field

import com.equalsp.commons.cvs.reflection.TemplateClass
import com.equalsp.commons.cvs.reflection.TemplateField;

import spock.lang.Specification


class TemplateFieldSpec extends Specification {
	
	/* Caso um campo seja anotado, seus repectivo attributo nao deve estar nulo ... */	
	
	void "Caso anotado com Collumn, Validate e Tranform, tais atributo de anotacoes não deve está nulo."(){
		given:
			Field field = DinamicClass.class.getDeclaredField("someEnum");
		when:
			TemplateField tField = new TemplateField(field);
		then:
			tField.columnAnno		
			tField.tranformAnno
			tField.validationAnno		
	}
	
	/* Caso um campo NAO seja anotado, seus repectivo attributo nao deve estar nulo ... */
	
	void "Caso o campo não esteja annotado, os atributo de anotações deve está nulo."(){
		given:
			Field field = DinamicClass.class.getDeclaredField("fieldNotAnnotead");
		when:
			TemplateField tField = new TemplateField(field);
		then:
			!tField.columnAnno
			!tField.tranformAnno
			!tField.validationAnno
	}
	
	/* Ordenação dos templates Fields */
	void "Caso o campo estaja anotado com Collumn, a ordem dos campos deve ser conforme o atibuto order da annotation"(){
		when: 
			TemplateClass templateClass = new TemplateClass(DinamicClass.class)
		then:
			templateClass.templateFields.size() == 4
			templateClass.templateFields.get(0).field.getName()
			templateClass.templateFields.get(1).field.getName()
			templateClass.templateFields.get(2).field.getName()
			templateClass.templateFields.get(3).field.getName()
	}
	
	void "Caso o campo NAO estaja anotado com Collumn, a ordem dos campos deve ser conforme o atibuto order da annotation"(){
	
	
	}
	
}
