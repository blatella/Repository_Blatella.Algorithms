package blatella.Algorithms.Common.LanguageIdentification;
import java.math.*;
import blatella.Common.*;

class LanguageIdentificationModelWrapper 
{
	protected String initialString;
	public String getInitialString() {
		return initialString;
	}
	public void setInitialString(String initialString) {
		this.initialString = initialString;
	}

	protected String finalString;
	public String getFinalString() {
		return finalString;
	}
	public void setFinalString(String finalString) {
		this.finalString = finalString;
	}
	
	public String getCombinedString() {
		String _answer = String.format("%1$s%2$s", Utility.IsNullOrEmpty(initialString) ? Utility.EMPTY_STRING : initialString, Utility.IsNullOrEmpty(finalString) ? Utility.EMPTY_STRING : finalString);
        return _answer;
	}
	
	protected BigDecimal probability;
	public BigDecimal getProbability() {
		return probability;
	}
	public void setProbability(BigDecimal probability) {
		this.probability = probability;
	}
	
	public LanguageIdentificationModelWrapper()
    {
    }
	
	public LanguageIdentificationModelWrapper(String initialString, String finalString, BigDecimal probability)
    {
        if (Utility.IsNullOrEmpty(initialString))
            throw new IllegalArgumentException("initialString");
        else
            if (finalString == null)
                throw new IllegalArgumentException("finalString");
            else
            	if (Utility.LessThan(probability, 0)||Utility.GreaterThan(probability, 1))
                    throw new IllegalArgumentException("probability (0,1]");
            	else
                {
                    this.initialString = initialString;
                    this.finalString = finalString;
                    this.probability = probability;
                }
    }
	
	public boolean Equals(Object obj)
    {
		boolean _answer = false;
        if (obj instanceof LanguageIdentificationModelWrapper)
            _answer = ((LanguageIdentificationModelWrapper)obj).getCombinedString() == this.getCombinedString();

        return _answer;
    }
}
