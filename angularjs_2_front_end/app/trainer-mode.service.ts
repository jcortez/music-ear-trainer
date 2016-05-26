import { Injectable } from '@angular/core'
import { MODES } from './mock-trainer-modes';

@Injectable()
// Obtains the trainer modes and related information that the music ear trainer
// supports.
export class TrainerModeService {
  getTrainerModes() {
    return Promise.resolve(MODES);
  }
}
