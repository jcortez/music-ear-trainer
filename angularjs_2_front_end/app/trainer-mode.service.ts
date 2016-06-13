import { Injectable } from '@angular/core';
import { TrainerMode } from './trainer-mode-object';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
// Obtains the trainer modes and related information that the music ear trainer
// supports.
export class TrainerModeService {
  constructor(private http: Http) {}

  getTrainerModes() : Promise<TrainerMode[]> {
    return this.http.get("rest/chord-ear-trainer/modes",
        { headers: new Headers({'Content-Type': 'application/json'})})
      .toPromise().then(response => response.json())
      .catch(function(error) {
        return Promise.reject(error.message || error)
      });
  }
}
