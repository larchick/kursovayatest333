package com.example.kursovayatest3;

import com.example.kursovaya3.model.Question;
import com.example.kursovaya3.service.impl.JavaQuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JavaQuestionServiceTest {

    private final JavaQuestionService javaQuestionService = new JavaQuestionService();


    @Test
    void addByStringTest() {
        Question actual = javaQuestionService.add("?", "!!!");
        Question expected = new Question("?", "!!!");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addByModelTest() {
        Question tmp = new Question("???", "!");
        Question actual = javaQuestionService.add(tmp);
        Question expected = new Question("???", "!");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldRemoveExistedQuestionAndReturn(){
        //given
        Question question = new Question("text", "answer");
        javaQuestionService.add(question);
        //when
        Question removedQuestion = javaQuestionService.remove(question);

        //then
        Assertions.assertEquals(question, removedQuestion);
    }

    @Test
    public void shouldRemoveExistedQuestionAndReturnNull(){
        //given
        Question question = new Question("text", "answer");

        //when
        Question removedQuestion = javaQuestionService.remove(question);

        //then
        Assertions.assertNull(removedQuestion);
    }

    @Test
    public void should(){

    }

}
