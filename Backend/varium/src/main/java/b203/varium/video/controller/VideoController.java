package b203.varium.video.controller;

import b203.varium.video.dto.ClipReqDTO;
import b203.varium.video.dto.ClipVideoDTO;
import b203.varium.video.dto.ReplayReqDTO;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

    private final AmazonS3 amazonS3;
    private final ReplayVideoService replayVideoService;

    @GetMapping("/list/replay/{streamerId}")
    public ResponseEntity<Map<String, Object>> viewReplayList(@PathVariable String streamerId) {
        List<ReplayVideoDTO> list = replayVideoService.getReplayVideos(streamerId);
        Map<String, Object> resp = new HashMap<>();

        if (list.isEmpty()) {
            resp.put("status", "fail");
            Map<String, String> msg = new HashMap<>();
            msg.put("msg", "no data");
            resp.put("data", msg);
            return ResponseEntity.status(400).body(resp);
        }

        resp.put("status", "success");
        Map<String, Object> data = new HashMap<>();
        data.put("videoList", replayVideoService.getReplayVideos(streamerId));
        resp.put("data", data);
        return ResponseEntity.ok(resp);
    }

//    @GetMapping("/list/clip/{streamerId}")
//    public ResponseEntity<List<ClipVideoDTO>> viewClipList(@PathVariable String streamerId) {
//        return ResponseEntity.ok(clipVideoService.getClipVideos(streamerId));
//    }

    @PostMapping("/upload/clip")
    public void uploadClip(@RequestBody ClipReqDTO clipReqDTO, @RequestParam(value = "videoclip", required = false) MultipartFile file
            , @RequestParam(value = "thumbnail", required = false) MultipartFile imgFile) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();


    }

    @PostMapping("/upload/replay")
    public ResponseEntity<Map<String, Object>> uploadReplay(@RequestBody ReplayReqDTO infoDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();


        return ResponseEntity.ok(replayVideoService.saveReplayInfo(infoDTO.getFilePath(), username));
    }

    @GetMapping("/replay/{videoNo}")
    public ResponseEntity<Map<String, Object>> getReplayDetail(@PathVariable int videoNo) {

        return ResponseEntity.ok(replayVideoService.detailVideo(videoNo));
    }
}
