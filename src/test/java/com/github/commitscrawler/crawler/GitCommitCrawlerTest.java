package com.github.commitscrawler.crawler;

import com.github.commitscrawler.domain.commit.CommitPayload;
import com.github.commitscrawler.domain.dto.CommitDetailRequest;
import com.github.commitscrawler.lib.MemberList;
import com.github.commitscrawler.lib.enumeration.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GitCommitCrawlerTest {
    @Autowired
    private ApplicationContext context;
    private CommitDetailCrawler commitDetailCrawler;
    private GitCommitCrawler gitCommitCrawler;

    @BeforeEach()
    void setUp() {
        commitDetailCrawler = (CommitDetailCrawler) context.getBean("gitApiCrawler");
        gitCommitCrawler = new GitCommitCrawler(commitDetailCrawler);
    }

    @Test
    @DisplayName("모든 학생 최신 커밋 조회 : 알고리즘 리포지토리")
    void getLatestCommitsAllMemberAlgorithm() {
        List<CommitPayload> payloads = gitCommitCrawler.getLatestCommitsAllMember(Subject.ALGORITHM);
        System.out.println(payloads);
        assertTrue(payloads.size() <= MemberList.getMembers().size());
    }

    @Test
    @DisplayName("최신 커밋 가져오기")
    void getLatestCommit() {
        // given
        CommitDetailRequest cdr = new CommitDetailRequest("menuhwang", "LikeLion");
        CommitPayload payload = gitCommitCrawler.getLatestCommit(cdr);
        System.out.println(payload);
        assertNull(payload.getMemberName());
        assertNotNull(payload.getMessage());
        assertNotNull(payload.getPush_at());
    }
}