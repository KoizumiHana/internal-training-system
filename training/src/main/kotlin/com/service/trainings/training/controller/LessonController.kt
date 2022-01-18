package com.service.trainings.training.controller

import com.service.trainings.training.model.LessonCreateRequest
import com.service.trainings.training.service.LessonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/lesson"],
    consumes = [MediaType.APPLICATION_JSON_VALUE],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class LessonController(@Autowired private val lessonService: LessonService) {

    @ResponseStatus(CREATED)
    @GetMapping("/{lessonId}")
    fun findById(@PathVariable lessonId: Long) = lessonService.findById(lessonId)

    @PostMapping("/")
    fun createLesson(@RequestBody lessonCreateRequest: LessonCreateRequest) = lessonService.create(lessonCreateRequest)
}