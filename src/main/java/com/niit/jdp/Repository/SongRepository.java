/*
 * Author Name: Philip Meshach
 * Date: 03-11-2022
 * Praise The Lord
 */
package com.niit.jdp.Repository;

import com.niit.jdp.Model.Songs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongRepository implements Repository<Songs>{
    @Override
    public List<Songs> getAll(Connection connection) throws SQLException {
        String readQuery = "SELECT * FROM `jukebox`.`song`;";
        List<Songs> songsList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet songsResultSet = statement.executeQuery(readQuery);
            while (songsResultSet.next()) {
                int songId = songsResultSet.getInt("song_id");
                String songName = songsResultSet.getString("song_name");
                String artistName = songsResultSet.getString("artist_name");
                String genre = songsResultSet.getString("genre");
                String duration = songsResultSet.getString("duration");
                String songPath = songsResultSet.getString("Song path");
                System.out.format("%s     %n%s     %n%s     %n%s%n", "Song ID :" + songsResultSet.getInt(1) + " ", "Song Name :" + songsResultSet.getString(2) + " ", "Artist Name :" + songsResultSet.getString(3) + " ", "Genre :" + songsResultSet.getString(4));
                System.out.println();
                Songs songs = new Songs(songId, songName, artistName, genre, duration, songPath);
                songsList.add(songs);
            }
        }
        return songsList;
    }

    @Override
    public Songs getById(Connection connection, int id) throws SQLException {
        String searchQuery = "SELECT * FROM `jukebox`.`song` WHERE(`song_id` = ?);";
        Songs songs = new Songs();
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet songsResultSet = preparedStatement.executeQuery();
            while (songsResultSet.next()) {
                int songId = songsResultSet.getInt("song_id");
                String songName = songsResultSet.getString("song_name");
                String artistName = songsResultSet.getString("artist_name");
                String genre = songsResultSet.getString("genre");
                String duration = songsResultSet.getString("duration");
                String songPath = songsResultSet.getString("Song path");
                songs = new Songs(songId, songName, artistName, genre, duration, songPath);
                if (songId == 0) {
                    throw new SongNotFoundException("The song is not in the list!! Try Valid choice.");
                }
            }
        } catch (SongNotFoundException e) {
            throw new RuntimeException(e);
        }
        return songs;
    }

    @Override
    public boolean deleteById(Connection connection, int id) throws SQLException {
        String deleteQuery = "DELETE FROM `jukebox`.`song` WHERE (`song_id` = ?);";
        int numberOfRowsAffected;
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);
            numberOfRowsAffected = preparedStatement.executeUpdate();
        }
        return numberOfRowsAffected > 0;
    }

}
