// add key events to the control buttons 


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

  JPanel northPanel, mainPanel;

  JPanel titlePanel, controlPanel, counterPanel, playGrp;
  JLabel title; 
  JTextArea currentTrack;
  String playNpausePath;
  JButton previousBtn, nextBtn; 
  JToggleButton playPauseBtn;

  JPanel listTitlePanel, songListPanel;
  JTable songList;
  JLabel playlist;

  public WinAmp() {
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

    currentTrack = new JTextArea("Test String", 0, 25);
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
          System.out.println("previous button");
        }
      }
    });
    
    playPauseBtn = new JToggleButton(new ImageIcon("src/icons/play2.jpg"));
    playPauseBtn.setPreferredSize(new DimensionUIResource(65, 50));
    playPauseBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playPauseBtn) {
          System.out.println("play/pause button");
          playPauseBtn.setSelectedIcon(new ImageIcon("src/icons/pause2.jpg"));
        }
      }
    });

    nextBtn = new JButton(new ImageIcon("src/icons/next2.jpg"));
    nextBtn.setPreferredSize(new Dimension(65, 50));
    nextBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nextBtn) {
          System.out.println("next button");
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

  // ensures that only one instance of the WinAmp will ever be created
  public static WinAmp runMusicApp() {
    if(firstInstance == null) {
      firstInstance = new WinAmp();
    }

    return firstInstance;
  } // runMusicApp 

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

  // public void play() {
  //   songClip.start();
  //   System.out.println("playing");
  // }
  // public void pause() {
  //   if(status.equals("paused")) {
  //     System.out.println("audio is already paused");
  //     return;
  //   } 

  // }

  public static void main(String[] args) {
    WinAmp.runMusicApp(); 
  } // main
} // WinAmp class

