package com.atguigu.api.preparedstatement;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ClassName: PSOtherPart
 * PackageName: com.atguigu.api.preparedstatement
 * Description:
 * prepareStatement 批量增加测试
 * @Author: Hanyu
 * @Date: 2023/12/3 - 9:55
 * @Version: v1.0
 */
public class PSOtherPart {

    @Test
    public void test() throws ClassNotFoundException, SQLException {
        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获取链接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu?rewriteBatchedStatements=true", "root", "123456");
        //3.编写sql，动态值部分用？代替
        String sql = "insert into t_user (account,PASSWORD,nickname) values (?,?,?)";
        //4.创建prepareStatement，传入sql语句结果？
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //5.占位符赋值，执行sql
//        preparedStatement.setObject();
        //插入10000条数据
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            preparedStatement.setString(1, "11test" + i);
            preparedStatement.setString(2, "11test" + i);
            preparedStatement.setString(3, "11test" + i);
//            preparedStatement.executeUpdate();
            //不执行，追加到values后面
            preparedStatement.addBatch();
        }
        //执行批量操作
        preparedStatement.executeBatch();
        //6.输出结果
        long end = System.currentTimeMillis();
        System.out.println("exe succeed " + (end - start)+ "ms passed");

        //7.关闭资源
        preparedStatement.close();
        connection.close();

        //7.关闭资源
        preparedStatement.close();
        connection.close();
    }
}
