package start;

import java.awt.*;

import javax.swing.*;

public class Mmm extends JFrame{

JPanel panel;

  public static void main(String[] args) {

    // TODO 自動生成されたメソッド・スタブ
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(10, 10, 540, 500);
    frame.setTitle("タイトル");
    JPanel mainPanel = new JPanel();
    Mmm[] taskView = new Mmm[11];//11個のタスク追加を想定
    SpringLayout layout1 = new SpringLayout();
    mainPanel.setLayout(layout1);

    JScrollPane scrollpane = new JScrollPane(mainPanel);

    for(int i=0;i<11;i++){

      taskView[i] = new Mmm("task"+String.valueOf(i),2016,7,8,12,9);

      if(i>0){

        System.out.println(i);
        layout1.putConstraint(SpringLayout.NORTH, taskView[i].getPanel(), 1, SpringLayout.SOUTH, taskView[i-1].getPanel());//前のタスクの1px下に配置

      }
      mainPanel.setPreferredSize(new Dimension(510,61*(i+1)));//Task数に合わせてmainPanelのサイズを変更する
      mainPanel.add(taskView[i].getPanel());
    }


    frame.getContentPane().add(scrollpane);
    frame.setVisible(true);

  }

  Mmm(String taskName,int year,int month,int date,int hour,int minute){

    this.panel = new JPanel();
    this.panel.setPreferredSize(new Dimension(500,40));
    this.panel.setBackground(Color.WHITE);
    SpringLayout layout = new SpringLayout();
    this.panel.setLayout(layout);

    JCheckBox ck = new JCheckBox();
    JLabel taskNameLabel = new JLabel(taskName);
    taskNameLabel.setFont(taskNameLabel.getFont().deriveFont(20.0f));

    layout.putConstraint(SpringLayout.NORTH, ck, 5, SpringLayout.NORTH, this.panel);
    layout.putConstraint(SpringLayout.WEST, ck, 5, SpringLayout.WEST, this.panel);
    layout.putConstraint(SpringLayout.NORTH, taskNameLabel, -5, SpringLayout.NORTH, ck);
    layout.putConstraint(SpringLayout.WEST, taskNameLabel, 10, SpringLayout.EAST, ck);

    this.panel.add(ck);
    this.panel.add(taskNameLabel);

    if(year != -1){
    JLabel dateLabel = new JLabel(String.format("%4d/%02d/%02d",year,month,date));
    dateLabel.setFont(dateLabel.getFont().deriveFont(10.0f));

    layout.putConstraint(SpringLayout.NORTH, dateLabel, 20, SpringLayout.NORTH, ck);
    layout.putConstraint(SpringLayout.WEST, dateLabel, 10, SpringLayout.EAST, ck);
    this.panel.add(dateLabel);
    }

    if(hour != -1){
      JLabel timeLabel = new JLabel(String.format("%02d:%02d",hour,minute));
      timeLabel.setFont(timeLabel.getFont().deriveFont(10.0f));

      layout.putConstraint(SpringLayout.NORTH, timeLabel, 20, SpringLayout.NORTH, ck);
      layout.putConstraint(SpringLayout.WEST, timeLabel, 80, SpringLayout.EAST, ck);
      this.panel.add(timeLabel);
    }

    JButton button = new JButton("編集");
    layout.putConstraint(SpringLayout.NORTH, button, 0, SpringLayout.NORTH, ck);
    layout.putConstraint(SpringLayout.EAST, button, -30, SpringLayout.EAST, this.panel);
    this.panel.add(button);

  }

  JPanel getPanel(){return this.panel;}

}
