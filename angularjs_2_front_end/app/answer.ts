import { Chord } from './chord';

// Corresponds to a user's answer.
export class Answer {
  // The file name of the MIDI file that was played to the user for the
  // question that this answer corresponds to.
  questionMidiFileName: string;
  // The chord with the characteristics that the user has selected in the GUI.
  answer: Chord;

  constructor() {
    this.questionMidiFileName = "";
    this.answer = new Chord();
  }
}
