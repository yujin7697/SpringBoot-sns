package com.example.demo.domain.dto;

import com.example.demo.domain.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private Long number; // 게시물 번호
    private String id; // userid
    private String contents; // 글내용
    private LocalDateTime date; // 작성날짜
    private Long hits; // 조회수
    private Long like; // 좋아요

    public static BoardDto Of(Board board){
        BoardDto dto = new BoardDto();
        return dto;
    }
}
