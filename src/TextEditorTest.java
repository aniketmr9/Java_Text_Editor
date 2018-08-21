import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.drjekyll.fontchooser.FontDialog;

public class TextEditorTest implements ActionListener
{	
	JFrame frame;
	JMenuItem newFile,saveFile,saveAsFile,openFile,exit,selectAll,cut,copy,paste,font;
	JMenu fileMenu,editMenu,formatMenu;
	JMenuBar menuBar;
	JTextArea textArea;	
	JScrollPane scrollPane;
	TextEditorUtil textEditorUtil = new TextEditorUtil();
	public TextEditorTest()
	{
		frame = new JFrame("My Text Editor");		
		menuBar = new JMenuBar();
		newFile = new JMenuItem("New");
		saveAsFile = new JMenuItem("SaveAs");
		saveFile = new JMenuItem("Save");
		openFile = new JMenuItem("Open");
		selectAll = new JMenuItem("SelectAll");
		exit = new JMenuItem("Exit");
		cut = new JMenuItem("Cut");
		copy = new JMenuItem("Copy");
		paste = new JMenuItem("Paste");
		font = new JMenuItem("Font");
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");	
		formatMenu = new JMenu("Format");
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);		
		/*textArea.setBounds(0, 0, 400, 500);*/
		
		newFile.addActionListener(this);
		saveFile.addActionListener(this);
		saveAsFile.addActionListener(this);
		openFile.addActionListener(this);
		exit.addActionListener(this);		
		selectAll.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		font.addActionListener(this);
		
		fileMenu.add(newFile);
		fileMenu.add(openFile);
		fileMenu.add(saveFile);
		fileMenu.add(saveAsFile);
		fileMenu.add(exit);
		
		editMenu.add(cut);
		editMenu.add(copy);
		editMenu.add(paste);
		editMenu.add(selectAll);		
				
		formatMenu.add(font);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(formatMenu);
		
		frame.setJMenuBar(menuBar);		
		frame.add(textArea, BorderLayout.CENTER);
		
		BorderLayout borderLayout = new BorderLayout();
		frame.setSize(500, 600);
		frame.addComponentListener(new ComponentAdapter()
		{
            public void componentResized(ComponentEvent e) {
                textArea.setBounds(0, 0, frame.getWidth(), frame.getHeight());
            }
        });
		frame.setLayout(borderLayout);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		System.out.println(actionEvent.getActionCommand());
		String command = actionEvent.getActionCommand();
		
		//New File
		if(command.equals("New"))
		{
			frame.dispose();
			new TextEditorTest();
		}
		//Open File
		if(command.equals("Open"))
		{
			textEditorUtil.openFile(textArea,frame);
		}
		//Save Existing File
		if(command.equals("Save"))
		{
			textEditorUtil.saveExistingFile(textArea,frame);
		}
		//Save As File
		if(command.equals("SaveAs"))
		{				
			textEditorUtil.saveFile(textArea,frame);
		}
		//Exit Text Editor
		if(command.equals("Exit"))
		{
			System.exit(1);
		}
		
		//Select All
		if(command.equals("Cut"))
		{
			textArea.cut();		
		}
		//Select All
		if(command.equals("Copy"))
		{
			textArea.copy();		
		}
		//Select All
		if(command.equals("Paste"))
		{
			textArea.paste();
		}
		//Select All
		if(command.equals("SelectAll"))
		{
			textArea.selectAll();		
			textArea.setSelectedTextColor(Color.BLUE);
			textArea.setSelectionColor(Color.YELLOW);
		}
		//Select Font
		if(command.equals("Font"))
		{
			FontDialog.showDialog(textArea);
		}
	}	
	public static void main(String[] args)
	{
		new TextEditorTest();
	}
}
