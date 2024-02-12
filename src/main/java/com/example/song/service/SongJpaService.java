package com.example.song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.song.model.Song;
import com.example.song.repository.*;

@Service
public class SongJpaService implements SongRepository {

    @Autowired
    private SongJpaRepository jp;

    @Override
    public ArrayList<Song> getSongs() {
        List<Song> songList = jp.findAll();
        ArrayList<Song> songs = new ArrayList<>(songList);
        return songs;
    }

    @Override
    public Song getSongById(int songId) {
        try {
            Song song = jp.findById(songId).get();
            return song;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Song addSong(Song song) {
        jp.save(song);
        return song;
    }

    @Override
    public Song updateSong(int songId, Song song) {
        try {
            Song newSong = jp.findById(songId).get();
            if (song.getSongName() != null)
                newSong.setSongName(song.getSongName());
            if (song.getLyricist() != null)
                newSong.setLyricist(song.getLyricist());
            if (song.getSinger() != null)
                newSong.setSinger(song.getSinger());
            if (song.getMusicDirector() != null)
                newSong.setMusicDirector(song.getMusicDirector());

            jp.save(newSong);
            return newSong;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteSong(int songId) {
        try{
            jp.deleteById(songId);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}