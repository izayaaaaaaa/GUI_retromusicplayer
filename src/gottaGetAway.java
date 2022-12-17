public class gottaGetAway extends Song {
  public gottaGetAway() {
    setTrackNo(3);
    setSong("Gotta Get Away - Vista Kicks");
    // setSongDuration

    // acquire the audio file
    setSongPath("src/music/Gotta Get Away - Vista Kicks.wav"); 
    
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