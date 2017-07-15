package com.kaishengit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Select;

import com.kaishengit.entity.User;

//二级缓存
@CacheNamespace
public interface UserMapper {

	@Select("select * from t_user where id = #{id}")
	@Options(useCache=false)
	User findById(Integer id);
	
	@Select("select * from t_user")
	List<User> findAll();
	
	@Insert("insert into t_user (user_name,adress,password) "
			+ "values (#{userName},#{address},#{password})")
	@Options(useGeneratedKeys=true,keyProperty="id",flushCache=FlushCachePolicy.FALSE)
	void svae(User user);
}
