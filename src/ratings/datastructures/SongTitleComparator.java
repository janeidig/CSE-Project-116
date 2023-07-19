package ratings.datastructures;

import ratings.Song;

public class SongTitleComparator extends Comparator<Song>{

    public boolean compare(Song a, Song b) {
        if (a.getTitle().compareToIgnoreCase(b.getTitle())<0){
            return true;
        }else {
            return false;
        }
    }
}
