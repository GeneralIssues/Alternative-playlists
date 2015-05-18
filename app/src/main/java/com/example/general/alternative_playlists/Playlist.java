package com.example.general.alternative_playlists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by General on 28/04/2015.
 */

public class Playlist {
    public int _playlistID;
    public String _playlistName;
    List<String> _songs = new ArrayList<>();

    Playlist(int playlistID, String playlistName, List<String> songs){
        _playlistID = playlistID;
        _playlistName = playlistName;
        _songs = songs;
    }

    public int get_albumID() {
        return _playlistID;
    }

    public void set_albumID(int _albumID) {
        this._playlistID = _albumID;
    }

    public String get_albumName() {
        return _playlistName;
    }

    public void set_albumName(String _albumName) {
        this._playlistName = _albumName;
    }

    public List<String> get_songs() {
        return _songs;
    }

    public void set_songs(List<String> _songs) {
        this._songs = _songs;
    }
}
