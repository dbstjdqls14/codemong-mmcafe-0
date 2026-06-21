package com.mmcafe.board.controller;

import com.mmcafe.board.dto.BoardRequest;
import com.mmcafe.board.dto.BoardResponse;
import com.mmcafe.board.repository.BoardRepository;
import com.mmcafe.common.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
@CrossOrigin(origins = "http://localhost:5173")
public class BoardController {

    private final BoardRepository repo;

    public BoardController(BoardRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoardResponse create(@RequestBody BoardRequest request) {
        return repo.save(request);
    }

    @GetMapping("/{id}")
    public BoardResponse get(@PathVariable long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("게시글을 찾을 수 없습니다."));
    }

}

/*
게시글 생성과 단건 조회를 구현합니다. POST /boards는 title/content를 받아 201 Created와 id, title, content, createdAt을 반환해야 하고, GET /boards/{id}는 존재하는 게시글을 200 OK로 조회해야 합니다. 존재하지 않는 게시글 조회는 404 Not Found를 반환해야 합니다.
 */
