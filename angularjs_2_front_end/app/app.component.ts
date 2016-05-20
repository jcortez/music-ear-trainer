import { Component } from '@angular/core';
import { TrainerModeComponent } from './trainer-mode.component';
import { TrainerModeService } from './trainer-mode.service';

@Component({
    selector: 'music-ear-trainer',
    directives: [TrainerModeComponent],
    templateUrl: 'app/app.component.html',
    providers: [TrainerModeService]
})
export class AppComponent {
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
