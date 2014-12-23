package com.topit.frame.core.util.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TreeUtils
 * @Description: 生成下拉列表树的工具类
 * @author ivan.zhang
 * @date 2014年12月5日 下午2:18:59
 * 
 */
public class TreeUtils {

	/**
	 * @Title: buildTree
	 * @Description: 下拉列表中如果有多棵树，输的root节点为0的遍历
	 * @param list
	 * @return
	 */
	public static List<Node> buildTree(List<Node> list) {
		List<Node> listnode = new ArrayList<Node>();
		Node node = null;
		for (int i = 0; i < list.size(); i++) {
			node = list.get(i);
			if (node.getNodeParent() == 0) {
				list.remove(node);
				i = i - 1;
				Node nodes = TreeUtils.addNode(node, list);
				listnode.add(nodes);
			}
		}
		return listnode;
	}

	/**
	 * @Title: addNode
	 * @Description: 递归查询组装树的结构
	 * @param node
	 * @param list
	 * @return
	 */
	public static Node addNode(Node node, List<Node> list) {

		Node element = null;
		List<Node> leafList = list;
		List<Node> nodeList = null;
		Node leafnode = null;

		for (int j = 0; j < list.size(); j++) {
			element = list.get(j);
			if (element.getNodeParent() == node.getId()) {
				leafnode = addNode(element, leafList);
				nodeList = node.getChildren();
				if (nodeList == null) {
					nodeList = new ArrayList<Node>();
				}
				nodeList.add(leafnode);
				node.setChildren(nodeList);
			}
		}
		return node;
	}

}
