package com.emp.model;

import com.func.model.FuncService;
import com.func.model.FuncVO;
import com.mpfunc.model.MpFuncService;
import com.mpfunc.model.MpFuncVO;
import com.utils.DateUtils;
import com.utils.PageBean;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EmpService {

    private EmpDAO dao;

    public EmpService() {
        dao = new EmpDAOImpl();
    }

    /**
     * 登入的方法,用帳號密碼查詢
     *
     * @param empVO
     * @return
     */

    public EmpVO login(EmpVO empVO) {
        return dao.findByEmpIdAndPassword(empVO.getEmpAccount(), empVO.getEmpPassword());
    }

    /**
     * 增加管理員
     *
     * @param empName
     * @param empAccount
     * @param empPassword
     * @param empStatus
     * @return
     */
    public EmpVO addEmp(String empName, String empAccount, String empPassword, Integer empStatus) {
        EmpVO empVO = new EmpVO();
        empVO.setEmpName(empName);
        empVO.setEmpAccount(empAccount);
        empVO.setEmpStatus(empStatus);
        empVO.setEmpPassword(empPassword);
        empVO.setEmpAddDate(new DateUtils().getSqlCurrentTime());
        dao.insert(empVO);

        return empVO;
    }


    /**
     * 修改管理員
     *
     * @param empId
     * @param empName
     * @param empAccount
     * @param empPassword
     * @param empStatus
     * @return
     */
    public EmpVO updateEmp(Integer empId, String empName, String empAccount, String empPassword, Integer empStatus, java.sql.Date empAddDate) {
        EmpVO empVO = new EmpVO();
        empVO.setEmpId(empId);
        empVO.setEmpName(empName);
        empVO.setEmpAccount(empAccount);
        empVO.setEmpStatus(empStatus);
        empVO.setEmpPassword(empPassword);
        empVO.setEmpAddDate(empAddDate);

//        (empVO);
        dao.update(empVO);

        return empVO;
    }


    /**
     * 刪除管理員
     *
     * @param empId
     */
    public void deleteEmp(Integer empId) {
        dao.delete(empId);
    }


    /**
     * 用管理員編號查詢
     *
     * @param empId
     * @return
     */
    public EmpVO getOneEmp(Integer empId) {
        return dao.findByPrimaryKey(empId);
    }

    /**
     * 查詢全部管理員
     *
     * @return
     */
    public List<EmpVO> getAll() {
        return dao.getAll();
    }


    /**
     * 查詢管理員擁有的權限
     *
     * @param empId
     * @return
     */

    public Set<FuncVO> getFuncByEmpId(Integer empId) {
        //強制用set,為了以後框架
        Set<FuncVO> empFuncList = new LinkedHashSet<FuncVO>();
        MpFuncService mpFuncService = new MpFuncService();

        FuncService funcService = new FuncService();

        //得到管理員擁有的權限列表
        List<MpFuncVO> oneMpFunc = mpFuncService.getOneMpFunc(empId);

        //根據權限列表得到對應的物件
        for (MpFuncVO mpFuncVO : oneMpFunc) {
            FuncVO funcVO = funcService.getOneFunc(mpFuncVO.getFuncId());
            empFuncList.add(funcVO);
        }
        return empFuncList;
    }

    /**
     * 刪除選中的管理員
     *
     * @param delEmpIds
     */
    public void delSelectedEmp(String[] delEmpIds) {
        if (delEmpIds != null && delEmpIds.length > 0) {
            for (String delEmpId : delEmpIds) {
                dao.delete(Integer.parseInt(delEmpId));
            }
        }
    }

    /**
     * 分頁條件查詢的方法
     * @param
     * @param _currentPage
     * @param _rows
     * @param condition
     * @return
     */

    public PageBean<EmpVO> findEmpByPage(String _currentPage, String _rows, Map<String, String> condition) {
        //1.創建一個空的PageBean物件
        PageBean<EmpVO> empVOPageBean = new PageBean<EmpVO>();

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);



        //2.設置參數
        empVOPageBean.setCurrentPage(currentPage);
        empVOPageBean.setRows(rows);

        //3.調用dao查詢總記錄數
        int totalCount= dao.findTotalCount(condition);

        empVOPageBean.setTotalCount(totalCount);

        //4.調用dao查詢List數據集合

        //計算開始的索引值
        int start = (currentPage - 1) * rows;

        List list = dao.findByPage(start,rows,condition);

        empVOPageBean.setList(list);

        //5.計算總頁碼

        int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount / rows) + 1;

        empVOPageBean.setTotalPage(totalPage);



        return empVOPageBean;
    }
}

