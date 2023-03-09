package org.example.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//NOT USED
public class MyFrame extends JFrame implements ActionListener {

    JButton btn;
    String filePath = "";
    public MyFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        btn = new JButton("Browse Files");
        btn.addActionListener(this);

        this.add(btn);
        this.pack();
        this.setVisible(true);
    }

    public String getFilePath() {
        return this.filePath;
    }

    public MyFrame setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btn) {
            JFileChooser fileChooser = new JFileChooser();

            int response = fileChooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                setFilePath(file.getAbsolutePath());
                String path = file.getAbsolutePath();

            }
        }
    }
}
