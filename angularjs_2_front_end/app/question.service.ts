import { Injectable } from '@angular/core';
import { Answer } from './answer';
import { AnswerResponse } from './answer-response';
import { Chord } from './chord';
import { Question } from './question-object';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
// Service for getting questions and checking answers from the server.
export class QuestionService {
  constructor(private http: Http) {}

  getQuestionCustomMode(chordQualities: string[], chordInversions: string[]) : Promise<Question> {
    return this.http.get("rest/chord-ear-trainer/modes/custom/question",
        { headers: new Headers({'Content-Type': 'application/json'})})
      .toPromise().then(response => response.json())
      .catch(function(error) {
        return Promise.reject(error.message || error)
      });
  }

  checkAnswerCustomMode(answer: Answer) : Promise<AnswerResponse> {
    return this.http.post("rest/chord-ear-trainer/modes/custom/answer",
      JSON.stringify(answer), { headers: new Headers({'Content-Type': 'application/json'})})
      .toPromise().then(response => response.json())
      .catch(function(error) {
        return Promise.reject(error.message || error)
      });
  }
}
