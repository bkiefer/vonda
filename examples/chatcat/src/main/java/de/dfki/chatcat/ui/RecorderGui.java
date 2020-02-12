package de.dfki.chatcat.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

//import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

//import de.dfki.lt.vad.VadSegmenter;

@SuppressWarnings("serial")
public class RecorderGui extends JFrame {
  /** Action Buttons */
  protected ArrayList<JButton> _actionButtons = new ArrayList<JButton>();

  protected static String _defaultFont = "Monospace";
  protected static File _iconPath = null;

  protected Font _textFont = new Font(_defaultFont, 0, 12);

  protected Dimension _preferredSize = new Dimension(400, 64);

  /** This contains the current directory. */
  protected File _currentDir;

  //private Microphone in;

  //private VadSegmenter encoder;

  //private DataLogger dl;
  private boolean _isLogging = false;

  JLabel outputView;

  protected RecorderGui(String title) {
    super(title);
    _currentDir = new File(".");
    this.initFrame();
  }

  protected void errorDialog(String string) {
    JOptionPane.showMessageDialog(this, string, "Error",
        JOptionPane.ERROR_MESSAGE);
  }

  /* *************************************************************************
   * Button and Menu specifications
   *
   * Implemented as methods to avoid the bogus fields in derived classes
   * *************************************************************************/

  private static URL iconUrl(String name) {
    File imgLocation =
        new File(new File(_iconPath, "24x24/"), name + ".png");
    // URL imageURL = MainFrame.class.getResource(imgLocation);
    if (imgLocation.exists()) {
      try {
        return imgLocation.toURI().toURL();
      } catch (MalformedURLException e) {
      }
    }
    return null;
  }

  public static class RunnableAction extends AbstractAction {
    private Runnable _r;

    public RunnableAction(String title, String iconName, String toolTipText,
        String altText, Object key, Runnable r) {
      super(title);
      if (iconName != null) {
        URL iconURL = iconUrl(iconName);
        if (iconURL != null) {
          putValue(SMALL_ICON, new ImageIcon(iconURL, altText));
        }
      }
      putValue(SHORT_DESCRIPTION, toolTipText);
      if (key != null)
        putValue(ACCELERATOR_KEY, key);
      _r = r;
    }

    public void actionPerformed(ActionEvent e) {
      _r.run();
    }
  }

  protected File newFileDialog() {
    // create file chooser for txt files
    JFileChooser jfc = new JFileChooser();
    FileFilter fexf = new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        return pathname.getName().endsWith(".flac");
      }

      @Override
      public String getDescription() { return "Select a FLAC file"; }};
    if (fexf != null) {
      jfc.addChoosableFileFilter(fexf);
    }
    jfc.setCurrentDirectory(_currentDir);
    int returnVal = -1;
    boolean success = false;
    do {
      returnVal = jfc.showOpenDialog(RecorderGui.this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        // update current directory
        _currentDir = jfc.getSelectedFile().getParentFile();
        // get the object read from this file
        File toRead = jfc.getSelectedFile();
        if (toRead.exists()) {
          errorDialog("file exists: " + toRead);
          success = false;
        } else {
          return toRead;
        }
      }
    } while (! success && returnVal != JFileChooser.CANCEL_OPTION);
    return null;
  }

  protected RunnableAction startAction() {
    return new RunnableAction(
        "Record", "actions/gtk-media-record", "Start Recording", "Start Recording",
      KeyStroke.getKeyStroke(Character.valueOf('n'), InputEvent.ALT_DOWN_MASK),
      new Runnable() { public void run() {
        File outputFile = //newFileDialog();
            new File("emptytest.flac");
        if (outputFile != null) {
          try (OutputStream outstream = new FileOutputStream(outputFile, false)) {
            //start();
          } catch (IOException ex) {
            errorDialog("Exception: " + outputFile + " " + ex);
          }
        }
      }});
  }

  protected RunnableAction pauseAction() {
    return new RunnableAction(
      "Pause", "actions/gtk-media-pause", "Pause", "Pause",
      KeyStroke.getKeyStroke(Character.valueOf('w'),
          InputEvent.META_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK),
          new Runnable() { public void run() {//pause();

          } });
  }

  protected RunnableAction stopAction() {
    return new RunnableAction(
        "Stop", "actions/gtk-media-stop", "Stop Recording", "Stop Recording",
      KeyStroke.getKeyStroke((char)KeyEvent.VK_O),
      new Runnable() { public void run() { //stop();

      } });
  }


  /*
  protected RunnableAction logAction() {
    return new RunnableAction(
        "Save Audio", "status/audio-volume-muted", "Audio Log on/off", "Audio Log on/off",
        KeyStroke.getKeyStroke((char)KeyEvent.VK_O),
        new Runnable() {
          @Override
          public void run() {
            if (dl == null) return;
            if (_isLogging) {
              URL iconURL = iconUrl("status/audio-volume-muted");
              if (iconURL != null) {
                _actionButtons.get(3).getAction().putValue(Action.SMALL_ICON,
                    new ImageIcon(iconURL, "audio log disabled"));
              }
              dl.stopLogging();
              _isLogging = false;
            } else {
              URL iconURL = iconUrl("status/audio-volume-high");
              if (iconURL != null) {
                _actionButtons.get(3).getAction().putValue(Action.SMALL_ICON,
                    new ImageIcon(iconURL, "audio log running"));
              }
              dl.startLogging();
              _isLogging = true;
            }
          }
        });
  }*/

  protected File getResourcesDir() {
    return new File("/usr/share/icons/mate/");
  }

  protected void initFrame() {
    try {
      _iconPath = getResourcesDir();
      //LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
      String lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
      // UIManager.getSystemLookAndFeelClassName();
      UIManager.setLookAndFeel(lookAndFeel);
      if (! _iconPath.isDirectory()) {
        if (lookAndFeel.contains("GTK")) {
          _iconPath = new File("/usr/share/icons/mate/");
        }
      }
    } catch (ClassNotFoundException e) {
      // well, we're content with everything we get
    } catch (InstantiationException e) {
      // well, we're content with everything we get
    } catch (IllegalAccessException e) {
      // well, we're content with everything we get
    } catch (UnsupportedLookAndFeelException e) {
      // well, we're content with everything we get
    }

    // use native windowing system to position new frames
    this.setLocationByPlatform(true);
    // set preferred size
    this.setPreferredSize(_preferredSize);
    // set handler for closing operations
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel contentPane = new JPanel(new BorderLayout());
    this.setContentPane(contentPane);

    JPanel bottomLine = new JPanel();
    bottomLine.setLayout(new BoxLayout(bottomLine, BoxLayout.LINE_AXIS));

    RunnableAction[] specs = {
      startAction(), pauseAction(), stopAction()
    };
    for (RunnableAction spec : specs) {
      JButton newButton = new JButton(spec);
      if (newButton.getIcon() != null) {
        newButton.setText(null);
      }
      _actionButtons.add(newButton);
      bottomLine.add(newButton);
    }

    outputView = new JLabel("Output will end up here");

    contentPane.add(bottomLine, BorderLayout.CENTER);
    contentPane.add(outputView, BorderLayout.EAST);


    // display the frame
    this.pack();
    this.setVisible(true);
  }

/*
  private void start() throws IOException {
    ASRServiceFactory factory = ASRServiceFactory.getFactory();
    final RecognizerService _recognizer =
        factory.getRecognizer(
            new Listener<String>(){
              @Override
              public void receive(String t) {
                outputView.setText(t);
              }
            }, factory.getDefaultRecognizer()
        );
    dl = new DataLogger();
    dl.setDelegate(_recognizer);
    //dl.startLogging();
    //_isLogging = true;
    encoder = new VadSegmenter(dl);

    try {
      // open streaming micro
      in = new Microphone(_recognizer.getDesiredAudioFormat());
      // connect micro output to segmenter/flac encoder/recognizer
      in.captureStreaming(encoder);
      in.start();testURI
    } catch (LineUnavailableException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private void pause() {
    if (in.isRunning()) in.stop();
    else in.start();
  }

  private void stop() {
    try {
      if (encoder != null) encoder.close();
      if (in != null) in.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }*/

  static final Integer startuplock = new Integer(0);
  static RecorderGui gui;

  public static String custom_config = "";

  public static void main(String[] args)
      throws InterruptedException, IOException {
    String custom_config = null;
    if (args.length != 0)
      custom_config = args[0];
    //ASRServiceFactory.init(custom_config);
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public synchronized void run() {
        // synch this otherwise the colors look bad
        // synchronized (startuplock) {
          // arg is tablet IP, currently ignored
          RecorderGui g = new RecorderGui("FLAC Recorder");
        //}
      }});
    /*
    gui = new RecorderGui("String");
    gui.start(new FileOutputStream("test.flac", false));
    Thread.sleep(3000);
    gui.stop();
    */
  }

}
