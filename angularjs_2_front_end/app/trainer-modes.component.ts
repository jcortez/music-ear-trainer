import { Component } from '@angular/core';
import { TrainerModeService } from './trainer-mode.service';

@Component({
    selector: 'trainer-modes',
    templateUrl: 'app/trainer-modes.component.html',
    styleUrls: ['app/trainer-modes.component.css'],
    providers: [TrainerModeService]
})
// Represents the trainer modes for the music ear trainer.
export class TrainerModesComponent {
  public trainerModes;

  constructor(private trainerModeService : TrainerModeService){}

  ngOnInit() {
    this.trainerModeService.getTrainerModes()
    .then(modes => this.trainerModes = modes)
    .catch(error => window.alert(error));
  }

  onTrainerModeSelected(trainerMode) {
    console.log(trainerMode.name + " selected");
  }
}
