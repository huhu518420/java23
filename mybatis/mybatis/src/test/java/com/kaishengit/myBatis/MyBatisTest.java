package com.kaishengit.myBatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


/**
 * 工厂读取和操作数据库配置文件测试类
 * 执行sql 读取主配置文件
 * @author huhu5
 *
 */
public class MyBatisTest {
	
	
	@Test
	public void first() throws IOException {
		
		 //1.加载配置文件
		//2.创建SqlSessionFactory
		//3.创建SqlSession
		//4.用SqlSession来操作数据库
		
		//1.加载配置文件
		//从classpath中读取mybatis.xml配置文件.。。。包导入不成功时降低版本即可
		Reader reader = Resources.getResourceAsReader("mybatis.xml");
		//2.创建SqlSessionFactory
		//根据SqlSessionFactoryBuilder对象构建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//3.创建SqlSession
		//根据SqlSessionFactory对象创建SqlSession对象
		SqlSession sqlSession = sessionFactory.openSession();
		//4.用SqlSession来操作数据库
		
		
		//5.关闭Session
		sqlSession.close();
		
		
		
		
		
		
		
		
		
		
	}
	
}
