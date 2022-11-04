/*
 * Author Name: Philip Meshach
 * Date: 03-11-2022
 * Praise The Lord
 */
package com.niit.jdp.Service;

import com.niit.jdp.Model.Songs;

import java.util.Comparator;
import java.util.List;

public class SongsService {
    public class SongComparator implements Comparator<Songs> {

        @Override
        public int compare(Songs o1, Songs o2) {
            return o1.getGenre().compareTo(o2.getGenre());
        }
    }
}
