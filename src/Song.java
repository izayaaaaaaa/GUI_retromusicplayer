import java.time.Duration;

public class Song {
  private int trackNo;
  
  private String song;

  private String songDuration; 
  private String[] strValues;
  private Duration durVer = Duration.ofMinutes(Integer.parseInt(strValues[0]));
  durVer = durVer.plusSeconds(Integer.parseInt(strValues[1]));

  // private sound AudioFile; 

  public int getTrackNo() {return trackNo;};
  public String getSong() {return song;};
  public String getSongDuration() {return songDuration;};
  
  // acquire the sound audio

}
