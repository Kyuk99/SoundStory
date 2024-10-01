package com.kyk.soundstory.mappers;

import com.kyk.soundstory.entities.PlaylistSongEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlaylistSongMapper {
    int addSongToPlaylist(PlaylistSongEntity playlistSong);

    int deleteSongFromPlaylist(@Param("songId") int songId);

    List<PlaylistSongEntity> selectSongsByPlaylistIndex(@Param("playlistIndex") int playlistIndex);

}
