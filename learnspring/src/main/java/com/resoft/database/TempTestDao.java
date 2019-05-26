package com.resoft.database;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class TempTestDao {
		private JdbcTemplate jdbcTemplate;

		public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate;
		}
		
		public Map<String, Object> select(String ename) {
			String sql="SELECT t.* FROM emp t where t.ename=?";
			Object[] param=new Object[]{ename};
			 Map<String, Object> queryForMap = jdbcTemplate.queryForMap(sql,param);
			return queryForMap;
		}
		public int insert(String ename) {
			String sql="insert into emp (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)values (7368, ?, 'CLERK', 7902, to_date('17-12-1980', 'dd-mm-yyyy'), 800.00, null, 20)";
			Object[] param=new Object[]{ename};
			int update = jdbcTemplate.update(sql,param);
			return update;
		}
}
