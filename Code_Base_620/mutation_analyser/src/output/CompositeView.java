package output;

import java.util.ArrayList;
import java.util.List;

import data.MutationAnalyserOutput;

public class CompositeView implements OutputView {

	private List<OutputView> constituentViews;

	public CompositeView() {
		constituentViews = new ArrayList<>();
	}

	@Override
	public void displayComponent(MutationAnalyserOutput output) {
		// Composite Pattern : Can add individual constituent views to display
		for (OutputView constituent : this.constituentViews) {
			constituent.displayComponent(output);
		}
	}

	public List<OutputView> getConstituentViews() {
		return constituentViews;
	}

	public void setConstituentViews(List<OutputView> constituentViews) {
		if (constituentViews == null) {
			this.constituentViews.clear();
		} else {
			this.constituentViews = constituentViews;
		}
	}

	public void addConstituentView(OutputView constituent) {
		this.constituentViews.add(constituent);
	}

}
