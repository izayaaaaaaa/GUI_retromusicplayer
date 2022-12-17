import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
  Song sixthSong = new ready();
  Song seventhSong = new where();
  Song eightSong = new drive();
  Song ninthSong = new follow();
  Song tenthSong = new gravity();

  Song[] arraySongs = {firstSong, secondSong, thirdSong, fourthSong, fifthSong, sixthSong, seventhSong, eightSong, ninthSong, tenthSong};

  JPanel northPanel, mainPanel, titlePanel, controlPanel, chooseSongPanel, playGrp, listTitlePanel, songListPanel;
  JLabel title, playlist;

  Long currentFrame;
  int counter = 0;
  JTextArea currentTrack, userInputTitle, userinput;
  Song currentSong = firstSong;
  
  String status;
  JButton playBtn, pauseBtn, stopBtn; 
  JToggleButton nextBtn, previousBtn;
  JTable songList;

  public WinAmp() {
    // =========================================== NORTH PANEL ===========================================
    northPanel = new JPanel(new BorderLayout());

    titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    titlePanel.setBackground(new Color(31, 32, 50));
    title = new JLabel("Izaya's Music Player");
    title.setFont(new Font("Verdana", 1, 16));
    title.setForeground(Color.white);
    titlePanel.add(title);

    controlPanel = new JPanel(new GridBagLayout());
    controlPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
    controlPanel.setBackground(new Color(31, 32, 50));
    
    chooseSongPanel = new JPanel(new BorderLayout());
    chooseSongPanel.setBorder(new EmptyBorder(10,10, 10, 10));
    chooseSongPanel.setPreferredSize(new Dimension(275, 125));
    chooseSongPanel.setBackground(Color.black);
    
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 1;
    c.gridheight = 2; 

    userInputTitle = new JTextArea("Press the Stop Button.\nThen, pick a song to play.\n(Just type in the track number.)");
    userInputTitle.setBackground(Color.black);
    userInputTitle.setForeground(Color.green);
    userInputTitle.setFont(new Font("Verdana", 0, 14));
    userinput = new JTextArea("> ", 1,0);
    userinput.setBackground(Color.black);
    userinput.setForeground(Color.green);
    userinput.setFont(new Font("Verdana", 0, 12));
    userinput.addKeyListener(new userInputListener());

    chooseSongPanel.add(userInputTitle, BorderLayout.CENTER);
    chooseSongPanel.add(userinput, BorderLayout.SOUTH);
    controlPanel.add(chooseSongPanel, c);

    currentTrack = new JTextArea(currentSong.getTrackNo() + ". " + currentSong.getSong(), 0, 25);
    currentTrack.setBorder(BorderFactory.createLoweredBevelBorder());
    currentTrack.setEditable(false);
    currentTrack.setFont(new Font("Verdana", 0, 16));
    currentTrack.setBackground(Color.black);
    currentTrack.setForeground(Color.green);
    c.gridx = 1; 
    c.gridy = 0; 
    c.insets = new Insets(27, 10, 0, 0);
    controlPanel.add(currentTrack, c);

    play(currentSong);

    playGrp = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 2));
    playGrp.setBorder(BorderFactory.createLoweredBevelBorder());
    playGrp.setBackground(new Color(30, 28, 45));
    c.gridx = 1;
    c.gridy = 2;
    c.insets = new Insets(10, 0, 0, 0);
    c.anchor = GridBagConstraints.FIRST_LINE_END;

    playBtn = new JButton(new ImageIcon("src/icons/play2.jpg"));
    playBtn.setPreferredSize(new DimensionUIResource(35, 27));
    playBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playBtn) {
          resumePlay(currentSong);
        }
      }
    });

    pauseBtn = new JButton(new ImageIcon("src/icons/pause2.jpg"));
    pauseBtn.setPreferredSize(new DimensionUIResource(35, 27));
    pauseBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == pauseBtn) {
          pause(currentSong);
        }
      }
    });

    stopBtn = new JButton(new ImageIcon("src/icons/stop.jpg"));
    stopBtn.setPreferredSize(new DimensionUIResource(35, 27));
    stopBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == stopBtn) {
          stop(currentSong);
        }
      }
    });

    previousBtn = new JToggleButton(new ImageIcon("src/icons/previous2.jpg"));
    previousBtn.setPreferredSize(new Dimension(35, 27));
    previousBtn.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        stop(currentSong);
        counter--;
          
        if(counter < 0) {
          counter = arraySongs.length - 1;
        } 

        currentSong = arraySongs[counter];
        System.out.println("song " + counter);
        currentTrack.setText(currentSong.getTrackNo() + ". " + currentSong.getSong());
        
        resumePlay(currentSong);
      }
    });    
    
    nextBtn = new JToggleButton(new ImageIcon("src/icons/next2.jpg"));
    nextBtn.setPreferredSize(new Dimension(35, 27));
    nextBtn.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        stop(currentSong);
        counter++;
        
        if(counter >= arraySongs.length) {
          counter = 0;
        } 
        
        currentSong = arraySongs[counter];
        System.out.println("song " + counter);
        currentTrack.setText(currentSong.getTrackNo() + ". " + currentSong.getSong());
        
        resumePlay(currentSong);   
      }
    });

    playGrp.add(previousBtn);
    playGrp.add(playBtn);
    playGrp.add(pauseBtn);
    playGrp.add(stopBtn);
    playGrp.add(nextBtn);
    
    controlPanel.add(playGrp, c);

    northPanel.add(titlePanel, BorderLayout.NORTH);
    northPanel.add(controlPanel, BorderLayout.CENTER);

    // ============================================ MAIN PANEL ============================================
    mainPanel = new JPanel(new BorderLayout());
    mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
    mainPanel.setBackground(new Color(31, 32, 50));

    listTitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    listTitlePanel.setBackground(new Color(31, 32, 50));
    listTitlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
    
    playlist = new JLabel("Song List");
    playlist.setFont(new Font("Verdana", 1, 14));
    playlist.setForeground(Color.WHITE);

    listTitlePanel.add(playlist);

    songListPanel = new JPanel(new BorderLayout());
    songListPanel.setBackground(Color.black);
    songListPanel.setBorder(new EmptyBorder(5,5, 5, 5));
    songList = new JTable(new songListTable());
    songList.setBackground(Color.BLACK);
    songList.setForeground(Color.green);
    songList.setShowGrid(false);
    songList.setCellSelectionEnabled(false);
    songList.setFont(new Font("Verdana", 0, 12));

    TableColumnModel columnModel = songList.getColumnModel();
    columnModel.getColumn(0).setMaxWidth(25);
    columnModel.getColumn(2).setMaxWidth(150);

    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
    songList.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
    songList.setRowHeight(22);
   
    songListPanel.add(songList, BorderLayout.CENTER);
    mainPanel.add(listTitlePanel, BorderLayout.NORTH);
    mainPanel.add(songListPanel, BorderLayout.CENTER);
  
    // ========================================= SETUP THE FRAME =========================================
    add(northPanel, BorderLayout.NORTH);
    add(mainPanel, BorderLayout.CENTER);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setResizable(false);
    setVisible(true);
  } // WinAmp constructor

  class userInputListener implements KeyListener {
    public void keyPressed(KeyEvent e) {
      if (e.getKeyChar() == '0') {
        currentSong = arraySongs[0];
        currentTrack.setText(currentSong.getTrackNo() + ". " + currentSong.getSong());
      } 
      else if (e.getKeyChar() == '1') {
        currentSong = arraySongs[1];
        currentTrack.setText(currentSong.getTrackNo() + ". " + currentSong.getSong());
      }
      else if (e.getKeyChar() == '2') {
        currentSong = arraySongs[2];
        currentTrack.setText(currentSong.getTrackNo() + ". " + currentSong.getSong());
      }
      else if (e.getKeyChar() == '3') {
        currentSong = arraySongs[3];
        currentTrack.setText(currentSong.getTrackNo() + ". " + currentSong.getSong());
      }
      else if (e.getKeyChar() == '4') {
        currentSong = arraySongs[4];
        currentTrack.setText(currentSong.getTrackNo() + ". " + currentSong.getSong());
      }
      else if (e.getKeyChar() == '5') {
        currentSong = arraySongs[5];
        currentTrack.setText(currentSong.getTrackNo() + ". " + currentSong.getSong());
      }
      else if (e.getKeyChar() == '6') {
        currentSong = arraySongs[6];
        currentTrack.setText(currentSong.getTrackNo() + ". " + currentSong.getSong());
      }
      else if (e.getKeyChar() == '7') {
        currentSong = arraySongs[7];
        currentTrack.setText(currentSong.getTrackNo() + ". " + currentSong.getSong());
      }
      else if (e.getKeyChar() == '8') {
        currentSong = arraySongs[8];
        currentTrack.setText(currentSong.getTrackNo() + ". " + currentSong.getSong());
      }
      else if (e.getKeyChar() == '9') {
        currentSong = arraySongs[9];
        currentTrack.setText(currentSong.getTrackNo() + ". " + currentSong.getSong());
      }
    } 

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
  } // userInputListener

  class songListTable extends AbstractTableModel {
    String [] columnNames = {"Track No.", "Song", "Duration"};
    Object[][] songs = {
      {firstSong.getTrackNo(), firstSong.getSong(), "5:11"},
      {secondSong.getTrackNo(), secondSong.getSong(), "3:08"},
      {thirdSong.getTrackNo(), thirdSong.getSong(), "3:13"},
      {fourthSong.getTrackNo(), fourthSong.getSong(), "3:50"},
      {fifthSong.getTrackNo(), fifthSong.getSong(), "5:09"},
      {sixthSong.getTrackNo(), sixthSong.getSong(), "1:56"},
      {seventhSong.getTrackNo(), seventhSong.getSong(), "4:02"}, 
      {eightSong.getTrackNo(), eightSong.getSong(), "3:53"},
      {ninthSong.getTrackNo(), ninthSong.getSong(), "3:59"},
      {tenthSong.getTrackNo(), tenthSong.getSong(), "4:07"}
    }; 
  
    public int getColumnCount() {return columnNames.length;}
  
    public int getRowCount() {return songs.length;}
  
    public String getColumnName(int col) {return columnNames[col];}
  
    public Object getValueAt(int row, int col) {return songs[row][col];}
  } // songListTable class

  public void play(Song s) {
    s.getSongClip().start();
    status = "playing";
  }

  public void pause(Song s) {
    currentFrame = s.getSongClip().getMicrosecondPosition();
    s.getSongClip().stop();
    status = "paused";
  } 

  public void stop(Song s) {
    currentFrame = 0L;
    s.getSongClip().stop();
  }

  public void resetAudioStream(Song s) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		s.setSongStream();
		s.getSongClip().open(s.getSongStream());
	}

  public void resumePlay(Song s) {
    try {
      s.getSongClip().close();
      resetAudioStream(currentSong);
      s.getSongClip().setMicrosecondPosition(currentFrame);
      play(currentSong);

    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
      System.out.println("resumePlay() error");
      e1.printStackTrace();
    }
    
  }

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

