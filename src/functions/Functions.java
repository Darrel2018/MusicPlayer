package functions;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class Functions {
	
	private Audio music;
	
	// Returns a Color object.
	public Color setColor(int r, int g, int b){
		Color color = new Color(r, g, b);
		return color;
	}
	
	// Creates side-Panel-Buttons.
	public JPanel createSidePanelButton(int x, int y, int sidePanelWidth, String iconPathLeft, String text){
		
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		
		panel.setBounds(x, y, sidePanelWidth, 50);
		panel.setBackground(setColor(132, 132, 132));
		panel.setBorder(BorderFactory.createLineBorder(setColor(255, 255, 255)));
		
		panel.add(createIcon(0, 0, iconPathLeft));
		panel.add(createTextLabel(70, 0, setColor(255, 255, 255), new Font("Segoe UI", 0, 12), text, 78));
		panel.add(createIcon(170, 0, "res\\images\\next.png"));
		
		return panel;
	}
	
	// Creates text-Labels.
	public JLabel createTextLabel(int x, int y, Color color, Font font, String text, int textLabelWidth){
		
		JLabel label = new JLabel();
		
		label.setBounds(x, y, textLabelWidth, 50);
		label.setText(text);
		label.setForeground(color);
		label.setFont(font);
		
		return label;
	}
	
	// Creates side-Button-Icons.
	private JLabel createIcon(int x, int y, String path){
		
		JLabel iconLabel = new JLabel();
		
		iconLabel.setIcon(new ImageIcon(path));
		iconLabel.setBounds(x, y, 50, 50);
		
		return iconLabel;
	}
	
	// Creates side-Panel-Header.
	public JPanel createSidePanelHeader(int x, int y, int width, int height){
		
		JPanel panel = new JPanel();
		JSeparator sep = new JSeparator();
		
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(132, 132, 132));
		
		sep.setBounds(0, height-2, width, 10);
		sep.setForeground(setColor(255, 255, 255));
		sep.setBackground(setColor(255, 255, 255));
		
		panel.add(sep);
		panel.add(createTextLabel(17, 0, setColor(255, 255, 255), new Font("Segoe UI", 0, 30), "Music Player", 170));
		
		return panel;
	}
	
	// adds listeners to buttons
	public void addButtonListeners(JPanel panel, String buttonName, JPanel mPanel){
		
		panel.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e){
				System.out.println("Pressed " + buttonName);
				panel.setBackground(setColor(160, 160, 160));
			}
			
			public void mouseReleased(MouseEvent e){
				getButtonFunc(buttonName, mPanel);
				panel.setBackground(setColor(132, 132, 132));
			}
		});
	}
	
	// checks what button was pressed, then executes its following code.
	private void getButtonFunc(String button, JPanel mPanel){
		
		mPanel.removeAll();
		mPanel.repaint();
		
		if(button.equals("Select Music")){
			selectMusic(mPanel);
		}
		else if(button.equals("Add Music")){
			addMusic(mPanel);
		}
		else if(button.equals("Remove Music")){
			removeMusic(mPanel);
		}
		else if(button.equals("Exit")){
			System.err.println("EXITING...");
			System.exit(0);
		}
		else {
			System.err.println("ERROR: Cannot find button func...");
		}
	}
	
	// creates the select music tab.
	private void selectMusic(JPanel mPanel){
		
		ArrayList<JPanel> objlist = new ArrayList<>();
		File file = new File("res\\music");
		
		File[] content = file.listFiles();
		for(File obj : content){
			
			if(obj.isFile()){
				System.out.println("Reading File: " + obj.getName());
				objlist.add(createMusicTab(obj.getName(), mPanel, objlist.size(), true));
			}
			
			if(obj.isDirectory()){
				System.out.println("Reading Directory: " + obj.getName());
			}
		}
		
		for(JPanel obj : objlist){
			mPanel.add(obj);
		}
		
		mPanel.repaint();
		
		// Implements scroll wheel if "OBJLIST" is over 9 objects.
		if(objlist.size() > 9){
			mPanel.addMouseWheelListener(new MouseWheelListener() {
				
				public void mouseWheelMoved(MouseWheelEvent e) {
					
					if(e.getPreciseWheelRotation() < 0){

						if(objlist.get(0).getY()+5 < 0){
						
							for(JPanel obj : objlist){
								obj.setBounds(obj.getX(), obj.getY()+7, obj.getWidth(), obj.getHeight());
							}
						}
					}
					else{
						
						if(objlist.get(objlist.size()-1).getY()+70 > mPanel.getHeight()){
							
							for(JPanel obj : objlist){
								obj.setBounds(obj.getX(), obj.getY()-7, obj.getWidth(), obj.getHeight());
							}
						}
					}
				}
			});
		}	
	}
	
	// creates each music tab when a music file is found.
	private JPanel createMusicTab(String name, JPanel mPanel, int tab, boolean remove){
		
		JPanel panel = new JPanel();
		JSeparator sep = new JSeparator();
		JSeparator dec_Line = new JSeparator();
		
		panel.setBounds(0, 50 * tab, mPanel.getWidth(), 50);
		panel.setBackground(setColor(132, 132, 132));
		
		sep.setBackground(setColor(255, 255, 255));
		sep.setForeground(setColor(255, 255, 255));
		sep.setBounds(0, 49, mPanel.getWidth(), 1);
		
		dec_Line.setBackground(setColor(255, 255, 255));
		dec_Line.setForeground(setColor(255, 255, 255));
		dec_Line.setBounds(200, 27, 300, 1);
		
		panel.add(sep);
		panel.add(createIcon(20, 0, "res\\images\\next.png"));
		
		if(remove == false){
			panel.add(createRemoveButton(450, 0, "res\\images\\delete.png", name, mPanel));
		}
		else {
			panel.add(createPlayButton(450, 0, "res\\images\\play.png", name));
		}
		
		panel.add(createTextLabel(70, 0, setColor(255, 255, 255), new Font("Segoe UI", 0, 12), name, 78));
		panel.add(dec_Line);
		
		return panel;
	}
	
	// creates way to play music.
	private JPanel createPlayButton(int x, int y, String imgPath, String songName){
		
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		panel.setBounds(x, y, 50, 48);
		
		panel.setBackground(setColor(132, 132, 132));
		
		panel.add(createIcon(0, 0, imgPath));
		
		panel.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e){
				panel.setBackground(setColor(160, 160, 160));
			}
			
			public void mouseReleased(MouseEvent e){
				panel.setBackground(setColor(132, 132, 132));
				
				if(music == null){
					music = new Audio("res\\music\\" + songName);
				}
				else {
					music.stopAudio();
					music = new Audio("res\\music\\" + songName);
				}
			}
		});
		
		return panel;
	}
	
	// uses windows explorer to find the music file.
	private void addMusic(JPanel mPanel){
		File file = new File("res\\music");
		String path = file.getAbsolutePath();
		
		try {
			System.out.println("Looking for path: " + path);
			Runtime.getRuntime().exec("explorer.exe " + path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mPanel.add(createTextLabel(17, 0, setColor(255, 255, 255), new Font("Segoe UI", 0, 30), "Add music to music file.", 400));
	}
	
	// creates the removeMusic tab
	private void removeMusic(JPanel mPanel){
		
		ArrayList<JPanel> objlist = new ArrayList<>();
		File file = new File("res\\music");
		
		File[] content = file.listFiles();
		for(File obj : content){
			
			if(obj.isFile()){
				System.out.println("Reading File: " + obj.getName());
				objlist.add(createMusicTab(obj.getName(), mPanel, objlist.size(), false));
			}
			
			if(obj.isDirectory()){
				System.out.println("Reading Directory: " + obj.getName());
			}
		}
		
		for(JPanel obj : objlist){
			mPanel.add(obj);
		}
		
		mPanel.repaint();
		
		// Implements scroll wheel if "OBJLIST" is over 9 objects.
		if(objlist.size() > 9){
			mPanel.addMouseWheelListener(new MouseWheelListener() {
				
				public void mouseWheelMoved(MouseWheelEvent e) {
							
					if(e.getPreciseWheelRotation() < 0){
						
						if(objlist.get(0).getY()+5 < 0){
							
							for(JPanel obj : objlist){
								obj.setBounds(obj.getX(), obj.getY()+7, obj.getWidth(), obj.getHeight());
							}
						}
					}
					else{
						
						if(objlist.get(objlist.size()-1).getY()+70 > mPanel.getHeight()){
							
							for(JPanel obj : objlist){
								obj.setBounds(obj.getX(), obj.getY()-7, obj.getWidth(), obj.getHeight());
							}
						}
					}
				}
			});
		}
	}
	
	// creates a remove button for the music tabs
	private JPanel createRemoveButton(int x, int y, String imgPath, String songName, JPanel mPanel){
		
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		panel.setBounds(x, y, 50, 48);
		
		panel.setBackground(setColor(132, 132, 132));
		
		panel.add(createIcon(0, 0, imgPath));
		
		panel.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e){
				panel.setBackground(setColor(160, 160, 160));
			}
			
			public void mouseReleased(MouseEvent e){
				panel.setBackground(setColor(132, 132, 132));
				
				try {
					System.out.println("removing: " + songName);
					deleteFile(songName);
					
					mPanel.removeAll();
					removeMusic(mPanel);
					
					mPanel.repaint();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		return panel;
	}
	
	// deletes music files
	private void deleteFile(String pathName) throws IOException{
		File file = new File("res\\music\\" + pathName);
		file.delete();
	}
}
