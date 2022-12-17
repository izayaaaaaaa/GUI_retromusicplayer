public class drive extends Song {
  public drive() {
    setTrackNo(7);
    setSong("Drive - Incubus");
    // setSongDuration

    // acquire the audio file
    setSongPath("src/music/Drive - Incubus.wav"); 
    
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