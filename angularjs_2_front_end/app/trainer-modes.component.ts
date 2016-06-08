import { Component } from '@angular/core';
import { TrainerModeService } from './trainer-mode.service';
import { ROUTER_DIRECTIVES, ROUTER_PROVIDERS, RouteConfig, Router } from '@angular/router-deprecated';

@Component({
    selector: 'trainer-modes',
    templateUrl: 'app/trainer-modes.component.html',
    styleUrls: ['app/trainer-modes.component.css'],
    providers: [TrainerModeService]
})
// Represents the trainer modes for the music ear trainer.
export class TrainerModesComponent {
  public trainerModes;

  constructor(private trainerModeService: TrainerModeService,
    private router: Router){}

  ngOnInit() {
    this.trainerModeService.getTrainerModes()
    .then(modes => this.trainerModes = modes)
    .catch(error => window.alert(error));
  }

  // Goes to the trainer mode that was selected.
  onTrainerModeSelected(trainerMode) {
    // Right now, only Custom Mode is implemented.
    if (trainerMode.name === "Challenge Mode") {
      window.alert("Challenge Mode is not implemented yet");
      return;
    }

    this.router.navigate(['CustomMode']);
  }
}
