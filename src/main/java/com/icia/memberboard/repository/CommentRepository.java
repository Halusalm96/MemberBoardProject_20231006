package com.icia.memberboard.repository;

import com.icia.memberboard.entity.BoardEntity;
import com.icia.memberboard.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

}
