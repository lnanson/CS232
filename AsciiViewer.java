// you do need the JPanel... 
//try the set the font method before you use resize, both times
//set panel layout to gridbag
//add textarea to panel using panel.add(textArea, new GridbagConstraints())
// think it should work

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AsciiViewer extends JPanel {
	private AsciiImage image;
	private JTextArea textArea;
	private int zoomLevel;
	private Font f;//created a new private variable Font f
	

	
	public AsciiViewer(AsciiImage image) { //changed the order up some and worked out a few kinks
		this.image = image;
		zoomLevel=1;
		JPanel panel = new JPanel();
		f = ImageUtilities.createFontForZoomLevel(zoomLevel);
		textArea=new JTextArea();
		textArea.setText(image.toString());
		textArea.setFont(f);
		textArea.setEditable(false); //you forgot this... though if we want to put in a secret message this might be a way to do it
		ImageUtilities.resize(textArea, image, zoomLevel); 
		panel.setLayout(new GridBagLayout());
		panel.add(textArea, new GridBagConstraints());
		JScrollPane scroll=new JScrollPane(panel);
		scroll.setPreferredSize(new Dimension(500,500));
		setLayout(new BorderLayout());
		add(scroll, "Center"); //I think this is right
		
	}
	
	public void setImage (AsciiImage image) {
		this.image = image;
		f = ImageUtilities.createFontForZoomLevel(zoomLevel);
		textArea.setFont(f);	
		ImageUtilities.resize(textArea,image,zoomLevel);
		textArea.setText(image.toString());
	}
	public void setZoomLevel(int zoomLevel) {
		this.zoomLevel=zoomLevel;
		if(zoomLevel>=1&&zoomLevel<=10) {
			this.zoomLevel=zoomLevel;
			f = ImageUtilities.createFontForZoomLevel(zoomLevel);
			textArea.setFont(f);	//set the font to text area to f
			ImageUtilities.resize(textArea,image,zoomLevel);
		}
	}
}