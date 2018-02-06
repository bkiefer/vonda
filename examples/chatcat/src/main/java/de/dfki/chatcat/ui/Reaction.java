/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.chatcat.ui;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JLabel;

import de.dfki.chatcat.StubClient;
import de.dfki.mlt.rudimant.agent.Behaviour;
import de.dfki.mlt.rudimant.agent.DialogueAct;

/**
 *
 * @author Christophe Biwer, christophe.biwer@dfki.de
 */
public class Reaction implements Runnable, Listener<Behaviour> {

  private StubClient _stub;
  private ChatProtocol _chat;
  private BlockingQueue<String> _q;
  private JLabel _statusbar;

  private boolean compute = true;

  public Reaction(StubClient client, ChatProtocol chat,
          JLabel statusbar) {
    _chat = chat;
    _stub = client;
    client.registerBehaviourListener(this);
    _q = new LinkedBlockingQueue<String>();
    _statusbar = statusbar;
  }

  public void execute() {
    Thread t = new Thread(this);
    t.setName("Reaction");
    t.setDaemon(true);
    t.start();
  }

  public void shutdown() {
    compute = false;
  }

  public void sendMessageToQueue(String mes) {
    _q.add(mes);
  }

  /*
  This is where the input will be processed by our dialog management.
  */
  public void processInputMessage(String in) {
    DialogueAct da = _stub.analyse(in);
    _statusbar.setText(da == null ? "No dialogueact" : da.toString());
    if (da != null) {
      _stub.sendEvent(da);
    }
  }

  public void run() {
    while (compute) {
      String s;
      try {
        if ((s = _q.take()) != null) {
          // _chat.sendMessage("Got: " + s);
          processInputMessage(s);
        }
      } catch (InterruptedException e) {
        compute = false;
      }
    }
  }

  @Override
  public void listen(Behaviour q) {
    _chat.sendMessage(Utilities.getTimeStamp() + " >>> " + q.getText());
    System.out.println("Response:" + q.getText());
  }
}
