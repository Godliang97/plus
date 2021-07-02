package com.wkcto.plus;

import com.wkcto.plus.entity.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeptARTest {

    @Test
    public void testARTest() {
        //定义dept的实体
        Dept dept = new Dept();
        dept.setName("市场部");
        dept.setMobile("010-5555555");
        dept.setManager(1);
        //调用实体对象自己的方法，完成对象自身到数据库的添加操作
        boolean flag = dept.insert();
        System.out.println("ar insert result:" + flag);
    }

    /**
     * 按主键更新
     */
    @Test
    public void testARUpdate() {
        //定义实体Dept
        Dept dept = new Dept();
//        dept.setId(1);
        dept.setName("市场部");
        dept.setMobile("010-22222222");
        dept.setManager(2);
        //根据主键id更新记录
        //UPDATE dept SET name=?, mobile=?, manager=? WHERE id=? //id =
        boolean result = dept.updateById();
        System.out.println("ar updateById result:" + result);
    }

    @Test
    public void testUpdateById2() {
        //定义实体Dept
        Dept dept = new Dept();
//        dept.setId(1);
        dept.setMobile("010-33333333");
        //name manager 是没有修改的

        //根据主键id更新记录
        //UPDATE dept SET mobile=? WHERE id=?
        //null的属性值不做更新处理，在update中没有null的字段
        boolean result = dept.updateById();
        System.out.println("ar updateById:" + result);
    }

    /**
     * deleteById()删除操作即使没有从数据库中删除数据，也返回是true
     */
    @Test
    public void testARDeleteById() {
        Dept dept = new Dept();
        boolean result = dept.deleteById(1);
        System.out.println("ar deleteById result:" + result);
    }


    @Test
    public void testARDeleteById2() {
        Dept dept = new Dept();
//        dept.setId(2);
        boolean result = dept.deleteById();
        System.out.println("ar deleteById result:" + result);
    }

    /**
     * selectById
     * 1.按实体的主键能查找出数据，返回对象
     * 2.按实体的主键不能查出数据，是null，不报错
     */

    /**
     * 根据对象自己的id属性查询
     */
    @Test
    public void testARSelectById() {
        Dept dept = new Dept();
        //设置主键的值
//        dept.setId(3);
        //调用查询方法
        Dept dept1 = dept.selectById();
        System.out.println("ar selectById result:" + dept1);

    }

    /**
     * 根据参数id值查询
     * 1.主键有记录，返回实体对象
     * 2.主键没有记录，返回是null
     */
    @Test
    public void testARSelectById2() {
        Dept dept = new Dept();
        Dept dept1 = dept.selectById(1);
        System.out.println("ar selectById result:" + dept1);
    }

}
