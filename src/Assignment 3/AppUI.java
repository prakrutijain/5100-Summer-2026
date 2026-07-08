
// Prakruti Jain

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AppUI {

    // file service handles all reading/writing
    private FileService fileService = new FileService();

    public AppUI() {
        // create the main window
        JFrame frame = new JFrame("Annual Data File Reader");
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ── INPUT SECTION ──
        JLabel inputLabel = new JLabel("Input File Name:");
        JTextField inputFileField = new JTextField("annual.csv");
        inputFileField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JButton readButton = new JButton("Read File");

        // text area to show original file preview
        JTextArea originalPreview = new JTextArea(8, 60);
        originalPreview.setEditable(false);
        originalPreview.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane originalScroll = new JScrollPane(originalPreview);

        // ── OUTPUT SECTION ──
        JLabel outputLabel = new JLabel("Output File Name:");
        JTextField outputFileField = new JTextField("output.csv");
        outputFileField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JButton writeButton = new JButton("Write File");
        writeButton.setEnabled(false); // disabled until file is read

        // text area to show new file preview
        JTextArea newFilePreview = new JTextArea(8, 60);
        newFilePreview.setEditable(false);
        newFilePreview.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane newFileScroll = new JScrollPane(newFilePreview);

        // ── READ BUTTON ACTION ──
        readButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileName = inputFileField.getText().trim();

                // check if file name is empty
                if (fileName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a file name!");
                    return;
                }

                try {
                    // read the file and show preview
                    String preview = fileService.readFile(fileName);
                    originalPreview.setText(preview);
                    writeButton.setEnabled(true); // enable write button
                    JOptionPane.showMessageDialog(frame, "File read successfully!");

                } catch (Exception ex) {
                    // show error if file not found or other issue
                    originalPreview.setText("");
                    writeButton.setEnabled(false);
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ── WRITE BUTTON ACTION ──
        writeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String outputName = outputFileField.getText().trim();

                // check if output file name is empty
                if (outputName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter an output file name!");
                    return;
                }

                try {
                    // write the file and show preview
                    String preview = fileService.writeFile(outputName);
                    newFilePreview.setText(preview);
                    JOptionPane.showMessageDialog(frame, "File written successfully!");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ── ADD EVERYTHING TO PANEL ──
        mainPanel.add(inputLabel);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(inputFileField);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(readButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(new JLabel("Original File Preview (header + first 5 lines):"));
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(originalScroll);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(outputLabel);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(outputFileField);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(writeButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(new JLabel("New File Preview (header + first 5 lines):"));
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(newFileScroll);

        // add panel to frame and show window
        frame.add(new JScrollPane(mainPanel));
        frame.setVisible(true);
    }
}