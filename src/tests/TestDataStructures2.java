package tests;

import org.junit.Test;
import ratings.Playlist;
import ratings.Song;
import ratings.datastructures.Comparator;
import ratings.datastructures.LinkedListNode;
import ratings.datastructures.SongBayesianRatingComparator;
import ratings.datastructures.SongTitleComparator;

import static org.junit.Assert.*;

public class TestDataStructures2 {

    @Test
    public void testPlaylistConstructor(){
        //playlist constructor with title comparator
        SongTitleComparator titleCompare=new SongTitleComparator();
        Playlist playlist = new Playlist(titleCompare);

        assertNotNull(playlist);

        //playlist constructor with bayesian average
        SongBayesianRatingComparator avgComparator = new SongBayesianRatingComparator();
        Playlist playlist2 = new Playlist(avgComparator);

        assertNotNull(playlist2);
    }

    @Test
    public void testAddSong(){
        //playlist adding no songs
        Comparator<Song> comparator = new SongTitleComparator();
        Playlist playlistEmpty = new Playlist(comparator);

        assertNull(playlistEmpty.getSongList());

        //playlist adding single song
        SongTitleComparator titleCompare=new SongTitleComparator();
        Playlist playlist = new Playlist(titleCompare);
        playlist.addSong(new Song("Song1","Artist1","1"));
        LinkedListNode<Song> headOfPlaylist=playlist.getSongList();
        assertEquals("Song1",headOfPlaylist.getValue().getTitle());

        //playlist adding multiple songs
        SongTitleComparator titleCompare2=new SongTitleComparator();
        Playlist playlist2 = new Playlist(titleCompare2);
        playlist2.addSong(new Song("Song1","Artist1","1"));
        playlist2.addSong(new Song("Song2","Artist2","2"));
        LinkedListNode<Song> headOfPlaylist2=playlist2.getSongList();
        assertEquals("Song1",headOfPlaylist2.getValue().getTitle());

        //playlist adding more than three songs
        SongTitleComparator titleCompare3=new SongTitleComparator();
        Playlist playlist3 = new Playlist(titleCompare2);
        playlist3.addSong(new Song("Song1","Artist1","1"));
        playlist3.addSong(new Song("Song2","Artist2","2"));
        playlist3.addSong(new Song("Song3","Artist3","3"));
        playlist3.addSong(new Song("Song4","Artist4","4"));
        playlist3.addSong(new Song("Song5","Artist5","5"));
        LinkedListNode<Song> headOfPlaylist3=playlist3.getSongList();
        assertEquals(5,headOfPlaylist3.size());
        assertEquals("Song1",headOfPlaylist3.getValue().getTitle());
    }

    @Test
    public void testGetSongList(){

        //songs to add to any playlist
        Song song1 = new Song("Song1","Artist1","1");
        Song song2 = new Song("Song2","Artist2","2");
        Song song3 = new Song("Song3","Artist3","3");
        Song song4 = new Song("Song4","Artist4","4");

        //no songs in playlist
        SongTitleComparator empty=new SongTitleComparator();
        Playlist playlist= new Playlist(empty);

        assertNull(playlist.getSongList());

        //one song added
        SongTitleComparator titleCompare1=new SongTitleComparator();
        Playlist playlist1 = new Playlist(titleCompare1);
        playlist1.addSong(song1);

        assertEquals(song1.getTitle(),playlist1.getSongList().getValue().getTitle());

        //two songs added
        SongTitleComparator titleCompare2=new SongTitleComparator();
        Playlist playlist2 = new Playlist(titleCompare2);
        playlist2.addSong(song1);playlist2.addSong(song2);

        assertEquals(song1.getTitle(),playlist2.getSongList().getValue().getTitle());
        assertEquals(song2.getTitle(),playlist2.getSongList().getNext().getValue().getTitle());
    }

}
