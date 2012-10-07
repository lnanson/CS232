//couldnt get this to work without putting the code into the Project2 class. So this isnt being used anymore.

import javax.swing.*;
import javax.swing.event.*;
public class ZoomControl extends JSlider implements ChangeListener{

	static final int ZOOM_MIN = 1;
	static final int ZOOM_MAX = 10;
	static final int ZOOM_INIT = 1;    //initial frames per second
	
	public ZoomControl() {
		JSlider zoomControl = new JSlider(JSlider.VERTICAL, ZOOM_MIN, ZOOM_MAX, ZOOM_INIT);
		zoomControl.addChangeListener(this);
		zoomControl.setMajorTickSpacing(1);
		zoomControl.setPaintTicks(true);
		zoomControl.setPaintLabels(true);
	}

	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
			int x = (int)source.getValue();
			imagePanel.setZoomLevel(x);
			asciiPanel.setZoomLevel(x);
		}
	}
}