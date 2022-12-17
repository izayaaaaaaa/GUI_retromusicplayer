public class gravity extends Song {
  public gravity() {
    setTrackNo(9);
    setSong("Gravity - John Mayer");
    // setSongDuration

    // acquire the audio file
    setSongPath("src/music/Gravity - John Mayer.wav"); 
    
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