package com.fatou.rest.webservices.webservices.restfulwebservices6.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class SomeBeanController {
	
//	@GetMapping("/filtering")
//	public SomeBean retrieveSomeBean() {
//		return new SomeBean("value1","value2","valu3");
//	}
	
//	@GetMapping("/filtering-list")
//	public List<SomeBean> retrieveListSomeBean(){
//		return Arrays.asList(new SomeBean("value1","value2","value3"), 
//				new SomeBean("value12", "value22","value32"));
//			
//		}
	
//field1,field2
		@GetMapping("/filtering")
		public MappingJacksonValue retrieveSomeBean() {
			SomeBean someBean = new SomeBean("value1", "value2", "value3");

			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

			FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

			MappingJacksonValue mapping = new MappingJacksonValue(someBean);

			mapping.setFilters(filters);

			return mapping;
		}

}
