import { Chord } from './chord';

// Corresponds to a user's answer.
export class Answer {
  // The file name of the MIDI file that was played to the user for the
  // question that this answer corresponds to.
  midiFileName: string;
  // The chord with the characteristics that the user has selected in the GUI.
  chosenChord: Chord;

  constructor() {
    this.midiFileName = "";
    this.chosenChord = new Chord();
  }
}
