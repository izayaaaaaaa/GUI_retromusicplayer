public class stillSane extends Song {
  public stillSane() {
    setTrackNo(1);
    setSong("Still Sane - Lorde");
    // setSongDuration

    // acquire the audio file
    setSongPath("src/music/Still Sane - Lorde.wav"); 
    
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