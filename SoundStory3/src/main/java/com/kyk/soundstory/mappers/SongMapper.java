package com.kyk.soundstory.mappers;

import com.kyk.soundstory.entities.SongEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SongMapper {
    List<SongEntity> selectAlbumIdByIndex(@Param("albumId") int albumId);

    SongEntity selectSongIdByIndex(@Param("songId") int songId);

    List<SongEntity> selectAllSong();
    int insertSong(SongEntity song);

}
