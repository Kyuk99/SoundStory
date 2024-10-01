package com.kyk.soundstory.mappers;

import com.kyk.soundstory.entities.CommentEntity;
import com.kyk.soundstory.vos.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {

    int getCommentsCount(PageVo pageVo);

    CommentEntity[] selectCommentsByPage(PageVo pageVo);

    int insertComment(CommentEntity comment);

    int deleteCommentByIndex(@Param("index") int index);

    CommentEntity selectCommentByIndex(@Param("index")int index); //

    CommentEntity[] selectCommentAllByArtistId(int artistId); //

}

