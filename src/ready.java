public class ready extends Song {
  public ready() {
    setTrackNo(5);
    setSong("Who's Ready for Tomorrow - Rat Boy");
    // setSongDuration

    // acquire the audio file
    setSongPath("src/music/Who's Ready for Tomorrow - Rat Boy.wav"); 
    
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