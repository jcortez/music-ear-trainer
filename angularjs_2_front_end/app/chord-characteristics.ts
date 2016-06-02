// An object for storing chord characteristics.
export class ChordCharacteristics {
  chordQualities: string[];
  chordInversions: string[];

  constructor(qualities: string[] = [], inversions: string[] = []) {
    this.chordQualities = qualities;
    this.chordInversions = inversions;
  }
}
