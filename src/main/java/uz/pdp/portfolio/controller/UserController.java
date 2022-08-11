package uz.pdp.portfolio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import uz.pdp.portfolio.payload.MessageDTO;
import uz.pdp.portfolio.service.MailService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final MailService mailService;

    @PostMapping("/send")
    public HttpEntity<?> sendMessage(@ModelAttribute MessageDTO messageDTO){
        boolean success = mailService.sendMessage(messageDTO);
        return ResponseEntity.status(success? OK:CONFLICT).body(success);
    }

    @PostMapping("/send_as_me")
    public HttpEntity<?> sendMessageAsMe(@ModelAttribute MessageDTO messageDTO){
        boolean success = mailService.sendMessageAsMe(messageDTO);
        return ResponseEntity.status(success? OK:CONFLICT).body(success);
    }


    @GetMapping("/download")
    public void downloadResume(HttpServletResponse response) throws IOException {
            response.setHeader("Content-Disposition", "attachment; filename=\"resume.pdf\"");
            response.setContentType("application/pdf");
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/static/assets/pdp/resume.pdf");
            FileCopyUtils.copy(fileInputStream, response.getOutputStream());
    }

}
