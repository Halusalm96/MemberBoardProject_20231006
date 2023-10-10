package com.icia.memberboard.controller;

import com.icia.memberboard.dto.BoardDTO;
import com.icia.memberboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board/list")
    public String boardList(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(value = "type", required = false, defaultValue = "boardTitle") String type,
                            @RequestParam(value = "q", required = false, defaultValue = "") String q) {
        Page<BoardDTO> boardDTOList = boardService.findAll(page, type, q);
        // 목록 하단에 보여줄 페이지 번호
        int blockLimit = 3;
        int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < boardDTOList.getTotalPages()) ? startPage + blockLimit - 1 : boardDTOList.getTotalPages();
        model.addAttribute("boardList", boardDTOList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("type", type);
        model.addAttribute("q", q);
        return "/boardPages/boardList";
    }
}
