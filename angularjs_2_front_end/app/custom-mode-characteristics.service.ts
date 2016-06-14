import { Injectable } from '@angular/core';
import { ChordCharacteristics } from './chord-characteristics';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
// Gets the chord characteristics in custom mode that the user can select
// to be tested on.
export class CustomModeCharacteristicsService {
  constructor(private http: Http) {}

  getCustomModeChordCharacteristics() : Promise<ChordCharacteristics> {
    return this.http.get("rest/chord-ear-trainer/modes/custom",
        { headers: new Headers({'Content-Type': 'application/json'})})
      .toPromise().then(response => response.json())
      .catch(function(error) {
        return Promise.reject(error.message || error)
      });
  }
}
