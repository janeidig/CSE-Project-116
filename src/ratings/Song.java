package ratings;

public class Song extends Ratable{
    private String songArtist;
    private String songID;

    public Song(String songTitle, String songArtist, String songID) {
        this.setTitle(songTitle);
        this.songArtist = songArtist;
        this.songID = songID;
    }

    //getter and setter methods

    public String getArtist() {
        return this.songArtist;
    }

    public void setArtist(String artist) {
        this.songArtist = artist;
    }

    public String getSongID() {
        return this.songID;
    }

    public void setSongID(String id) {
        this.songID = id;
    }
}
