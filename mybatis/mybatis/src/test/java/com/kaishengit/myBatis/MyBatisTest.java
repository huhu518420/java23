package com.kaishengit.myBatis;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaishengit.entity.User;

import com.kaishengit.util.MyBatisUtil;


/**
 * 工厂读取和操作数据库配置文件测试类
 * 执行sql 读取主配置文件
 * @author huhu5
 *
 */
public class MyBatisTest {
	//将MyBatisTest类加入到日志中
	private Logger logger = LoggerFactory.getLogger(MyBatisTest.class);
	@Test
	public void first() throws IOException {
		
		 //1.加载配置文件
		//2.创建SqlSessionFactory
		//3.创建SqlSession
		//4.用SqlSession来操作数据库
		//1. 加载配置文件
		/*SqlSession sqlSession = MyBatisUtil.getSqlsession();
		
		//4.用SqlSession来操作数据库
		//User user = sqlSession.selectOne("com.kaishengit.mapper.UserMaooer.findById", 1);
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = mapper.findById(1);
		
		System.out.println(user.getUserName());
		System.out.println(user.getAddress());
		//5.关闭Session
		sqlSession.close();
		*/
	}
	
	@Test
	public void findAll() throws IOException {
		
		SqlSession sqlSession = MyBatisUtil.getSqlsession();
		
		//4.用SqlSession来操作数据库
		List<User> userList =  sqlSession.selectList("com.kaishengit.mapper.UserMapper.findAll");
		
		for(User users:userList) {
			System.out.println(users.getUserName());
		}
		
		//5.关闭Session
		sqlSession.close();
		
	}
	
	
	@Test
	public void findByNameAndPassword() throws IOException {
		
		SqlSession sqlSession = MyBatisUtil.getSqlsession();
		
		//4.用SqlSession来操作数据库
		List<User> userList =  sqlSession.selectList("com.kaishengit.mapper.UserMapper.findByMapParam");
		
		for(User user : userList) {
			//日志输出形式
			logger.debug("{} -> {}",user.getUserName(),user.getPassword());
			//user.getUserName() + "->" + user.getPassword()
		}
		
		//5.关闭Session
		sqlSession.close();
		
	}
	
	
	
	
}

