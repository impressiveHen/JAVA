package com.spring.jdbc.student;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class StudentJDBCTemplate implements StudentDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    private static final String INSERT_STUDENT_QUERY = "INSERT INTO students(name, age) VALUES(?, ?)";
    private static final String DELETE_STUDENT_QUERY = "DELETE FROM students WHERE id = ?";
    private static final String UPDATE_STUDENT_QUERY = "UPDATE students SET age = ? WHERE id = ?";
    private static final String SELECT_STUDENT_WITH_ID_QUERY = "SELECT * FROM students WHERE ID = ?";
    private static final String SELECT_ALL_STUDENT = "SELECT * FROM students";

    private static final Logger logger = LogManager.getLogger(StudentJDBCTemplate.class);

    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplateObject = new JdbcTemplate(ds);
    }

    public void createStudent(String name, Integer age) {
        jdbcTemplateObject.update(INSERT_STUDENT_QUERY, name, age);
        logger.info("Created student name: " + name + "age: " + age);
    }

    public Student getStudent(Integer id) {
        Student student = jdbcTemplateObject.queryForObject(SELECT_STUDENT_WITH_ID_QUERY, new Object[]{id}, new StudentMapper());
        return student;
    }

    public List<Student> listAllStudents() {
        List<Student> students = jdbcTemplateObject.query(SELECT_ALL_STUDENT, new StudentMapper());
        return students;
    }

    public void delete(Integer id) {
        jdbcTemplateObject.update(DELETE_STUDENT_QUERY, id);
        logger.warn("Deleted student with id: " + id);
    }

    public void updateAge(Integer id, Integer age) {
        jdbcTemplateObject.update(UPDATE_STUDENT_QUERY, age, id);
        logger.info("Updated student with id: " + id);
    }
}
