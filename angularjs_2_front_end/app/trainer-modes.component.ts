import { Component } from '@angular/core';
import { TrainerModeService } from './trainer-mode.service';
import { Router } from '@angular/router';
import { TrainerMode } from './trainer-mode-object';

@Component({
    selector: 'trainer-modes',
    templateUrl: 'app/trainer-modes.component.html',
    styleUrls: ['app/trainer-modes.component.css'],
    providers: [TrainerModeService]
})
// Represents the trainer modes for the music ear trainer.
export class TrainerModesComponent {
  public trainerModes: TrainerMode[];

  constructor(private trainerModeService: TrainerModeService,
    private router: Router){}

  ngOnInit() {
    this.trainerModeService.getTrainerModes()
    .then(modes => this.trainerModes = modes)
    .catch(error => window.alert(error._body));
  }

  // Goes to the trainer mode that was selected.
  onTrainerModeSelected(trainerMode: TrainerMode) {
    // Right now, only Custom Mode is implemented.
    if (trainerMode.name === "Challenge Mode") {
      window.alert("Challenge Mode is not implemented yet. Please select Custom Mode.");
      return;
    }

    this.router.navigate(['/customMode']);
  }
}
