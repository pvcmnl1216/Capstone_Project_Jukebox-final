package com.niit.jdp.Repository;

import com.niit.jdp.Model.Playlist;
import com.niit.jdp.Model.Songs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository {
    List<Songs> displayAllSong();

    List<Songs> songSearchBySongName(List<Songs> songList, String name);

    List<Songs> songSearchByGenre(List<Songs> songList, String genre);

}
