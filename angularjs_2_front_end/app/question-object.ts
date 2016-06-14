// Represents a question that can be asked to the user.
export class Question {
  questionMidiFileName: string;

  constructor(midiFile : string = "") {
    this.questionMidiFileName = midiFile;
  }
}
