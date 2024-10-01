package com.kyk.soundstory.controllers;

import com.kyk.soundstory.entities.*;
import com.kyk.soundstory.results.CommonResult;
import com.kyk.soundstory.results.Result;
import com.kyk.soundstory.services.*;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService albumService;
    private final ArtistService artistService;
    private final SongService songService;
    private final PlaylistService playlistService;
    private final PlaylistSongService playlistSongService;


    @Autowired
    public AlbumController(AlbumService albumService, ArtistService artistService, SongService songService, PlaylistSongService playlistSongService, PlaylistService playlistService) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.songService = songService;
        this.playlistSongService = playlistSongService;
        this.playlistService = playlistService;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView addAlbum() {
        ModelAndView modelAndView = new ModelAndView("index/albumAdd");
        List<ArtistEntity> artists = this.artistService.getAllArtists();
        modelAndView.addObject("artists", artists);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String postIndex(
            @RequestParam("_thumbnail") MultipartFile thumbnail,
            @RequestParam("artistId") int artistId,
            AlbumEntity album) throws IOException {

        album.setImageData(thumbnail.getBytes());
        album.setImageContentType(thumbnail.getContentType());
        album.setImageFileName(thumbnail.getOriginalFilename());
        album.setArtistId(artistId); // 아티스트 ID 설정

        Result<?> result = this.albumService.addAlbum(album);
        JSONObject responseObject = new JSONObject();
        responseObject.put("result", result.name().toLowerCase());

        if (result == CommonResult.SUCCESS) {
            responseObject.put("albumId", album.getAlbumId());
        }
        return responseObject.toString();
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getAlbum(@RequestParam("albumId") int albumId,
                                 HttpSession session) {
        ModelAndView modelAndView;
        UserEntity user = (UserEntity) session.getAttribute("user");
        AlbumEntity album = this.albumService.getAlbum(albumId);
        ArtistEntity artist = this.artistService.getArtist(album.getArtistId());
        List<SongEntity> songs = this.songService.getSongsByAlbumId(albumId);

        if (user != null) {
            List<PlaylistEntity> playlists = this.playlistService.getPlaylistsByUserEmail(user.getEmail());


            modelAndView = new ModelAndView("index/album");
            modelAndView.addObject("playlists", playlists); // 전체 플레이리스트를 추가
        } else {
            // 로그인되지 않은 경우
            modelAndView = new ModelAndView("index/album"); // 로그인되지 않은 사용자용 뷰 설정
            modelAndView.addObject("playlists", new ArrayList<>()); // 빈 리스트를 추가
        }
        modelAndView.addObject("album", album);
        modelAndView.addObject("artist", artist);
        modelAndView.addObject("songs", songs);
        return modelAndView;
    }

    @RequestMapping(value = "addPlaylist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addSongToPlaylist(@SessionAttribute("user") UserEntity user, PlaylistSongEntity playlistSong) {
        CommonResult result = playlistSongService.addToPlaylist(user, playlistSong);
        JSONObject responseObject = new JSONObject();
        responseObject.put("result", result.name().toLowerCase());
        return responseObject.toString();
    }

    @RequestMapping(value = "/AlbumUpdate", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String updateAlbumInfo(AlbumEntity album) {
        this.albumService.updateAlbum(album);
        return "redirect:/album?albumId=" + album.getAlbumId();
    }


    @RequestMapping(value = "/AlbumDelete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteAlbum(@RequestParam("albumId") int albumId) {
        CommonResult result = this.albumService.deleteAlbum(albumId);
        JSONObject responseObject = new JSONObject();
        responseObject.put("result", result.name().toLowerCase());
        return responseObject.toString();
    }


    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@RequestParam("index") int index) {
        AlbumEntity album = this.albumService.getAlbum(index);

        if (album == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(album.getImageContentType()))
                .contentLength(album.getImageData().length)
                .body(album.getImageData());

    }
}
