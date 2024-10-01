package com.kyk.soundstory.services;

import com.kyk.soundstory.entities.PlaylistEntity;
import com.kyk.soundstory.mappers.PlaylistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    private final PlaylistMapper playlistMapper;

    @Autowired
    public PlaylistService(PlaylistMapper playlistMapper) {
        this.playlistMapper = playlistMapper;
    }

    public List<PlaylistEntity> getPlaylistsByUserEmail(String userEmail) {
        return playlistMapper.playlistByEmail(userEmail);
    }

    public PlaylistEntity getPlaylistByIndex(int index) {
        return playlistMapper.getPlaylistByIndex(index);
    }
}
