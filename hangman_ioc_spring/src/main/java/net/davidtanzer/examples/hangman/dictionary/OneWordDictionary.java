package net.davidtanzer.examples.hangman.dictionary;

public class OneWordDictionary implements Dictionary {
	@Override
	public int size() {
		return 1;
	}

	@Override
	public String wordAt(final int index) {
		if(index != 0) {
			throw new IndexOutOfBoundsException("Index \""+index+"\" is invialid.");
		}
		return "tomato";
	}
}
