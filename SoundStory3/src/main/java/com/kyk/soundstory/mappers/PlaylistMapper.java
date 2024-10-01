package com.kyk.soundstory.mappers;

import com.kyk.soundstory.entities.PlaylistEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaylistMapper {
    PlaylistEntity insertPlaylistByIndex(int playlist);
    PlaylistEntity playlistByUserEmail(String Email);
    void insertPlaylist(PlaylistEntity playlist);

    List<PlaylistEntity> playlistByEmail(String Email);

    PlaylistEntity getPlaylistByIndex(int index);



}
