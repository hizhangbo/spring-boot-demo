package io.github.hizhangbo.mapper;

import io.github.hizhangbo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper
public interface UserMapper {

//    @Select("select * from t_user")
    List<User> findAllUsers();

}
