package com.icia.memberboard.repository;

import com.icia.memberboard.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {

    //    Optional<BoardEntity> findById(Long id);
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
//    @Query(value = "update board_table set board_hits=board_hits+1 where id=:id", nativeQuery = true)
    void increaseHits(@Param("id") Long id);

    List<BoardEntity> findByBoardTitle(String boardTitle);

    List<BoardEntity> findByBoardTitleOrderById(String boardTitle);

    List<BoardEntity> findByBoardTitleContaining(String q);

    List<BoardEntity> findByBoardWriterContaining(String q);

    List<BoardEntity> findByBoardWriterContainingOrderById(String q);

    // 제목으로 검색한 결과를 Page 객체로 리턴
    Page<BoardEntity> findByBoardTitleContaining(String q, Pageable pageable);

    Page<BoardEntity> findByBoardWriterContaining(String q, Pageable pageable);

    Page<BoardEntity> findByBoardTitleContainingOrBoardWriterContaining(String q1,String q2, Pageable pageable);

}
