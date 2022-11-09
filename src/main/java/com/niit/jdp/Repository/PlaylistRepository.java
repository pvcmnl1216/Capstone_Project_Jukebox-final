package com.niit.jdp.Repository;

import com.niit.jdp.Model.Playlist;
import com.niit.jdp.Model.Songs;
import com.niit.jdp.Service.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepository {

    static DatabaseService databaseService = new DatabaseService();



    public void addIntoDatabase(String playListName) {
        Connection getConnection = databaseService.connect();
        String query = "Insert into playlist values(?);";
        try {
            PreparedStatement preparedStatement = getConnection.prepareStatement(query);
            preparedStatement.setString(1, playListName);
            int row = preparedStatement.executeUpdate();
            if (row == 1) {
                System.out.println("Play List successful created");
            } else {
                System.out.println("Play List not created");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public  List<Playlist> showPlaylist() throws SQLException, PlaylistNotFoundException, SongNotFoundException {
        String playlist;
        int count = 0;
        List<Playlist> playlistsName = new ArrayList<>();
        Connection connection = databaseService.connect();
        String query = "select * from `jukebox`.`playlist`;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("Playlist Name" + " " + "Id");
        while (resultSet.next()) {
            count++;
            System.out.println(resultSet.getString(1) + "         " + count);
            System.out.println();
        }
        if (playlistsName.equals(null)) {
            throw new PlaylistNotFoundException("Empty Playlist exception");
        }
        return playlistsName;
    }




    public List<Songs> getSongFromList(int playListId, List<Songs> songList) {
        List<Songs> getSong = new ArrayList<>();
        Connection getConnection = databaseService.connect();
        String query = "Select * from playlist1 where playlist_Id = " + playListId;

        try {
            PreparedStatement preparedStatement = getConnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int playListIdFromTable = resultSet.getInt(1);
                int songId = resultSet.getInt(2);
                for (Songs song : songList) {
                    if (songId == song.getSongId()) {
                        getSong.add(song);
                    }
                }
            }
            if (getSong == null) {
                throw new PlaylistNotFoundException("The following playlist is empty");
            }
        } catch (SQLException | PlaylistNotFoundException e) {
            e.printStackTrace();
        }
        return getSong;
    }

    public void insertSongIntoPlayList(int playlistId, int songId) {
        Connection getConnection = databaseService.connect();
        String query = "Insert into playlist1 values(?,?);";
        try {
            PreparedStatement preparedStatement = getConnection.prepareStatement(query);
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, songId);
            int row = preparedStatement.executeUpdate();
            if (row == 1) {
                System.out.println("Song successful added into list");
            } else {
                System.out.println("Song not added please try again!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}