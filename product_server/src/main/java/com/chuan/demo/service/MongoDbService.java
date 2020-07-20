package com.chuan.demo.service;


import com.chuan.demo.entity.PersonMongoDb;
import com.chuan.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class MongoDbService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public String saveObj(PersonMongoDb person){
        mongoTemplate.save(person);
        return "添加成功";
    }

    public List<PersonMongoDb> findAll(){
        return mongoTemplate.findAll(PersonMongoDb.class);
    }

    public PersonMongoDb getPersonById(int id){
        Criteria criteria=Criteria.where("_id").is(id);
        Query query=new Query(criteria);
        return mongoTemplate.findOne(query, PersonMongoDb.class,"t_person");
    }

    public List<PersonMongoDb> findPersonList(PersonMongoDb person){

        Criteria criteria = Criteria.where("user").is(person.getName());
        criteria.and("age").is(person.getAge());
        if(!StringUtils.isEmpty(person.getSex())){
            criteria.and("sex").is(person.getSex());
        }
        Query query=new Query(criteria);
        return mongoTemplate.find(query, PersonMongoDb.class,"t_person");
    }

    public String updatePerson(PersonMongoDb person) {
        Query query = new Query(Criteria.where("_id").is(person.getId()));
        Update update=new Update().set("age",person.getAge()).set("sex",person.getSex());
        mongoTemplate.updateFirst(query,update,"t_person");
        return "success";
    }

    /***
     * 删除对象
     * @param person
     * @return
     */
    public String deletePerson(PersonMongoDb person) {
        mongoTemplate.remove(person);
        return "success";
    }
    /***
     * 根据条件 删除对象
     * @param person
     * @return
     */
    public String deletePersonBy(PersonMongoDb person) {
        Query query = Query.query(Criteria.where("name").is(person.getName()));
        mongoTemplate.remove(query);
        return "success";
    }




}



