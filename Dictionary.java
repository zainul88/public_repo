package com.my.algos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Dictionary {
	HashMap<Character, Dictionary> children = new HashMap<Character, Dictionary>();
	boolean isWord;
	
	/** This method will construct a dictionary by parsing the file
	 * @param file path as String
	 */
	public void constructDictionary(String filePath){
		File file = new File(filePath);
		InputStream in = null;
		BufferedReader reader = null;
		try {
			in = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(in));
			while(null != reader.readLine()){
				String line = reader.readLine();
				insertData(line);
			}
		} catch (IOException e) {
			System.out.println("File not found");
		} finally {
			try {
				reader.close();
				in.close();
			} catch (IOException e) {
				System.out.println("Exception in closing stream/file " + e.getMessage());
			}
		}
	}
		
	/**
	 * @param word to be searched in dictionary
	 * @return result as boolean
	 */
	public boolean findWord(String str){
		boolean result = false;
		int maxLen = str.length();
		
		Dictionary temp = children.get(str.charAt(0));
		if(null == temp){
			return false;
		}
		
		for(int index = 1; index < maxLen; index++){
			if(null == temp.children.get(str.charAt(index))){
				return false;
			}
			temp = temp.children.get(str.charAt(index));
		}
		
		result = temp.isWord;
		return result;
	}
	
	public void insertData(String line){
		String[] strings = line.split(" ");
		for (String string : strings) {
			insertWord(string);
		}
	}
	
	private void insertWord(String str){
		int maxLen = str.length();
		Dictionary temp = children.get(str.charAt(0));
		if(null == temp){
			children.put(str.charAt(0), new Dictionary());
			temp = children.get(str.charAt(0));
		}
		
		for(int index = 1; index < maxLen; index++){
			if(null == temp.children.get(str.charAt(index))){
				temp.children.put(str.charAt(index), new Dictionary());
			}
			temp = temp.children.get(str.charAt(index));
		}
		temp.setWord(true);
	}

	
	public boolean isWord() {
		return isWord;
	}
	public void setWord(boolean isWord) {
		this.isWord = isWord;
	}
}
