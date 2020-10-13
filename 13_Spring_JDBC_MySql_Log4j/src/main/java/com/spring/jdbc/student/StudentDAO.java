package com.spring.jdbc.student;

import com.spring.jdbc.student.Student;

import javax.sql.DataSource;
import java.util.List;

public interface StudentDAO {
    public void setDataSource(DataSource ds);

    public void createStudent(String name, Integer age);

    public Student getStudent(Integer id);

    public List<Student> listAllStudents();

    public void delete(Integer id);

    public void updateAge(Integer id, Integer age);
}
