package com.niit.jdp;

import com.niit.jdp.Model.ExistingUser;
import com.niit.jdp.Model.Playlist;
import com.niit.jdp.Model.Songs;
import com.niit.jdp.Model.UserDetails;
import com.niit.jdp.Repository.PlaylistNotFoundException;
import com.niit.jdp.Repository.PlaylistRepository;
import com.niit.jdp.Repository.SongNotFoundException;
import com.niit.jdp.Repository.SongRepository;
import com.niit.jdp.Service.DatabaseService;
import com.niit.jdp.Service.MusicPlayerService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, SongNotFoundException, PlaylistNotFoundException {

        UserDetails user=new UserDetails();
        user.userDisplay();
        //creating scanner object for take input from user
        Scanner scanner = new Scanner(System.in);
        // creating object of song repository class for call method
        SongRepository songRepository = new SongRepository();
        // creating object of playlist repository class for call method
        PlaylistRepository playListRepository = new PlaylistRepository();
        //creating object os song service class for call method
        MusicPlayerService musicPlayerService = new MusicPlayerService();
        //display method return value store this object
        List<Songs> displayAllSong = songRepository.displayAllSong();
        songRepository.displayFormat(displayAllSong);
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println();
        int task = 0;
        do {
            System.out.println("Press 1 to search in list");
            System.out.println("Press 2 to Play a Song");
            System.out.println("Press 3 to create play list");
            System.out.println("Press 4 to insert song into play List");
            System.out.println("Press 5 to view play list");
            System.out.println("Press 6 to sort list by genre");
            System.out.println("Press 7 to Exit");
            task = scanner.nextInt();
            System.out.println();
            System.out.println("--------------------------------------------------------------------------");
            System.out.println();
            switch (task) {
                case 1:
                    System.out.println("Press 1 to Search song details-by Genre");
                    System.out.println("Press 2 to Search song detail-by Album");
                    System.out.println("Press 3 to Search song detail by-Artist Name");
                    System.out.println("Press 4 to Search song detail by-Song Name");
                    int choice = scanner.nextInt();
                    System.out.println("------------------------------------------------------------------------");
                    if (choice == 1) {
                        System.out.println("Enter Genre name ::");
                        String genre = scanner.next();
                        List<Songs> getGenre = null;
                        getGenre = songRepository.songSearchByGenre(displayAllSong, genre);
                        songRepository.displayFormat(getGenre);
                        System.out.println("If you want to play song then press (Y/N)");
                        String option = scanner.next();
                        if (option.equals("Y")) {
                            System.out.println("please enter the song id which you want play");
                            int id = scanner.nextInt();
                            musicPlayerService.playParticular(id);
                            System.out.println();
                        } else {
                            break;
                        }
                    } else if (choice == 2) {
                        System.out.println("Enter Album name ::");
                        String album = scanner.next();
                        List<Songs> getAlbum = songRepository.songSearchByAlbumName(displayAllSong, album);
                        songRepository.displayFormat(getAlbum);
                        System.out.println("If you want to play song then press (Y/N)");
                        String option = scanner.next();
                        if (option.equals("Y")) {
                            System.out.println("please enter the song id which you want play");
                            int id = scanner.nextInt();
                            musicPlayerService.playParticular(id);
                            System.out.println();
                        } else {
                            break;
                        }
                    } else if (choice == 3) {
                        System.out.println("Enter Artist Name ::");
                        String artistName = scanner.next();
                        scanner.nextLine();
                        List<Songs> getArtist = songRepository.songSearchByArtistName(displayAllSong, artistName);
                        songRepository.displayFormat(getArtist);
                        System.out.println("If you want to play song then press (Y/N)");
                        String option = scanner.next();
                        if (option.equals("Y")) {
                            System.out.println("please enter the song id which you want play");
                            int id = scanner.nextInt();
                            musicPlayerService.playParticular(id);
                            System.out.println();
                        } else {
                            break;
                        }
                    } else if (choice == 4) {
                        System.out.println("Enter Song Name ::");
                        scanner.nextLine();
                        String songName = scanner.nextLine();
                        List<Songs> getSong = null;
                        getSong = songRepository.songSearchBySongName(displayAllSong, songName);
                        songRepository.displayFormat(getSong);
                        System.out.println("If you want to play song then press (Y/N)");
                        String option = scanner.next();
                        if (option.equals("Y")) {
                            System.out.println("please enter the song id which you want play");
                            int id = scanner.nextInt();
                            musicPlayerService.playParticular(id);
                            System.out.println();
                        } else {
                            break;
                        }
                    }
                case 2:
                    songRepository.displayFormat(displayAllSong);
                    System.out.println("Please enter song id which you want to play");
                    int choice2 = scanner.nextInt();
                    System.out.println("---------------------------------------------------------------------");
                    musicPlayerService.playParticular(choice2);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("-----------------Press 3 to create play list----------------------------");
                    System.out.println("Enter play list name::");
                    String playListName = scanner.next();
                    //scanner.nextLine();
                    playListRepository.addIntoDatabase(playListName);
                    System.out.println();
                    System.out.println("Enter play list name::");
                    break;
                case 4:
                    System.out.println("------------------insert song into play List------------------------------");
                    List<Playlist> getPlaylistName1 = playListRepository.ShowPlayList();
                    for (Playlist playList : getPlaylistName1) {
                        System.out.println(playList.getPlaylistId() + " " + playList.getPlaylistName());
                    }
                    System.out.println("Enter Play list number for choose playList which you want add song::");
                    int playListId = scanner.nextInt();
                    songRepository.displayFormat(displayAllSong);
                    System.out.println("Enter Song id which song you want to add into play list::");
                    int songId = scanner.nextInt();
                    playListRepository.insertSongIntoPlayList(playListId, songId);
                    break;
                case 5:
                    System.out.println("-------------------view to play list--------------------------------------");
                    List<Playlist> getPlayListName = playListRepository.ShowPlayList();
                    for (Playlist playList : getPlayListName) {
                        System.out.println(playList.getPlaylistId() + " " + playList.getPlaylistName());
                    }
                    System.out.println("Press 1 for view song from the play list");
                    System.out.println("Press 2 for Exit");

                    int choice3 = scanner.nextInt();
                    if (choice3 == 1) {
                        System.out.println("Please enter the play list id which you view the song");
                        int playListIds = scanner.nextInt();
                        List<Songs> getSongFromList = playListRepository.getSongFromList(playListIds, displayAllSong);
                        songRepository.displayFormat(getSongFromList);
                    }
                    System.out.println("If you want to play song then press (Y/N)");
                    String option = scanner.next();
                    if (option.equals("Y")) {
                        System.out.println("please enter the song id which you want play");
                        int songIds = scanner.nextInt();
                        musicPlayerService.playParticular(songIds);
                    } else {
                        break;
                    }
                case 6:
                    System.out.println("Sorted list");
                    List<Songs> songList = songRepository.sortSongs(displayAllSong);
                    songRepository.displayFormat(songList);
                case 7:
                    System.out.println("Successful Exit");
                    System.out.println("--------------------------------------------------------------------------");

            }
        } while (task < 7);
    }
}