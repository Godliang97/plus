package com.wkcto.plus;

import com.wkcto.plus.entity.Student;
import com.wkcto.plus.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SuppressWarnings("all")
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {

    //定义StudentMapper
    @Autowired
    private StudentMapper studentDao;

    @Test
    public void testInsert() {
        Student student = new Student();
        student.setName("李四");
        student.setEmail("li1234@163.com");
        student.setAge(23);
        student.setStatus(0);
        int rows = studentDao.insertStudent(student);
        System.out.println("insertStudent rows" + rows);
    }

    @Test
    public void testSelectStudentById() {
        Student student = studentDao.selectStudentById(2);
        if (student != null) {
            //其他业务方法
        }
        System.out.println("student:" + student);
    }

    @Test
    public void testSelectByName() {
        List<Student> students = studentDao.selectByName("李四");
        students.forEach(stu -> System.out.println(stu));
    }


}
