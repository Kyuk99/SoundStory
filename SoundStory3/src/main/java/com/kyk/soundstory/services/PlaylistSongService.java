package com.kyk.soundstory.services;


import com.kyk.soundstory.entities.PlaylistEntity;
import com.kyk.soundstory.entities.PlaylistSongEntity;
import com.kyk.soundstory.entities.UserEntity;
import com.kyk.soundstory.mappers.PlaylistMapper;
import com.kyk.soundstory.mappers.PlaylistSongMapper;
import com.kyk.soundstory.results.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistSongService {
    private final PlaylistSongMapper playlistSongMapper;
    private final PlaylistMapper playlistMapper;
    @Autowired
    public PlaylistSongService(PlaylistSongMapper playlistSongMapper, PlaylistMapper playlistMapper) {
        this.playlistSongMapper = playlistSongMapper;
        this.playlistMapper = playlistMapper;
    }

    public List<PlaylistSongEntity> getSongsByPlaylist(int playlistIndex) {
        return this.playlistSongMapper.selectSongsByPlaylistIndex(playlistIndex);
    }
    public CommonResult addToPlaylist(UserEntity user, PlaylistSongEntity playlistSong) {
        // 로그인 한 유저가 playlist index 를 select 하고 playolistSong 에 넣고 playlistIndex 를 insert 한다
        PlaylistEntity playlist = this.playlistMapper.playlistByUserEmail(user.getEmail());
        if (playlist == null) {
            return CommonResult.FAILURE; // 또는 적절한 에러 메시지 반환
        }
        playlistSong.setPlaylistIndex(playlist.getIndex());

        return this.playlistSongMapper.addSongToPlaylist(playlistSong) > 0
                ? CommonResult.SUCCESS
                : CommonResult.FAILURE;
    }

    public CommonResult deletePlaylist(int songId) {
        return this.playlistSongMapper.deleteSongFromPlaylist(songId) > 0
                ? CommonResult.SUCCESS
                : CommonResult.FAILURE;
    }
}
