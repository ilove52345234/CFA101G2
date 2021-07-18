package com.emp.model;

import java.util.List;
import java.util.Map;


public interface EmpDAO {

        public void insert(EmpVO empVO);
        public void update(EmpVO empVO);
        public void delete(Integer empId);
        public EmpVO findByPrimaryKey(Integer empId);
        public List<EmpVO> getAll();
        public EmpVO findByEmpIdAndPassword(String empAccount,String empPassword);
        public int findTotalCount(Map<String, String> condition);
        public List findByPage(int start, int rows, Map<String, String> condition);
}


