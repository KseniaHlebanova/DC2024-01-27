package org.example.publisher.api.Controllers;

import org.example.publisher.api.exception.DuplicateEntityException;
import org.example.publisher.api.exception.EntityNotFoundException;
import org.example.publisher.impl.note.dto.NoteRequestTo;
import org.example.publisher.impl.note.dto.NoteResponseTo;
import org.example.publisher.impl.tag.*;
import org.example.publisher.impl.tag.Service.TagService;
import org.example.publisher.impl.tag.dto.TagRequestTo;
import org.example.publisher.impl.tag.dto.TagResponseTo;
import org.example.publisher.impl.tag.mapper.Impl.TagMapperImpl;
import org.example.publisher.impl.tag.mapper.TagMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1.0/labels")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TagResponseTo> getTags() {
        return tagService.getTags();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagResponseTo getTagById(@PathVariable BigInteger id) throws EntityNotFoundException {
        return tagService.getTagById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagResponseTo saveTag(@Valid @RequestBody TagRequestTo tagTo) throws DuplicateEntityException, EntityNotFoundException {
        return tagService.saveTag(tagTo);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public TagResponseTo updateNote(@Valid @RequestBody TagRequestTo tagTo) throws DuplicateEntityException, EntityNotFoundException {
        return tagService.updateTag(tagTo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable BigInteger id) throws EntityNotFoundException {
        tagService.deleteTagById(id);
    }

}
