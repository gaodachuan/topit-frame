package com.topit.frame.core.entity.dao.imp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.topit.frame.common.util.IdGeneratorConstant;
import com.topit.frame.core.dao.BaseDAO;
import com.topit.frame.core.entity.dao.base.IIdGenerator;
import com.topit.frame.core.entity.data.SysSequence;

/**
 * 
 * @ClassName: IdGenerator
 * @Description: id生成工具的实现类
 * @author gaodachuan
 * @date 2014年11月18日 下午5:13:22
 *
 */
@Transactional
@Repository("idGenerator")
@Scope("prototype")
public class IdGenerator extends BaseDAO<SysSequence> implements IIdGenerator {
	@Resource
	private JdbcTemplate jdbcTemplate;

	public BigInteger getNextId(String sequenceName) {
		return getNextIds(sequenceName, 1, 1).get(0);
	}

	public List<BigInteger> getNextIds(String sequenceName, int Size) {

		return getNextIds(sequenceName, Size, 1);
	}

	public List<BigInteger> getNextIds(String sequenceName, int Size,
			int IncreamentSize) {

		// 跟新指定表的的策略
		String update_sql =IdGeneratorConstant.GENERATOR_SQL_UPDATECURRENTID;
		int result = jdbcTemplate.update(update_sql, Size, IncreamentSize,
				sequenceName);

		if (result > 0)// 跟新成功
		{
			String query_sql =IdGeneratorConstant.GENERATOR_SQL_QUERYCURRENTID;		
			BigInteger start = new BigInteger(jdbcTemplate.queryForInt(
					query_sql, sequenceName)-(Size*IncreamentSize)+"");
			return CreateList(Size, start, IncreamentSize);
		} else {			
			// 插入新的策略
			SysSequence sequence = new SysSequence(sequenceName);
			sequence.setCurrentId(new BigInteger(1 + "").add(new BigInteger(
					Size * IncreamentSize + "")));
			sequence.setIncrementSize(IncreamentSize);
			createGenerator(sequence);
			return CreateList(Size, new BigInteger(1+ ""),
					sequence.getIncrementSize());
			
		}

	}

	private List<BigInteger> CreateList(int Size, BigInteger start,
			int IncrementSize) {
		int k=start.intValue();
		List<BigInteger> list = new ArrayList<BigInteger>();
		list.add(start);
		for (int i = 1; i < Size; i++) {
			BigInteger stemp = start.add(new BigInteger(i + "")
					.multiply(new BigInteger(IncrementSize + "")));
			list.add(stemp);
		}
		return list;
	}

	public synchronized boolean deleteGenerator(String SeQuenceName) {
		String sql = "delete from sys_sequence  where SequenceName=?";
		return jdbcTemplate.update(sql, SeQuenceName) > 0;

	}

	public synchronized boolean reSetToZero(String SeQuenceName) {
		return reSetToGoalId(SeQuenceName, new BigInteger("0"));
	}

	public synchronized boolean reSetToGoalId(String SeQuenceName,
			BigInteger goal) {
		String sql = "UPDATE sys_sequence s SET s.CurrentId=? WHERE s.SequenceName=?";
		return jdbcTemplate.update(sql, goal, SeQuenceName) > 0;
	}

	public synchronized boolean reSetIncrementSize(String SeQuenceName,
			int IncrementSize) {
		String sql = "UPDATE sys_sequence s SET s.IncrementSize=? WHERE s.SequenceName=?";
		return jdbcTemplate.update(sql, IncrementSize, SeQuenceName) > 0;
	}

	public boolean createGenerator(SysSequence sequence) {
		getHibernateTemplate().save(sequence);
		getHibernateTemplate().flush();
		return false;

	}

}
