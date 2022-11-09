/*
 * Author Name: Philip Meshach
 * Date: 03-11-2022
 * Praise The Lord
 */
package com.niit.jdp.Model;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Playlist {
    private int playlistId;
    private String playlistName;

    private Set<Songs> song;

    //create a no args and a parameterised constructor
    public Playlist(int playlistId, String playlistName, List<Songs> songs) {
        song = new TreeSet<>();
    }

    public Playlist(String playlistName, Set<Songs> song) {
        this.playlistName = playlistName;
        this.song = song;
    }

    public Playlist(String playList, int count) {
    }

    public Playlist() {

    }

    //create getter and setter methods for the fields
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

    public Set<Songs> getSong() {
        return song;
    }

    public void setSong(Set<Songs> song) {
        this.song = song;
    }

    //override the equals and hashcode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist that = (Playlist) o;
        return playlistId == that.playlistId && Objects.equals(playlistName, that.playlistName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, playlistName);
    }

    //override the toString method
    @Override
    public String toString() {
        return "PlaylistModel{" + "playlistId=" + playlistId + ", playlistName='" + playlistName + '\'' + '}';
    }
}
