package com.kyk.soundstory.controllers;

import com.kyk.soundstory.dtos.SearchDto;
import com.kyk.soundstory.entities.ArtistEntity;
import com.kyk.soundstory.entities.SongEntity;
import com.kyk.soundstory.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getSearch() {
        ModelAndView modelAndView = new ModelAndView("index/search");
        modelAndView.addObject("searchDto", new SearchDto());
        return modelAndView;
    }

    @RequestMapping(value = "search", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView postSearch(SearchDto searchDto) {
        ModelAndView modelAndView = new ModelAndView("index/search");
        List<ArtistEntity> artists = searchService.searchArtists(searchDto);
        List<SongEntity> songs = searchService.searchSongs(searchDto);
        modelAndView.addObject("artists", artists);
        modelAndView.addObject("songs", songs);
        return modelAndView;
    }
}
