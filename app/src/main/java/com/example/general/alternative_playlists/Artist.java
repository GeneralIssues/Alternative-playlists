package com.example.general.alternative_playlists;

/**
 * Created by General on 28/04/2015.
 */

public class Artist {
    public int _artistID;
    public String _artistName;
    String[] _songs;

    Artist(int artistID, String artistName, String[] songs){
         _artistID = artistID;
        _artistName = artistName;
        _songs = songs;
    }

    public int get_ID() {
        return _artistID;
    }

    public void set_ID(int _ID) {
        this._artistID = _ID;
    }

    public String get_artistName() {
        return _artistName;
    }

    public void set_artistName(String _artistName) {
        this._artistName = _artistName;
    }

    public String[] get_songs() {
        return _songs;
    }

    public void set_songs(String[] _songs) {
        this._songs = _songs;
    }
}
