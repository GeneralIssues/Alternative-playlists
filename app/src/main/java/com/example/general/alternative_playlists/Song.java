package com.example.general.alternative_playlists;

/**
 * Created by General on 28/04/2015.
 */

public class Song {
    private String _songName;
    private int _albumID;

    Song(String songName){
    _songName = songName;
    //_albumID = albumID;
    }

    public String get_songName() {
        return _songName;
    }

    public void set_songName(String _songName) {
        this._songName = _songName;
    }
}
