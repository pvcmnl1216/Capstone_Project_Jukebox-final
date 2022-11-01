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
}
