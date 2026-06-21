package com.mmcafe.board.repository;

import com.mmcafe.board.dto.BoardRequest;
import com.mmcafe.board.dto.BoardResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BoardResponse save(BoardRequest request) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        LocalDateTime createdAt = LocalDateTime.now();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into boards (title, content, created_at) values (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, request.title());
            ps.setString(2, request.content());
            ps.setTimestamp(3, Timestamp.valueOf(createdAt));
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();

        return new BoardResponse(id, request.title(), request.content(), createdAt);
    }

    public Optional<BoardResponse> findById(long id) {
        List<BoardResponse> results = jdbcTemplate.query(
                "select id, title, content, created_at from boards where id = ?",
                (rs, rowNum) -> new BoardResponse(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ),
                id
        );

        return results.stream().findFirst();
    }
}
