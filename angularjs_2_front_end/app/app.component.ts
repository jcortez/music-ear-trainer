import { Component } from '@angular/core';
import { TrainerModesComponent } from './trainer-modes.component';
import { CustomModeCharacteristics } from './custom-mode-characteristics.component';

@Component({
    selector: 'music-ear-trainer',
    directives: [TrainerModesComponent, CustomModeCharacteristics],
    templateUrl: 'app/app.component.html'
})
export class AppComponent {

}
