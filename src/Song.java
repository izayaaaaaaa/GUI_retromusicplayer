import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// import java.time.Duration;

public abstract class Song {
  private int trackNo;
  private String song;
  // public int songDuration; 
  private String songPath;
  private AudioInputStream songStream;
  private Clip songClip;

  String status;
  Long currentFrame;

  // private String songDuration; 
  // private String[] strValues;
  // private Duration durVer = Duration.ofMinutes(Integer.parseInt(strValues[0]));
  // durVer = durVer.plusSeconds(Integer.parseInt(strValues[1]));
  // FIX THIS WEIRD SEMICOLON BUG

  public void setTrackNo(int num) {trackNo = num;}
  public int getTrackNo() {return trackNo;}

  public void setSong(String name) {song = name;}
  public String getSong() {return song;}

  // public int getSongDuration() {return songDuration;};

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
  
}