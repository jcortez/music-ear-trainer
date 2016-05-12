import {Component} from '@angular/core';
import {TrainerModeComponent} from './trainer-mode.component';

@Component({
    selector: 'music-ear-trainer',
    directives: [TrainerModeComponent],
    templateUrl: 'app/app.component.html'
})
export class AppComponent {
  customMode = {
    name: "Custom Mode"
  };
  challengeMode = {
    name: "Challenge Mode"
  };

  onTrainerModeSelected(trainerMode) {
    console.log(trainerMode.name + " selected");
  }
}
