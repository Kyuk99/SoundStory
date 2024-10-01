package com.kyk.soundstory.mappers;

import com.kyk.soundstory.entities.ArtistEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArtistMapper {
    int deleteArtist(@Param("artistId")int artistId);

    int updateArtist(ArtistEntity artist);

    List<ArtistEntity> selectAllArtist();


    int insertArtist(ArtistEntity artist);
    ArtistEntity selectArtistByArtistId(@Param("artistId") int artistId);

    ArtistEntity[] selectPopularArtists(@Param("limit") int limit); //

    ArtistEntity getArtistName(@Param("name") String name);
}
