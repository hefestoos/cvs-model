package com.equalsp.commons.csv

import spock.lang.Shared
import spock.lang.Specification

import com.equalsp.commons.cvs.CSV
import com.equalsp.commons.cvs.Configuration


class LoadSimpleTemplate extends Specification{
	
	@Shared List<StaticClassWithStaticTypes> result
	
	void setupSpec(){
		result = new CSV<StaticClassWithStaticTypes>(
													Configuration.init()
													 .forFile("staticType.csv")
													 .template(StaticClassWithStaticTypes.class)
													 ).load();
	}
	
	void ""(){
		expect:
			def obj = result.get(i) 
			obj.number 			== 	values[0]
			obj.numberObj 		== 	values[1]
			obj.realNumber 		== 	values[2]
			obj.realNumberObj 	== 	values[3]
			obj.trueOrFalse 	==  values[4]
			obj.trueOrFalseObj 	==  values[5]
			obj.letter 			==  values[6]
			obj.letterObj		==  values[5]
			
		where:
			i << [0,1,2,3,4,5]
			values << [ new ArrayList([1000000,31313,-2.23,1.121341234,true,true,'w','W']),
					    new ArrayList([1000000,231313,-2.23,1.121341234,true,true,'j','W']),
			 	        new ArrayList([110,1313,-2.23,1.121341234,true,false,'w','c']),
						new ArrayList([10000,1231212,-2.23,1.121341234,true,true,'L','i']),
						new ArrayList([1000000,1231313,-2.23,1.121341234,false,true,'w','B']),
						new ArrayList([1000000,31313,-2.23,1.121341234,false,true,'N','W'])]			
	}
	
}
