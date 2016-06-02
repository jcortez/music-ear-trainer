import { Injectable } from '@angular/core';
import { CHORD_QUALITIES, CHORD_INVERSIONS } from './mock-custom-mode-characteristics';
import { ChordCharacteristics } from './chord-characteristics';

@Injectable()
// Gets the chord characteristics in custom mode that the user can select
// to be tested on.
export class CustomModeCharacteristicsService {
    getCustomModeChordCharacteristics() {
      let chordCharacteristics = new ChordCharacteristics(CHORD_QUALITIES,
        CHORD_INVERSIONS);
      return Promise.resolve(chordCharacteristics);
    }
}
