package com.roomreview.model;

import com.ron.model.RonDAO;
import com.ron.model.RonVO;

import java.sql.Timestamp;
import java.util.List;

public class RoomReviewService {


    private RoomReviewDAO roomReviewDAO;

    public RoomReviewService() {
        roomReviewDAO = new RoomReviewDAOImpl();
    }


    public RoomReviewVO addRoomReview(Integer roomCategoryId, Integer memId, String commentContent, Timestamp commentDate, String satisfaction) {
        RoomReviewVO roomReviewVO = new RoomReviewVO();

        roomReviewVO.setRoomCategoryId(roomCategoryId);
        roomReviewVO.setMemId(memId);
        roomReviewVO.setCommentContent(commentContent);
        roomReviewVO.setCommentDate(commentDate);
        roomReviewVO.setSatisfaction(satisfaction);

        roomReviewDAO.insert(roomReviewVO);
        return roomReviewVO;
    }

    public RoomReviewVO updateRoomReview(Integer roomReviewId, Integer roomCategoryId, Integer memId, String commentContent, Timestamp commentDate, String satisfaction) {
        RoomReviewVO roomReviewVO = new RoomReviewVO();

        roomReviewVO.setRoomCategoryId(roomCategoryId);
        roomReviewVO.setMemId(memId);
        roomReviewVO.setCommentContent(commentContent);
        roomReviewVO.setCommentDate(commentDate);
        roomReviewVO.setSatisfaction(satisfaction);
        roomReviewVO.setRoomReviewId(roomReviewId);

        roomReviewDAO.update(roomReviewVO);
        return roomReviewVO;

    }


    public void deleteRoomReview(Integer roomReviewId) {
        roomReviewDAO.delete(roomReviewId);
    }


    public List<RoomReviewVO> getAll() {
        return roomReviewDAO.getAll();
    }


    public RoomReviewVO getOneRoomReview(Integer roomReviewId) {
        return roomReviewDAO.findByPrimaryKey(roomReviewId);
    }
}
