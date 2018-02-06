/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.chatcat.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/*
import de.dfki.lt.audiomanager.ASRServiceFactory;
import de.dfki.lt.audiomanager.Constants;
import de.dfki.lt.audiomanager.Microphone;
import de.dfki.lt.audiomanager.RecognizerService;
import de.dfki.lt.audiomanager.RecorderGui;
import de.dfki.lt.vad.VadSegmenter;
*/

/**
 *
 * @author Christophe Biwer, christophe.biwer@dfki.de
 */


public class GUI extends JFrame {
  private static final long serialVersionUID = 1L;

  public static final String LANGUAGE = "en-US";

  public JTextField queryInput;

  private List<Listener<String>> _listeners = new ArrayList<>();

  public ChatProtocol _chat;
  public JTable chatprotocol;
  public JLabel _statusbar;

  public Reaction _react;
  final GUI mainFrame;

  public void register(Listener<String> l) {
    _listeners.add(l);
  }

  private static JButton getIconButton(String name) {
    URL url = GUI.class.getClassLoader()
        .getResource("icons/" + name + ".png");
    Icon icon = new ImageIcon(url);
    JButton button = new JButton(icon);
    button.setPreferredSize(new Dimension(32,32));
    button.setBorderPainted(false);
    button.setBorder(null);
    //button.setFocusable(false);
    button.setMargin(new Insets(0, 0, 0, 0));
    button.setContentAreaFilled(false);
    return button;
  }

  private JTextField createQueryInput(ActionListener execute) {
    JTextField queryInput = new JTextField();
    queryInput.setText("");
    queryInput.addActionListener(execute);
    return queryInput;
  }

  private static void setDefaultFont(int size) {
    FontUIResource font = new FontUIResource("'DejaVu Sans Mono",
            Font.PLAIN, size);
    for (Map.Entry<Object, Object> e :
      UIManager.getLookAndFeelDefaults().entrySet()) {
      try {
        String key = (String) e.getKey();
        if (key.endsWith(".font")) {
          UIManager.put(key, font);
        }
        // If you want to list them all.
        // System.out.println(e.getKey() + " " + e.getValue());
      } catch (ClassCastException ex) { /* so what. */ }
    }
  }

  //private Microphone in;

  //private VadSegmenter encoder;

  private void start() {
    /*
    RecognizerService _recognizer = null;
    try {
      _recognizer = ASRServiceFactory.getFactory().getRecognizer(
              new de.dfki.lt.audiomanager.Listener<String>(){
            @Override
            public void receive(String t) {
              queryInput.setText(t);
              String timestamp = Utilities.getTimeStamp();
              _chat.sendMessage(timestamp + " " + queryInput.getText());
              queryInput.setText("");
              _react.sendMessageToQueue(queryInput.getText());
            }
          },
          Constants.GOOGLE
      );
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    encoder = new VadSegmenter(_recognizer);

    try {
      // open streaming micro
      in = new Microphone(_recognizer.getDesiredAudioFormat());
      // connect micro output to segmenter/flac encoder/recognizer
      in.captureStreaming(encoder);
      in.start();
    } catch (LineUnavailableException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    */
  }

  private void pause() {
    /*
    if (in.isRunning()) {
      in.stop();
    } else {
      in.start();
    }
    */
  }

  private void stop() {
    /*
    try {
      if (encoder != null) {
        encoder.close();
      }
      if (in != null) {
        in.close();
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    */
  }

  protected void errorDialog(String string) {
    JOptionPane.showMessageDialog(this, string, "Error",
        JOptionPane.ERROR_MESSAGE);
  }

  protected RecorderGui.RunnableAction startAction() {
    return new RecorderGui.RunnableAction(
            "Record", "icons/gtk-record", "Start Recording", "Start Recording",
            KeyStroke.getKeyStroke(Character.valueOf('n'), InputEvent.ALT_DOWN_MASK),
            new Runnable() {
      public void run() {
        File outputFile
                = //newFileDialog();
                new File("emptytest.flac");
        if (outputFile != null) {
          try {
            OutputStream outstream = new FileOutputStream(outputFile, false);
            start();
          } catch (FileNotFoundException ex) {
            errorDialog("No such file: " + outputFile);
          }
        }
      }
    });
  }

  protected RecorderGui.RunnableAction pauseAction() {
    return new RecorderGui.RunnableAction(
            "Pause", "actions/gtk-media-pause", "Pause", "Pause",
            KeyStroke.getKeyStroke(Character.valueOf('w'),
                    InputEvent.META_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK),
            new Runnable() {
      public void run() {
        pause();
      }
    });
  }

  protected RecorderGui.RunnableAction stopAction() {
    return new RecorderGui.RunnableAction(
            "Stop", "actions/gtk-media-stop", "Stop Recording", "Stop Recording",
            KeyStroke.getKeyStroke((char) KeyEvent.VK_O),
            new Runnable() {
      public void run() {
        stop();
      }
    });
  }

  public void initializeComponents() {

    // use native windowing system to position new frames
    this.setLocationByPlatform(true);
    // set preferred size
    this.setPreferredSize(new Dimension(800, 600));
    setDefaultFont(18);
    /*
    try {
      ASRServiceFactory.init(null);
    } catch (IOException ex) {}
     */

    // create content panel and add it to the frame
    JPanel contentPane = new JPanel(new BorderLayout());
    this.setContentPane(contentPane);

    ActionListener execute = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        synchronized(queryInput) {
          String input = queryInput.getText();

          // save the current output to a file specified in brackets
          if (input.contains("write(")) {
            try {
              String filename = Utilities.slice_range(input, 6, -1);
              _chat.writeLog(filename);
              _statusbar.setText("Output written to file " + filename + ".txt");
              return;
            } catch (FileNotFoundException ex) {
              Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
              _react.shutdown();
            } catch (UnsupportedEncodingException ex) {
              Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
              _react.shutdown();
            }
          }

          // check for special expressions, otherwise return to GUI and
          // process in chat
          switch (input) {

          // clear history aka chat
          case "clear()" :
            _chat.resetMessages();
            queryInput.setText("");
            _statusbar.setText("History has been cleared.");
            return;

          // dump history aka chat to standard file called "log.txt"
          case "write()" :
            try {
              _chat.writeLog();
              _statusbar.setText("Output written to file log.txt");
              return;
            } catch (FileNotFoundException ex) {
              Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
              _react.shutdown();
            } catch (UnsupportedEncodingException ex) {
              Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
              _react.shutdown();
            }

          case "" :
            return;

          default :
            String timestamp = Utilities.getTimeStamp();
            _chat.sendMessage(timestamp + " " + input);
            _react.sendMessageToQueue(input);
            queryInput.setText("");
          }
        }
      }
    };

    JPanel south = new JPanel();
    south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));

    JPanel south_buttons = new JPanel();
    south_buttons.setLayout(new BoxLayout(south_buttons, BoxLayout.X_AXIS));

    JButton jb = getIconButton("info");
    jb.setToolTipText("show help");
    south_buttons.add(jb);

    jb = getIconButton("gtk-record");
    jb.setToolTipText("start recording speech");
    south_buttons.add(jb);
    jb.addActionListener(startAction());

    jb = getIconButton("gtk-pause");
    jb.setToolTipText("pause recording speech");
    south_buttons.add(jb);
    jb.addActionListener(pauseAction());

    jb = getIconButton("gtk-stop");
    jb.setToolTipText("stop recording speech");
    south_buttons.add(jb);
    jb.addActionListener(stopAction());

    south_buttons.add(queryInput = createQueryInput(execute));

    jb = getIconButton("gtk-apply");
    jb.setToolTipText("execute query");
    south_buttons.add(jb);
    jb.addActionListener(execute);

    jb = getIconButton("gtk-clear");
    jb.setToolTipText("clear input field");
    south_buttons.add(jb);
    jb.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        queryInput.setText("");
      }});

    south.add(south_buttons);

    JPanel south_status = new JPanel();
    south_status.setLayout(new FlowLayout(FlowLayout.LEFT));
    _statusbar = new JLabel("Welcome", SwingConstants.LEFT);
    _statusbar.setForeground(new Color(168, 168, 168));
    south_status.add(_statusbar);

    _chat = new ChatProtocol(mainFrame,
    new Listener<String>() {
      @Override
      public void listen(String q) {
        queryInput.setText(q);
      }
    },
    execute, _statusbar);

    south.add(south_status);
    contentPane.add(_chat);
    contentPane.add(south, BorderLayout.SOUTH);

    // display the frame
    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }



  public GUI() {
    super("Reco / Socrates test environment");

    mainFrame = this;

    // set handler for closing operations
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
