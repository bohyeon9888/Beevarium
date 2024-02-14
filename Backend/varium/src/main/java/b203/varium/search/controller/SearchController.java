package b203.varium.search.controller;

import b203.varium.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    // 스트리머 검색
    @GetMapping("/{keyword}")
    public ResponseEntity<Map<String, Object>> search(@PathVariable String keyword) {
        log.info(keyword);
        return ResponseEntity.ok(searchService.searchStreamer(keyword));
    }

}
