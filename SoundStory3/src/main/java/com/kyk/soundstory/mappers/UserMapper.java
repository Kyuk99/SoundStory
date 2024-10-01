package com.kyk.soundstory.mappers;

import com.kyk.soundstory.entities.EmailAuthEntity;
import com.kyk.soundstory.entities.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insertEmailAuth(EmailAuthEntity emailAuth);

    int insertUser(UserEntity user);

    EmailAuthEntity selectUserByEmailCodeSalt(@Param("email") String email, @Param("code") String code, @Param("salt") String salt);

    UserEntity selectUserByEmail(@Param("email") String email);

    UserEntity selectUserByNickname(@Param("nickname") String nickname);

    int updateEmailAuth(EmailAuthEntity emailAuth);

    int updateUser(UserEntity user);

}
