import { Chord } from './chord';

// The response from the server after the answer to a question has been submitted.
export class AnswerResponse {
  userAnswerIsCorrect: boolean;
  // The correct answer for the question that was presented to the user.
  correctAnswer: Chord;

  constructor(userAnswerIsCorrect: boolean, correctAnswer: Chord) {
    this.userAnswerIsCorrect = userAnswerIsCorrect;
    this.correctAnswer = correctAnswer;
  }
}
