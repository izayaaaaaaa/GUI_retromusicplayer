public class team extends Song {
  public team() {
    setTrackNo(3);
    setSong("Team - Lorde");
    // setSongDuration

    // acquire the audio file
    setSongPath("src/music/Team - Lorde.wav"); 
    
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