package com.kyk.soundstory.mappers;

import com.kyk.soundstory.dtos.SearchDto;

import com.kyk.soundstory.entities.ArtistEntity;
import com.kyk.soundstory.entities.SongEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {
    List<ArtistEntity> selectArtistBySearch(SearchDto searchDto);
    List<SongEntity> selectSongBySearch(SearchDto searchDto);
}
