package hackerrank;

public class BinarySearch {
	public static class Node{
	    Node left,right;
	    int data;
	    
	    public Node(int data){
	        this.data = data;
	    }
	    
	    //insert
	    public void insert(int value){
	        if(value<=data){
	            if(left==null){
	                left = new Node(value);
	            } else{
	                left.insert(value);
	            }
	        } else{
	            if(right==null){
	                right = new Node(value);
	            } else{
	                right.insert(value);
	            }
	        }
	    }
	    
	    public void delete(int value){
	    	if(contains(value)){
	    		
	    	}
	    	
	    	
	    	
	    }
	    public boolean contains(int value){
	        if(value == data){
	            return true;
	        }
	        else if(value<data){
	            if(left==null){
	                return false;
	            }else{
	                left.contains(value);
	            }
	        }else {
	            if(right==null){
	                return false;
	            }else{
	                right.contains(value);
	            }
	        }
	        return false;
	    }
	    
	    public void printInOrder(){
	        if(left!=null){
	            left.printInOrder();
	        }
	        System.out.println(data);
	        if(right!=null){
	            right.printInOrder();
	        }
	    }
	}
	public static void main(String []args){
		Node root = new Node(10);
		root.insert(5);
		root.insert(7);
		root.insert(12);
		root.insert(6);
		root.printInOrder();
		System.out.println(root.contains(10));
	}
}
