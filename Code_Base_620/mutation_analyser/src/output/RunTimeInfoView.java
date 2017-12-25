package output;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.MutationAnalyserOutput;

public class RunTimeInfoView implements OutputView {

	private JFrame mainFrame;

	public RunTimeInfoView(JFrame mainFrame) {
		this.setMainFrame(mainFrame);
	}

	@Override
	public void displayComponent(MutationAnalyserOutput output) {
		// run time of the whole code
		JPanel labels = new JPanel(new GridLayout(10, 10));
		JLabel runTime = new JLabel("Run Time of code (in seconds) : " + (output.getProgramRunTime() / 1000.0) + "\n");
		labels.add(runTime);
		this.mainFrame.add(labels, BorderLayout.SOUTH);
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	private void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

}
