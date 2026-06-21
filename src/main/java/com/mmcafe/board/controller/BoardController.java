package com.mmcafe.board.controller;
import com.mmcafe.board.dto.BoardRequest;
import com.mmcafe.board.dto.BoardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RestController
@RequestMapping("/boards")
public class BoardController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoardResponse create(@RequestBody BoardRequest request) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "TODO: 게시글 등록을 구현하세요.");
    }
    @GetMapping("/{id}")
    public BoardResponse get(@PathVariable long id) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "TODO: 게시글 단건 조회를 구현하세요.");
    }
}
