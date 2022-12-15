import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public class WinAmp extends JFrame {
  private static WinAmp firstInstance = null;

  JPanel northPanel, mainPanel;

  JPanel counterPanel, playGrp;
  JTextArea currentTrack;
  String playNpausePath;
  JButton previousBtn, nextBtn; 
  JToggleButton playPauseBtn;

  public WinAmp() {
    
    // =========================================== NORTH PANEL ===========================================
    northPanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    counterPanel = new JPanel();
    counterPanel.setPreferredSize(new Dimension(150, 100));
    counterPanel.setBackground(Color.green);
    c.gridx = 0;
    c.gridy = 1;
    c.gridheight = 2; 
    northPanel.add(counterPanel, c);

    currentTrack = new JTextArea("Test String", 0, 20);
    currentTrack.setEditable(false);
    c.gridx = 1; 
    c.gridy = 0; 
    northPanel.add(currentTrack, c);

    playGrp = new JPanel();
    playGrp.setBackground(Color.yellow);
    c.gridx = 1;
    c.gridy = 2;
    
    previousBtn = new JButton(new ImageIcon("src/icons/previous.jpg"));
    previousBtn.setPreferredSize(new Dimension(30, 24));
    previousBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == previousBtn) {
          System.out.println("previous button");
        }
      }
    });
    
    playPauseBtn = new JToggleButton(new ImageIcon("src/icons/play.jpg"));
    playPauseBtn.setPreferredSize(new DimensionUIResource(31, 24));
    playPauseBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playPauseBtn) {
          System.out.println("play/pause button");
          playPauseBtn.setSelectedIcon(new ImageIcon("src/icons/pause.jpg"));
        }
      }
    });

    nextBtn = new JButton(new ImageIcon("src/icons/next.jpg"));
    nextBtn.setPreferredSize(new Dimension(31, 24));
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
    northPanel.add(playGrp, c);

    // ============================================ MAIN PANEL ============================================
    mainPanel = new JPanel();
    // titled border for the playlist title on top of the text area
    // table with two columns and 10 rows/songs
    

    // ========================================= SETUP THE FRAME =========================================
    add(northPanel, BorderLayout.NORTH);
    add(mainPanel, BorderLayout.CENTER);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // setSize(500, 500);
    pack();
    // setResizable(false);
    setVisible(true);
  } // WinAmp constructor

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

