package com.kyk.soundstory.services;

import com.kyk.soundstory.entities.AlbumEntity;
import com.kyk.soundstory.mappers.AlbumMapper;
import com.kyk.soundstory.results.CommonResult;
import com.kyk.soundstory.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AlbumService {
    private final AlbumMapper albumMapper;

    @Autowired
    public AlbumService(AlbumMapper albumMapper) {
        this.albumMapper = albumMapper;
    }


    public AlbumEntity getAlbum(int albumId) {
        return this.albumMapper.selectAlbumByAlbumId(albumId);
    }

    public List<AlbumEntity> getAllAlbums() {
        return this.albumMapper.selectAllAlbum();
    }


    public Result<?> addAlbum(AlbumEntity album) {
        return this.albumMapper.insertAlbum(album) > 0
                ? CommonResult.SUCCESS
                : CommonResult.FAILURE;
    }

    public List<AlbumEntity> getAlbumsByArtistId(int artistId) {
        return this.albumMapper.selectAlbumsByArtistId(artistId);
    }

    public void updateAlbum(AlbumEntity album) {
        albumMapper.updateAlbum(album);
    }

    public CommonResult deleteAlbum(int albumId) {
        return this.albumMapper.deleteAlbum(albumId) > 0
                ? CommonResult.SUCCESS
                : CommonResult.FAILURE;
    }

    public List<AlbumEntity> getRandomAlbum(int count) {
        List<AlbumEntity> allAlbums = this.albumMapper.selectAllAlbum();
        Collections.shuffle(allAlbums);
        return allAlbums.subList(0, Math.min(count, allAlbums.size()));
    }
}