import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

// import java.time.Duration;

public abstract class Song {
  private int trackNo;
  private String song;
  private String songPath;
  private AudioInputStream songStream;
  private Clip songClip;

  String status;
  // Public Long songCurrentFrame;

  public void setTrackNo(int num) {trackNo = num;}
  public int getTrackNo() {return trackNo;}

  public void setSong(String name) {song = name;}
  public String getSong() {return song;}

  public void setSongPath(String path) {songPath = path;}
  public String getSongPath() {return songPath;}
  
  public void setSongStream() throws UnsupportedAudioFileException, IOException {
      songStream = AudioSystem.getAudioInputStream(new File(songPath).getAbsoluteFile()); 
  }
  public AudioInputStream getSongStream() {return songStream;}

  public void setSongClip() throws LineUnavailableException {
    songClip = AudioSystem.getClip();
  }
  public Clip getSongClip() {return songClip;}
 
  // public Long getSongCurrentFrame() {return songCurrentFrame;}
}