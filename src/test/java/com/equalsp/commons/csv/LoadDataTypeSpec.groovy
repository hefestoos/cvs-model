package com.equalsp.commons.csv

import spock.lang.Shared;
import spock.lang.Specification;

import com.equalsp.commons.cvs.CSV
import com.equalsp.commons.cvs.Configuration;

class LoadDataTypeSpec extends Specification{
	
	List<StaticClass> result
	String filePath
	Class templateClass
	
	void setupData(){
		result = new CSV<StaticClass>(Configuration.init()
													 .forFile(filePath)
													 .template(templateClass)
													 ).load();
	}
	
	void "Em um templates sem anotações de collumns, deve carreagar os dados conforme a ordem dos campos"(){
		setup:
			filePath = "staticType.csv"
			templateClass = StaticClass.class
			setupData()
			StaticClass obj = result.get(i)
		expect:
			obj.number 			== 	values[0]
			obj.numberObj 		== 	values[1]
			obj.realNumber 		== 	values[2]
			obj.realNumberObj 	== 	values[3]
			obj.trueOrFalse 	==  values[4]
			obj.trueOrFalseObj 	==  values[5]
			obj.letter 			==  values[6]
			obj.letterObj		==  values[7]
			
		where:
				 i << [0,1,2,3,4,5]
			values << [ new ArrayList([1000000,31313,-2.23,1.121341234,true,true,'w','W']),
						new ArrayList([1000000,231313,-2.23,1.121341234,true,true,'j','W']),
						new ArrayList([110,1313,-2.23,1.121341234,true,false,'w','c']),
						new ArrayList([10000,1231212,-2.23,1.121341234,true,true,'L','i']),
						new ArrayList([1000000,1231313,-2.23,1.121341234,false,true,'w','B']),
						new ArrayList([1000000,31313,-2.23,1.121341234,false,true,'N','W'])]
	}
	
	void "Em um templates com anotações collumns, deve carreagar os dados conforme a ordem especificada nas annotations"(){
		setup:
			filePath = "dinamicType.csv"
			templateClass = DinamicClass.class
			setupData()
			DinamicClass obj = result.get(i)
		expect:
			obj.someEnum        ==  values[0]
			obj.tranform        ==  values[1]
			obj.string 			== 	values[2]
			obj.object       	==  values[3]
		where:
				 i << [0,1,2,3]
			values << [ new ArrayList([SomeEnum.ONE,  "Yes", "String1",  "Obj"]),
						new ArrayList([SomeEnum.TWO,  "No",  "String2" , "Obj"]),
						new ArrayList([SomeEnum.THREE,"Yes", "String3" , "Obj"]),
						new ArrayList([SomeEnum.THREE,"Yes", "String4",  "Obj"])]
			
	}
		

}
