package com.func.model;

import com.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncDAOImpl implements FuncDAO {


    JDBCUtils jdbcUtils = new JDBCUtils();


    private static final String INSERT_STMT =
            "insert into FUNC(FUNC_NAME, FUNC_DESC)value(?,?)";
    private static final String GET_ONE_STMT =
            "select FUNC_ID, FUNC_NAME, FUNC_DESC from FUNC where FUNC_ID=?";
    private static final String GET_ALL_STMT =
            "select FUNC_ID, FUNC_NAME, FUNC_DESC from FUNC order by FUNC_ID";
    private static final String DELETE =
            "delete from FUNC where FUNC_ID=?";
    private static final String UPDATE =
            "update FUNC set FUNC_NAME=? ,FUNC_DESC=? where FUNC_ID=?";


    @Override
    public void insert(FuncVO funcVO) {


        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);
            pstmt.setString(1, funcVO.getFuncName());
            pstmt.setString(2, funcVO.getFuncDesc());


            int i = pstmt.executeUpdate();

//            System.out.println("新增" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }
    }

    @Override
    public void update(FuncVO funcVO) {


        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, funcVO.getFuncName());
            pstmt.setString(2, funcVO.getFuncDesc());
            pstmt.setInt(3, funcVO.getFuncId());


            int i = pstmt.executeUpdate();

//            System.out.println("修改" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }
    }

    @Override
    public void delete(Integer funcId) {


        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, funcId);

            int i = pstmt.executeUpdate();

//            System.out.println("刪除" + i + "筆完成");


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(pstmt, con);
        }
    }

    @Override
    public FuncVO findByPrimaryKey(Integer funcId) {

        FuncVO funcVO = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);
            pstmt.setInt(1,funcId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                funcVO = new FuncVO();

                funcVO.setFuncId(rs.getInt("FUNC_ID"));
                funcVO.setFuncName(rs.getString("FUNC_NAME"));
                funcVO.setFuncDesc(rs.getString("FUNC_DESC"));

            }


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(rs,pstmt, con);
        }
        return funcVO;
    }

    @Override
    public List<FuncVO> getAll() {
        ArrayList<FuncVO> funcVOList = new ArrayList<FuncVO>();

        FuncVO funcVO = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                funcVO = new FuncVO();

                funcVO.setFuncId(rs.getInt("FUNC_ID"));
                funcVO.setFuncName(rs.getString("FUNC_NAME"));
                funcVO.setFuncDesc(rs.getString("FUNC_DESC"));

                funcVOList.add(funcVO);

            }


        } catch (SQLException se) {
            throw new RuntimeException("發生錯誤:" + se.getMessage());
        } finally {
            jdbcUtils.close(rs,pstmt, con);
        }
        return funcVOList;
    }
}
