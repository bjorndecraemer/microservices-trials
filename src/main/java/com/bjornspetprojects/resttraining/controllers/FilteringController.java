package com.bjornspetprojects.resttraining.controllers;

import com.bjornspetprojects.resttraining.beans.SomeBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    //field1, field3
    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean(){
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        // Adding filters
        return applyMapping(new String[]{"field1","field3"},someBean);
    }

    //field1, field2
    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfSomeBeans(){
        List<SomeBean> someBeans = Arrays.asList(
                new SomeBean("field1", "field2", "field3"),
                new SomeBean("value4", "value5", "value6")
        );

        // Adding filters
        return applyMapping(new String[]{"field1","field2"},someBeans);

    }

    private MappingJacksonValue applyMapping(String[] fields, Object applyToObject) {
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", simpleBeanPropertyFilter);
        MappingJacksonValue mapping = new MappingJacksonValue(applyToObject);
        mapping.setFilters(filters);
        return mapping;
    }

}
