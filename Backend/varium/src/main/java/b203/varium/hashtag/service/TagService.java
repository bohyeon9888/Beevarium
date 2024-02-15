package b203.varium.hashtag.service;

import b203.varium.broadcasting.repository.BroadcastingRepository;
import b203.varium.hashtag.entity.HashTag;
import b203.varium.hashtag.entity.TagEntity;
import b203.varium.hashtag.repository.HashTagRepository;
import b203.varium.hashtag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {

    private final TagRepository tagRepository;
    private final HashTagRepository hashTagRepository;
    private final BroadcastingRepository broadcastingRepository;

    @Transactional
    public int insertTag(String text) {
        Timestamp nowT = new Timestamp(System.currentTimeMillis());

        TagEntity tag = new TagEntity();
        tag.setTagText(text);
        tag.setCreatedDate(nowT);
        tag.setUpdatedDate(nowT);

        tagRepository.save(tag);

        return tag.getId();
    }

    @Transactional
    public List<HashTag> insertHashTag(int broadcastingId, List<String> tagList) {
        List<HashTag> result = new ArrayList<>();
        Timestamp nowT = new Timestamp(System.currentTimeMillis());

        for (String tag : tagList) {
            TagEntity newTag = tagRepository.findByTagText(tag);
            int tagId = -1;

            if (newTag == null) {
                tagId = insertTag(tag);
            } else {
                tagId = newTag.getId();
            }

            HashTag hashtag = new HashTag();
            hashtag.setBroadcasting(broadcastingRepository.findById(broadcastingId));
            hashtag.setTag(tagRepository.findById(tagId));
            hashtag.setCreatedDate(nowT);
            hashtag.setUpdatedDate(nowT);

            hashTagRepository.save(hashtag);
            result.add(hashtag);
        }

        return result;
    }
}
