package b203.varium.hashtag.service;

import b203.varium.broadcasting.entity.Broadcasting;
import b203.varium.hashtag.entity.HashTag;
import b203.varium.hashtag.entity.TagEntity;
import b203.varium.hashtag.repository.HashTagRepository;
import b203.varium.hashtag.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final HashTagRepository hashTagRepository;

    public TagService(TagRepository tagRepository, HashTagRepository hashTagRepository) {
        this.tagRepository = tagRepository;
        this.hashTagRepository = hashTagRepository;
    }

    public void insertTag(String text) {

        TagEntity tag = new TagEntity(text);
        tagRepository.save(tag);
    }

    public List<HashTag> insertHashTag(Broadcasting broadcasting, List<String> tagList) {
        List<HashTag> result = new ArrayList<>();

        for (String tag : tagList) {
            TagEntity newTag = tagRepository.findByTagText(tag);

            if (newTag == null) {
                insertTag(tag);
            }

            HashTag hashtag = new HashTag();
            hashtag.setBroadcasting(broadcasting);
            hashtag.setTag(newTag);
            hashTagRepository.save(hashtag);
            result.add(hashtag);
        }

        return result;
    }
}
