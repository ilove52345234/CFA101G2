package com.extrabill.model;

import java.sql.Timestamp;
import java.util.List;

public class ExtraBillService {
        private ExtraBillDAO extraBillDAO;

    public ExtraBillService() {
       extraBillDAO = new ExtraBillDAOImpl();
    }

    public ExtraBillVO addExtraBill(Integer orderListId, Integer extraPrice, Integer amount, String serviceItem, Timestamp consumptionDate){
        ExtraBillVO extraBillVO = new ExtraBillVO();

        extraBillVO.setOrderListId(orderListId);
        extraBillVO.setExtraPrice(extraPrice);
        extraBillVO.setAmount(amount);
        extraBillVO.setServiceItem(serviceItem);
        extraBillVO.setConsumptionDate(consumptionDate);



        extraBillDAO.insert(extraBillVO);
        return extraBillVO;
    }



    public ExtraBillVO updateExtraBill(Integer extraBillId,Integer orderListId, Integer extraPrice, Integer amount, String serviceItem, Timestamp consumptionDate){
        ExtraBillVO extraBillVO = new ExtraBillVO();

        extraBillVO.setOrderListId(orderListId);
        extraBillVO.setExtraPrice(extraPrice);
        extraBillVO.setAmount(amount);
        extraBillVO.setServiceItem(serviceItem);
        extraBillVO.setConsumptionDate(consumptionDate);
        extraBillVO.setExtraBillId(extraBillId);



        extraBillDAO.update(extraBillVO);
        return extraBillVO;
    }



    public void deleteExtraBill(Integer extraBillId) {
        extraBillDAO.delete(extraBillId);
    }


    public List<ExtraBillVO> getAll() {
        return extraBillDAO.getAll();
    }


    public ExtraBillVO getOneExtraBill(Integer extraBillId) {
        return extraBillDAO.findByPrimaryKey(extraBillId);
    }
}
