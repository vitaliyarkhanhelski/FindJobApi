package jobflowproject.api;

import jobflowproject.model.Tag;
import jobflowproject.repository.TagRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/tags")
@RestController
public class TagController {

    private TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping
    public Iterable<Tag> getList(){
    return tagRepository.findAll();
}

}
