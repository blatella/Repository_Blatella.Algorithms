package blatella.Algorithms.Common.LanguageIdentification;
import java.util.*;
import blatella.Common.*;
//commit_004
class CountWrapper 
{
	protected String textWrapper;
	public String getTextWrapper() {
		return textWrapper;
	}
	public void setTextWrapper(String textWrapper) {
		this.textWrapper = textWrapper;
	}
	
	protected List<String> sentences;
	public List<String> getSentences() {
		return sentences;
	}
	public void setSentences(List<String> sentences) {
		this.sentences = sentences;
	}
	
	protected List<WritableTuple_2<String, Integer>> textToFind;
	public List<WritableTuple_2<String, Integer>> getTextToFind() {
		return textToFind;
	}
	public void setTextToFind(List<WritableTuple_2<String, Integer>> textToFind) {
		this.textToFind = textToFind;
	}
	
	CountWrapper()
    {
		setTextWrapper(Utility.EMPTY_STRING);
		setTextToFind(new ArrayList<WritableTuple_2<String, Integer>>());
		setSentences(new ArrayList<String>());
    }
}