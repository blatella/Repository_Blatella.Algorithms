import java.util.*;
import java.io.*;
import java.util.stream.*;
import blatella.Common.*;
import blatella.Algorithms.*;
import blatella.Algorithms.Common.AlgUtility;

public class Test_001 {

	public static void main(String[] args) 
	{
		Return<Object> _answer=new Return<Object>();
		
		_answer= TestFromISO8601Text();
		
		if(_answer.theresError)
			System.out.println(_answer.error.toString());
		else
			System.out.println("finished test");
	}
	
	private static Return<Object> TestFromISO8601Text()
	{
		Return<Object>_answer=new Return<Object>();
		try
		{
			String _text="2018-12-09";
			Return<Integer>_answerGetYearFromISO8601Text= AlgUtility.GetYearFromISO8601Text(_text);
			if(_answerGetYearFromISO8601Text.theresError)
			{
				_answer.theresError=true;
				_answer.error=_answerGetYearFromISO8601Text.error;
			}
		}
		catch(Exception _ex)
		{
			_answer.theresError=true;
			_answer.error=Utility.GetError(_ex);
		}
		return _answer;
	}
	
}
