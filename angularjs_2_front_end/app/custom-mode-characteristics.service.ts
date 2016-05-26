import { Injectable } from '@angular/core';
import { CHORD_QUALITIES, CHORD_INVERSIONS } from './mock-custom-mode-characteristics';

@Injectable()
// Gets the chord characteristics in custom mode that the user can select
// to be tested on.
export class CustomModeCharacteristicsService {
  getChordQualities() {
    return Promise.resolve(CHORD_QUALITIES);
  }

  getChordInversions() {
    return Promise.resolve(CHORD_INVERSIONS);
  }
}
