package blatella.Algorithms.Common.LanguageIdentification;
import java.util.*;
import blatella.Common.*;

class ProbabilityInitialStateWrapper 
{
	public Integer countSentences;
	public Integer getCountSentences() {
		return countSentences;
	}
	public void setCountSentences(Integer countSentences) {
		this.countSentences = countSentences;
	}

    List<WritableTuple_2<String, Integer>> countBeginningOfSentence;
	public List<WritableTuple_2<String, Integer>> getCountBeginningOfSentence() {
		return countBeginningOfSentence;
	}
	public void setCountBeginningOfSentence(List<WritableTuple_2<String, Integer>> countBeginningOfSentence) {
		this.countBeginningOfSentence = countBeginningOfSentence;
	}

    List<LanguageIdentificationModelWrapper> fragmentsOfModel;
    public List<LanguageIdentificationModelWrapper> getFragmentsOfModel() {
		return fragmentsOfModel;
	}
	public void setFragmentsOfModel(List<LanguageIdentificationModelWrapper> fragmentsOfModel) {
		this.fragmentsOfModel = fragmentsOfModel;
	}

	public ProbabilityInitialStateWrapper()
    {
        countSentences = 0;
        countBeginningOfSentence = new ArrayList<WritableTuple_2<String, Integer>>();
        fragmentsOfModel = new ArrayList<LanguageIdentificationModelWrapper>();
    }
    
}
