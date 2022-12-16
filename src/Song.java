// import java.time.Duration;

public class Song {
  public int trackNo;
  
  public String song;

  // private String songDuration; 
  // private String[] strValues;
  // private Duration durVer = Duration.ofMinutes(Integer.parseInt(strValues[0]));
  // durVer = durVer.plusSeconds(Integer.parseInt(strValues[1]));
  // FIX THIS WEIRD SEMICOLON BUG

  public int songDuration; 

  public int songClip; 

  public Song (int num, String name, int time, int file) {
    trackNo = num;
    song = name;
    songDuration = time; 
    // songClip = uploadAudio(this.filePath);
  }

  public int getTrackNo() {return trackNo;}
  public void setSong(String name) {song = name;}
  public String getSong() {return song;}
  public int getSongDuration() {return songDuration;};
  public int getSongClip() {return songClip;}
  
  
  // acquire the sound audio
  // RESUME HERE!!!

  class sound {

  }
  
}
