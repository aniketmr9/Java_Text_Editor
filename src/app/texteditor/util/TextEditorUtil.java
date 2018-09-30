package app.texteditor.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class TextEditorUtil
{
	JFileChooser fileChooser = new JFileChooser();
	File openFileDir = null;
	File saveFileDir = null;
	//Open file
	public void openFile(JTextArea textArea, JFrame frame)
	{
		int approveOpen = fileChooser.showOpenDialog(null);
		System.out.println(approveOpen);
		if(approveOpen == JFileChooser.APPROVE_OPTION)
		{
			openFileDir = fileChooser.getSelectedFile();
			System.out.println(openFileDir.getAbsolutePath());
			openFileDir = new File(openFileDir.getAbsolutePath());
			String textToOpen = "",lineInFile = null;
			BufferedReader reader = null;				
			try
			{
				reader = new BufferedReader(new FileReader(openFileDir));
				while((lineInFile = reader.readLine()) != null)
				{
					if(lineInFile.isEmpty())
					{
						textToOpen = textToOpen + "\n\n";
					}
					else
					{
						textToOpen = textToOpen + lineInFile;
					}
				}
				textArea.setText(textToOpen);
				frame.setTitle(openFileDir.getName());
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally 
			{
				try
				{
					reader.close();
				} 
				catch (IOException e)
				{					
					e.printStackTrace();
				}
			}
		}
		else
		{
			//cancel open file option
		}
	}
	
	//Save As file
	public void saveFile(JTextArea textArea, JFrame frame)
	{
		int approveSave = fileChooser.showSaveDialog(null);
		System.out.println(approveSave);		
		if(approveSave == JFileChooser.APPROVE_OPTION)
		{
			saveFileDir = fileChooser.getSelectedFile();
			System.out.println(saveFileDir.getAbsolutePath());
			String textToSave = textArea.getText();
			saveFileDir = new File(saveFileDir.getAbsolutePath());
			BufferedWriter writer = null;
			try
			{
				writer = new BufferedWriter(new FileWriter(saveFileDir));
				writer.write(textToSave);
				frame.setTitle(saveFileDir.getName());
			} 
			catch (IOException e)
			{					
				e.printStackTrace();
			}
			finally 
			{
				try
				{
					writer.close();
				} 
				catch (IOException e)
				{						
					e.printStackTrace();
				}
			}				
		}
		else
		{
			//cancel save file option
		}
	}
	
	//Save Existing File
	public void saveExistingFile(JTextArea textArea, JFrame frame)
	{	
		File saveExistingFileDir = null;
		if((openFileDir == null) && (saveFileDir == null))
		{
			saveFile(textArea, frame);
		}
		else
		{
			System.out.println("save existing");
			String textToSave = textArea.getText();
			if(openFileDir == null)
			{
				saveExistingFileDir = new File(saveFileDir.getAbsolutePath());
			}
			else if(saveFileDir == null)
			{
				saveExistingFileDir = new File(openFileDir.getAbsolutePath());
			}
			BufferedWriter writer = null;
			try
			{
				writer = new BufferedWriter(new FileWriter(saveExistingFileDir));
				writer.write(textToSave);
			} 
			catch (IOException e)
			{					
				e.printStackTrace();
			}
			finally 
			{
				try
				{
					writer.close();
				} 
				catch (IOException e)
				{						
					e.printStackTrace();
				}
			}		
		}
	}
	
}
