import { Component } from '@angular/core';
import { TrainerModesComponent } from './trainer-modes.component';
import { CustomModeCharacteristics } from './custom-mode-characteristics.component';
import { ChordTester } from './chord-tester.component'
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from '@angular/router-deprecated';

@Component({
    selector: 'music-ear-trainer',
    directives: [ROUTER_DIRECTIVES],
    providers: [ROUTER_PROVIDERS],
    templateUrl: 'app/app.component.html',
    styleUrls: ['app/app.component.css']
})
@RouteConfig([
  {
    path: '/trainerModes',
    name: 'TrainerModes',
    component: TrainerModesComponent,
    useAsDefault: true
  },
  {
    path: '/customMode',
    name: 'CustomMode',
    component: CustomModeCharacteristics
  },
  {
    path: '/chordTester',
    name: 'ChordTester',
    component: ChordTester
  }
])
export class AppComponent {

}
