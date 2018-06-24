package zhiguang.datastructure.interview;

public class Node {
	private int index;
	private Object obj;
	
	public Node(int index, Object obj){
		this.index = index;
		this.obj = obj;
	}
	
	public int getIndex(){
		return index;
	}
	
	public Object getObj(){
		return obj;
	}
}
