import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HttpModule } from '@angular/http'
import { TrainerModesComponent } from './trainer-modes.component';
import { CustomModeCharacteristics } from './custom-mode-characteristics.component';
import { ChordTester } from './chord-tester.component';
import { routing } from './app.routing';

@NgModule({
    declarations: [
      AppComponent,
      TrainerModesComponent,
      CustomModeCharacteristics,
      ChordTester
    ],
    imports:      [ BrowserModule, HttpModule, routing ],
    bootstrap:    [ AppComponent ],
})
export class AppModule { }
