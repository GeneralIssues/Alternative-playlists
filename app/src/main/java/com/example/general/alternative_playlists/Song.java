package com.example.general.alternative_playlists;

/**
 * Created by General on 28/04/2015.
 */

public class Song {
    public int _songID;
    public String _songName;

    Song(int songID, String songName){
    _songID = songID;
    _songName = songName;
    }

    public int get_songID() {
        return _songID;
    }

    public void set_songID(int _songID) {
        this._songID = _songID;
    }

    public String get_songName() {
        return _songName;
    }

    public void set_songName(String _songName) {
        this._songName = _songName;
    }
}
