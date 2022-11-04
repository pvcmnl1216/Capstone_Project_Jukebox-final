package com.niit.jdp.Service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MusicPlayerService {

    public void playParticular(int songId) {
        Scanner scanner = new Scanner(System.in);
        try {
            DatabaseService connection = new DatabaseService();
            Connection getConnection = connection.connect();
            String query = "select song_path from `jukebox`.`songs` where song_Id = ?";
            PreparedStatement preparedStatement = getConnection.prepareStatement(query);
            preparedStatement.setInt(1, songId);
            //executeQuery this predefines method
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //return the value from the table .
                File file = new File(resultSet.getString(1));
                // create AudioInputStream object
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                // create clip reference
                Clip clip = AudioSystem.getClip();
                // open audioInputStream to the clip
                clip.open(audioStream);
                String response = "";
                while (!response.equals("X")) {
                    System.out.println("P = play/Resume, S = Pause, X= End");
                    response = scanner.next();
                    switch (response) {
                        case ("P"):
                            clip.start();
                            break;
                        case ("S"):
                            clip.stop();
                            break;
                        case ("X"):
                            clip.stop();
                            break;
                        default:
                            System.out.println("Not a valid response");
                    }
                }
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}