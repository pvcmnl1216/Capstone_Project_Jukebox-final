/*
 * Author Name: Philip Meshach
 * Date: 01-11-2022
 * Praise The Lord
 */
package com.niit.jdp.Model;

public class Songs {
    private int id;
    private String SongName;
    private int userId;

    public Songs() {
    }

    public Songs(int id, String songName, int userId) {
        this.id = id;
        SongName = songName;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
