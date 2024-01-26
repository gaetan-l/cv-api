package com.gaetanl.cv.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gaetanl.cv.exception.EntityException;
import com.gaetanl.cv.exception.EntityNotFoundException;
import com.gaetanl.cv.model.Skill;
import com.gaetanl.cv.service.SkillService;
import com.gaetanl.cv.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
public class SkillController {
    @Autowired
    SkillService skillService;

    @PutMapping("/skill")
    public @NonNull ResponseEntity<String> create(@RequestBody final String jsonSkill) {
        final HttpHeaders responseHeaders = new HttpHeaders();

        String body = "{}";
        HttpStatus httpStatus = CREATED;
        Exception exception = null;

        try {
            final Skill newSkill, savedSkill;
            newSkill = ApiUtil.getObjectMapper().readValue(jsonSkill, Skill.class);
            savedSkill = skillService.create(newSkill);
            body = ApiUtil.getObjectAsPrettyJson(savedSkill, "{}", responseHeaders);
        }
        catch (final JsonProcessingException | EntityException e) {
            exception = e;
            httpStatus = BAD_REQUEST;
        }
        finally {
            if (exception != null) ApiUtil.putExceptionInResponseHeaders(responseHeaders, exception);
        }

        return new ResponseEntity<>(
                body,
                responseHeaders,
                httpStatus);
    }

    @GetMapping("/skill/{query}")
    public @NonNull ResponseEntity<String> read(@PathVariable("query") final String query) {
        final HttpHeaders responseHeaders = new HttpHeaders();

        Optional<Skill> foundSkill;
        try {
            foundSkill = skillService.read(Long.parseLong(query));
        } catch (final NumberFormatException e) {
            foundSkill = skillService.readByName(String.valueOf(query));
        }

        if (foundSkill.isEmpty()) {
            return new ResponseEntity<>(
                    "{}",
                    responseHeaders,
                    NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(
                    ApiUtil.getObjectAsPrettyJson(foundSkill.get(), "{}", responseHeaders),
                    responseHeaders,
                    OK);
        }
    }

    @GetMapping("/skill")
    public @NonNull ResponseEntity<String> readAll(@RequestParam(required = false) final Integer page) {
        final HttpHeaders responseHeaders = new HttpHeaders();
        final List<Skill> users = skillService.readAll();

        return new ResponseEntity<>(
                ApiUtil.getObjectAsPrettyJson(users, "[]", responseHeaders),
                responseHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/skill/{id}")
    public @NonNull ResponseEntity<String> update(@RequestBody final String jsonSkill) {
        final HttpHeaders responseHeaders = new HttpHeaders();

        String body = "{}";
        HttpStatus httpStatus = OK;
        Exception exception = null;

        try {
            final Skill savedSkill = skillService.update(ApiUtil.getObjectMapper().readValue(jsonSkill, Skill.class));
            body = ApiUtil.getObjectAsPrettyJson(savedSkill, "{}", responseHeaders);
        }
        catch (final EntityNotFoundException e) {
            exception = e;
            httpStatus = NOT_FOUND;
        }
        catch (final JsonProcessingException | EntityException e) {
            exception = e;
            httpStatus = BAD_REQUEST;
        }
        finally {
            if (exception != null) ApiUtil.putExceptionInResponseHeaders(responseHeaders, exception);
        }

        return new ResponseEntity<>(
                body,
                responseHeaders,
                httpStatus);
    }

    @DeleteMapping("/skill/{id}")
    public @NonNull ResponseEntity<String> delete(@PathVariable("id") final Long id) {
        final HttpHeaders responseHeaders = new HttpHeaders();
        final Optional<Skill> foundSkill = skillService.read(id);

        if (foundSkill.isEmpty()) {
            return new ResponseEntity<>(
                    "{}",
                    responseHeaders,
                    HttpStatus.NOT_FOUND);
        }
        else {
            skillService.delete(foundSkill.get());

            return new ResponseEntity<>(
                    "{}",
                    responseHeaders,
                    HttpStatus.OK);
        }
    }
}
