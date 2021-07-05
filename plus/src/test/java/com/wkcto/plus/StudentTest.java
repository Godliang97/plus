package com.wkcto.plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wkcto.plus.entity.Student;
import com.wkcto.plus.mapper.StudentMapper;
import jdk.nashorn.internal.ir.CallNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Test
    public void testAllEq() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        //组成条件
        HashMap<String, Object> param = new HashMap<>();
        //map<key,value> key列名， value：查询的值
        param.put("name", "张三");
        param.put("age", 29);
        param.put("status", 1);

        qw.allEq(param);
        //调用MP自己的查询方法
        //SELECT id,name,age,email,status FROM student WHERE name = ? AND age = ? AND status = ?
        List<Student> students = studentDao.selectList(qw);
        students.forEach(stu -> System.out.println(stu));
    }

    /**
     * 1)Map对象中有key的value是null
     * 使用的是  qw.allEq(param,true);
     * 结果：WHERE name = ? AND age IS NULL
     * <p>
     * 2)Map对象中有key的value是null
     * qw.allEq(param,false);
     * 结果是：WHERE name = ?
     * <p>
     * 结论：
     * allEq(map,boolean)
     * true:处理null值，where 条件加入 字段 is null
     * false：忽略null，不作为where 条件
     */
    @Test
    public void testAllEq2() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        //组成条件
        HashMap<String, Object> param = new HashMap<>();
        param.put("name", "张三");
        //age 是null
        param.put("age", null);
        //allEq 第二个参数为true
        qw.allEq(param, false);
        List<Student> students = studentDao.selectList(qw);
        students.forEach(stu -> System.out.println(stu));

    }

    /**
     * eq使用
     * eq("列名",值)
     */
    @Test
    public void testEq() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        //组成条件
        qw.eq("name", "李四");
        //WHERE name = ?
        List<Student> students = studentDao.selectList(qw);
        students.forEach(stu -> System.out.println("查询eq:" + stu));
    }

    /**
     * ne使用
     * ne表示不等于<>
     * <p>
     * ne("列名",值)
     */
    @Test
    public void testNe() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        //组成条件
        qw.ne("name", "张三");
        //WHERE name <> ?
        List<Student> students = studentDao.selectList(qw);
        students.forEach(stu -> System.out.println("查询ne:" + stu));
    }

    /**
     * gt 大于( > )
     */
    @Test
    public void testGt() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        //组成条件
        qw.gt("age", 30);
        //WHERE age > ?
        List<Student> students = studentDao.selectList(qw);
        students.forEach(stu -> System.out.println("stu:" + stu));
    }

    /**
     * ge 大于等于 ( >= )
     */

    @Test
    public void testGe() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.ge("age", 31);
        //WHERE age >= ?
        List<Student> students = studentDao.selectList(qw);
        students.forEach(stu -> System.out.println("student:" + stu));
    }

    /**
     * lt 小于 ( < )
     */
    @Test
    public void testLt() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.lt("age", 31);
        //WHERE age < ?
        List<Student> students = studentDao.selectList(qw);
        students.forEach(stu -> System.out.println("student:" + stu));
    }

    /**
     * le 小于等于 ( <= )
     */
    @Test
    public void testLe() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.le("age", 31);
        //age <= ?
        List<Student> students = studentDao.selectList(qw);
        students.forEach(stu -> System.out.println("student:" + stu));
    }

    /**
     * between ( ? and ?)
     */

    @Test
    public void testBetween() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        //between("列名",开始值,结束值)
        qw.between("age", 21, 29);
        //where age >= 21 and age <= 29
        List<Student> students = studentDao.selectList(qw);
        students.forEach(stu -> System.out.println(stu));
    }

    /**
     * notbetween(不在范围区间内)
     */
    @Test
    public void testNotBetween() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.notBetween("age", 22, 29);
        //WHERE age NOT BETWEEN ? AND ?
        //where age <22 or age >29
        List<Student> students = studentDao.selectList(qw);
        students.forEach(stu -> System.out.println(stu));
    }

    /**
     * like 匹配某个值
     */
    @Test
    public void testLike() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.like("name", "张");
        //WHERE name LIKE ?  %张%
        List<Student> students = studentDao.selectList(qw);
        students.forEach(stu -> System.out.println(stu));
    }

    /**
     * notLike 不匹配某个值
     */
    @Test
    public void testNotLike() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.notLike("name", "张");
        //WHERE name NOT LIKE ? %张%
        List<Student> students = studentDao.selectList(qw);
        students.forEach(stu -> System.out.println(stu));
    }

    /**
     * likeLeft "%值"
     */

    @Test
    public void testLikeLeft() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.likeLeft("name", "张");
        //WHERE name LIKE ?
        List<Student> students = studentDao.selectList(qw);
        students.forEach(stu -> System.out.println(stu));
    }

    /**
     * likeRight "%值"
     */

    @Test
    public void testLikeRight() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.likeRight("name", "李");
        //WHERE name LIKE ?
        List<Student> students = studentDao.selectList(qw);
        students.forEach(stu -> System.out.println(stu));
    }

    /**
     * isNull 判断字段是 is null
     */
    @Test
    public void testIsNull() {
        QueryWrapper<Object> qw = new QueryWrapper<>();
        //判断email is null
        //WHERE email IS NULL
        qw.isNull("email");
        print(qw);
    }

    /**
     * isNotNull 判断字段是 is not null
     */
    @Test
    public void testIsNotNull() {
        QueryWrapper<Object> qw = new QueryWrapper<>();
        //判断email is not null
        //WHERE email IS NOT NULL
        qw.isNotNull("email");
        print(qw);
    }


    /**
     * in 值列表
     */
    @Test
    public void testIn() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        //in(列名，多个值的列表)
        //WHERE name IN (?,?,?)
        qw.in("name", "张三", "李四", "旺财");
        print(qw);
    }

    @Test
    public void testIn2() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        //WHERE status IN (?,?)
        qw.in("status", list);
        print(qw);
    }

    /**
     * notIn 不在值列表
     */
    @Test
    public void testNotIn() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        //in(列名，多个值的列表)
        //WHERE name IN (?,?,?)
        qw.notIn("name", "张三", "李四", "旺财");
        print(qw);
    }

    /**
     * inSql() ：使用子查询
     */
    @Test
    public void testInSql() {
        QueryWrapper<Object> qw = new QueryWrapper<>();
        qw.inSql("age", "select age from student where id=1");
        //WHERE age IN (select age from student where id=1)
        print(qw);
    }


    /**
     * notInSql() ：使用子查询
     */
    @Test
    public void testNotInSql() {
        QueryWrapper<Object> qw = new QueryWrapper<>();
        qw.notInSql("age", "select age from student where id=1");
        //WHERE age NOT IN (select age from student where id=1)
        print(qw);
    }

    /**
     * groupBy 分组
     */
    @Test
    public void testGroupby() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.select("name,count(*) personNumbers");//select name,count(*) personNumbers
        qw.groupBy("name");
        //SELECT name,count(*) personNumbers FROM student GROUP BY name
        print(qw);
    }

    /**
     * orderbyAsc 按字段升序
     */

    @Test
    public void testOrderByAsc() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.orderByAsc("name", "age");
        //SELECT id,name,age,email,status FROM student ORDER BY name ASC , age ASC
        print(qw);
    }

    /**
     * orderByDesc 按字段降序
     */
    @Test
    public void testOrderByDesc() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.orderByDesc("name", "age");
        //SELECT id,name,age,email,status FROM student ORDER BY name DESC , age DESC
        print(qw);
    }

    /**
     * order 指定字段和排序方向
     * boolean condition:条件内容是否加入到sql语句的后面
     * true：条件加入到sql语句
     * FROM student ORDER BY name ASC
     * <p>
     * false：条件不加入到sql语句
     * From student
     */
    @Test
    public void testOrder() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.orderBy(true, true, "name")
                .orderBy(true, false, "age")
                .orderBy(true, false, "email");
        //name ASC , age DESC , email DESC
        print(qw);
    }

    /**
     * and,or 方法
     */
    @Test
    public void testOr() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.eq("name", "张三")
                .or()
                .eq("age", 22);
        print(qw);
    }

    /**
     * last:拼接sql语句到MP的sql语句的最后
     */
    @Test
    public void testLast() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.eq("name", "李四")
                .or()
                .eq("age", 31)
                .last("limit 1");
        //SELECT id,name,age,email,status FROM student WHERE name = ? OR age = ? limit 1
        print(qw);
    }

    /**
     * exists:判断条件
     * <p>
     * notExists
     */
    @Test
    public void testExists() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        //qw.exists("select id from student where age >40");
        //SELECT id,name,age,email,status FROM student WHERE EXISTS (select id from student where age >40)

        qw.notExists("select id from student where age >40");
        //SELECT id,name,age,email,status FROM student WHERE NOT EXISTS (select id from student where age >40)
        print(qw);
    }

    private void print(QueryWrapper qw) {
        List<Student> students = studentDao.selectList(qw);
        students.forEach(stu -> System.out.println(stu));
    }

}
