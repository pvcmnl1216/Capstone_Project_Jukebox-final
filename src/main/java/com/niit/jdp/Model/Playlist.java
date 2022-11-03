/*
 * Author Name: Philip Meshach
 * Date: 03-11-2022
 * Praise The Lord
 */
package com.niit.jdp.Model;

public class Playlist {
    private int playlistId;
    private String playlistName;
    private int songId;
    private String songName;

    public Playlist() {
    }

    public Playlist(int playlistId, String playlistName, int songId, String songName) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.songId = songId;
        this.songName = songName;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    @Override
    public String toString() {
        return "Playlist ID : " + getPlaylistId() + ", Playlist Name : " + getPlaylistName() + ", Song ID : " + getSongId() + ", Song Name : " + getSongName();
    }
}
