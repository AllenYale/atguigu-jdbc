package com.atguigu.api.preparedstatement;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ClassName: PSCRUDTest
 * PackageName: com.atguigu.api.preparedstatement
 * Description:
 *
 * @Author: Hanyu
 * @Date: 2023/12/3 - 8:07
 * @Version: v1.0
 */
public class PSCRUDTest {

    @Test
    public void test() throws ClassNotFoundException, SQLException {
        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获取链接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu","root","123456");
        //3.编写sql，动态值部分用？代替
        String sql = "insert into t_user (account,PASSWORD,nickname) values (?,?,?);";
        //4.创建prepareStatement，传入sql语句结果？
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //5.占位符赋值，执行sql
//        preparedStatement.setObject();
        preparedStatement.setString(1,"allenAcc");
        preparedStatement.setString(2,"allenPWD");
        preparedStatement.setString(3,"allenNickName");

        int executeUpdateRows = preparedStatement.executeUpdate();

        //6.输出结果
        if(executeUpdateRows>0) {
            System.out.println("update succeed!");
        }else {
            System.out.println("update fail...");
        }


        //7.关闭资源
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void test1(){

    }
}
