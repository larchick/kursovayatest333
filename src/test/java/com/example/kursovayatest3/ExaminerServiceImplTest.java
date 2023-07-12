package com.example.kursovayatest3;

import com.example.kursovaya3.exeption.NoteEnoughQuestionsInStorage;
import com.example.kursovaya3.model.Question;
import com.example.kursovaya3.service.QuestionService;
import com.example.kursovaya3.service.impl.ExaminerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    public void shouldThrowExceptionWhenAmountMoreThenAvailableQuestions(){
        //given

        int amount = 5;
        when(questionService.getAll()).thenReturn(Collections.emptyList());
        //when

        //then

        Assertions.assertThrows(
                NoteEnoughQuestionsInStorage.class,
                () -> examinerService.getQuestions(amount)
        );
    }

    @Test
    public void shouldSuccessfullyGenerateRandomQuestion(){
        //given
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Сколько метров в километре?", "Тысяча"));
        questions.add(new Question("Сколько будет дважды два?", "Четыре"));
        questions.add(new Question("А?", "Б"));
        questions.add(new Question("Кто написал Войну и мир?", "Лев Толстой"));
        int amount = 3;
        when(questionService.getAll()).thenReturn(questions);
        when(questionService.getRandomQuestion()).thenReturn(questions.get(0), questions.get(1),questions.get(1),questions.get(2));
        //when
        Collection<Question> actualQuestion = examinerService.getQuestions(amount);
        //then
        Assertions.assertEquals(amount, actualQuestion.size());
        Assertions.assertTrue(actualQuestion.contains(questions.get(0)));
        Assertions.assertTrue(actualQuestion.contains(questions.get(1)));
        Assertions.assertTrue(actualQuestion.contains(questions.get(2)));

        verify(questionService, times(4)).getRandomQuestion();
    }
}
