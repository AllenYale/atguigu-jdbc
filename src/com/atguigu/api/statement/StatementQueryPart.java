package com.atguigu.api.statement;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

/**
 * ClassName: StatementQueryPart
 * PackageName: com.atguigu.api.statement
 * Description: 使用statement查询t_user表所有数据
 *
 * @Author: Hanyu
 * @Date: 2023/12/2 - 21:41
 * @Version: v1.0
 */
public class StatementQueryPart {
    public static void main(String[] args) throws SQLException {
        //1. 注册驱动，如果驱动版本是8+版本，导入带cj的包
        DriverManager.registerDriver(new Driver());

        //2. 获取链接；java程序和DB链接
        String ip = "127.0.0.1";
        String username = "root";
        String password = "123456";
        String dbName = "atguigu";
        String port = "3306";

        /**
         * 参数1：url
         *      jdbc://数据库厂商名://ip地址:prot/数据库名
         *      jdbc://mysql://127.0.0.1:3306/atguigu
         */
        String url = "jdbc:mysql://"+ip+":"+port+"/"+dbName;

        //3. 获取连接
        Connection connection = DriverManager.getConnection(url, username, password);

        //4. 获取Statement
        Statement statement = connection.createStatement();

        //5. 发送sql
        String sql = "select * from t_user";
        ResultSet resultSet = statement.executeQuery(sql);

        //6. 处理结果集
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String pwd = resultSet.getString("password");
            String nickName = resultSet.getString("nickname");
            System.out.println(id+"--"+account+"--"+pwd+"--"+nickName);
        }

        //7. 关闭资源
        resultSet.close();
        statement.close();
        connection.close();


    }
}
