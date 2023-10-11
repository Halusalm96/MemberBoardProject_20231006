package com.icia.memberboard.service;
import com.icia.memberboard.dto.CommentDTO;
import com.icia.memberboard.entity.BoardEntity;
import com.icia.memberboard.entity.CommentEntity;
import com.icia.memberboard.repository.BoardRepository;
import com.icia.memberboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Long save(CommentDTO commentDTO) {
        BoardEntity boardEntity = boardRepository.findById(commentDTO.getBoardId()).orElseThrow(() -> new NoSuchElementException());
        CommentEntity commentEntity = CommentEntity.toEntity(boardEntity, commentDTO);
        return commentRepository.save(commentEntity).getId();
    }

    @Transactional
    public List<CommentDTO> findAll(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException());
        // 1. BoardEntity에서 댓글 목록 가져오기
//        List<CommentEntity> commentEntityList = boardEntity.getCommentEntityList();
        // 2. CommentRepository에서 가져오기
        // select * from comment_table where board_id=?
        List<CommentEntity> commentEntityList = commentRepository.findByBoardEntityOrderByIdDesc(boardEntity);

        List<CommentDTO> commentDTOList = new ArrayList<>();
        commentEntityList.forEach(comment -> {
            commentDTOList.add(CommentDTO.toDTO(comment));
        });
        return commentDTOList;
    }
}
