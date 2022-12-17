// add key events to the control buttons 

// decrease button size

// FIRST SONG HAS TO PLAY BY DEFAULT


// song can change
  // previous/next button
  // once a song finishes

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
// import javax.swing.border.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.*;

public class WinAmp extends JFrame {
  private static WinAmp firstInstance = null;
  
  Song firstSong = new stranger();
  Song secondSong = new stillSane();
  Song thirdSong = new team();
  Song fourthSong = new gottaGetAway();
  Song fifthSong = new adults();

  Song[] arraySongs = {firstSong, secondSong, thirdSong, fourthSong, fifthSong};

  JPanel northPanel, mainPanel;

  JPanel titlePanel, controlPanel, counterPanel, playGrp;
  JLabel title; 
  int counter;
  JTextArea currentTrack;
  Song currentSong;
  String status, playNpausePath;
  JButton previousBtn, nextBtn; 
  JToggleButton playPauseBtn;

  JPanel listTitlePanel, songListPanel;
  JTable songList;
  JLabel playlist;

  public WinAmp() {
    // play the first song upon startup
    counter = 0;
    currentSong = firstSong;
    currentSong.getSongClip().loop(Clip.LOOP_CONTINUOUSLY); 
    play(currentSong);
    status = "playing";
    // =========================================== NORTH PANEL ===========================================
    northPanel = new JPanel(new BorderLayout());
    // northPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

    // Border lineBorder = BorderFactory.createLineBorder(new Color(102, 101, 117));
    // Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 5, 5);
    // Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder); 
    // northPanel.setBorder(compoundBorder);

    titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    titlePanel.setBackground(new Color(45, 47, 72));
    title = new JLabel("WINAMP");
    title.setFont(new Font("Verdana", 1, 13));
    title.setForeground(Color.white);
    titlePanel.add(title);

    controlPanel = new JPanel(new GridBagLayout());
    // controlPanel.setBorder(BorderFactory.createRaisedBevelBorder());
    controlPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
    // controlPanel.setBackground(new Color(45, 47, 72));

    GridBagConstraints c = new GridBagConstraints();

    counterPanel = new JPanel();
    controlPanel.setBackground(new Color(45, 47, 72));
    counterPanel.setPreferredSize(new Dimension(275, 125));
    counterPanel.setBackground(Color.black);
    c.gridx = 0;
    c.gridy = 1;
    c.gridheight = 2; 
    controlPanel.add(counterPanel, c);

    currentTrack = new JTextArea(currentSong.getSong(), 0, 25);
    currentTrack.setBorder(BorderFactory.createLoweredBevelBorder());
    currentTrack.setEditable(false);
    currentTrack.setFont(new Font("Verdana", 0, 16));
    currentTrack.setBackground(Color.black);
    currentTrack.setForeground(Color.green);
    c.gridx = 1; 
    c.gridy = 0; 
    c.insets = new Insets(27, 10, 0, 0);
    controlPanel.add(currentTrack, c);

    playGrp = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 2));
    playGrp.setBorder(BorderFactory.createLoweredBevelBorder());
    playGrp.setBackground(new Color(30, 28, 45));
    c.gridx = 1;
    c.gridy = 2;
    c.insets = new Insets(10, 0, 0, 0);
    c.anchor = GridBagConstraints.FIRST_LINE_END;
    
    previousBtn = new JButton(new ImageIcon("src/icons/previous2.jpg"));
    previousBtn.setPreferredSize(new Dimension(65, 50));
    previousBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == previousBtn) {
          stop(currentSong);
          counter--;
            
          if(counter < 0) {
            counter = arraySongs.length - 1;
          } 

          currentSong = arraySongs[counter];
          System.out.println("song " + counter);
          currentTrack.setText(currentSong.getSong());
          
          resumePlay(currentSong);
        }
      }
    });
    
    playPauseBtn = new JToggleButton(new ImageIcon("src/icons/pause2.jpg"));
    playPauseBtn.setPreferredSize(new DimensionUIResource(65, 50));
    playPauseBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playPauseBtn) {
          if(status.equals("playing")) {
            pause(currentSong);
            status = "paused";

            playPauseBtn.setSelectedIcon(new ImageIcon("src/icons/play2.jpg"));
            System.out.println("music is now paused");
          } else if (status.equals("paused")) {
            // put the try catch on the resumePlay method itself
            resumePlay(currentSong);
            status = "playing";
            System.out.println("music is now playing");
                       
          } 
        }
      }
    });

    nextBtn = new JButton(new ImageIcon("src/icons/next2.jpg"));
    nextBtn.setPreferredSize(new Dimension(65, 50));
    nextBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nextBtn) {
          stop(currentSong);
          counter++;
            
          if(counter >= arraySongs.length) {
            counter = 0;
          } 

          currentSong = arraySongs[counter];
          System.out.println("song " + counter);
          currentTrack.setText(currentSong.getSong());
          
          resumePlay(currentSong);
        }
      }
    });

    playGrp.add(previousBtn);
    playGrp.add(playPauseBtn);
    playGrp.add(nextBtn);
    
    controlPanel.add(playGrp, c);

    northPanel.add(titlePanel, BorderLayout.NORTH);
    northPanel.add(controlPanel, BorderLayout.CENTER);

    // ============================================ MAIN PANEL ============================================
    mainPanel = new JPanel(new BorderLayout());
    mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));
    mainPanel.setBackground(new Color(45, 47, 72));
    // titled border for the playlist title on top of the text area
    // highlight a row that is currently playing through a listener

    listTitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    listTitlePanel.setBackground(new Color(45, 47, 72));
    listTitlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
    playlist = new JLabel("Song List");
    playlist.setFont(new Font("Verdana", 1, 12));
    playlist.setForeground(Color.WHITE);

    listTitlePanel.add(playlist);

    songListPanel = new JPanel(new BorderLayout());
    songList = new JTable(new songListTable());
    songList.setBackground(Color.BLACK);
    songList.setForeground(Color.green);
    songList.setShowGrid(false);

    TableColumnModel columnModel = songList.getColumnModel();
    columnModel.getColumn(0).setMaxWidth(25);
    columnModel.getColumn(2).setMaxWidth(150);

    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
    songList.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
    songList.setRowHeight(17);
   
    songListPanel.add(songList, BorderLayout.CENTER);
    mainPanel.add(listTitlePanel, BorderLayout.NORTH);
    mainPanel.add(songListPanel, BorderLayout.CENTER);
  
    // ========================================= SETUP THE FRAME =========================================
    add(northPanel, BorderLayout.NORTH);
    add(mainPanel, BorderLayout.CENTER);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // setSize(500, 500);
    pack();
    setResizable(false);
    setVisible(true);
  } // WinAmp constructor

  class songListTable extends AbstractTableModel {
    String [] columnNames = {"Track No.", "Song", "Duration"};
    Object[][] songs = {
      {firstSong.getTrackNo(), firstSong.getSong(), "4:57"},
      {secondSong.getTrackNo(), secondSong.getSong(), "4:57"},
      {thirdSong.getTrackNo(), thirdSong.getSong(), "4:57"},
      {fourthSong.getTrackNo(), fourthSong.getSong(), "4:57"},
      {fifthSong.getTrackNo(), fifthSong.getSong(), "4:57"}
    };
  
    public int getColumnCount() {return columnNames.length;}
  
    public int getRowCount() {return songs.length;}
  
    public String getColumnName(int col) {return columnNames[col];}
  
    public Object getValueAt(int row, int col) {return songs[row][col];}
  }

  public void play(Song s) {
    s.getSongClip().start();
    status = "playing";
  }

  public void resumePlay(Song s) {
    try {
      s.getSongClip().close();
      resetAudioStream(currentSong);
      s.getSongClip().setMicrosecondPosition(s.currentFrame);
      play(currentSong);
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
      System.out.println("resumePlay() error");
      e1.printStackTrace();
    }
    
  }
  
  public void pause(Song s) {
    s.currentFrame = s.getSongClip().getMicrosecondPosition();
    s.getSongClip().stop();
    status = "paused";
  } 

  public void resetAudioStream(Song s) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		s.setSongStream();
		s.getSongClip().open(s.getSongStream());
		s.getSongClip().loop(Clip.LOOP_CONTINUOUSLY);
	}

  public void stop(Song s) {
    s.currentFrame = 0L;
    s.getSongClip().stop();
  }

  // ensures that only one instance of the WinAmp will ever be created
  public static WinAmp runMusicApp() {
    if(firstInstance == null) {
      firstInstance = new WinAmp();
    }

    return firstInstance;
  } // runMusicApp 

  public static void main(String[] args) {
    WinAmp.runMusicApp(); 
  } // main
} // WinAmp class

