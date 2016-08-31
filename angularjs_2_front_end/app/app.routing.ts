import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TrainerModesComponent } from './trainer-modes.component';
import { CustomModeCharacteristics } from './custom-mode-characteristics.component';
import { ChordTester } from './chord-tester.component'

const appRoutes: Routes = [
  {
    path: '',
    redirectTo: '/trainerModes',
    pathMatch: 'full'
  },
  {
    path: 'trainerModes',
    component: TrainerModesComponent,
  },
  {
    path: 'customMode',
    component: CustomModeCharacteristics
  },
  {
    path: 'chordTester',
    component: ChordTester
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
