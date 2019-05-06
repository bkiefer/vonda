/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.chatcat.ui;

import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Christophe Biwer, christophe.biwer@dfki.de
 */
public class ChatProtocol extends JPanel {

  public final JTable _table;
  private final Listener<String> _listener;
  private final ActionListener _exe;
  private final JScrollPane _scrollpane;

  public static Yaml yaml = new Yaml();

  public LinkedList<String> messages;

  public ChatProtocol(Frame owner, Listener<String> listener,
      ActionListener exe, JLabel statusbar) {
    _listener = listener;
    _exe = exe;
    this.setPreferredSize(new Dimension(300, 120));

    String homepath = System.getProperty("user.home");
    File file = new File(homepath + "/.chat-history.yml");
    try {
      ArrayList<String> temp = (ArrayList<String>)
          yaml.load(new FileInputStream(file));
      if (temp != null) {
        messages = new LinkedList<>(temp);
      }
    } catch (FileNotFoundException e) {
      // fixed by the next if
    }
    if (messages == null) {
      statusbar.setText("No chat history found, creating a new one.");
      messages = new LinkedList<>();
    }

    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
     _scrollpane = new JScrollPane(_table = makeTable());
    _table.setTableHeader(null);
    _table.setRowHeight(30);
    this.add(_scrollpane, BorderLayout.CENTER);
    scrollToBottom(_scrollpane);
  }

  public void scrollToBottom(JScrollPane scrollPane) {
    JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
    AdjustmentListener downScroller = new AdjustmentListener() {
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            Adjustable adjustable = e.getAdjustable();
            adjustable.setValue(adjustable.getMaximum());
            verticalBar.removeAdjustmentListener(this);
        }
    };
    verticalBar.addAdjustmentListener(downScroller);
}

  public void resetMessages() {
    messages.clear();
    ((AbstractTableModel) _table.getModel()).fireTableStructureChanged();
    scrollToBottom(_scrollpane);
    try {
      String homepath = System.getProperty("user.home");
      FileWriter writer = new FileWriter((homepath + "/.chat-history.yml"), false);
      yaml.dump(messages, writer);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  public void sendMessage(String input) {
    messages.addLast(input);
    ((AbstractTableModel) _table.getModel()).fireTableStructureChanged();
    scrollToBottom(_scrollpane);
    try {
      String homepath = System.getProperty("user.home");
      FileWriter writer = new FileWriter((homepath + "/.chat-history.yml"), false);
      yaml.dump(messages, writer);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  public void writeLog() throws FileNotFoundException, UnsupportedEncodingException {
    String homepath = System.getProperty("user.home");
    try {
      PrintWriter writer = new PrintWriter(homepath + "/Desktop/log.txt", "UTF-8");
      for (String x : messages) {
          writer.println(x);
      }
      writer.close();
    } catch (FileNotFoundException ex) {
      throw new RuntimeException(ex);
    }
  }

  public void writeLog(String filename) throws FileNotFoundException, UnsupportedEncodingException {
    String homepath = System.getProperty("user.home");
    try {
      PrintWriter writer = new PrintWriter(homepath + "/Desktop/" + filename + ".txt", "UTF-8");
      for (String x : messages) {
          writer.println(x);
      }
      writer.close();
    } catch (FileNotFoundException ex) {
      throw new RuntimeException(ex);
    }
  }

  private class RecentQueryModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private LinkedList<String> _d;

    public RecentQueryModel(LinkedList<String> qr) { _d = qr;}

    @Override
    public int getRowCount() { return _d.size(); }

    @Override
    public int getColumnCount() { return 1; }

    @Override
    public String getColumnName(int column) {
      return "None";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      return _d.get(rowIndex);//.get(columnIndex);
    }
  }

  private JTable makeTable() {
    final JTable table = new JTable();
    // listener to sort columns
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int row = table.rowAtPoint(e.getPoint());
        RecentQueryModel qrm = (RecentQueryModel)table.getModel();
        String var = ((String)qrm.getValueAt(row, 0));
        // remove timestamp when copying
        _listener.listen(var.substring(22));
        if (e.getClickCount() == 2) {
          ActionEvent f = new ActionEvent(e.getSource(), e.getID(), e.paramString());
          _exe.actionPerformed(f);
        }
      }
    });
    TableModel tm = new RecentQueryModel(messages);
    table.setModel(tm);
    return table;
  }

}
