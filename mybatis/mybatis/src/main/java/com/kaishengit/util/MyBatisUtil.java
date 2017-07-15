package com.kaishengit.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 将sqlSessionFactory封装为单列
 * @author huhu5
 *
 */
public class MyBatisUtil {

	private static SqlSessionFactory sqlSessionFactory = buliderSessionFactory();

	private static SqlSessionFactory buliderSessionFactory() {
		
			
		try {
			
			//1.加载配置文件
			//从classpath中读取mybatis.xml配置文件.。。。包导入不成功时降低版本即可
			Reader reader = Resources.getResourceAsReader("mybatis.xml");
			//2.创建SqlSessionFactory
			//根据SqlSessionFactoryBuilder对象构建SqlSessionFactory
			SqlSessionFactoryBuilder sessionFactoryBuilder = new SqlSessionFactoryBuilder();
			return sessionFactoryBuilder.build(reader);
			
		} catch (IOException e) {
			
			throw new RuntimeException("执行sqlSessionFactoryBuilder时异常",e);
		}
		
	}
	
	//提供一个获取SqlSessionFactory的公有方法
	public static SqlSessionFactory getSqlSessionFactory() {
		
		return sqlSessionFactory;
	}
	//提供一个获取SqlSession的公有方法
	public static SqlSession getSqlsession() {
		
		return sqlSessionFactory.openSession();
	}
	
}
