import { Injectable } from '@angular/core';
import { Http, Headers, URLSearchParams } from '@angular/http';
import { Chord } from './chord';
import { MIDINotes } from './midi-notes';

@Injectable()
// Service for making /info web service calls.
export class InfoService {
  constructor(private http: Http) { }

  getMidiNotesForChord(chord: Chord): Promise<MIDINotes> {
    let queryParameters = new URLSearchParams();
    queryParameters.append('chordRoot', chord.chordRoot);
    queryParameters.append('chordQuality', chord.chordQuality);
    queryParameters.append('chordInversion', chord.chordInversion);
    return this.http.get("rest/chord-ear-trainer/info/midi-notes",
      { headers: new Headers({'Content-Type': 'application/json'}),
        search: queryParameters })
      .toPromise().then(response => response.json())
      .catch(function(error) {
        return Promise.reject(error.message || error)
      });
  }

}
