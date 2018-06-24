package zhiguang.datastructure.interview;

import java.util.ArrayList;
import java.util.List;

//给定一个单链表, 长度未知, 找该链表的中间节点
//方法1:1. 先确定该链表的长度;2.查找中间节点
//方法2:设定两个游标a,b, a的步长为1, b为2; b走到终点时, a正好在中间
public class TencentLinkedList {
	private List<Node> nodes = new ArrayList<>();

	public TencentLinkedList() {
		this.nodes.add(new Node(1, "a"));
		this.nodes.add(new Node(3, "c"));
		this.nodes.add(new Node(2, "b"));
		this.nodes.add(new Node(5, "e"));
		this.nodes.add(new Node(4, "d"));
	}

	// 因为不知道长度, 所以要先获取长度;不用size()方法
	//复杂度: o(n)
	public int getLength() {
		int len = 0;
		for (Node node : nodes) {
			len += 1;
		}
		System.out.println("长度: " + len);
		return len;
	}

	// 获取长度之后, 求出中间节点
	//复杂度: o(n/2)
	//总体复杂度: o(3n/2)
	public Object getMiddle() {
		int len = getLength();
		int m = len / 2 + 1;
		
		for (Node node : nodes) {
			if(node.getIndex() == m){
				return node.getObj();
			}
		}

		return null;
	}
	
	
	//方法二
	
	
	
	
}
