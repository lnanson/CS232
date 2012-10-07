import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.io.*;
<<<<<<< HEAD

public class Project2 extends JFrame implements ChangeListener{
	private AsciiViewer myAsciiViewer;
	private ImageViewer myImageViewer;
	//private ButtonPanel myButtonPanel;

	public Project2(Image image) {
		int zoom = 1;
		myAsciiViewer = new AsciiViewer(new AsciiImage(image));
		myImageViewer = new ImageViewer(new Image(image));
		JSlider zoomControl = new JSlider(JSlider.VERTICAL, 1, 10, 1);
		zoomControl.addChangeListener(this);
		zoomControl.setMajorTickSpacing(1);
		zoomControl.setPaintTicks(true);
		zoomControl.setPaintLabels(true);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(myImageViewer);
		panel.add(myAsciiViewer);
		setLayout(new BorderLayout());
		add(panel, "Center");
		add(zoomControl, "East");
		//add(myButtonPanel, "South");
		
	}
	
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
			int x = (int)source.getValue();
			myImageViewer.setZoomLevel(x);
			myAsciiViewer.setZoomLevel(x);
		}
	}
	
	
	public static void main(String[] args) {
		String file = args[0];
		try {
			Image image = ImageUtilities.loadJPEG(file);
			Project2 window = new Project2(image);
			window.setDefaultCloseOperation(3);
			window.pack();
			window.setLocationRelativeTo(null);
			window.setVisible(true);
		}
		catch (IOException exception) {
			System.out.println("You must pass a JPEG filename as a command-line parameter.");
			System.out.println("USAGE: java Project2 <filename>");
		}
	}
}

		//JScrollPane scrollImage = new JScrollPane(ImageViewer); //needs to be in Class
		//JScrollPane scrollAscii = new JScrollPane(AsciiViewer); // needs to be in Class
		//add(scrollImage);
		//add(scrollAscii);
		//frame.add(ZoomControl);
=======
import java.awt.event.*;

public class Project2 extends JFrame implements ChangeListener, ActionListener {
	private AsciiViewer myAsciiViewer;
	private ImageViewer myImageViewer;
	private JButton[] button;
	private JTextField newInput;
	//private Image copy;
	//private ButtonPanel myButtonPanel;

	public Project2(Image image) {
		//zoom
		int zoom = 1;
		JSlider zoomControl = new JSlider(JSlider.VERTICAL, 1, 10, 1);
		zoomControl.addChangeListener(this);
		zoomControl.setMajorTickSpacing(1);
		zoomControl.setPaintTicks(true);
		zoomControl.setPaintLabels(true);
		
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
		buttonPanel.setLayout(new GridLayout(1,8));
		String[] labels = {"Invert", "Horizontal Flip", "Vertical Flip", "Increase Contrast", "Decrease Contrast", "Brighter", "Darker", "Save Ascii"};
		String[] help = {"get the negative", "duh... flips horizontally", "in case you hadn't guessed, flips the image vertically",
						"darkest third gets darker, lightest third gets lighter", "darkest third gets lighter, lightest third gets darker", 
						"all pixels get lighter by 5", "all pixels get darker by 5", "saves the ascii to the file ascii.jpg in the local directory"};
		button = new JButton[8];
		for (int i=0; i< button.length; i++) {
			button[i] = new JButton(labels[i]); 
			button[i].addActionListener(this);
			button[i].setToolTipText(help[i]);
			buttonPanel.add(button[i]);
		}
		// main window setup
		setLayout(new BorderLayout());
		add(zoomControl, BorderLayout.EAST);
		add(panel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);	
		add(newInput, BorderLayout.NORTH); 
		//copy = copyImage(image);//why doesnt this work???
	}
	
	// public Image copyImage(Image image) { //doesnt work, cant find image.image
		// copy = Image.Image(image);
	// }
	
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		int x = (int)source.getValue();
		myImageViewer.setZoomLevel(x);
		myAsciiViewer.setZoomLevel(x);
	}
	
	public void actionPerformed(ActionEvent event) {
		Image image = myImageViewer.getImage();
		if (event.getSource().equals(newInput)) {
			try {
				image = ImageUtilities.loadJPEG(newInput.getText());
			}
			catch (IOException e) {
				//System.out.println("didnt work");
			}
		}
		if (event.getSource().equals(button[0])){
				image.invert();			
			}
		if (event.getSource().equals(button[1])) {
				image.mirror(Image.Axis.HORIZONTAL);
			}
		if (event.getSource().equals(button[2])) {
				image.mirror(Image.Axis.VERTICAL);
			}
		if (event.getSource().equals(button[3])) {
				image.incContrast();
			}
		if (event.getSource().equals(button[4])) {
				image.decContrast();
			}
		if (event.getSource().equals(button[5])) {
				image.brighten();
			}
		if (event.getSource().equals(button[6])) {
				image.darken();
			}
			
		AsciiImage asciiImage = new AsciiImage(image);
		
		if (event.getSource().equals(button[7])) {
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
				System.out.println("You must pass a JPEG filename as a command-line parameter.");
				System.out.println("USAGE: java Project2 <filename>");
			}
		}

		Project2 window = new Project2(image);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}

>>>>>>> works great
