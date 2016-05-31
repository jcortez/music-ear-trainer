// Represents a chord in the chord trainer.
export class Chord {
  chordRoot: string;
  chordQuality: string;
  chordInversion: string;

  constructor(chordRoot: string = "", chordQuality: string = "",
    chordInversion: string = "") {
      this.chordRoot = chordRoot;
      this.chordQuality = chordQuality;
      this.chordInversion = chordInversion;
    }
}
