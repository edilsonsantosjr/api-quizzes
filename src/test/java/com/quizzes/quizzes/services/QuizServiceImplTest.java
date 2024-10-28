package com.quizzes.quizzes.services;

import com.quizzes.quizzes.Repository.QuizRepository;
import com.quizzes.quizzes.dtos.QuizRequestDTO;
import com.quizzes.quizzes.exceptions.QuizException;
import com.quizzes.quizzes.models.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class QuizServiceImplTest {

    @Mock
    private QuizRepository quizRepository;

    @InjectMocks
    private QuizServiceImpl quizService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveQuiz(){
        QuizRequestDTO quizRequestDTO = new QuizRequestDTO("Teste Quiz", "Description of teste quiz",
                "1", 300, 5, new ArrayList<>());

        Quiz quiz = new Quiz();
        quiz.setId("1");
        quiz.setDescription(quizRequestDTO.description());
        quiz.setAuthorId(quizRequestDTO.authorId());
        quiz.setDurationSec(quizRequestDTO.durationSec());
        quiz.setScore(quizRequestDTO.score());
        quiz.setQuestions(quizRequestDTO.questions());

        when(quizRepository.save(any(Quiz.class))).thenReturn(quiz);

        Quiz savedQuiz = quizService.saveQuiz(quizRequestDTO);
        savedQuiz.setId("someId");

        assertNotNull(savedQuiz);
        assertNotNull(savedQuiz.getId());
        assertNotNull(savedQuiz.getCreatedAt());

    }

    @Test
    void getOneQuizCase1(){

        String quizId = "123";
        Quiz expectedQuiz = new Quiz();
        expectedQuiz.setId(quizId);

        when(quizRepository.findById(quizId)).thenReturn(Optional.of(expectedQuiz));

        Quiz actualQuiz = quizService.getOneQuiz(quizId);
        assertEquals(expectedQuiz, actualQuiz);
    }

    @Test
    void getOneQuizCase2(){
        String nonExistenQuizId = "111";

        when(quizRepository.findById(nonExistenQuizId)).thenReturn(Optional.empty());

        assertThrows(QuizException.class, () -> quizService.getOneQuiz(nonExistenQuizId));
    }

    @Test
    void getAllQuizCase1(){
        String authorId = "user111";
        Quiz quiz1 = new Quiz();
        quiz1.setAuthorId(authorId);
        Quiz quiz2 = new Quiz();
        quiz2.setAuthorId(authorId);
        List<Quiz> quizList = List.of(quiz1, quiz2);

        when(quizRepository.findByAuthorId(authorId)).thenReturn(quizList);

        List<Quiz> result = quizService.getAllQuiz(authorId);
        assertEquals(2, result.size());
        assertEquals(quizList, result);

    }

    @Test
    void getAllQuizCase2(){
        String authorId = "unknownUser";

        when(quizRepository.findByAuthorId(authorId)).thenReturn(new ArrayList<>());

        assertThrows(QuizException.class, () -> quizService.getAllQuiz(authorId));
    }

    @Test
    void updateQuizCase1(){
        String quizId = "user111";
        int newScore = 80;
        Quiz quiz = new Quiz();
        quiz.setId(quizId);
        quiz.setScore(70);

        when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));
        when(quizRepository.save(quiz)).thenReturn(quiz);

        Quiz updateQuiz = quizService.updateQuiz(quizId, newScore);

        assertEquals(newScore, updateQuiz.getScore());
        verify(quizRepository).save(quiz);
    }

    @Test
    void updateQuizCase2(){
        String quizId = "noexistentQuiz";
        int newScore = 80;

        when(quizRepository.findById(quizId)).thenReturn(Optional.empty());

        assertThrows(QuizException.class, () -> quizService.updateQuiz(quizId, newScore));
    }

    @Test
    void deleteQuizCase1() {
        String quizId = "validQuizId";
        Quiz quiz = new Quiz();
        quiz.setId(quizId);

        when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));

        quizService.deleteQuiz(quizId);

        verify(quizRepository, times(1)).delete(quiz);
    }

    @Test
    void deleteQuizCase2() {
        String quizId = "nonexistentQuizId";

        when(quizRepository.findById(quizId)).thenReturn(Optional.empty());

        assertThrows(QuizException.class, () -> quizService.deleteQuiz(quizId));
    }

}