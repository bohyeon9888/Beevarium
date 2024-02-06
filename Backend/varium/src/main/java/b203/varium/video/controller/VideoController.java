package b203.varium.video.controller;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

    private final AmazonS3 amazonS3;

    @PostMapping("/upload/clip")
    public void uploadClip(@RequestParam(value = "file", required = false) MultipartFile file) {

    }


}
