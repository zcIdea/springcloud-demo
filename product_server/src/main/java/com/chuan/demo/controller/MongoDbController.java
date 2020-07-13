package com.chuan.demo.controller;

import com.chuan.demo.entity.PersonMongoDb;
import com.chuan.demo.service.MongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author: huangyibo
 * @Date: 2019/1/30 23:31
 * @Description:
 */
@RestController
public class MongoDbController {

    @Autowired
    private MongoDbService mongoDbService;

    @PostMapping("/mongo/save")
    public String saveObj(@RequestBody PersonMongoDb person) {return mongoDbService.saveObj(person);}

    @GetMapping("/mongo/findAll")
    public List<PersonMongoDb> findAll() {return mongoDbService.findAll();}

    @GetMapping("/mongo/findOne")
    public PersonMongoDb findOne(@RequestParam int id) {return mongoDbService.getPersonById(id);}

    @PostMapping("/mongo/update")
    public String update(@RequestBody PersonMongoDb person) {return mongoDbService.updatePerson(person);}

    @PostMapping("/mongo/delOne")
    public String delOne(@RequestBody PersonMongoDb person) {return mongoDbService.deletePerson(person);}

    @GetMapping("/mongo/delById")
    public String delById(@RequestBody PersonMongoDb person) {
        return mongoDbService.deletePersonBy(person);
    }
}