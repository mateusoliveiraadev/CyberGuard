package com.cyberguard.cyberguard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyberguard.cyberguard.entity.RespostaQuiz;
import com.cyberguard.cyberguard.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin(origins = "http://localhost:5173")
public class QuizController {

    private final QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }

    @GetMapping
    public List<?> listarPerguntas(@RequestParam(required = false) String categoria) {
        return service.getPerguntas(categoria);
    }

    @PostMapping("/responder")
    public String responder(@RequestBody RespostaQuiz resposta, @RequestParam(required = false) String categoria) {
        int pontos = service.corrigirESalvar(resposta.getRespostas(), categoria, resposta.getEmailUsuario());
        return "Pontuação: " + pontos;
    }
}