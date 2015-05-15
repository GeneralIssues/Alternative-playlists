package com.example.general.alternative_playlists;

/**
 * Created by General on 28/04/2015.
 */

public class Song {
    private String songName;
    private int albumID;

    Song(String _songName, int _albumID){
    songName = _songName;
    //_albumID = albumID;
    albumID = _albumID;


    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String _songName) {
        this.songName = _songName;
    }

    public int getAlbumID() {
        return albumID;
    }
}
