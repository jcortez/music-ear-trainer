import { Injectable } from '@angular/core';
import { Answer } from './answer';
import { AnswerResponse } from './answer-response';
import { Chord } from './chord';

@Injectable()
// Service for getting questions and checking answers from the server.
export class QuestionService {
  getQuestionCustomMode(chordQualities: string[], chordInversions: string[]) {
    return Promise.resolve("0.midi");
  }

  checkAnswerCustomMode(answer: Answer) {
    return Promise.resolve(new AnswerResponse(true, new Chord("C", "Major", "Root Position")));
  }
}
