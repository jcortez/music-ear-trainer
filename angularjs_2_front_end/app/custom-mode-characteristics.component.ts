import { Component } from '@angular/core';
import { CustomModeCharacteristicsService } from './custom-mode-characteristics.service';

@Component({
    selector: 'custom-mode-characteristics',
    templateUrl: 'app/custom-mode-characteristics.html',
    styleUrls: ['app/custom-mode-characteristics.css'],
    providers: [CustomModeCharacteristicsService]
})
// The view for selecting chord characteristics that will be tested in custom
// mode.
export class CustomModeCharacteristics {
  public chordQualities;
  public chordInversions;

  constructor(private customModeCharacteristicsService : CustomModeCharacteristicsService) {}

  ngOnInit() {
    this.customModeCharacteristicsService.getChordQualities()
    .then(qualities => this.chordQualities = qualities)
    .catch(error => window.alert(error));
    this.customModeCharacteristicsService.getChordInversions()
    .then(inversions => this.chordInversions = inversions)
    .catch(error => window.alert(error));
  }

  onChordQualitySelected(quality) {
    console.log(quality + " quality selected");
  }

  onChordInversionSelected(inversion) {
    console.log(inversion + " inversion selected");
  }
}
