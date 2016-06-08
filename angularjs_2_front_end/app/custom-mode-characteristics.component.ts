import { Component } from '@angular/core';
import { CustomModeCharacteristicsService } from './custom-mode-characteristics.service';
import { ChordCharacteristics } from './chord-characteristics';
import { Router } from '@angular/router-deprecated';

@Component({
    selector: 'custom-mode-characteristics',
    templateUrl: 'app/custom-mode-characteristics.html',
    styleUrls: ['app/custom-mode-characteristics.css'],
    providers: [CustomModeCharacteristicsService]
})
// The view for selecting chord characteristics that will be tested in custom
// mode.
export class CustomModeCharacteristics {
  private selectedChordQualities: Map<string, boolean> = new Map<string, boolean>();
  private selectedChordInversions: Map<string, boolean> = new Map<string, boolean>();
  private allChordCharacteristics: ChordCharacteristics = new ChordCharacteristics();

  constructor(private customModeCharacteristicsService: CustomModeCharacteristicsService,
    private router : Router) {}

  ngOnInit() {
    this.customModeCharacteristicsService.getCustomModeChordCharacteristics()
    .then(characteristics => this.initializeChordCharacteristics(characteristics))
    .catch(error => window.alert(error));
  }

  // Initializes the chord characteristics data structures in this class after
  // they are received from the server.
  private initializeChordCharacteristics(chordCharacteristics : ChordCharacteristics) {
    this.allChordCharacteristics = chordCharacteristics;
    // Adding all chord characteristics to the specific maps of selected
    // characteristics.
    this.selectedChordQualities = new Map<string, boolean>();
    this.selectedChordInversions = new Map<string, boolean>();
    for (let q of this.allChordCharacteristics.chordQualities) {
      this.selectedChordQualities.set(q, false);
    }
    for (let i of this.allChordCharacteristics.chordInversions) {
      this.selectedChordInversions.set(i, false);
    }
  }

  onChordQualitySelected(quality: string) {
    //Setting or unsetting a chord quality in the selected characteristics.
    let isSet = this.selectedChordQualities.get(quality);
    if (isSet === undefined) {
      // If it can't find the value, then there is a bug.
      window.alert("Cannot select chord quality");
      return;
    }
    else {
      this.selectedChordQualities.set(quality, !isSet);
    }
  }

  onChordInversionSelected(inversion: string) {
    //Setting or unsetting a chord inversion in the selected characteristics.
    let isSet = this.selectedChordInversions.get(inversion);
    if (isSet === undefined) {
      // If it can't find the value, then there is a bug.
      window.alert("Cannot select chord inversion");
      return;
    }
    else {
      this.selectedChordInversions.set(inversion, !isSet);
    }
  }

  onStartTrainerButtonSelected() {
    // Building an array of selected chord qualities. If none were selected,
    // then all are available to be tested on and they are added to the array.
    let chordQualities = [];
    this.selectedChordQualities.forEach(function(value, key, map){
      if (value === true) {
        chordQualities.push(key);
      }
    });
    if (chordQualities.length === 0) {
      chordQualities = this.allChordCharacteristics.chordQualities;
    }

    // Building an array of selected chord inversions. If none were selected,
    // then all are available to be tested on and they are added to the array.
    let chordInversions = [];
    this.selectedChordInversions.forEach(function(value, key, map){
      if (value === true) {
        chordInversions.push(key);
      }
    });
    if (chordInversions.length === 0) {
      chordInversions = this.allChordCharacteristics.chordInversions;
    }

    let chordCharacteristics = new ChordCharacteristics(chordQualities, chordInversions);
    this.router.navigate(['ChordTester', chordCharacteristics]);
  }

}
