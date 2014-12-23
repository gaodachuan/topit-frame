package com.topit.frame.busniess.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.topit.frame.busniess.base.IComObjectSortTypeService;
import com.topit.frame.common.util.CategoryConstant;
import com.topit.frame.core.entity.dao.base.IComObjectSortTypeDAO;
import com.topit.frame.core.entity.dao.imp.ComObjectSortCategoryDAO;
import com.topit.frame.core.entity.dao.imp.ComObjectSortTypeDAO;
import com.topit.frame.core.entity.data.ComObjectSortType;
import com.topit.frame.core.util.entity.CategoryNode;
import com.topit.frame.core.util.entity.Node;
import com.topit.frame.core.util.entity.TreeUtils;

@Repository("comObjectSortTypeService")
public class ComObjectSortTypeService implements IComObjectSortTypeService {

	@Resource(name = "comObjectSortTypeDAO")
	private IComObjectSortTypeDAO comObjectSortTypeDAO;

	public List<Node> getTree(String sql)
			throws Exception {
		List<Node> list = new ArrayList<Node>();
		list = comObjectSortTypeDAO
				.getListTree(sql);
		list = TreeUtils.buildTree(list);
		return list;
	}

	/**   
	 * <p>Title: getTreeWithModule</p>   
	 * <p>Description: 只加载包含模块的树的节点</p>   
	 * @param sql
	 * @return
	 * @throws Exception   
	 * @see com.topit.frame.busniess.base.IComObjectSortTypeService#getTreeWithModule(java.lang.String)   
	 */
	 
	public List<Node> getTreeWithModule(String sql) throws Exception {

			List<Node> list = new ArrayList<Node>();
			list = comObjectSortTypeDAO
					.getListTree(CategoryConstant.MOUDLE_TYPE_SQL);
			list = TreeUtils.buildTree(list);
			List<Node> lastList=new ArrayList<Node>();
			for(Node node : list){
				List<Node> childrenList=node.getChildren();
				if(childrenList!=null){
					this.hasModule(node);

					if(node.getChildren()!=null){
						lastList.add(node);
					}
					
				}
			}
			return lastList;
	}
	
	/**   
	 * @Title: hasModule   
	 * @Description: 将非模块节点从获得的树 的结构上移除   
	 * @param node        
	 */
	 
	public void hasModule(Node node){
		List <Node> nodes=node.getChildren();
		for (int i =0 ;i<nodes.size();i++){
			Node n=nodes.get(i);
			if(n.getChildren()!=null){
				this.hasModule(n);
			}else{
				CategoryNode cNode=(CategoryNode) n;
				if(!"SYS_MODULE".equals(cNode.getTablename())){
					nodes.remove(nodes.get(i));
				}
			}
		}
	}

}
