package com.topit.frame.busniess.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topit.frame.busniess.base.ISysOptionService;
import com.topit.frame.common.util.SqlUtil;
import com.topit.frame.core.entity.dao.base.IComObjectSortCategoryDAO;
import com.topit.frame.core.entity.dao.base.ISysOptionDAO;
import com.topit.frame.core.entity.data.ComObjectSortCategory;
import com.topit.frame.core.entity.data.SysOption;
import com.topit.frame.core.util.entity.TreeDTO;

/**
 * @ClassName: SysOptionService
 * @Description: 系统选项的服务类
 * @author qiugui
 * @date 2014年11月20日 下午2:37:55
 * 
 */
@Service("sysOptionService")
@Transactional
public class SysOptionServiceImp implements ISysOptionService {

	@Resource(name = "sysOptionDAOImp")
	private ISysOptionDAO sysOptionDAOImp;
	@Resource(name = "comObjectSortCategoryDAO")
	private IComObjectSortCategoryDAO icDao;
	/**
	 * <p>
	 * Title: loadAllByCategoryId
	 * </p>
	 * <p>
	 * Description: 根据分类选项id加载数据库中系统选项表的所有当前数据
	 * </p>
	 * 
	 * @param categoryId
	 * @return
	 * @throws Exception
	 * @see com.topit.frame.busniess.base.ISysOptionService#loadAllByCategoryId(java.lang.String)
	 */

	public List<SysOption> loadAllByCategoryId(String categoryId)
			throws Exception {

		return sysOptionDAOImp.loadAllByCategoryId(categoryId);

	}

	public List<TreeDTO> loadAllSysOption() throws Exception {
		List<ComObjectSortCategory> list = icDao.getList(1);
		TreeDTO tree = new TreeDTO();
		Set<TreeDTO> childen = new HashSet<TreeDTO>();
		// TODO 动态的设置系统设置分类编号
		tree.setId(0);
		tree.setText("全部");
		tree.setState("open");
		for (ComObjectSortCategory comObjectSortCategory : list) {
			TreeDTO child_tree = new TreeDTO();
			child_tree.setId(comObjectSortCategory.getItemId());
			child_tree.setText(comObjectSortCategory.getCategoryName());
			child_tree.setState("close");
			child_tree.setChecked(0);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("categoryId", comObjectSortCategory.getItemId());
			child_tree.setAttributes(map);
			childen.add(child_tree);

		}
		tree.setChildren(childen);
		List<TreeDTO> treeList = new ArrayList<TreeDTO>();
		treeList.add(tree);
		return treeList;

	}

	public boolean batchUpdate(List<SysOption> sysOptions) throws Exception {

		String tableName = SysOption.class.getSimpleName();
		String[] columns = new String[] { "OptionValue" };
		String[] conditions = new String[] { "CategoryId", "OptionKey" };
		String hql = SqlUtil.createUpdate(tableName, columns, conditions);
		return sysOptionDAOImp.batchUpdate(hql, sysOptions);

	}

	public boolean batchUpdate2(List<Map<String, Object>> sysOptionList)
			throws Exception {
		String hql="UPDATE SysOption SET OptionValue=? WHERE CategoryId=? AND OptionKey=?";

		return sysOptionDAOImp.batchUpdate2(hql, sysOptionList);
		 
	}

}
