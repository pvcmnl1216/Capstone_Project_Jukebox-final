package com.niit.jdp.Repository;

import com.niit.jdp.Model.Playlist;
import com.niit.jdp.Model.Songs;
import com.niit.jdp.Service.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepository {

    DatabaseService databaseService = new DatabaseService();



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


    public List<Playlist> ShowPlayList() throws SQLException, PlaylistNotFoundException {
        String playList = "";
        int count = 0;
        List<Playlist> playListsName;
        playListsName = new ArrayList<>();
        Connection getConnection = databaseService.connect();
        String query = "Select * from playlist;";
        Statement statement = getConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            playList = resultSet.getString(1);
            count++;
            playListsName.add(new Playlist(playList, count));
            if (playListsName == null) {
                throw new PlaylistNotFoundException("Playlist is empty");
            }
        }
        return playListsName;
    }

    public List<Songs> getSongFromList(int playListId, List<Songs> songList) {
        List<Songs> getSong = new ArrayList<>();
        Connection getConnection = databaseService.connect();
        String query = "Select * from playlist where playList_Id = " + playListId;

        try {
            PreparedStatement preparedStatement = getConnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int playListIdFromTable = resultSet.getInt(1);
                int songId = resultSet.getInt(3);
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
        String query = "Insert into playlist values(?,?);";
        try {
            PreparedStatement preparedStatement = getConnection.prepareStatement(query);
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(3, songId);
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