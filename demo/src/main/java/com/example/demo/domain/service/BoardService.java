package com.example.demo.domain.service;

import com.example.demo.domain.dto.BoardDto;
import com.example.demo.domain.entity.Board;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardService {
    
    //모든 게시물 가져오기
    public List<BoardDto> getAllBoard(){
        
    }
    //게시물 하나 조회하기
    public BoardDto getBoard(Long number){
        
    }
    //나의 게시물 조회하기
    public List<BoardDto> getMyBoard(){
        
    }
    //id로 글 조회하기
    public List<BoardDto> getIdBoard(){

    }
    //게시물 등록하기
    public boolean boardAdd(BoardDto dto){
        Board board = new Board();
        board.setId(dto.getId());
        board.setContents(dto.getContents());
        board.setDate(LocalDateTime.now());
        board.setHits(0L);
        board.setLike(0L);

        return true;
    }
    //게시물 수정하기
    public boolean boardUpdate(BoardDto dto){
        return true;
    }
    //게시물 삭제하기
    public Long boardDelete(Long number){

    }
}
