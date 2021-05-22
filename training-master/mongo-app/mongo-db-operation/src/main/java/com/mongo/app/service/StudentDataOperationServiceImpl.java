package com.mongo.app.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongo.app.document.Student;

@Service
public class StudentDataOperationServiceImpl implements StudentDataOperationService {

	private MongoTemplate mongoTemplate;

	public StudentDataOperationServiceImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	@Transactional
	@Override
	public Student createData(Student student) {
		student.set_id(ObjectId.get());
		mongoTemplate.insert(student);
		return student;
	}
    @Transactional
	@Override
	public List<Student> readAllData() {
		return mongoTemplate.findAll(Student.class);
	}
    @Transactional
	@Override
	public Student updateData(Student student) {
		mongoTemplate.save(student);
		return student;
	}
	@Transactional
	@Override
	public void deleteData(ObjectId  id) {
		mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), Student.class);
	}
}
