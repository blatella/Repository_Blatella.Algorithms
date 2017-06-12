package blatella.Algorithms.Common.LanguageIdentification;
import java.util.*;
import java.math.*;
import blatella.Common.*;
import blatella.Algorithms.Common.*;
import blatella.Common.Error;

public class LanguageIdentificationUtility 
{//public class LanguageIdentificationUtility

	protected List<String> characters;
	protected List<String> getCharacters() {
		return characters;
	}
	protected void setCharacters(List<String> characters) {
		this.characters = characters;
	}

	protected List<WritableTuple_2<String, String>> charactersChanges;
	protected List<WritableTuple_2<String, String>> getCharactersChanges() {
		return charactersChanges;
	}
	protected void setCharactersChanges(List<WritableTuple_2<String, String>> charactersChanges) {
		this.charactersChanges = charactersChanges;
	}
	
	protected List<WritableTuple_2<Object, Error>> errorsMultithreadedProcess;
	protected List<WritableTuple_2<Object, Error>> getErrorsMultithreadedProcess() {
		return errorsMultithreadedProcess;
	}
	protected void setErrorsMultithreadedProcess(List<WritableTuple_2<Object, Error>> errorsMultithreadedProcess) {
		this.errorsMultithreadedProcess = errorsMultithreadedProcess;
	}
	
	protected final int MAXIMUM_NUMBER_WORDS_ANALYZE = 300000;
	protected final int MINIMUM_NUMBER_WORDS_ANALYZE = 300000;
	protected final int SIZE_PARTITION_STRINGS = 1000;
	protected final int PAUSE_MILISECONDS_PROCESSOR = 100;
	protected final BigDecimal SOFT_PROBABILITY_0 = new BigDecimal(1).divide(new BigDecimal(Integer.MAX_VALUE));
	
	protected List<WritableTuple_4<EnumLanguage, HashMap<String, LanguageIdentificationModelWrapper>, Integer, Integer>> defaultLanguageModels;
	protected List<WritableTuple_4<EnumLanguage, HashMap<String, LanguageIdentificationModelWrapper>, Integer, Integer>> getDefaultLanguageModels() {
		return defaultLanguageModels;
	}
	protected void setDefaultLanguageModels(
			List<WritableTuple_4<EnumLanguage, HashMap<String, LanguageIdentificationModelWrapper>, Integer, Integer>> defaultLanguageModels) {
		this.defaultLanguageModels = defaultLanguageModels;
	}
    
	protected boolean loadCastilianModel;
	public boolean isLoadCastilianModel() {
		return loadCastilianModel;
	}
	public void setLoadCastilianModel(boolean loadCastilianModel) {
		this.loadCastilianModel = loadCastilianModel;
	}
    protected boolean loadCatalanModel;
	public boolean isLoadCatalanModel() {
		return loadCatalanModel;
	}
	public void setLoadCatalanModel(boolean loadCatalanModel) {
		this.loadCatalanModel = loadCatalanModel;
	}
	protected boolean loadEnglishModel;
	public boolean isLoadEnglishModel() {
		return loadEnglishModel;
	}
	public void setLoadEnglishModel(boolean loadEnglishModel) {
		this.loadEnglishModel = loadEnglishModel;
	}
	protected boolean loadDutchModel;
	public boolean isLoadDutchModel() {
		return loadDutchModel;
	}
	public void setLoadDutchModel(boolean loadDutchModel) {
		this.loadDutchModel = loadDutchModel;
	}
	protected boolean loadFrenchModel;
	public boolean isLoadFrenchModel() {
		return loadFrenchModel;
	}
	public void setLoadFrenchModel(boolean loadFrenchModel) {
		this.loadFrenchModel = loadFrenchModel;
	}
	protected boolean loadGermanModel;
	public boolean isLoadGermanModel() {
		return loadGermanModel;
	}
	public void setLoadGermanModel(boolean loadGermanModel) {
		this.loadGermanModel = loadGermanModel;
	}
	protected boolean loadItalianModel;
	public boolean isLoadItalianModel() {
		return loadItalianModel;
	}
	public void setLoadItalianModel(boolean loadItalianModel) {
		this.loadItalianModel = loadItalianModel;
	}
	protected boolean loadPortugueseModel;
	public boolean isLoadPortugueseModel() {
		return loadPortugueseModel;
	}
	public void setLoadPortugueseModel(boolean loadPortugueseModel) {
		this.loadPortugueseModel = loadPortugueseModel;
	}
	protected boolean loadLatinModel;
	public boolean isLoadLatinModel() {
		return loadLatinModel;
	}
	public void setLoadLatinModel(boolean loadLatinModel) {
		this.loadLatinModel = loadLatinModel;
	}

	public LanguageIdentificationUtility()
    {
		characters = Arrays.asList(AlgConstants.STRING_SPACE_CHARACTER
				
				//ISO-8859-15(reduced)
                ,"|"
                ,"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"
                ,"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
                ,"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o"
                ,"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
                ,"Š" , "š"
                ,"Ž", "ž", "Œ", "œ", "Ÿ"
                ,"À", "Á", "Â", "Ã", "Ä", "Å", "Æ", "Ç", "È", "É", "Ê", "Ë", "Ì", "Í", "Î", "Ï"
                ,"Ð", "Ñ", "Ò", "Ó", "Ô", "Õ", "Ö", "Ø", "Ù", "Ú", "Û", "Ü", "Ý", "Þ", "ß"
                ,"à", "á", "â", "ã", "ä", "å", "æ", "ç", "è", "é", "ê", "ë", "ì", "í", "î", "ï"
                ,"ð", "ñ", "ò", "ó", "ô", "õ", "ö", "ø", "ù", "ú", "û", "ü", "ý", "þ", "ÿ"
									);
		
		charactersChanges = Arrays.asList(	new WritableTuple_2<String, String>("'", "|")
											,new WritableTuple_2<String, String>("’", "|")
										);
																				
        errorsMultithreadedProcess = new ArrayList<WritableTuple_2<Object, Error>>();

        loadCastilianModel = true;
        loadCatalanModel = true;
        loadEnglishModel = true;
        loadDutchModel = true;
        loadFrenchModel = true;
        loadGermanModel = true;
        loadItalianModel = true;
        loadPortugueseModel = true;
        loadLatinModel = true;
    }

	/*
	public Return<List<LanguageIdentificationModelWrapper>> Model(int countSubprocesses, int sizeBefore, int sizeAfter, List<String> text)
    {
        Return<List<LanguageIdentificationModelWrapper>> _answer = new Return<List<LanguageIdentificationModelWrapper>>(new ArrayList<LanguageIdentificationModelWrapper>());
        if (countSubprocesses <= 0)
        {
            _answer.setTheresError(true);
            _answer.setError(Utility.GetError(new IllegalArgumentException("countSubprocesses >0")));
        }
        else
            if (sizeBefore <= 0)
            {
                _answer.setTheresError(true);
                _answer.setError(Utility.GetError(new IllegalArgumentException("sizeBefore >0")));
                
            }
            else
                if (sizeAfter <= 0)
                {
                	_answer.setTheresError(true);
                    _answer.setError(Utility.GetError(new IllegalArgumentException("sizeAfter >0")));
                }
                else
                    if (text == null)
                    {
                    	_answer.setTheresError(true);
                        _answer.setError(Utility.GetError(new IllegalNullArgumentException("text")));
                    }
                    else
                    {
                        try
                        {
                            Return<List<String>> _answerExtractSentences = AlgUtility.ExtractSentences(text);
                            
                            if (_answerExtractSentences.theresError)
                            {
                                _answer.theresError = true;
                                _answer.error = _answerExtractSentences.error;
                            }
                            else
                            {//we have sentences
                                List<string> _sentences = new List<string>();
                                List<string> _words = new List<string>();
                                foreach (string _sentence in _answerExtractSentences.data.Where(s => !string.IsNullOrWhiteSpace(s) && s.Where(c => char.IsLetter(c)).Any()))
                                {//sentence to sentence
                                    if (_words.Count > MAXIMUM_NUMBER_WORDS_ANALYZE)
                                        break;
                                    else
                                    {
                                        _sentences.Add(_sentence);
                                        Return<List<string>> _answerGetWords = AlgUtility.GetWords(new List<string>() { _sentence });
                                        if (_answerGetWords.theresError)
                                        {
                                            _answer.theresError = true;
                                            _answer.error = _answerGetWords.error;
                                        }
                                        else
                                            _words.AddRange(_answerGetWords.data);
                                    }

                                    if (_answer.theresError)
                                        break;
                                }//sentence to sentence

                                if (!_answer.theresError)
                                    if (_words.Count < MINIMUM_NUMBER_WORDS_ANALYZE)
                                    {
                                        string _message = string.Format("There's a minimum limit of {0} character to create the model. You just have {1}", Convert.ToString(MINIMUM_NUMBER_WORDS_ANALYZE), Convert.ToString(_words.Count));
                                        _answer.theresError = true;
                                        _answer.error = Utility.GetError(new Exception(_message), this.GetType());
                                    }

                                if (!_answer.theresError)
                                {//we have words
                                    string _entireText = string.Join(GetSeparator(sizeBefore, sizeAfter), _words);

                                    List<string> _initialStrings = new List<string>() { string.Empty };
                                    List<string> _finalString = new List<string>() { string.Empty };

                                    //normalizing
                                    characters = characters.Select(c => c.ToLower()).Distinct().ToList();
                                    _entireText = _entireText.ToLower().Trim();
                                    charactersChanges.ForEach(t => _entireText = _entireText.Replace(t.Item2, string.Empty));
                                    charactersChanges.ForEach(t => _entireText = _entireText.Replace(t.Item1, t.Item2));

                                    for (int i = 0; i < sizeBefore; i++)
                                    {
                                        List<string> _addedStrings = _initialStrings.SelectMany(ci => AddCharactersToTheRight(ci)).ToList();
                                        _initialStrings = _addedStrings;
                                    }

                                    for (int i = 0; i < sizeAfter; i++)
                                    {
                                        List<string> _addedStrings = _finalString.SelectMany(ci => AddCharactersToTheRight(ci)).ToList();
                                        _finalString = _addedStrings;
                                    }
                                    _initialStrings = _initialStrings.OrderBy(c => c).ToList();
                                    _finalString = _finalString.OrderBy(c => c).ToList();

                                    Return<List<WritableTuple<string, int>>> _answerCountExistingCombinations = CountExistingCombinations(countSubprocesses, _entireText, _initialStrings, _finalString);
                                    if (_answerCountExistingCombinations.theresError)
                                    {
                                        _answer.theresError = true;
                                        _answer.error = _answerCountExistingCombinations.error;
                                    }
                                    else
                                    {//let us count the probabilities of all combinations
                                        Return<List<WritableTuple<string, int>>> _answerCountExistingStrings = CountExistingStrings(countSubprocesses, _entireText, _initialStrings);
                                        if (_answerCountExistingStrings.theresError)
                                        {
                                            _answer.theresError = true;
                                            _answer.error = _answerCountExistingStrings.error;
                                        }
                                        else
                                        {
                                            Stopwatch _stopwatchCalculateProbabilitiesChangeState = new Stopwatch();
                                            _stopwatchCalculateProbabilitiesChangeState.Start();

                                            SafeSortedList<string, int> _countCombinedStrings = new SafeSortedList<string, int>()
                                                                        , _countInitialStrings = new SafeSortedList<string, int>();

                                            _answerCountExistingCombinations.data.ForEach(t => _countCombinedStrings.Add(t.Item1, t.Item2));
                                            _answerCountExistingStrings.data.ForEach(t => _countInitialStrings.Add(t.Item1, t.Item2));

                                            Return<List<LanguageIdentificationModelWrapper>> _answerCalculateProbabilitiesChangeState = CalculateProbabilitiesChangeStateNew(countSubprocesses, _initialStrings, _finalString
                                                                                                                                                    , _countCombinedStrings, _countInitialStrings);

                                            _stopwatchCalculateProbabilitiesChangeState.Stop();
                                            if (_answerCalculateProbabilitiesChangeState.theresError)
                                            {
                                                _answer.theresError = true;
                                                _answer.error = _answerCalculateProbabilitiesChangeState.error;
                                            }
                                            else
                                                _answer.data.AddRange(_answerCalculateProbabilitiesChangeState.data);
                                        }
                                    }//let us count the probabilities of all combinations

                                    if (!_answer.theresError)
                                    {//save initial probabilities
                                        Return<List<WritableTuple<string, int>>> _answerCountBeginningOfSentence = CountBeginningOfSentence(countSubprocesses, _sentences, _initialStrings);
                                        if (_answerCountBeginningOfSentence.theresError)
                                        {
                                            _answer.theresError = true;
                                            _answer.error = _answerCountBeginningOfSentence.error;
                                        }
                                        else
                                        {
                                            Return<List<LanguageIdentificationModelWrapper>> _answerCalculateProbabilitiesInitialState
                                                                        = CalculateProbabilitiesInitialState(countSubprocesses, _sentences, _initialStrings, _answerCountBeginningOfSentence.data);
                                            if (_answerCalculateProbabilitiesInitialState.theresError)
                                            {
                                                _answer.theresError = true;
                                                _answer.error = _answerCalculateProbabilitiesInitialState.error;
                                            }
                                            else
                                                _answer.data.AddRange(_answerCalculateProbabilitiesInitialState.data);
                                        }
                                    }//save initial probabilities

                                }//we have words
                            }//we have sentences
                        }
                        catch (Exception _ex)
                        {
                            _answer.theresError = true;
                            _answer.error = Utility.GetError(_ex, this.GetType());
                        }
                    }
                    
        return _answer;
    }
	*/
}//public class LanguageIdentificationUtility