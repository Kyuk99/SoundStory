package com.kyk.soundstory.mappers;

import com.kyk.soundstory.entities.AlbumEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlbumMapper {

    int deleteAlbum(@Param("albumId") int albumId);

    int updateAlbum(AlbumEntity album);

    AlbumEntity selectAlbumByAlbumId(@Param("albumId") int albumId);

    List<AlbumEntity> selectAlbumsByArtistId(@Param("artistId") int artistId);

    List<AlbumEntity> selectAllAlbum();

    int insertAlbum(AlbumEntity album);
}
