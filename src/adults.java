public class adults extends Song {
  public adults() {
    setTrackNo(5);
    setSong("The Adults are Talking - The Strokes");
    // setSongDuration

    // acquire the audio file
    setSongPath("src/music/The Adults are Talking - The Strokes.wav"); 
    
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