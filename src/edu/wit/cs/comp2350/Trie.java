package edu.wit.cs.comp2350;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Trie extends Speller {

	private final int AlPHA = 26; //length of alphabet
	private Trie[] trieNode; // array for the character dictionary
	private boolean isWord; //boolean to tell if word or not
	private char aChar;
	private Trie parent;
	char[] chars;
	private Trie root = this;



	public Trie() { //trie constructor

		trieNode = new Trie[AlPHA]; //creates new array of type trie
		isWord = false;

	}

	@Override
	public void insertWord(String s) {
		// TODO Implement this method

		Trie trie = this;

		for (int i = 0; i < s.length(); i++) {

			int index = s.charAt(i) - 'a'; //finds index by subtracting ascii
			if (trie.trieNode[index] == null) { //if character doesnt exist in dictionary add it and create new trie array

				trie.trieNode[index] = new Trie(); //creates new trie array
				trie.aChar = s.charAt(i);
			}
			trie.parent = trie;
			trie = trie.trieNode[index]; //changes what character is being looked at
		}
		trie.isWord = true; // makes the last trie array value have a value of true so the dictionary knows what the end of the word is
	}

	@Override
	public boolean contains(String s) {
		// TODO Implement this method
		Trie trie = this;

		for (int i = 0; i < s.length(); i ++) {

			int index = s.charAt(i) - 'a'; //finds index by subtracting the ascii value
			if (trie.trieNode[index]!= null) { //checks if trie array contains the character of the string

				trie = trie.trieNode[index]; // if it does than it goes on to check the trie array for the next character
			} else {
				return false; // if it doesnt then it returns false because the dictionary doesnt contain the word
			}
		}
		return trie.isWord; // it returns true if the word is in the dictionary

	}

	@Override
	public String[] getSugg(String s) {
		// TODO Implement this method

		ArrayList<String> suggestions = new ArrayList<String>();
		int editDistance = 2;

		findWords(s, suggestions, 0, editDistance, root);

		return suggestions.toArray(new String[0]);
	}

	private void findWords(String s, ArrayList<String> suggestions, int strPos, int editDistance, Trie trie) {
		if (strPos == s.length()) {
			if (this.contains(s))
				if (!suggestions.contains(s))
					suggestions.add(s);
			return;
		}

		for (int i = 0; i < 26; i++) {
			if (trie.trieNode[i] != null) {
				if (s.charAt(strPos) == 'a' + i)
					findWords(s, suggestions, strPos + 1, editDistance, trie.trieNode[i]);
				else if (editDistance > 0)
					findWords(s.substring(0, strPos) + (char) ('a' + i) + s.substring(strPos + 1, s.length()), suggestions, strPos + 1, editDistance - 1, trie.trieNode[i]);
			}
		}

		//Trie trie = this;
		//System.out.println(trie.toString());
	}



/*
	public String[] getSugg(String s) {

		ArrayList<String> suggestions = new ArrayList<String>();
		int editDistance = 2;

		findWords(s, suggestions, 0, editDistance);


		return null;
	}

	*/




}




/*


		for (int i = 0; i < s.length(); i++) {


			int index = s.charAt(i) - 'a';
			if (trie.trieNode[index].isWord == true) {
				//editDistance--;
				//findWords(s, suggestions, i + 1, editDistance);
				trie = trie.trieNode[index];
			} else if (trie.trieNode[index].isWord == false) {


				editDistance--;

			}
			trie = trie.trieNode[index];
		}

		Trie triee = new Trie();
		ArrayList<String> arr = new ArrayList<>();


		for (int i = 0; i < s.length(); i++) {

			int index = s.charAt(i) - 'a'; //finds index by subtracting ascii
			if (triee.trieNode[index] == null) { //if character doesnt exist in dictionary add it and create new trie array
				triee.trieNode[index] = new Trie(); //creates new trie array
			}
			triee = triee.trieNode[index]; //changes what character is being looked at
		}
		trie.isWord = true;

		for (int i = 0; i < s.length(); i++) {

			//int ascii = (int) s.charAt(i);
			int index = s.charAt(i) - 'a';


			if (trie.trieNode[index] == triee.trieNode[index]) {
				trie = trie.trieNode[index];
				triee = triee.trieNode[index];
				arr.add(trie.toString());
				System.out.print(trie.toString());
			} else {
				editDistance--;
				trie = trie.trieNode[index];
				triee = triee.trieNode[index];
				arr.add(trie.toString());
				System.out.print(trie.toString());
				System.out.println("Yah");

			}


		}









	private void findWords(String s, ArrayList<String> suggestions, int strPos, int editDistance) {

		Trie trie = this;
		ArrayList<Integer> dexes = new ArrayList<>();
		ArrayList<char[]> words = new ArrayList<>();
		//char[] chars = new char[s.length()];

		if (chars == null) {
			chars = new char[s.length()];

		}

/*
		for (int j = 0; j < AlPHA; j++) {
			//if (trie.trieNode[i] != null)
			//	trie.

			for (int i = 0; i < s.length(); i++) {

				if (trie.trieNode[j].aChar == s.charAt(i)) {
					trie = trie.trieNode[j];
					chars[i] = trie.trieNode[j].aChar;


				} else if (trie.trieNode[j].aChar != s.charAt(i) && editDistance > 0) {



					chars[i] = trie.trieNode[j].aChar;
					editDistance--;

				}

			}

		}

		*/

/*
		//for (int j = 0; j < s.length(); j ++) {

			for (int i = 0; i < AlPHA; i++) {

				if (trie.trieNode[i] == null || strPos > s.length()) {
					break;
				} else if (trie.trieNode[i].aChar == s.charAt(strPos)) {
					//chars[strPos] = trie.trieNode[i].aChar;
					trie = trie.trieNode[i];
					findWords(s, suggestions, strPos + 1, editDistance);
				} else if (trie.trieNode[i].aChar != s.charAt(strPos) && editDistance > 0) {
					//chars[strPos] = trie.trieNode[i].aChar;
					//editDistance--;
					trie = trie.trieNode[i];
					findWords(s, suggestions, strPos + 1, editDistance - 1);
				}

			}

			String str = String.valueOf(trie.trieNode[strPos].aChar);
			while (trie.parent != root) {

				trie.trieNode
			}

			suggestions.add(String.valueOf(chars));
*/
//}

//	}

