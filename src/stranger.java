public class stranger extends Song {
  public stranger() {
    setTrackNo(0);
    setSong("The Stranger - Billy Joel");
    // setSongDuration

    // acquire the audio file
    setSongPath("src/music/The Stranger - Billy Joel.wav"); 
    
    try {
      setSongStream();
      setSongClip();
      getSongClip().open(getSongStream());
    } 
    catch(Exception ex){
      System.out.println("Error acquiring audio file."); 
      ex.printStackTrace();
    }

    
  }
}