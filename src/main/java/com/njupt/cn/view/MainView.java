package com.njupt.cn.view;

import com.njupt.cn.control.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ref.SoftReference;

public class MainView {
    private Frame mainFrame = new Frame("抽奖小程序");
    private Button startBtn = new Button("开始抽选");
    private Button endBtn = new Button("结束抽选");
    private JLabel label = new JLabel("中奖者");

    private SoftReference<MainController> controller;


    private static MainView instance;

    private MainView() {

    }

    public static synchronized MainView getInstance() {
        instance = new MainView();
        return instance;
    }

    public void setReference(SoftReference<MainController> controller) {
        this.controller = controller;
    }


    public void init() {
        startBtn.setBounds(300, 450, 200, 100);
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.get() != null) {
                    class myRun implements Runnable {
                        @Override
                        public void run() {
                            controller.get().onStartClicked();
                        }
                    }
                    Thread thread = new Thread(new myRun());
                    thread.start();
                }
            }
        });
        startBtn.setFont(new Font("Serif", Font.PLAIN, 20));

        endBtn.setBounds(800, 450, 200, 100);
        endBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.get() != null) {
                    class myRun implements Runnable {
                        @Override
                        public void run() {
                            controller.get().onEndClicked();
                        }
                    }
                    Thread thread = new Thread(new myRun());
                    thread.start();
                }
            }
        });
        endBtn.setFont(new Font("Serif", Font.PLAIN, 20));

        label.setText("点击开始抽选开始");
        label.setBounds(500, 200, 400, 200);
        label.setFont(new Font("Serif", Font.PLAIN, 30));

        mainFrame.add(label);
        mainFrame.add(startBtn);
        mainFrame.add(endBtn);
        mainFrame.setSize(1280, 720);
        mainFrame.setLocation(100, 100);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
    }

    public void setAwardedInfo(String name) {
        label.setText(name);
    }

}
