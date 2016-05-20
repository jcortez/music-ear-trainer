import { Injectable } from '@angular/core'
import { MODES } from './mock-trainer-modes';

@Injectable()
export class TrainerModeService {
  getTrainerModes() {
    return Promise.resolve(MODES);
  }
}
