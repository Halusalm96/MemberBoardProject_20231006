package com.icia.memberboard.dto;

import com.icia.memberboard.entity.BoardEntity;
import com.icia.memberboard.util.UtilClass;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {
    private Long id;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private int boardHits;
    private String createdAt;
    private List<MultipartFile> boardFile;
    private int fileAttached;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        String formattedDate = boardEntity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        boardDTO.setCreatedAt(formattedDate);
//        boardDTO.setCreatedAt((UtilClass.dateTimeFormat(boardEntity.getCreatedAt())));
        // 파일 첨부 여부에 따라 파일이름 가져가기
        if (boardEntity.getFileAttached()==1){
            for(com.icia.memberboard.entity.BoardFileEntity boardFileEntity : boardEntity.getBoardFileEntityList()) {
                boardDTO.getOriginalFileName().add(boardFileEntity.getOriginalFileName());
                boardDTO.getStoredFileName().add(boardFileEntity.getStoredFileName());
            }
            boardDTO.setFileAttached(1);
        }else{
            boardDTO.setFileAttached(0);
        }
        return boardDTO;

    }
}
