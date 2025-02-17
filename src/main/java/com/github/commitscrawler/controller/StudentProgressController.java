package com.github.commitscrawler.controller;

import com.github.commitscrawler.crawler.GitCommitCrawler;
import com.github.commitscrawler.domain.commit.CommitPayload;
import com.github.commitscrawler.lib.enumeration.Subject;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/progress")
public class StudentProgressController {
    private final GitCommitCrawler gitCommitCrawler;

    public StudentProgressController(GitCommitCrawler gitCommitCrawler) {
        this.gitCommitCrawler = gitCommitCrawler;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CommitPayload>> getList(@ApiParam(value = "명렬표 컬럼명 입력") @RequestParam("column") String column) {
        return ResponseEntity.ok().body(gitCommitCrawler.getLatestCommitsAllMember(column));
    }

    @GetMapping("/columns")
    public Map<Integer, String> getColumns() {
        return gitCommitCrawler.getColumns();
    }
}
