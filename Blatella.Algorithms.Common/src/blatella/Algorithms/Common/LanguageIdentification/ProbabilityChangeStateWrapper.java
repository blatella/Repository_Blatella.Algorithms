package blatella.Algorithms.Common.LanguageIdentification;
import java.util.*;

class ProbabilityChangeStateWrapper
{
	HashMap<String, Integer> countInitialStrings;
	public HashMap<String, Integer> getCountInitialStrings() {
		return countInitialStrings;
	}
	public void setCountInitialStrings(HashMap<String, Integer> countInitialStrings) {
		this.countInitialStrings = countInitialStrings;
	}

	HashMap<String, Integer> countCombinedStrings;
	public HashMap<String, Integer> getCountCombinedStrings() {
		return countCombinedStrings;
	}

	public void setCountCombinedStrings(HashMap<String, Integer> countCombinedStrings) {
		this.countCombinedStrings = countCombinedStrings;
	}

	
	List<LanguageIdentificationModelWrapper> fragmentsOfModel;
	public List<LanguageIdentificationModelWrapper> getFragmentsOfModel() {
		return fragmentsOfModel;
	}
	public void setFragmentsOfModel(List<LanguageIdentificationModelWrapper> fragmentsOfModel) {
		this.fragmentsOfModel = fragmentsOfModel;
	}
	
	public ProbabilityChangeStateWrapper()
    {
        countInitialStrings = new HashMap<String, Integer>();
        countCombinedStrings = new HashMap<String, Integer>();

        fragmentsOfModel = new ArrayList<LanguageIdentificationModelWrapper>();
    }
	
}