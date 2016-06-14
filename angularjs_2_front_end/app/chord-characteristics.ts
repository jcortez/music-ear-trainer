// An object for storing chord characteristics.
export class ChordCharacteristics {
  trainerChordQualities: string[];
  trainerChordInversions: string[];

  constructor(qualities: string[] = [], inversions: string[] = []) {
    this.trainerChordQualities = qualities;
    this.trainerChordInversions = inversions;
  }
}
