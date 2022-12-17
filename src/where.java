public class where extends Song {
  public where() {
    setTrackNo(6);
    setSong("Where Did Your Love Go");
    // setSongDuration

    // acquire the audio file
    setSongPath("src/music/Where Did Your Love Go.wav"); 
    
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