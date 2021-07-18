package com.rmtype.model;

import com.utils.Base64VO;
import java.util.List;
import java.util.Map;

public interface RtDAO_interface {
    void insert(RtVO paramRtVO);

    void update(RtVO paramRtVO);

    void delete(Integer paramInteger);

    RtVO findByPrimaryKey(Integer paramInteger);

    List<RtVO> getAll();

    RtVO findRoom(String paramString);

    void insertWithRtAndPic(RtVO paramRtVO, List<Base64VO> paramList);

    void updateByNormal(RtVO paramRtVO);

    int findTotalCount(Map<String, String> paramMap);

    List<RtVO> findByPage(int paramInt1, int paramInt2, Map<String, String> paramMap);
}


/* Location:              /Users/ilove52345234/eclipse_TestWorkspace/Test/src/main/ImportedClasses/com/rmtype/model/RtDAO_interface.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */