package com.kyk.soundstory.services;

import com.kyk.soundstory.dtos.SearchDto;

import com.kyk.soundstory.entities.ArtistEntity;
import com.kyk.soundstory.entities.SongEntity;
import com.kyk.soundstory.mappers.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final SearchMapper searchMapper;

    @Autowired
    public SearchService(SearchMapper searchMapper) {
        this.searchMapper = searchMapper;
    }

    public List<ArtistEntity> searchArtists(SearchDto searchDto) {
        return searchMapper.selectArtistBySearch(searchDto);
    }

    public List<SongEntity> searchSongs(SearchDto searchDto) {
        return searchMapper.selectSongBySearch(searchDto);
    }
}
