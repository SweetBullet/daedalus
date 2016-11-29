package com.baidu.unbiz.generated

public class EasyMapper_Person_TO_People_Mapper24363904457210$0 extends GeneratedMapperBase {
	public void map(java.lang.Object a, java.lang.Object b) {

com.bullet.sweet.daedalus.kern.mapper.Person source = ((com.bullet.sweet.daedalus.kern.mapper.Person)a);
com.bullet.sweet.daedalus.kern.mapper.People destination = ((com.bullet.sweet.daedalus.kern.mapper.People)b);
destination.setRealAge(((int)source.getAge())); 
if ( !(((java.lang.String)source.getName()) == null)){ 
destination.setTrickName(((java.lang.String)source.getName())); 
}
		if(customMapping != null) { 
			 customMapping.map(source, destination);
		}
	}

}