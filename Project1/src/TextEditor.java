import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    //Declaring Properties of TextEditor
    JFrame frame;

    //Declaring menu bar
    JMenuBar menubar;

    //Declaring fun of menubar
    JMenu file,edit;

    JMenuItem newfile,openfile,savefile;

    JMenuItem cut,copy,paste,selectall,close;

    JTextArea textArea;
    TextEditor()
    {
        //Initializing frame
        frame = new JFrame();

        //Initializing menubar;
        menubar = new JMenuBar();

        //Initializing textarea
        textArea = new JTextArea();

        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Initializing file menu items
        newfile = new JMenuItem("New File");
        openfile = new JMenuItem("Open File");
        savefile = new JMenuItem("Save");

        //add action listeners to the file menu items
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);

        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

        //Initializing edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectall = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //add action listeners to the edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.add(close);

        //pairing fun to the menubar
        menubar.add(file);
        menubar.add(edit);

        //adding menu bar to the  frame
        frame.setJMenuBar(menubar);

        //creating content panel
        JPanel jPanel = new JPanel();
        jPanel.setBorder(new EmptyBorder(5,5,5,5));
        jPanel.setLayout(new BorderLayout(0,0));

        //add textarea to panel
        jPanel.add(textArea,BorderLayout.CENTER);

        //create scroll pane
        JScrollPane jScrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //add scroll pne to panel
        jPanel.add(jScrollPane);

        //add panel to frame
        frame.add(jPanel);

        //set Dimensions of frame
        frame.setBounds(0,0,500,500);

        // setting title to frame
        frame.setTitle("Text Editor");

        //set true as visible for every initializing of frame
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if(actionEvent.getSource() == cut)
        {
            //perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource() == copy)
        {
            textArea.copy();
        }
        if(actionEvent.getSource() == paste)
        {
            textArea.paste();
        }
        if(actionEvent.getSource() == selectall)
        {
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close)
        {
            System.exit(0);
        }
        if(actionEvent.getSource() == openfile)
        {
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option from filechooser
            int chooseOption = fileChooser.showOpenDialog(null);
            // if we have clicked on open button
            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                //getting selected file
                File file = fileChooser.getSelectedFile();
                // get the path of selected file
                String filepath = file.getPath();
                try {
                    //initialize file reader
                    FileReader fileReader = new FileReader(filepath);
                    //initialize buffer reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "",output = "";
                    //Read contents of text line by line
                    while((intermediate = bufferedReader.readLine()) != null)
                    {
                        output += intermediate+"\n";
                    }
                    // set output string to the Textarea
                    textArea.setText(output);
                }
                catch (IOException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == savefile)
        {
            //Initialize a file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //check if clicked a save button
            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                //create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsoluteFile()+".txt");

                try {
                    //initialize a file writer
                    FileWriter fileWriter = new FileWriter(file);
                    // initialize a buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == newfile)
        {
            TextEditor newTextEditor = new TextEditor();
        }

    }
    public static void main(String[] args)
    {
        TextEditor texteditor = new TextEditor();

    }
}