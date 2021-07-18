package com.emp.model;


import com.mpfunc.model.MpFuncService;
import com.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EmpDAOImpl implements EmpDAO {


    JDBCUtils jdbcUtils = new JDBCUtils();


    private static final String INSERT_STMT =
            "insert into EMPLOYEE (EMP_NAME, EMP_ACCOUNT, EMP_PASSWORD, EMP_STATUS,EMP_ADD_DATE) value (?,?,?,?,?)";
    private static final String GET_ONE_STMT =
            "select EMP_ID, EMP_NAME, EMP_ACCOUNT, EMP_PASSWORD, EMP_STATUS,EMP_ADD_DATE from EMPLOYEE where EMP_ID =?";

    private static final String GET_ALL_STMT =
            "select EMP_ID, EMP_NAME, EMP_ACCOUNT, EMP_PASSWORD, EMP_STATUS,EMP_ADD_DATE from EMPLOYEE order by EMP_ID";
    private static final String DELETE =
            "delete from EMPLOYEE where EMP_ID =?";

    private static final String UPDATE =
            "update EMPLOYEE set EMP_NAME=?,EMP_ACCOUNT=?,EMP_PASSWORD=?,EMP_STATUS=? ,EMP_ADD_DATE=? where EMP_ID=?";

    private static final String GET_ONE_ID_PASSWORD =
            "select * from EMPLOYEE where EMP_ACCOUNT =? and EMP_PASSWORD=?";





    @Override
    public void insert(EmpVO empVO) {


        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);
            pstmt.setString(1, empVO.getEmpName());
            pstmt.setString(2, empVO.getEmpAccount());
            pstmt.setString(3, empVO.getEmpPassword());
            pstmt.setInt(4, empVO.getEmpStatus());
            pstmt.setDate(5, empVO.getEmpAddDate());


            int i = pstmt.executeUpdate();

            System.out.println("新增" + i + "筆完成");

        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }


    }

    @Override
    public void update(EmpVO empVO) {


        Connection con = null;
        PreparedStatement pstmt = null;
        try {

            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(UPDATE);
            pstmt.setString(1, empVO.getEmpName());
            pstmt.setString(2, empVO.getEmpAccount());
            pstmt.setString(3, empVO.getEmpPassword());
            pstmt.setInt(4, empVO.getEmpStatus());
            pstmt.setDate(5, empVO.getEmpAddDate());
            pstmt.setInt(6, empVO.getEmpId());

            int i = pstmt.executeUpdate();

            System.out.println("修改" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }
    }

    @Override
    public void delete(Integer empId) {
        System.out.println("刪除的ID"+empId);
        Connection con = null;
        PreparedStatement pstmt = null;
        try {

            con = jdbcUtils.getConnection();

            // 1●設定於 pstm.executeUpdate()之前
            con.setAutoCommit(false);

            // 先刪權限
            new MpFuncService().deleteAllMpFunc(empId);



            pstmt = con.prepareStatement(DELETE);


            pstmt.setInt(1, empId);

            // 刪除員工
            int i = pstmt.executeUpdate();

            // 2●設定於 pstm.executeUpdate()之後
            con.commit();
            con.setAutoCommit(true);

            System.out.println("刪除" + i + "筆完成");


        } catch (SQLException se) {
            if (con != null) {
                try {
                    // 3●設定於當有exception發生時之catch區塊內
                    con.rollback();
                } catch (SQLException e) {
                    throw new RuntimeException("rollback error "
                            + e.getMessage());
                }
            }
            throw new RuntimeException("發生錯誤:"
                    + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }
    }


    @Override
    public EmpVO findByPrimaryKey(Integer empId) {


        EmpVO empVO = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);
            pstmt.setInt(1, empId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                empVO = new EmpVO();
                empVO.setEmpId(rs.getInt("EMP_ID"));
                empVO.setEmpName(rs.getString("EMP_NAME"));
                empVO.setEmpAccount(rs.getString("EMP_ACCOUNT"));
                empVO.setEmpPassword(rs.getString("EMP_PASSWORD"));
                empVO.setEmpStatus(rs.getInt("EMP_STATUS"));
                empVO.setEmpAddDate(rs.getDate("EMP_ADD_DATE"));
            }


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(rs, pstmt, con);
        }
        return empVO;
    }


    @Override
    public List<EmpVO> getAll() {
        List<EmpVO> empVOList = new ArrayList<EmpVO>();

        EmpVO empVO = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                empVO = new EmpVO();
                empVO.setEmpId(rs.getInt("EMP_ID"));
                empVO.setEmpName(rs.getString("EMP_NAME"));
                empVO.setEmpAccount(rs.getString("EMP_ACCOUNT"));
                empVO.setEmpPassword(rs.getString("EMP_PASSWORD"));
                empVO.setEmpStatus(rs.getInt("EMP_STATUS"));
                empVO.setEmpAddDate(rs.getDate("EMP_ADD_DATE"));

                empVOList.add(empVO);
            }


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(rs, pstmt, con);
        }
        return empVOList;
    }


    public EmpVO findByEmpIdAndPassword(String empAccount, String empPassword) {
        EmpVO empVO = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(GET_ONE_ID_PASSWORD);
            pstmt.setString(1, empAccount);
            pstmt.setString(2, empPassword);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                empVO = new EmpVO();
                empVO.setEmpId(rs.getInt("EMP_ID"));
                empVO.setEmpName(rs.getString("EMP_NAME"));
                empVO.setEmpAccount(rs.getString("EMP_ACCOUNT"));
                empVO.setEmpPassword(rs.getString("EMP_PASSWORD"));
                empVO.setEmpStatus(rs.getInt("EMP_STATUS"));
                empVO.setEmpAddDate(rs.getDate("EMP_ADD_DATE"));

            }


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(rs, pstmt, con);
        }
        return empVO;
    }


    /**
     * 查詢總紀錄數量
     *
     * @param condition
     * @return
     */
    @Override
    public int findTotalCount(Map<String, String> condition) {
//        ("total方法開始");


        JdbcTemplate Template = new JdbcTemplate(jdbcUtils.getDataSource());
        //定義模板語句
        String GET_ALL_COUNT =
                "select count(*) from EMPLOYEE where 1 = 1 ";

        StringBuilder sb = new StringBuilder(GET_ALL_COUNT);


        //迭代map
        Set<String> keySet = condition.keySet();
        //定義參數的集合
        List<Object> params = new ArrayList<Object>();

        for (String key : keySet) {

//            ("Key:"+key);
            //排除分頁條件參數
            if ("currentPage".equals(key)||
                    "rows".equals(key) ||
                    "funs".equals(key)||
                    "delEmpId".equals(key)){
                continue;
            }

            //獲取值
            String value = condition.get(key);
//            ("value:"+value);

            //判斷是否有值
            if (value != null && !"".equals(value)) {
                //有值
                sb.append(" and " + key + " like ? ");
                params.add("%"+value+"%"); //加問號條件的值
            }
        }


//        (params);



            return Template.queryForObject(sb.toString(), Integer.class, params.toArray());

    }


    /**
     * 分頁查詢每頁的紀錄
     *
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    @Override
    public List<EmpVO> findByPage(int start, int rows, Map<String, String> condition) {

        JdbcTemplate Template = new JdbcTemplate(jdbcUtils.getDataSource());
         String GET_LIMIT =
                "select * from EMPLOYEE where 1 = 1 ";

//        ("ByPage方法開始");



        StringBuilder sb = new StringBuilder(GET_LIMIT);

        //迭代map
        Set<String> keySet = condition.keySet();

        //定義參數的集合

        List<Object> params = new ArrayList<Object>();

        for (String key : keySet) {
            //排除分頁條件參數
            if ("currentPage".equals(key)||
                    "rows".equals(key) ||
                    "funs".equals(key)||
                    "delEmpId".equals(key)){
                continue;
            }

            //獲取值
            String value = condition.get(key);

//            (value);
            //判斷是否有值
            if (value != null && !"".equals(value)) {
                //有值
                sb.append(" and " + key + " like ? ");
                params.add("%"+value+"%"); //加問號條件的值
            }
        }

        //添加分頁查詢
        sb.append("limit ?,?");

        //添加分頁查詢的參數

        params.add(start);
        params.add(rows);


        String sql = sb.toString();




            return Template.query(sql,new BeanPropertyRowMapper<EmpVO>(EmpVO.class),params.toArray());


    }
}
