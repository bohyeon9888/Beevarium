package b203.varium.video.controller;

import b203.varium.video.dto.ClipReqDTO;
import b203.varium.video.dto.ClipVideoDTO;
import b203.varium.video.dto.ReplayInfoDTO;
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

    @GetMapping("/list/replay/{streamerId}")
    public ResponseEntity<List<ReplayVideoDTO>> viewReplayList(@PathVariable String streamerId) {
        return ResponseEntity.ok(replayVideoService.getReplayVideos(streamerId));
    }

    @GetMapping("/list/clip/{streamerId}")
    public ResponseEntity<List<ClipVideoDTO>> viewClipList(@PathVariable String streamerId) {
        return ResponseEntity.ok(clipVideoService.getClipVideos(streamerId));
    }

    @PostMapping("/upload/clip")
    public void uploadClip(@RequestBody ClipReqDTO clipReqDTO, @RequestParam(value = "videoclip", required = false) MultipartFile file
            , @RequestParam(value = "thumbnail", required = false) MultipartFile imgFile) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();


    }

    @PostMapping("/upload/replay")
    public void uploadReplay(@RequestBody ReplayInfoDTO infoDTO) {

    }

}
