import { Component } from '@angular/core';
import { TrainerModesComponent } from './trainer-modes.component';
import { CustomModeCharacteristics } from './custom-mode-characteristics.component';
import { ChordTester } from './chord-tester.component'

@Component({
    selector: 'music-ear-trainer',
    directives: [TrainerModesComponent, CustomModeCharacteristics, ChordTester],
    templateUrl: 'app/app.component.html'
})
export class AppComponent {

}
