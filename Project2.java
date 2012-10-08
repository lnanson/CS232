import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.event.*;

public class Project2 extends JFrame implements ChangeListener, ActionListener {
	private AsciiViewer myAsciiViewer;
	private ImageViewer myImageViewer;
	private JButton[] button;
	private JTextField newInput;
	private JTextField imageString;

	public Project2(Image image) {
		//zoom
		int zoom = 1;
		JSlider zoomControl = makeZoomControl();
		
		//image holder panel
		myAsciiViewer = new AsciiViewer(new AsciiImage(image));
		myImageViewer = new ImageViewer(new Image(image));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(myImageViewer);
		panel.add(myAsciiViewer);
		
		// text field to get new file name
		newInput = new JTextField(20);
		newInput.setText("Enter a JPEG file name");
		newInput.addActionListener(this);

		// button panel setup
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5,2));
		String[] labels = {"Invert", "Shrink", "Horizontal Axis Flip", "Vertical Axis Flip", "Increase Contrast", "Decrease Contrast", "Brighter", "Darker",  "Save Ascii"};
		String[] help = {"get the negative", "flips about a horizontal axis", "flips about a vertical axis",
						"darkest third gets darker, lightest third gets lighter", "darkest third gets lighter, lightest third gets darker", 
						"all pixels get lighter by 5", "all pixels get darker by 5", "shrinks image in half, cannot be undone","saves the ascii to the file ascii.jpg in the local directory"};
		button = new JButton[9];
		for (int i=0; i< button.length; i++) {
			button[i] = new JButton(labels[i]); 
			button[i].addActionListener(this);
			button[i].setToolTipText(help[i]);
			buttonPanel.add(button[i]);
		}

		imageString = new JTextField(50);
		imageString.setText("Enter text here to create an image from the text.");
		imageString.addActionListener(this);
		
		// main window setup
		setLayout(new BorderLayout());
		add(zoomControl, BorderLayout.EAST);
		add(panel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.WEST);	
		add(newInput, BorderLayout.NORTH); 
		add(imageString, BorderLayout.SOUTH);
	}
	
	public JSlider makeZoomControl() {
		int ZOOM_MIN = 1;
		int ZOOM_MAX = 10;
		int ZOOM_INIT = 1;  
		JSlider zoomControl = new JSlider(JSlider.VERTICAL, ZOOM_MIN, ZOOM_MAX, ZOOM_INIT);
		zoomControl.addChangeListener(this);
		zoomControl.setMajorTickSpacing(1);
		zoomControl.setPaintTicks(true);
		zoomControl.setPaintLabels(true);
		return zoomControl;
	}
	
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		int x = (int)source.getValue();
		myImageViewer.setZoomLevel(x);
		myAsciiViewer.setZoomLevel(x);
	}
	
	public void actionPerformed(ActionEvent event) {
		Image image = myImageViewer.getImage();
		if (event.getSource().equals(imageString)) {
			String str = event.getActionCommand();
			//String str = imageString.getText();
			System.out.println(str);
			image = new Image(str);
			imageString.setText("done");
		}
		if (event.getSource().equals(newInput)) {
			try {
				image = ImageUtilities.loadJPEG(newInput.getText());
			}
			catch (IOException e) {
				//System.out.println("didnt work");
			}
		}
		
		if (event.getSource().equals(button[0])){
			image.invert();	}
		if (event.getSource().equals(button[1])) {
			image.shrink(); }
		if (event.getSource().equals(button[2])) {
			image.mirror(Image.Axis.HORIZONTAL); }
		if (event.getSource().equals(button[3])) {
			image.mirror(Image.Axis.VERTICAL); }
		if (event.getSource().equals(button[4])) {
			image.incContrast(); }
		if (event.getSource().equals(button[5])) {
			image.decContrast(); }
		if (event.getSource().equals(button[6])) {
			image.brighten(); }
		if (event.getSource().equals(button[7])) {
			image.darken(); }
			
		AsciiImage asciiImage = new AsciiImage(image);
		
		if (event.getSource().equals(button[8])) {
			String str = asciiImage.toString(); 
			try {
				PrintWriter outfile = new PrintWriter("ascii.txt");
				outfile.println(str);
				outfile.close();
			}
			catch (FileNotFoundException e) {
			}
		}
			
		myImageViewer.setImage(image);	
		myAsciiViewer.setImage(asciiImage);
	}
	
	public static void main(String[] args) {
		Image image = new Image(200, 200);
		if (args.length!=0){
			String file = args[0];
			try {
				image = ImageUtilities.loadJPEG(file);
			}
			catch (IOException exception) {
				System.out.println("You must pass a JPEG filename as a command-line parameter or leave the parameter blank.");
				System.out.println("USAGE: java Project2 <filename> or java.Project2");
			}
		}

		Project2 window = new Project2a(image);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}

