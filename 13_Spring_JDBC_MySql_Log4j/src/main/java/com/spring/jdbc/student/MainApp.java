package com.spring.jdbc.student;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

// https://www.tutorialspoint.com/spring/spring_jdbc_example.htm
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        StudentJDBCTemplate studentJDBCTemplate = (StudentJDBCTemplate)context.getBean("studentJDBCTemplate");

        studentJDBCTemplate.createStudent("Zara", 11);
        studentJDBCTemplate.createStudent("Nuha", 2);
        studentJDBCTemplate.createStudent("Ayan", 15);

        List<Student> students = studentJDBCTemplate.listAllStudents();
        for (Student st : students) {
            System.out.println(st);
        }
        System.out.println();

        studentJDBCTemplate.updateAge(2, 20);

        studentJDBCTemplate.delete(3);

        students = studentJDBCTemplate.listAllStudents();
        for (Student st : students) {
            System.out.println(st);
        }

    }
}
