package com.mpfunc.model;

import com.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MpFuncDAOImpl implements MpFuncDAO {


    JDBCUtils jdbcUtils = new JDBCUtils();

    private static final String INSERT_STMT =
            "insert into MP_FUNC(EMP_ID, FUNC_ID)value(?,?)";
    private static final String UPDATE =
            "update MP_FUNC set FUNC_ID=? where EMP_ID=? and FUNC_ID=? ";
    private static final String DELETE =
            "delete from MP_FUNC where EMP_ID=? and FUNC_ID=? ";
    private static final String GET_ALL_STMT =
            "select EMP_ID,FUNC_ID from MP_FUNC order by EMP_ID";
    private static final String GET_ONE_STMT =
            "select EMP_ID,FUNC_ID from MP_FUNC where EMP_ID=?";

    private static final String DELETE_ALL_FUNC =
    "delete from MP_FUNC where EMP_ID=?";


    @Override
    public void insert(MpFuncVO mpFuncVO) {

        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1,mpFuncVO.getEmpId());
            pstmt.setInt(2,mpFuncVO.getFuncId());


            int i = pstmt.executeUpdate();

            System.out.println("新增" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }

    }

    @Override
    public void update(MpFuncVO mpFuncVO,Integer funcId) {

        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(UPDATE);


            pstmt.setInt(1,funcId);
            pstmt.setInt(2,mpFuncVO.getEmpId());
            pstmt.setInt(3,mpFuncVO.getFuncId());

            int i = pstmt.executeUpdate();

            System.out.println("修改" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }

    }

    @Override
    public void delete(Integer empId , Integer funcId) {

        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, empId);
            pstmt.setInt(2, funcId);


            int i = pstmt.executeUpdate();

            System.out.println("刪除" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }
    }

    @Override
    public void deleteAllFunc(Integer empId) {

        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(DELETE_ALL_FUNC);

            pstmt.setInt(1, empId);



            int i = pstmt.executeUpdate();

            System.out.println("刪除" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }

    }

    @Override
    public List<MpFuncVO> findByPrimaryKey(Integer empId) {
        ArrayList<MpFuncVO> oneMpFuncVOList = new ArrayList<MpFuncVO>();
        MpFuncVO mpFuncVO = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);
            pstmt.setInt(1, empId);

            rs =pstmt.executeQuery();

            while(rs.next()){
                mpFuncVO = new MpFuncVO();
                mpFuncVO.setEmpId(rs.getInt("EMP_ID"));
                mpFuncVO.setFuncId(rs.getInt("FUNC_ID"));
                oneMpFuncVOList.add(mpFuncVO);
            }





        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(rs,pstmt, con);
        }
        return oneMpFuncVOList;
    }

    @Override
    public List<MpFuncVO> getAll() {
        ArrayList<MpFuncVO> mpFuncVOList = new ArrayList<MpFuncVO>();

        MpFuncVO mpFuncVO = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);


            rs =pstmt.executeQuery();

            while(rs.next()){
                mpFuncVO = new MpFuncVO();
                mpFuncVO.setEmpId(rs.getInt("EMP_ID"));
                mpFuncVO.setFuncId(rs.getInt("FUNC_ID"));
            mpFuncVOList.add(mpFuncVO);
            }


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(rs,pstmt, con);
        }
        return mpFuncVOList;
    }
}
