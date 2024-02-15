package b203.varium.relation.controller;

import b203.varium.relation.dto.response.RelationDtoResponse;
import b203.varium.relation.service.RelationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.IdentityHashMap;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/relation")
@RequiredArgsConstructor
public class RelationController {

    private final RelationService relationService;

    private static ResponseEntity<String> getStringResponseEntity(BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError error : result.getAllErrors()) {
                if (error instanceof FieldError fieldError) {
                    errorMessage.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append("; ");
                } else {
                    errorMessage.append(error.getDefaultMessage()).append("; ");
                }
            }
            log.info("Validation errors: {}", errorMessage);
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @RequestMapping("/save")
    public ResponseEntity<Map<String, Object>> saveRelation(@Validated @RequestBody RelationDtoResponse relationDtoResponse, BindingResult result) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<String> errorMessage = getStringResponseEntity(result);
        if (errorMessage != null) {
            Map<String, Object> msg = new IdentityHashMap<>();
            msg.put("msg", errorMessage);
            ResponseEntity.ok(msg);
        }
        return ResponseEntity.ok(relationService.saveRelation(relationDtoResponse, auth.getName()));
    }
}
