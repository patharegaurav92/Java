package tries;

import java.util.HashMap;
import java.util.Map;

public class Tries {

	private class TrieNode{
		Map<Character,TrieNode> children;
		boolean endOfWord;
		public TrieNode(){
			children = new HashMap<>();
			endOfWord = false;
		}
	}
	
	public static TrieNode root;
	public Tries(){
		root = new TrieNode();
	}
	
	
	//Insert into TrieNode
	
	public void insert(String word){
		TrieNode current = root;
		for(int i=0;i<word.length();i++){
			Character ch = word.charAt(i);
			TrieNode node = current.children.get(ch);
			if(node == null){
				node = new TrieNode();
				current.children.put(ch, node);
			}
			current = node;
		}
		current.endOfWord = true;		
	}
	
	public void insertRecursive(String word){
		insertRecursive(root,word,0);
	}
	
	private void insertRecursive(TrieNode current, String word, int index){
		if(index == word.length()){
			current.endOfWord = true;
			return;
		}
		Character ch = word.charAt(index);
		TrieNode node = current.children.get(ch);
		if(node == null){
			node = new TrieNode();
			current.children.put(ch,node);
		}
		insertRecursive(node, word, index+1);
	}
	
	public boolean search(String word){
		TrieNode current = root;
		for(int i=0;i<word.length();i++){
			Character ch = word.charAt(i);
			TrieNode node = current.children.get(ch);
			if(node==null) return false;
			current = node;
		}
		return current.endOfWord;
	}
	
	public boolean searchRecursive(String word){
		return searchRecursive(root,word,0);
	}
	private boolean searchRecursive(TrieNode current, String word, int index){
		if(index == word.length()){
			current.endOfWord = true;
			return current.endOfWord;
		}
		Character ch = word.charAt(index);
		TrieNode node = current.children.get(ch);
		if(node==null) return false;
		return searchRecursive(node,word,index+1 );
		
	}
	
	public static void main(String args[]){
		Tries tries = new Tries();
		tries.insertRecursive("abcd");
		tries.insertRecursive("abdf");
		System.out.println(tries.searchRecursive("abcd"));
		System.out.println(tries.searchRecursive("abqwe"));
	}
	
}
