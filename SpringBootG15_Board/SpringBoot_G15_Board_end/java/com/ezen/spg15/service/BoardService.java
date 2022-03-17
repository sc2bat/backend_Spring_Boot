package com.ezen.spg15.service;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spg15.dao.IBoardDao;
import com.ezen.spg15.dto.BoardVO;
import com.ezen.spg15.dto.Paging;

@Service
public class BoardService {
	
	@Autowired
	IBoardDao bdao;

	public int getAllCount() {
		return bdao.getAllCount();
	}

	public List<BoardVO> getBoardListAll(Paging paging) {
		List<BoardVO> list = bdao.getBoardListAll(paging);
		for(BoardVO bvo : list) {
			int count = bdao.getCount(bvo.getNum());
			bvo.setReplycnt(count);
		}
		return list;
	}

	public void insertBoard(BoardVO boardvo) {
		bdao.insertBoard(boardvo);
	}

	public HashMap<String, Object> boardView(int num) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		// 조회수 증가
		bdao.plusReadCount(num);
		
		// 게시물 조회
		hm.put("board", bdao.getBoard(num));
		
		// 댓글 리스트 조회
		hm.put("replyList", bdao.getReplyList(num));
		
		return hm;
	}

	public HashMap<String, Object> boardViewWithOutReadCount(int num) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("board", bdao.getBoard(num));
		hm.put("replyList", bdao.getReplyList(num));
		return hm;
	}

	public void addReply(int boardnum, String userid, String content) {
		bdao.addReply(boardnum, userid, content);
	}

	public void deleteReply(int num) {
		bdao.deleteReply(num);
	}

	public BoardVO getBoardOne(int num) {
		return bdao.getBoard(num);
	}

	public void updateBoard(BoardVO boardvo) {
		bdao.updateBoard(boardvo);
	}

	public void deleteBoard(int num) {
		// 게시글 삭제
		bdao.deleteBoard(num);
		// 해당 댓글 삭제
		bdao.deleteBoardReply(num);
	}
}
