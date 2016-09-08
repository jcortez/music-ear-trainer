import { Chord } from './chord';

// The response from the server after the answer to a question has been submitted.
export class AnswerResponse {
  // Will be set to true if the user's answer is correct, false if the user is
  // incorrect.
  userAnswerCorrect: boolean;
  // The correct answer for the question that was presented to the user.
  correctAnswer: Chord;
  // The MIDI notes of the correct answer;
  midiNotes: number[];

  constructor(userAnswerIsCorrect: boolean, correctAnswer: Chord, midiNotes: number[]) {
    this.userAnswerCorrect = userAnswerIsCorrect;
    this.correctAnswer = correctAnswer;
    this.midiNotes = midiNotes;
  }
}
