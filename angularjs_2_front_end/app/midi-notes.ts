// Stores raw MIDI notes.
export class MIDINotes {
  public midiNotes: number[];

  constructor(notes: number[] = [ ]) {
    this.midiNotes = notes;
  }
}
