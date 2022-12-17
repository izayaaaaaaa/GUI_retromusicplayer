public class follow extends Song {
  public follow() {
    setTrackNo(8);
    setSong("Follow Your Fire - Kodaline");
    // setSongDuration

    // acquire the audio file
    setSongPath("src/music/Follow Your Fire - Kodaline.wav"); 
    
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