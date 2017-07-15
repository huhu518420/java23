package com.kaishengit.myBatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaishengit.entity.User;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.util.MyBatisUtil;

public class UserMapperTest {

	private Logger logger = LoggerFactory.getLogger(UserMapperTest.class);
	
	private SqlSession sqlSession;
	private UserMapper userMapper;
	
	@Before
	public void before() {
		sqlSession = MyBatisUtil.getSqlsession();
		userMapper = sqlSession.getMapper(UserMapper.class);
	} 
	
	@After
	public void after() {
		sqlSession.close();
	}
	
	@Test
	public void findById() {
		SqlSession sqlSession = MyBatisUtil.getSqlsession();
		
		//!!! MyBatis根据定义的Mapper接口动态的生成该接口的实现类
		//接口指向实现类
		//动态代理模式
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findById(1);
		System.out.println(user.getUserName());
		logger.debug("user: {}",user);
		
		sqlSession.close();
	}

	
	@Test
	public void findAll() {
		SqlSession sqlSession = MyBatisUtil.getSqlsession();
		
		//!!! MyBatis根据定义的Mapper接口动态的生成该接口的实现类
		//接口指向实现类
		//动态代理模式
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> userList = userMapper.findAll();
		for(User users : userList) {
			System.out.println(users.getUserName());
		}
		
		sqlSession.close();
	}
	
	@Test
	public void save() {
		SqlSession sqlSession = MyBatisUtil.getSqlsession();
		
		//!!! MyBatis根据定义的Mapper接口动态的生成该接口的实现类
		//接口指向实现类
		//动态代理模式
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = new User();
		user.setUserName("花花");
		user.setAddress("上海");
		user.setPassword("123456");
		
		
		userMapper.svae(user);
		
		System.out.println(user.getId());
		
		sqlSession.commit();
		sqlSession.close();
	}
}
