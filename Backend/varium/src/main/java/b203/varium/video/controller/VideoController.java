package b203.varium.video.controller;

import b203.varium.video.dto.ClipVideoDTO;
import b203.varium.video.dto.ReplayVideoDTO;
import b203.varium.video.service.ClipVideoService;
import b203.varium.video.service.ReplayVideoService;
import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

    private final AmazonS3 amazonS3;
    private final ReplayVideoService replayVideoService;
    private final ClipVideoService clipVideoService;

    @GetMapping("/list/replay/{userid}")
    public ResponseEntity<List<ReplayVideoDTO>> veiwReplayList(@PathVariable String userid) {
        return ResponseEntity.ok(replayVideoService.getReplayVideos(userid));
    }

    @GetMapping("/list/clip/{userid}")
    public ResponseEntity<List<ClipVideoDTO>> veiwClipList(@PathVariable String userid) {
        return ResponseEntity.ok(clipVideoService.getClipVideos(userid));
    }

    @PostMapping("/upload/clip")
    public void uploadClip(@RequestParam(value = "file", required = false) MultipartFile file) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();


    }


}
