/*
 * Author Name: Philip Meshach
 * Date: 06-11-2022
 * Praise The Lord
 */
package Repository;

import com.niit.jdp.Model.Songs;
import com.niit.jdp.Repository.SongRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SongRepositoryTest {
    SongRepository songRepository;
    List<Songs> songList;

    @BeforeEach
    void setUp() {
        songRepository = new SongRepository();
        songList = songRepository.displayAllSong();
    }

    @AfterEach
    void tearDown() {
        songRepository = null;
    }

    @Test
    void displayAllNames() {
        List<Songs> songList = songRepository.displayAllSong();
        Assertions.assertEquals(10, songList.size());
    }

    @Test
    void displayAllNamesFailure() {
        List<Songs> songList = songRepository.displayAllSong();
        Assertions.assertNotEquals(4, songList.size());
    }

    @Test
    void searchByGenre() {
        List<Songs> actual = songRepository.songSearchByGenre(songList, "pop");
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    void searchByGenreFailure() {
        List<Songs> actual = songRepository.songSearchByGenre(songList, "pop");
        Assertions.assertNotEquals(3, actual.size());
    }
}
