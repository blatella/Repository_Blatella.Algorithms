package blatella.Algorithms.Common;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;
import blatella.Common.*;

public class AlgUtility
{
	private static final String PATTERN_VALUE_DATE = "((?'initial_character_00'(^))(?'part_year_00'(\\d{4}))(?'final_character_00'($)))|((?'initial_character_02'(^))(?'part_year_02'(\\d{4}))(?'separator_part_02'(\\-))(?'part_unknown_month_02'((Q[1-4])|(WI)|(SP)|(SU)|(AU)))(?'final_character_02'($)))|((?'initial_character_04'(^))(?'part_unknown_year_04'(XXXX))(?'separator_part_04'(\\-))(?'part_month_04'((0[1-9])|(1[0-2])))(?'final_character_04'($)))|((?'initial_character_06'(^))(?'part_unknown_year_06'(XXXX))(?'separator_part_06'(\\-))(?'part_unknown_month_06'(XX))(?'separator_part_07'(\\-))(?'part_day_06'((0[1-9])|([1-2][0-9])|(3[0-1])))(?'final_character_06'($)))|((?'initial_character_08'(^))(?'part_year_08'(\\d{4}))(?'separator_part_08'(\\-))(?'part_month_08'((0[1-9])|(1[0-2])))(?'final_character_08'($)))|((?'initial_character_10'(^))(?'part_unknown_year_10'(XXXX))(?'separator_part_10'(\\-))(?'part_month_10'((0[1-9])|(1[0-2])))(?'separator_part_11'(\\-))(?'part_day_10'((0[1-9])|([1-2][0-9])|(3[0-1])))(?'final_character_10'($)))|((?'initial_character_12'(^))(?'part_unknown_year_12'(XXXX))(?'final_character_12'($)))|((?'initial_character_14'(^))(?'part_unknown_year_14'(XXXX))(?'separator_part_14'(\\-))(?'part_unknown_month_14'((XX)|(Q[1-4])|(WI)|(SP)|(SU)|(AU)))(?'final_character_14'($)))|((?'initial_character_16'(^))(?'part_unknown_year_16'(XXXX))(?'separator_part_16'(\\-))(?'part_unknown_month_16'(XX))(?'separator_part_17'(\\-))(?'part_unknown_day_16'(XX))(?'final_character_16'($)))|((?'initial_character_18'(^))(?'part_year_18'(\\d{4}))(?'separator_part_18'(\\-))(?'part_month_18'((0[1-9])|(1[0-2])))(?'separator_part_19'(\\-))(?'part_day_18'((0[1-9])|([1-2][0-9])|(3[0-1])))(?'final_character_18'($)))|((?'initial_character_20'(^))(?'part_unknown_year_20'(XXXX))(?'separator_part_20'(\\-))(?'part_unknown_week_20'(WXX))(?'final_character_20'($)))";
	
	public static Return<List<String>> ExtractSentences(List<String> Strings)
	{
		Return<List<String>> _answer = new Return<List<String>>(new ArrayList<String>());
		if (Strings == null)
		{
			_answer.theresError=true;
            _answer.error=Utility.GetError(new IllegalNullArgumentException("Strings"));
		}
		else
		{
			try
			{
				HashMap<String, String> _replacements = new HashMap<String, String>();
				_replacements.put(".", AlgConstants.STRING_DOT);
				_replacements.put("¿", Utility.EMPTY_STRING);
				_replacements.put("?", AlgConstants.STRING_DOT);
				_replacements.put("¡", Utility.EMPTY_STRING);
				_replacements.put("!", AlgConstants.STRING_DOT);

				String _words = Utility.Join(AlgConstants.STRING_DOT, Strings);
				for (Map.Entry<String, String> _entry : _replacements.entrySet()) 
				    _words = _words.replaceAll(_entry.getKey(), _entry.getValue());
				
				_words = _words.replace((char)13, AlgConstants.CHARACTER_DOT);

				_answer.data=Utility.Split(_words,"[.]").stream().map(s->s.trim()).collect(Collectors.toList());
			}
			catch (Exception _ex)
			{
				_answer.theresError=true;
				_answer.error=Utility.GetError(_ex);
			}
		}
		return _answer;
	}
	
	public static Return<List<String>> GetWords(List<String> Strings)
	{
		Return<List<String>> _answer = new Return<List<String>>(new ArrayList<String>());
		if (Strings == null)
		{
			_answer.theresError=true;
            _answer.error=Utility.GetError(new IllegalNullArgumentException("Strings"));
		}
		else
		{
			try
			{
				Return<List<String>> _answerExtractSentences = ExtractSentences(Strings);
				if (_answerExtractSentences.theresError)
				{
					_answer.theresError=true;
					_answer.error=_answerExtractSentences.error;
				}
				else
				{//we have the sentences
					String _words = Utility.Join(AlgConstants.STRING_SPACE_CHARACTER, _answerExtractSentences.data);
					HashMap<String, String> _replacements = new HashMap<String, String>();
					_replacements.put("'", AlgConstants.STRING_SPACE_CHARACTER);
					_replacements.put(",", AlgConstants.STRING_SPACE_CHARACTER);
					_replacements.put(":", AlgConstants.STRING_SPACE_CHARACTER);
					_replacements.put(";", AlgConstants.STRING_SPACE_CHARACTER);
					_replacements.put("(", AlgConstants.STRING_SPACE_CHARACTER);
					_replacements.put(")", AlgConstants.STRING_SPACE_CHARACTER);
					_replacements.put("\\", AlgConstants.STRING_SPACE_CHARACTER);
					_replacements.put("/", AlgConstants.STRING_SPACE_CHARACTER);
					_replacements.put("\"", AlgConstants.STRING_SPACE_CHARACTER);
					_replacements.put(System.lineSeparator(), Utility.EMPTY_STRING);
					
					for (Map.Entry<String, String> _entry : _replacements.entrySet()) 
					    _words = _words.replaceAll(_entry.getKey(), _entry.getValue());
					
					_words = _words.replace((char)13, AlgConstants.SPACE_CHARACTER);

					_answer.data=Utility.Split(_words,AlgConstants.STRING_SPACE_CHARACTER).stream().map(s->s.trim()).collect(Collectors.toList());
				}//we have the sentences
			}
			catch (Exception _ex)
			{
				_answer.theresError=true;
				_answer.error=Utility.GetError(_ex);
			}
		}
		return _answer;
	}

	public static Return<EnumLanguage> ParseLanguage(String language)
	{
		Return<EnumLanguage> _answer = new Return<EnumLanguage>(EnumLanguage.undetermined);
		try
		{
			if (!Utility.IsNullOrWhiteSpace(language))
			{
				language = language.toLowerCase().trim();
				if (language == AlgConstants.PREFIX_LANGUAGE_CASTILIAN || language.startsWith(String.format("%1$s-", AlgConstants.PREFIX_LANGUAGE_CASTILIAN, "-")))
					_answer.data=EnumLanguage.castilian;
				else
					if (language == AlgConstants.PREFIX_LANGUAGE_CATALAN || language.startsWith(String.format("%1$s-", AlgConstants.PREFIX_LANGUAGE_CATALAN, "-")))
						_answer.data=EnumLanguage.catalan;
					else
						if (language == AlgConstants.PREFIX_LANGUAGE_ENGLISH || language.startsWith(String.format("%1$s-", AlgConstants.PREFIX_LANGUAGE_ENGLISH, "-")))
							_answer.data=EnumLanguage.english;
						else
							if (language == AlgConstants.PREFIX_LANGUAGE_FRENCH || language.startsWith(String.format("%1$s-", AlgConstants.PREFIX_LANGUAGE_FRENCH, "-")))
								_answer.data=EnumLanguage.french;
							else
								if (language == AlgConstants.PREFIX_LANGUAGE_ITALIAN || language.startsWith(String.format("%1$s-", AlgConstants.PREFIX_LANGUAGE_ITALIAN, "-")))
									_answer.data=EnumLanguage.italian;
								else
									if (language == AlgConstants.PREFIX_LANGUAGE_GERMAN || language.startsWith(String.format("%1$s-", AlgConstants.PREFIX_LANGUAGE_GERMAN, "-")))
										_answer.data=EnumLanguage.german;
									else
										if (language == AlgConstants.PREFIX_LANGUAGE_DUTCH || language.startsWith(String.format("%1$s-", AlgConstants.PREFIX_LANGUAGE_DUTCH, "-")))
											_answer.data=EnumLanguage.dutch;
										else
											if (language == AlgConstants.PREFIX_LANGUAGE_PORTUGUESE || language.startsWith(String.format("%1$s-", AlgConstants.PREFIX_LANGUAGE_PORTUGUESE, "-")))
												_answer.data=EnumLanguage.portuguese;
											else
												if (language == AlgConstants.PREFIX_LANGUAGE_LATIN || language.startsWith(String.format("%1$s-", AlgConstants.PREFIX_LANGUAGE_LATIN, "-")))
													_answer.data=EnumLanguage.latin;
			}
		}
		catch (Exception _ex)
		{
			_answer.theresError=true;
			_answer.error=Utility.GetError(_ex);
		}
		return _answer;
	}

	public static Return<Integer> GetYearFromISO8601Text(String text)
	{
		Return<Integer> _answer = new Return<Integer>();
		if (Utility.IsNullOrWhiteSpace(text))
		{
			_answer.theresError=true;
            _answer.error=Utility.GetError(new IllegalNullArgumentException("text"));
		}
		else
		{
			try
			{
				if(!Pattern.matches(PATTERN_VALUE_DATE, text))
				{
					String _message = "no match";
					_answer.theresError=true;
		            _answer.error=Utility.GetError(new Exception(_message));
				}
				else
				{//there's match
					Pattern _pattern = Pattern.compile(PATTERN_VALUE_DATE);
					Matcher _matcher= _pattern.matcher(text);
					while (_matcher.find())
					{//match to match
	                    System.out.print("Start index: " + _matcher.start());
	                    System.out.print(" End index: " + _matcher.end() + " ");
	                    System.out.println(_matcher.group());
	                    
	                    /*
	                    List<System.Text.RegularExpressions.Group> _groups = new List<System.Text.RegularExpressions.Group>();
						foreach (string _name in groupNamePartYear)
						{
							System.Text.RegularExpressions.Group _group = _match.Groups[_name];
							if (_group != null && !string.IsNullOrEmpty(_group.Value))
								_groups.Add(_group);
						}
						_groups = _groups.OrderBy(g => g.Index).ToList();

						string _part = string.Empty;

						if (_groups.Any())
						{
							foreach (System.Text.RegularExpressions.Group _grupo in _groups)
							{
								string _foundText = _grupo.Value;
								_part = string.Format("{0}{1}", _part, _foundText);
							}
						}

						if (!string.IsNullOrEmpty(_part))
							_answer.data = int.Parse(_part);
						*/
	                    
	                    
					}//match to match
				}//there's match
			}
			catch (Exception _ex)
			{
				_answer.theresError=true;
				_answer.error=Utility.GetError(_ex);
			}
		}
		return _answer;
	}


}
