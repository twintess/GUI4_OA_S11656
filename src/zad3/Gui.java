package zad3;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class Gui extends JFrame implements ActionListener
{	
	private static final long serialVersionUID = 1L;
	private ArrayList<JMenuItem> fileItems;
	private ArrayList<JMenuItem> editItems;
	private ArrayList<JMenuItem> fontsizeItems;
	private ArrayList<JMenuItem> backgroundItems;
	private ArrayList<JMenuItem> foregroundItems;

	private JTextArea workspace;
	private static String pathname;
	private static File actualFile;
	private static HashMap<String, String> adresses;
	private static HashMap<String, Color> colors;
	
	static
	{
		pathname = ".";
		actualFile = null;
		adresses = new HashMap<String, String>();
		adresses.put("praca", "Praca ul. Dickensa 1 Warszawa 02-154 ");
		adresses.put("szkoła", "Szkoła ul. Dickensa 2 Warszawa 02-154 ");
		adresses.put("dom", "Dom ul. Dickensa 3 Warszawa 02-154 ");
		colors = new HashMap<String, Color>();
		colors.put("red", Color.red);
		colors.put("blue", Color.blue);
		colors.put("green", Color.green);
		colors.put("yellow", Color.yellow);
		colors.put("black", Color.black);
	}

	public Gui()
	{
		super("Text editor - bez tytułu");
		
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu options = new JMenu("Options");
		JMenu addresses = new JMenu("Adresy");
		ArrayList<JMenu> optionItems = createMenus("Foreground", "Background", "Font size");
		
		fileItems = createMenuItems("OSAX", false, false, false, "Open", "Save", "Save as...", "Exit");
		editItems = createMenuItems("PSD", true, false, false, "Praca", "Szkoła", "Dom");
		backgroundItems = createMenuItems("", false, true, false, "RED", "BLUE", "GREEN", "YELLOW", "BLACK");
		foregroundItems = createMenuItems("", false, true, false, "RED", "BLUE", "GREEN", "YELLOW", "BLACK");
		fontsizeItems = createMenuItems("", false, false, true, "8", "10", "12", "14", "16", "18", "20");
		
		JMenuBar menu = new JMenuBar();
		
		menu.add(file);
		menu.add(edit);
		menu.add(options);
		edit.add(addresses);
		
		CustomSeparator sep2 = new CustomSeparator();
		
		for(JMenuItem item : fileItems)
		{
			file.add(sep2);
			file.add(item);
		}
			
		for(JMenuItem item : editItems)
			addresses.add(item);
		
		for(JMenu item : optionItems)
			options.add(item);
		
		for(JMenuItem item : foregroundItems)
			optionItems.get(0).add(item);
		
		for(JMenuItem item : backgroundItems)
			optionItems.get(1).add(item);
		
		for(JMenuItem item : fontsizeItems)
			optionItems.get(2).add(item);
		

		setJMenuBar(menu);
		menu.setSize(getPreferredSize());
		menu.setMinimumSize(getSize());
		menu.setMaximumSize(getSize());
		menu.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		workspace = new JTextArea(15, 50);
		workspace.setLineWrap(true);
		add(new JScrollPane(workspace));
	
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{		
		Object source = e.getSource();
		ArrayList<ArrayList<JMenuItem>> list = new ArrayList<ArrayList<JMenuItem>>();
		list.add(fileItems);
		list.add(editItems);
		list.add(backgroundItems);
		list.add(foregroundItems);
		list.add(fontsizeItems);
		
		for(ArrayList<JMenuItem> tList : list)
		{
			for(JMenuItem item : tList)
			{
				
				if(source == item)
				{
					String key = item.getActionCommand();
					
					if(key.equals("open"))
					{
						JFileChooser openDial = new JFileChooser(pathname);
						int result = openDial.showOpenDialog(this);
						
						try{
							if(result == JFileChooser.APPROVE_OPTION)
								readFile(openDial.getSelectedFile());
							if(result == JFileChooser.CANCEL_OPTION)
								pathname = openDial.getCurrentDirectory().toString();
							
						}catch(IOException exc)
						{
							return;
						}
					}
					
					else if(key.equals("save"))
					{
						try
						{
							saveFile(actualFile);
						}
						catch (IOException exc)
						{
							System.out.println("cos");
						}
					}
					
					else if(key.equals("save as..."))
					{
						JFileChooser saveDial = new JFileChooser(new File(pathname));
						int result = saveDial.showSaveDialog(this);
						
						if(result == JFileChooser.APPROVE_OPTION)
						{
							try
							{
								saveFile(saveDial.getSelectedFile());
							}
							catch (IOException exc)
							{
								exc.printStackTrace();
							}
						}
					}
					
					else if(key.equals("exit"))
						dispose();
					
					else if(editItems.stream().anyMatch(x -> x == item))
						workspace.insert(adresses.get(key), workspace.getCaretPosition());
					
					else if(foregroundItems.stream().anyMatch(x -> x == item))
						workspace.setForeground(colors.get(item.getActionCommand()));
					
					else if(backgroundItems.stream().anyMatch(x -> x == item))
						workspace.setBackground(colors.get(item.getActionCommand()));
					
					else if(fontsizeItems.stream().anyMatch(x -> x == item))
						workspace.setFont(this.getFont().deriveFont(Float.parseFloat(key)));
				}
			}
		}
	}
	
	private ArrayList<JMenu> createMenus(String ...items)
	{
		ArrayList<JMenu> list = new ArrayList<JMenu>();
		for(String s : items)
		{
			list.add(new JMenu(s));
		}
		return list;
	}
	
	private ArrayList<JMenuItem> createMenuItems(String accelerators, boolean shift, boolean icon, boolean font, String ... items)
	{
		ArrayList<JMenuItem> list = new ArrayList<JMenuItem>();
		
		int i = 0;
		for(String s : items)
		{
			JMenuItem mi = new JMenuItem(s);
			mi.addActionListener(this);
			mi.setActionCommand(s.toLowerCase());
			
			if(accelerators.length() > 0)
			{
				StringBuffer hotkey = new StringBuffer("control ");
				if(shift)
					hotkey.append("shift ");
				mi.setAccelerator(KeyStroke.getKeyStroke(hotkey.toString() + accelerators.charAt(i)));
				mi.setMnemonic(s.charAt(0));
			}
			if(font)
				mi.setText(mi.getText() + " pts");
			
			if(icon)
				mi.setIcon(new CustomIcon(colors.get(mi.getActionCommand())));
				
			list.add(mi);
			i++;
		}
		return list;
	}
	
	private void readFile(File file) throws IOException
	{
		
		BufferedReader bf = new BufferedReader(new FileReader(file.getAbsolutePath()));
		String line;
		
		workspace.setText("");
		
		while((line = bf.readLine()) != null)
		{
			workspace.append(line + "\n");
		}
		
		setTitle("Text editor - " + file.getAbsolutePath());
		actualFile = file;
		pathname = file.getAbsolutePath().toString();
		bf.close();
	}

	private void saveFile(File file) throws IOException
	{
		if(file == null)
		{
			System.out.println("Choose a destination...");
			return;
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		for(String s : workspace.getText().split("\n"))
		{
			bw.write(s);
			bw.newLine();
			bw.flush();
		}
		setTitle("Text editor - " + file.getAbsolutePath());
		bw.close();
	}
}
