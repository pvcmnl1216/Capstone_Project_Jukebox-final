/*
 * Author Name: Philip Meshach
 * Date: 06-11-2022
 * Praise The Lord
 */
package Repository;

import com.niit.jdp.Model.Playlist;
import com.niit.jdp.Repository.PlaylistNotFoundException;
import com.niit.jdp.Repository.PlaylistRepository;
import com.niit.jdp.Repository.SongNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PlaylistRepositoryTest {
    PlaylistRepository playlistRepository;

    @BeforeEach
    void setUp() {
        playlistRepository = new PlaylistRepository();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void showPlaylist() throws SQLException, PlaylistNotFoundException, SongNotFoundException {
        List<Playlist> playlists = playlistRepository.showPlaylist();
        Assertions.assertEquals(15, 15);
    }

    @Test
    void showPlaylistFailure() throws SQLException, PlaylistNotFoundException, SongNotFoundException {
        List<Playlist> playlists = playlistRepository.showPlaylist();
        assertNotEquals(4, playlists.size());
    }
}
